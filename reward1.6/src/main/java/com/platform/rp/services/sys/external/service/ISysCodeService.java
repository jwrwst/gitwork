/**
 * 
 */
package com.platform.rp.services.sys.external.service;

import java.util.List;

import com.platform.rp.services.sys.core.dao.entity.SysCodeInfoEntity;
import com.platform.rp.services.sys.external.vo.DataVo;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;

public interface ISysCodeService {

	public SysCodeInfoVo findSysCodeByClassAndCodeValue(String codeClass, String codeValue);

	/**
	 * 根据分类获取列表信息
	 * @param classifyid
	 * @param name
	 * @return
	 */
	public List<SysCodeInfoVo> getListByClassify( String name);
	/**
	 * 获取数据字典
	 * @param name
	 * @param code
	 * @return
	 */
	public SysCodeInfoVo getSysCodeInfo(String name,String code);
	/**
	 * 根据父id获取列表信息
	 * @param classifyid
	 * @param name
	 * @return
	 */
	public List<SysCodeInfoEntity> getListByParentCode(String parCode);
	/**
	 * 获取数据字典
	 * @param name
	 * @param code
	 * @return
	 */
	public SysCodeInfoEntity  getByParentCode(String parCode,String Code);
	

	/**
	 * 获取所有的字典列表
	 * @return
	 */
	public List<DataVo> getAllList(String parCode) ;

	public void cleanCache();
}
