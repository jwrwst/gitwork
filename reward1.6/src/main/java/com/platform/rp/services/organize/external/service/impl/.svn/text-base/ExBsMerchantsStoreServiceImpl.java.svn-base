package com.platform.rp.services.organize.external.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.organize.core.dao.BsMerchantsStoreDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsStoreService;
import com.platform.rp.services.organize.external.service.IExBsOrganizeService;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;

@Service
public class ExBsMerchantsStoreServiceImpl implements IExBsMerchantsStoreService {

	@Autowired
	BsMerchantsStoreDAO<BsMerchantsStoreEntity> bsMerchantsStoreDAO;
	@Autowired
	IExBsOrganizeService bsOrganizeService;
	
	@Override
	public List<BsMerchantsStoreEntity> getList(String orgCode,int status,String proId,String cityId,String areaId) throws Exception {
	
		return bsMerchantsStoreDAO.getlistByareaId(orgCode, status, proId, cityId, areaId,null);
	}
	

	@Override
	public List<BsMerchantsStoreEntity> getList(String orgCode,String storeName) throws Exception {
	
		return bsMerchantsStoreDAO.getlist(orgCode,0,storeName);
	}
	@Override
	public List<BsMerchantsStoreEntity> getSubListByOrgCode(String orgCode,String storeName) throws Exception {
	
		return bsMerchantsStoreDAO.getSubListByOrgCode(orgCode,0,storeName);
	}
	public List<BsMerchantsStoreEntity> searchStoreByParentCode(String orgCode,
			String storeName){

		return bsMerchantsStoreDAO.searchStoreByParentCode(orgCode,0,storeName);
	}
	public List<BsMerchantsStoreEntity> getMerchantStoreList( List<String> orgCodes){
		return bsMerchantsStoreDAO.getMerchantStoreList(orgCodes);
	}

	@Override
	public List<BsStoreInfoEntity> getListByOrgCode(String orgCode) {
		return  bsMerchantsStoreDAO.getListByOrgCode(orgCode);
	}

	@Override
	public List<BsMerchantsStoreEntity> getAllListByOrgCode(String orgCode) {

		//查询组织信息
		//获取下级节点
		List<String> orgList = new ArrayList<String>();
		try {
			orgList = bsOrganizeService.findLevel(orgCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//获取当前节点下所有关联门店
		List<BsMerchantsStoreEntity> merStoreList=bsMerchantsStoreDAO.getMerchantStoreList(orgList);
		return  merStoreList;
	}
}
