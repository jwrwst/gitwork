package com.platform.rp.services.store.external.service;

import java.util.List;

import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtStatisticalEntity;


public interface IExStoreStatisticService {

	public void update(BsStoreExtStatisticalEntity entity)throws Exception;

	public BsStoreExtStatisticalEntity get(long storeID) throws Exception;
	/**
	 * 区域经理数量
	 * @param storeID
	 * @return
	 */
	public long getAreaManagerCount(long storeID);
	/**
	 * 区域管理人员
	 * @param storeId
	 * @return
	 */
	public List<BsEmployeeInfoEntity> getAreaManagerByStoreID(long storeId);
}
