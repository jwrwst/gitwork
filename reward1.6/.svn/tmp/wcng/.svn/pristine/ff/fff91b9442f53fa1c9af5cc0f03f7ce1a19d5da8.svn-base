package com.platform.rp.services.store.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreAuthDetailEntity;

public interface BsStoreAuthDetailDAO<T> extends BaseDAO<T> {
	
	//根据店铺ID获取店铺开放权限
	public List<BsStoreAuthDetailEntity> getStoreAuthDetailByStoreId(@Param("storeId") long storeId);

}
