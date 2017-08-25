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

import com.platform.rp.services.organize.external.rest.impl.ExBsMerchantsInfoRestImpl;
import com.platform.rp.services.sys.core.dao.SysAreaInfoDAO;
import com.platform.rp.services.sys.core.dao.entity.SysAreaInfoEntity;
import com.platform.rp.services.sys.external.service.ISysAreaInfoService;
import com.platform.rp.services.sys.external.vo.DataVo;
import com.platform.rp.util.cache.CacheConstant;
import com.platform.rp.util.cache.ICache;


@SuppressWarnings("unchecked")
@Service
public class SysAreaInfoServiceImpl implements ISysAreaInfoService {
	private Log log = LogFactory.getLog(SysAreaInfoServiceImpl.class);

	@Autowired
	SysAreaInfoDAO<SysAreaInfoEntity> sysAreaInfoDAO;

	@Autowired
	ICache cache;


	@Override
	public List<SysAreaInfoEntity> getListByParentCode(String parCode) {
		//从缓存中查询
		List<SysAreaInfoEntity> areas = (List<SysAreaInfoEntity>) cache.get(CacheConstant.SYS_AREA_LIST,parCode);
		//如果缓存中不存在，直接从数据库查询
		if(areas == null){
			areas = sysAreaInfoDAO.getSysAreaInfoList(parCode);
			Map<String,SysAreaInfoEntity> sysCodeMap = new HashedMap();
			for (int i = 0; i < areas.size(); i++) {
				SysAreaInfoEntity sysCode = areas.get(i);
				sysCodeMap.put(sysCode.getCode(),sysCode);			
			}
			cache.save(CacheConstant.SYS_AREA_LIST,parCode, areas);
			cache.save(CacheConstant.SYS_AREA_MAP,parCode, sysCodeMap);
		}
		return areas;
	}


	@Override
	public SysAreaInfoEntity getByParentCode(String parCode, String code) {
		Map<String,SysAreaInfoEntity> sysCodeMap = (Map<String, SysAreaInfoEntity>) cache.get(CacheConstant.SYS_AREA_MAP,parCode);
		if(sysCodeMap == null){
			getListByParentCode(parCode);
			sysCodeMap = (Map<String, SysAreaInfoEntity>) cache.get(CacheConstant.SYS_AREA_MAP,parCode);
		}
		return sysCodeMap.get(code);
	}
	
	public List<DataVo> getList() {
		List<DataVo> vos = (List<DataVo>) cache.get(CacheConstant.SYS_AREA_MAP,"all");
		if(vos == null){
			vos = new ArrayList<DataVo>();
			List<SysAreaInfoEntity> areas  = sysAreaInfoDAO.getSysAreaInfoList(null);		
			buildDataVos(areas,vos,SysAreaInfoEntity.DEF_CODE,1," null");
			cache.save(CacheConstant.SYS_AREA_MAP,"all", vos);
		}
		return vos;
	}
	
	private void buildDataVos(List<SysAreaInfoEntity> areas,List<DataVo> vos,String parentCode,int count,String name){
		for (Iterator<SysAreaInfoEntity> iterator = areas.iterator(); iterator.hasNext();) {
			SysAreaInfoEntity area = iterator.next();
			if(parentCode.equals(area.getParentCode())){
				DataVo vo = new DataVo();
				vo.setText(area.getName());
				vo.setValue(area.getCode());
				vos.add(vo);
				iterator.remove();
			}
		}
		//log.debug(count+"  size:"+vos.size()+"  code = "+parentCode+"  name="+name);
		if(count<=3){
			for (int i = 0; i < vos.size(); i++) {
				DataVo vo = vos.get(i);
				List<DataVo> vos2 = new ArrayList<DataVo>();
				vo.setChildren(vos2);
				buildDataVos(areas, vos2,vo.getValue(),count+1,vo.getText());
			}
		}
	}
}
