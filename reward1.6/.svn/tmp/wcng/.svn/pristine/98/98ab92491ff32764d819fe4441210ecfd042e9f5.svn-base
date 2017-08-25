package com.platform.rp.services.organize.external.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.organize.core.dao.BsMerchantsInfoDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsStoreDAO;
import com.platform.rp.services.organize.core.dao.BsOrganizeDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.external.service.IExBsOrganizeService;
import com.platform.rp.services.store.core.dao.BsStoreInfoDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.util.Constant;
import com.platform.rp.util.TinyUrlGenerater;

/**
 * 商户组织结构
 * @author tangjun
 * 
 * 2016年5月6日
 */
@Service
public class ExBsOrganizeServiceImpl implements IExBsOrganizeService {

	@Autowired
	BsOrganizeDAO<BsOrganizeEntity> bsOrganizeDAO;
	
	@Autowired
	BsMerchantsStoreDAO<BsMerchantsStoreEntity> bsMerchantsStoreDAO;
	
	@Autowired
	BsMerchantsInfoDAO<BsMerchantsInfoEntity> bsMerchantsInfoDAO;
	
	@Autowired
	BsStoreInfoDAO<BsStoreInfoEntity> bsStoreInfoDAO;
	
	/**
	 * 查询组织列表及店铺列表，整合成树列表结构
	 */
	@Override
	public List<BsOrganizeEntity> getList(String orgCode,int status,String storeName) throws Exception {
		//查询组织信息
		List<BsOrganizeEntity> orgList = bsOrganizeDAO.getList(orgCode,status);
		
		List<BsMerchantsStoreEntity> merchantList=bsMerchantsStoreDAO.getlist(orgCode, status,storeName);
		for (BsMerchantsStoreEntity bsMerchantsStoreEntity : merchantList) {
			BsOrganizeEntity entity=new BsOrganizeEntity();
			entity.setLast(1);
			entity.setOrgCode(bsMerchantsStoreEntity.getStoreId()+"");
			entity.setParentCode(bsMerchantsStoreEntity.getOrgCode());
			entity.setOrgName(bsMerchantsStoreEntity.getStoreName());
			
			orgList.add(entity);
		}
		
		return orgList;
	}
	
	/**
	 * 删除组织列表及店铺列表
	 */
	@Override
	public void remove(String orgCode) throws Exception {
		//查询组织信息
		//获取下级节点
		List<String> orgList=this.findLevel(orgCode);
		//获取当前节点下所有关联门店
		List<BsMerchantsStoreEntity> merStoreList=bsMerchantsStoreDAO.getMerchantStoreList(orgList);
		//删除机构
		BsOrganizeEntity entity;
		for (String str : orgList) {
			entity=new BsOrganizeEntity();
			entity.setOrgCode(str);
			entity.setStatus(Constant.AUDITDELETE);
			bsOrganizeDAO.update(entity);
		}
			
		//删除店铺信息
		if(null!=merStoreList&&merStoreList.size()>0){
			String [] storeIds = new String[merStoreList.size()];
			int i=0;
			for (BsMerchantsStoreEntity bsMerchantsStoreEntity : merStoreList) {				
				storeIds[i]=bsMerchantsStoreEntity.getStoreId()+"";
				i++;
			}
			bsStoreInfoDAO.updateStatuses(Constant.DELETE, storeIds);		
		}
	}
	
	/**
	 * 保存组织机构
	 */
	public void save(BsOrganizeEntity entity) throws Exception{
		Date dt=new Date();
		String orgCode =  TinyUrlGenerater.generatorByUUID();
		entity.setOrgCode(orgCode);
		entity.setStatus(Constant.AUDIED);
		entity.setCreateTime(dt);
		bsOrganizeDAO.save(entity);		
		//保存机构详细信息		
		BsMerchantsInfoEntity merEntity = new BsMerchantsInfoEntity();
		merEntity.setOrgCode(orgCode);
		merEntity.setUpdateTime(dt);
		//获取上一级信息
		BsMerchantsInfoEntity parentEntity=bsMerchantsInfoDAO.getInfoByOrgCode(entity.getParentCode());
		if(null!=parentEntity){
			merEntity.setRewardMoney(parentEntity.getRewardMoney());
			merEntity.setIsUpdateMoney(parentEntity.getIsUpdateMoney());
			merEntity.setIsedit(parentEntity.getIsedit());
			merEntity.setIseditVal1(parentEntity.getIseditVal1());
			merEntity.setIseditVal2(parentEntity.getIseditVal2());
			merEntity.setIseditVal3(parentEntity.getIseditVal3());
			merEntity.setWish(parentEntity.getWish());
			merEntity.setAuthType(parentEntity.getAuthType());
		}
		bsMerchantsInfoDAO.save(merEntity);
	}

	/**
	 * 查询所有子节点
	 * @param orgCode
	 * @return
	 * @throws Exception
	 */
	public List<String> findLevel(String orgCode)throws Exception{
		try {
			List<String> retlist = new ArrayList<String>();
			retlist.add(orgCode);		
			
			levelIteration(retlist,retlist);			
			
			return retlist;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 迭代查询子节点
	 * @param retlist
	 * @param paramList
	 */
	private void levelIteration(List<String> retlist,List<String> paramList){
		List<String> list=bsOrganizeDAO.getlevelList(paramList);
		if(list==null||list.size()==0){
			return;
		}
		retlist.addAll(list);
		levelIteration(retlist,list);
	}


	@Override
	public List<BsOrganizeEntity> getListByParentCode(String parentCode,String schema,String orgName) {
		// TODO Auto-generated method stub
		return bsOrganizeDAO.getListByParentCode(parentCode,schema,orgName);
	}
	public BsOrganizeEntity getByOrgCode(String orgCode){

		return bsOrganizeDAO.getInfoByOrgCode(orgCode);
	}
}
