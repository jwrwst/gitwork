package com.platform.rp.services.store.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface BsStoreInfoDAO<T> extends BaseDAO<T>{

	
	public List<BsStoreInfoEntity> getInfoByOpenId(@Param("openId")String openId);
	
	public int getCountByOpenId(@Param("openId")String openId);
	
	public int getCountByOpenIdAndStoreCode(@Param("openId")String openId);
	
	public int getCountByOpenIdAndStoreId(@Param("openId")String openId,@Param("storeId")long storeId);
	
	//根据店长ID获取该店长管理的所有店铺信息
	public List<BsStoreInfoEntity> getStoreListByEmpId(@Param("empId") long empID);
	
	public void updateStatus(@Param("status") int status,@Param("storeId")long storeId);
	public void updateStatuses(@Param("status") int status,@Param("storeIds")String[] storeIds);
	
	public void updateStatusByStoreCode(@Param("status") int status,@Param("storeCode")String storeCode);
}
