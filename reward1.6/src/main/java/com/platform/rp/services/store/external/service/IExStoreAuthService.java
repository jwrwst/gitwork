package com.platform.rp.services.store.external.service;

import java.util.List;

import com.platform.rp.services.store.core.dao.entity.BsStoreAuthEntity;


public interface IExStoreAuthService {

	/**
	 * 获取店铺权限列表项
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	public List<BsStoreAuthEntity> getList(String groupCode)throws Exception;

}
