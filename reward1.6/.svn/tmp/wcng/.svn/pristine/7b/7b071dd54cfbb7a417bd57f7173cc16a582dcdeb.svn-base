package com.platform.rp.services.store.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreEmployeeEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface BsStoreEmployeeDAO<T> extends BaseDAO<T>{

	public List<BsStoreEmployeeEntity> getEmpListByStoreId(@Param("storeId") long storeId);
	
	public void deleteByStoreIdAndEmpId(@Param("storeId") long storeId,@Param("empId") long empId);

	
}
