/**
 * 
 */
package com.platform.rp.services.sys.external.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.sys.core.dao.SysCodeInfoDAO;
import com.platform.rp.services.sys.core.dao.entity.SysAreaInfoEntity;
import com.platform.rp.services.sys.core.dao.entity.SysCodeInfoEntity;
import com.platform.rp.services.sys.external.service.ISysCodeService;
import com.platform.rp.services.sys.external.vo.DataVo;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;
import com.platform.rp.util.cache.CacheConstant;
import com.platform.rp.util.cache.ICache;


@SuppressWarnings("unchecked")
@Service
public class SysCodeServiceImpl implements ISysCodeService {
	private Log log = LogFactory.getLog(SysAreaInfoServiceImpl.class);

	@Autowired
	SysCodeInfoDAO<SysCodeInfoEntity> sysCodeInfoDAO;

	@Autowired
	ICache cache;

	@Override
	public SysCodeInfoVo findSysCodeByClassAndCodeValue(String codeClass, String codeValue) {		
		//return sysCodeInfoDAO.findSysCodeByClassAndCodeValue(codeClass, codeValue);
		return getSysCodeInfo(codeClass, codeValue);
	}
		
	
	public List<SysCodeInfoVo> getListByClassify(String name) {
		 List<SysCodeInfoVo> sysCodes = (List<SysCodeInfoVo>) cache.get(CacheConstant.SYS_CODE_LIST,name);
		if(sysCodes == null){
			sysCodes = sysCodeInfoDAO.getByClassvalue(name);
			Map<String,SysCodeInfoVo> sysCodeMap = new HashedMap();
			for (int i = 0; i < sysCodes.size(); i++) {
				SysCodeInfoVo sysCode = sysCodes.get(i);
				sysCodeMap.put(sysCode.getValue(),sysCode);
				
			}
			cache.save(CacheConstant.SYS_CODE_LIST,name, sysCodes);
			cache.save(CacheConstant.SYS_CODE_MAP,name, sysCodeMap);
		}
		return sysCodes;
	}
	
	public SysCodeInfoVo getSysCodeInfo(String name,String code){
		Map<String,SysCodeInfoVo> sysCodeMap = (Map<String, SysCodeInfoVo>) cache.get(CacheConstant.SYS_CODE_MAP,name);
		if(sysCodeMap == null){
			getListByClassify(name);
			sysCodeMap = (Map<String, SysCodeInfoVo>) cache.get(CacheConstant.SYS_CODE_MAP,name);
		}
		return sysCodeMap.get(code);
	}


	@Override
	public List<SysCodeInfoEntity> getListByParentCode(String parId) {
		 List<SysCodeInfoEntity> sysCodes = (List<SysCodeInfoEntity>) cache.get(CacheConstant.SYS_CODE_LIST_PARENT,parId);
		if(sysCodes == null){
			sysCodes = sysCodeInfoDAO.getListByParentCode(parId);
			Map<String,SysCodeInfoEntity> sysCodeMap = new HashedMap();
			for (int i = 0; i < sysCodes.size(); i++) {
				SysCodeInfoEntity sysCode = sysCodes.get(i);
				sysCodeMap.put(sysCode.getValue(),sysCode);
				
			}
			cache.save(CacheConstant.SYS_CODE_LIST_PARENT,parId, sysCodes);
			cache.save(CacheConstant.SYS_CODE_MAP_PARENT,parId, sysCodeMap);
		}
		return sysCodes;
	}


	@Override
	public SysCodeInfoEntity getByParentCode(String parId, String code) {
		Map<String,SysCodeInfoEntity> sysCodeMap = (Map<String, SysCodeInfoEntity>) cache.get(CacheConstant.SYS_CODE_MAP_PARENT,parId);
		if(sysCodeMap == null){
			getListByParentCode(parId);
			sysCodeMap = (Map<String, SysCodeInfoEntity>) cache.get(CacheConstant.SYS_CODE_MAP_PARENT,parId);
		}
		return sysCodeMap.get(code);
	}
	

	/**
	 * 获取所有的字典列表
	 */
	public List<DataVo> getAllList(String parCode) {
		List<DataVo> vos = (List<DataVo>) cache.get(CacheConstant.SYS_CODE_LIST_PARENT,parCode+"all");
		if(vos == null){
			vos = new ArrayList<DataVo>();
			List<SysCodeInfoVo> codeInfos = sysCodeInfoDAO.getByClassvalue(parCode);
			buildDataVos(codeInfos,vos," null");
			cache.save(CacheConstant.SYS_CODE_LIST_PARENT,parCode+"all", vos);
		}
		return vos;
	}
	/**
	 * 构建VO
	 * @param codeInfos
	 * @param vos
	 * @param parentCode
	 * @param count
	 * @param name
	 */
	private void buildDataVos(List<SysCodeInfoVo> codeInfos, List<DataVo> vos, String name) {
		for (Iterator<SysCodeInfoVo> iterator = codeInfos.iterator(); iterator
				.hasNext();) {
			SysCodeInfoVo codeInfo = iterator.next();
			DataVo vo = new DataVo();
			String code = codeInfo.getValue();
			vo.setText(codeInfo.getName());
			vo.setValue(code);
			vos.add(vo);
			List<DataVo> vos2 = new ArrayList<DataVo>();
			vo.setChildren(vos2);

			log.debug("  size:" + vos.size() + "  code = " + code);
			buildDataVos(sysCodeInfoDAO.getListByParentCode(code), vos2);
		}
	}
	/**
	 * 构建VO
	 * @param list
	 * @param vos
	 * @param parentCode
	 * @param count
	 * @param name
	 */
	private void buildDataVos(List<SysCodeInfoEntity> list, List<DataVo> vos) {
		for (Iterator<SysCodeInfoEntity> iterator = list.iterator(); iterator
				.hasNext();) {
			SysCodeInfoEntity codeInfo = iterator.next();
			DataVo vo = new DataVo();
			String code = codeInfo.getValue();
			vo.setText(codeInfo.getName());
			vo.setValue(code);
			vos.add(vo);
			List<SysCodeInfoEntity> list2 = sysCodeInfoDAO.getListByParentCode(code);
			if(list2.size()>0){
				List<DataVo> vos2 = new ArrayList<DataVo>();
				vo.setChildren(vos2);
				buildDataVos(list2, vos2);
			}
		}
	}


	@Override
	public void cleanCache() {
		cache.remove(CacheConstant.SYS_CODE_LIST_PARENT);
	}
}
