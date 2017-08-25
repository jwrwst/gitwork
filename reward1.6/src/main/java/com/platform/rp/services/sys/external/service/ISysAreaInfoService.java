/**
 * 
 */
package com.platform.rp.services.sys.external.service;

import java.util.List;

import com.platform.rp.services.sys.core.dao.entity.SysAreaInfoEntity;
import com.platform.rp.services.sys.external.vo.DataVo;

public interface ISysAreaInfoService {

	/**
	 * 根据父id获取列表信息
	 * @param classifyid
	 * @param name
	 * @return
	 */
	public List<SysAreaInfoEntity> getListByParentCode(String parId);
	/**
	 * 获取数据字典
	 * @param name
	 * @param code
	 * @return
	 */
	public SysAreaInfoEntity  getByParentCode(String parId,String Code);
	/**
	 * 获取所有的城市列表
	 * @return
	 */
	public List<DataVo> getList() ;
}
