/**
 * 
 */
package com.platform.rp.services.store.external.service;

import java.util.List;

import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeEntity;

/**
 * 
 * @author tangjun
 * 
 * 2016年3月16日
 */
public interface IExBsStoreEmpService {

	public List<BsStoreEmployeeEntity> getEmpListByStoreId(long storeId,String filter) throws Exception;

	/**
	 * 删除店长
	 * @param storeId
	 * @param empId
	 * @throws Exception
	 */
	public void deleteManager(long storeId,long empId)throws Exception;
}
