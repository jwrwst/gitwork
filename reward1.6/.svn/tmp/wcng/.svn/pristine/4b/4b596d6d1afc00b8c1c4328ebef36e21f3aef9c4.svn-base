package com.platform.rp.services.organize.external.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;


public interface IExBsMerchantsStoreService {

	public List<BsMerchantsStoreEntity> getList(String orgCode,int status,String proId,String cityId,String areaId) throws Exception;

	List<BsMerchantsStoreEntity> getList(String orgCode, String storeName)
			throws Exception;

	/**
	 * 根据父编码获取店铺列表
	 * @param orgCode
	 * @return
	 */
	public List<BsMerchantsStoreEntity> searchStoreByParentCode(String orgCode,
			String storeName);

	List<BsMerchantsStoreEntity> getSubListByOrgCode(String orgCode,
			String storeName) throws Exception;
	
	
	/**
	 * 获取区域列表下所有关联店铺
	 * @param orgCodes
	 * @return
	 */
	public List<BsMerchantsStoreEntity> getMerchantStoreList( List<String> orgCodes);
	

	/**
	 * 获取区域下直辖门店
	 * @param orgCode
	 * @param status
	 * @param storeName
	 * @return
	 */
	public List<BsStoreInfoEntity> getListByOrgCode( String orgCode);
	

	/**
	 * 获取区域下所有门店
	 * @param orgCode
	 * @param status
	 * @param storeName
	 * @return
	 */
	public List<BsMerchantsStoreEntity> getAllListByOrgCode(String orgCode);
}
