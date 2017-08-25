package com.platform.rp.services.organize.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface BsMerchantsStoreDAO<T> extends BaseDAO<T>{

	
	/**
	 * 获取商户下所有门店
	 * @param orgCode
	 * @param status
	 * @param storeName
	 * @return
	 */
	public List<BsMerchantsStoreEntity> getlist(@Param("orgCode") String orgCode,@Param("status") int status
			,@Param("storeName") String storeName);
	/**
	 * 获取商户下所有门店
	 * @param orgCode
	 * @param status
	 * @param storeName
	 * @return
	 */
	public List<BsMerchantsStoreEntity> getSubListByOrgCode(@Param("orgCode") String orgCode,@Param("status") int status
			,@Param("storeName") String storeName);

	
	public List<BsMerchantsStoreEntity> getlistByareaId(@Param("orgCode") String orgCode,@Param("status") int status,
			@Param("proId")String proId,@Param("cityId")String cityId,@Param("areaId")String areaId,@Param("storeName") String storeName);
	
	public void deleteByOrgCodeAndStoreId(@Param("orgCode") String orgCode,@Param("storeId") long storeId);
	
	/**
	 * 获取区域列表下所有关联店铺
	 * @param orgCodes
	 * @return
	 */
	public List<BsMerchantsStoreEntity> getMerchantStoreList(@Param("orgCodes") List<String> orgCodes);

	/**
	 * 获取区域下所有门店
	 * @param orgCode
	 * @param status
	 * @param storeName
	 * @return
	 */
	public List<BsStoreInfoEntity> getListByOrgCode(@Param("orgCode") String orgCode);

	/**
	 * 根据父编码获取店铺列表
	 * @param orgCode
	 * @return
	 */
	public List<BsMerchantsStoreEntity> searchStoreByParentCode(@Param("orgCode") String orgCode,@Param("status") int status
			,@Param("storeName") String storeName);

	/**
	 * 根据门店ID查询商户
	 * @param storeID
	 * @return
	 */
	public BsMerchantsStoreEntity geOrgByStoreId(@Param("storeID") long storeID);
}
