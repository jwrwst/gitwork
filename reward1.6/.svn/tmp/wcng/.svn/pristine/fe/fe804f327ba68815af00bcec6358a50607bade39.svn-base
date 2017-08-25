package com.platform.rp.services.organize.external.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.organize.core.dao.BsMerchantsExtRewardDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsInfoDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsRewardStoreDAO;
import com.platform.rp.services.organize.core.dao.BsMerchantsStoreDAO;
import com.platform.rp.services.organize.core.dao.BsOrganizeDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsExtRewardEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsRewardStoreEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsRewardService;
import com.platform.rp.services.organize.external.service.IExBsOrganizeService;
import com.platform.rp.services.organize.external.vo.BsMerchantsVo;
import com.platform.rp.services.store.core.dao.BsStoreExtRewardDAO;
import com.platform.rp.services.store.core.dao.BsStoreInfoDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;

@Service
public class ExBsMerchantsRewardServiceImpl implements IExBsMerchantsRewardService {

	@Autowired
	BsMerchantsExtRewardDAO<BsMerchantsExtRewardEntity> merchantsExtRewardDAO;
	
	@Autowired
	BsMerchantsRewardStoreDAO<BsMerchantsRewardStoreEntity> bsMerchantsRewardStoreDAO;
	
	@Autowired
	BsMerchantsInfoDAO<BsMerchantsInfoEntity> bsMerchantsInfoDAO;
	
	@Autowired
	IExBsOrganizeService bsOrganizeService;
	
	@Autowired
	BsOrganizeDAO<BsOrganizeEntity> bsOrganizeDAO;
	
	@Autowired
	BsMerchantsStoreDAO<BsMerchantsStoreEntity> bsMerchantsStoreDAO; 
	
	@Autowired
	BsStoreExtRewardDAO<BsStoreExtRewardEntity> bsStoreExtRewardDAO;
	
	@Autowired
	BsStoreInfoDAO<BsStoreInfoEntity> bsStoreInfoDAO;
	
	/**
	 * 获取上一级赏金分配规则
	 */
	@Override
	public List<BsMerchantsExtRewardEntity> getInfo(String orgCode) throws Exception {
		List<BsMerchantsExtRewardEntity>  list = merchantsExtRewardDAO.getList(orgCode);
		if(list==null || list.size()==0){
			list = this.iteratorMerReward(orgCode);
		}
		return list;
	}
	
	/**
	 * 查询上一级赏金分配
	 * @param orgCode
	 * @return
	 */
	private List<BsMerchantsExtRewardEntity>  iteratorMerReward(String orgCode){
		BsOrganizeEntity orgEntity = bsOrganizeDAO.getInfoByOrgCode(orgCode);
		if(orgEntity!=null){
			List<BsMerchantsExtRewardEntity>  list = merchantsExtRewardDAO.getList(orgEntity.getParentCode());
			if(list==null || list.size()==0){
				return iteratorMerReward(orgEntity.getParentCode());
			}else{
				for (BsMerchantsExtRewardEntity bsMerchantsExtRewardEntity : list) {
					bsMerchantsExtRewardEntity.setParentId(bsMerchantsExtRewardEntity.getId());
					bsMerchantsExtRewardEntity.setId(0);
				}
				return list;
			}
		}
		return null;
		
	}
	
	/**
	 * 保存赏金分配 规则
	 */
	@Override
	public void save(BsMerchantsVo vo)
			throws Exception {
		Date dt = new Date();
		
		/**
		//保存赏金分配关联门店		
		if(vo.getMerchantsRewardStoreEntity()!=null){
			List<BsMerchantsRewardStoreEntity> entitylist = vo.getMerchantsRewardStoreEntity();
			bsMerchantsRewardStoreDAO.deleteList(vo.getOrgCode());
			
			for (BsMerchantsRewardStoreEntity bsMerchantsRewardStoreEntity : entitylist) {
				bsMerchantsRewardStoreDAO.save(bsMerchantsRewardStoreEntity);
			}
		}
		**/
		
		//保存商户信息
		BsMerchantsInfoEntity entity=new BsMerchantsInfoEntity();
		entity.setOrgCode(vo.getOrgCode());
		entity.setIsUpdateMoney(vo.getIsUpdateMoney());
		entity.setRewardMoney(vo.getRewardMoney());
		entity.setWish(vo.getWish());
		entity.setUpdateTime(dt);
		bsMerchantsInfoDAO.update(entity);
		
		//获取删除列表
		List<Integer> idList=new ArrayList<Integer>();

		//保存赏金分配 
		List<BsMerchantsExtRewardEntity> list = vo.getMerchantsExtReward();
		if(null != list && list.size()>0){
			//获取原有赏金分配方案
			List<BsMerchantsExtRewardEntity> orgRewardList=merchantsExtRewardDAO.getList(vo.getOrgCode());
			
			//修改商户赏金分配
			for (BsMerchantsExtRewardEntity bsMerchantsExtRewardEntity : list) {
				bsMerchantsExtRewardEntity.setUpdateTime(dt);
				if(bsMerchantsExtRewardEntity.getId()==0){
					bsMerchantsExtRewardEntity.setFlag(1);
					merchantsExtRewardDAO.save(bsMerchantsExtRewardEntity);
				}else{
					bsMerchantsExtRewardEntity.setFlag(0);
					merchantsExtRewardDAO.update(bsMerchantsExtRewardEntity);
				}
			}
			
			//删除规则
			if(null!=orgRewardList){
				for (BsMerchantsExtRewardEntity temp : orgRewardList) {
					boolean flag=true;
					for (BsMerchantsExtRewardEntity tlist : list) {
						if(temp.getId() == tlist.getId()){
							flag = false;
							break;
						}
					}
					if(flag){
						idList.add(temp.getId());	
						merchantsExtRewardDAO.deleteById(temp.getId(),0);
					}
				}
			}
		}else{
			//删除所有分配
			merchantsExtRewardDAO.deleteList(vo.getOrgCode());
		}
		
		///////////////////  修改下级区域、下级店铺基础信息,赏金分配  /////////////////
		//获取下级节点
		List<String> orgList=bsOrganizeService.findLevel(vo.getOrgCode());
		//获取当前节点下所有关联店铺
		List<BsMerchantsStoreEntity> merStoreList=bsMerchantsStoreDAO.getMerchantStoreList(orgList);
		
		//保存下级、店铺信息  判断是否可修改打赏规则
		entity = bsMerchantsInfoDAO.getInfoByOrgCode(vo.getOrgCode());
		if(entity.getIseditVal1() == 2){
			/** 修改下级赏金分配规则 **/
			if(null != list && list.size()>0){
				//修改下级及店铺赏金分配规则
				updateOrgRewardList(idList,list,vo.getOrgCode());				
			}else{					
				//删除所有下级赏金分配
				for (String str : orgList) {
					if(str.equals(vo.getOrgCode()))continue;
					merchantsExtRewardDAO.deleteList(str);
				}
				//删除所有下级店铺赏金分配
				for (BsMerchantsStoreEntity bsMerchantsStoreEntity : merStoreList) {
					bsStoreExtRewardDAO.deleteByStoreId(bsMerchantsStoreEntity.getStoreId());
				}
			}
			
			/** 保存下级节点信息 **/
			BsMerchantsInfoEntity tempEntity;
			for (String str : orgList) {
				if(str.equals(vo.getOrgCode()))continue;
				tempEntity = new BsMerchantsInfoEntity();
				tempEntity.setOrgCode(str);
				tempEntity.setIsUpdateMoney(vo.getIsUpdateMoney());
				tempEntity.setRewardMoney(vo.getRewardMoney());
				tempEntity.setWish(vo.getWish());
				tempEntity.setUpdateTime(dt);
				bsMerchantsInfoDAO.update(tempEntity);
			}
			//保存店铺信息
			BsStoreInfoEntity tempStoreEntity;
			for (BsMerchantsStoreEntity bsMerchantsStoreEntity : merStoreList) {
				tempStoreEntity = new BsStoreInfoEntity();
				tempStoreEntity.setStoreId(bsMerchantsStoreEntity.getStoreId());
				tempStoreEntity.setWish(vo.getWish());
				tempStoreEntity.setRewardMoney(vo.getRewardMoney());
				tempStoreEntity.setIsUpdate(vo.getIsUpdateMoney());
				tempStoreEntity.setUpdateTime(dt);
				bsStoreInfoDAO.update(tempStoreEntity);
			}
		}
	}
	
	/**
	 * 修改子级机构赏金分配，迭代修改
	 * @param deleteIdList
	 * @param list
	 * @param orgCode
	 */
	private void updateOrgRewardList(List<Integer> idList,List<BsMerchantsExtRewardEntity> list,String orgCode){
		try {
			//修改店铺规则
			this.updateStoreRewardList(idList, list, orgCode);
			
			//修改机构
			List<BsOrganizeEntity> orgLevelList= bsOrganizeService.getListByParentCode(orgCode,null , null);
			if(orgLevelList!=null && orgLevelList.size()!=0){	
				
				for (BsOrganizeEntity bsOrganizeEntity : orgLevelList) {						
					
					//判断下级是否保存过赏金分配
					List<BsMerchantsExtRewardEntity> orgRewardListTemp=merchantsExtRewardDAO.getList(bsOrganizeEntity.getOrgCode());
					//编辑的规则
					List<BsMerchantsExtRewardEntity> levelList= new ArrayList<>();
					//删除规则
					List<Integer> levelIdList= new ArrayList<Integer>();
					
					if(null != orgRewardListTemp && orgRewardListTemp.size()!=0){
						//修改
						for (BsMerchantsExtRewardEntity bsMerchantsExtRewardEntity : list) {					
							BsMerchantsExtRewardEntity entity=new BsMerchantsExtRewardEntity();
							entity.setParentId(bsMerchantsExtRewardEntity.getId());
							entity.setPercent(bsMerchantsExtRewardEntity.getPercent());
							entity.setMoney(bsMerchantsExtRewardEntity.getMoney());
							entity.setRemark(bsMerchantsExtRewardEntity.getRemark());
							entity.setUpdateTime(new Date());
							entity.setAllotPlan(bsMerchantsExtRewardEntity.getAllotPlan());
							entity.setOrgCode(bsOrganizeEntity.getOrgCode());
							
							boolean flag = false;							
							for (BsMerchantsExtRewardEntity bstemp : orgRewardListTemp) {							
								if(bstemp.getParentId() == bsMerchantsExtRewardEntity.getId() || bstemp.getParentId() == bsMerchantsExtRewardEntity.getParentId() ){
									entity.setId(bstemp.getId());
									flag = true;
									break;
								}
							}							
							
							//存在，修改赏金分配
							if(flag){
								//根据当前机构的编号及上级机构修改
								merchantsExtRewardDAO.updateOrgRewardByParentId(entity, bsMerchantsExtRewardEntity.getId(), bsMerchantsExtRewardEntity.getParentId());
							}else{
								//保存数据
								merchantsExtRewardDAO.save(entity);
							}
							
							levelList.add(entity);
						}
						
						//删除						
						for (BsMerchantsExtRewardEntity bstemp : orgRewardListTemp) {	
							boolean flag=true;
							if(bstemp.getParentId() == 0){
								flag = true;
							}else{
								for (BsMerchantsExtRewardEntity tlist : list) {
									if(bstemp.getParentId() == tlist.getId() || bstemp.getParentId() == tlist.getParentId() ){
										flag = false;
										break;
									}
								}
							}
							if(flag){
								levelIdList.add(bstemp.getId());	
								merchantsExtRewardDAO.deleteById(bstemp.getId(),0);
							}
						}
						
					}else{
						levelList = list;
					}

					//修改机构店铺及下级机构
					updateOrgRewardList(levelIdList, levelList, bsOrganizeEntity.getOrgCode());
					
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 修改店铺赏金分配规则
	 * @param idList
	 * @param list
	 * @param orgCode
	 */
	private void updateStoreRewardList(List<Integer> idList,List<BsMerchantsExtRewardEntity> list,String orgCode){
		//获取当前机构下所有店铺
		List<BsStoreInfoEntity> storeList = bsMerchantsStoreDAO.getListByOrgCode(orgCode);
		
		/**  删除规则 **/
		//删除上级已经删除的规则
		if(idList!=null && idList.size()>0){
			bsStoreExtRewardDAO.deleteByParentId(idList);
		}
		//删除店铺新增的规则
		if(storeList!=null && storeList.size()>0){
			bsStoreExtRewardDAO.deleteByParentIdAndStoreId(storeList);	
		}
		
		/** 新增、修改规则 **/		
		for (BsStoreInfoEntity bsStoreInfoEntity : storeList) {				
			//获取当前店铺的规则
			List<BsStoreExtRewardEntity> storeRewardList = bsStoreExtRewardDAO.getStoreExtRewardByStoreId(bsStoreInfoEntity.getStoreId());//.getStoreRewardByParentId(bsStoreInfoEntity.getStoreId(), bsMerchantsExtRewardEntity.getId(), bsMerchantsExtRewardEntity.getParentId());
			
			//当店铺没有设置规则，不进行编辑，
			if(storeRewardList == null || storeRewardList.size()==0){
				continue;
			}
			
			for (BsMerchantsExtRewardEntity bsMerchantsExtRewardEntity : list) {					
				BsStoreExtRewardEntity entity=new BsStoreExtRewardEntity();
				entity.setParentId(bsMerchantsExtRewardEntity.getId());
				entity.setPercent(bsMerchantsExtRewardEntity.getPercent());
				entity.setMoney(bsMerchantsExtRewardEntity.getMoney());
				entity.setRemark(bsMerchantsExtRewardEntity.getRemark());
				entity.setUpdateTime(new Date());
				entity.setAllotPlan(bsMerchantsExtRewardEntity.getAllotPlan());
				entity.setStoreId(bsStoreInfoEntity.getStoreId());
				
				boolean flag = false;
				//判断是否存在				
				for (BsStoreExtRewardEntity bsStoreExtRewardEntity : storeRewardList) {
					if(bsStoreExtRewardEntity.getParentId() == bsMerchantsExtRewardEntity.getId() || bsStoreExtRewardEntity.getParentId() == bsMerchantsExtRewardEntity.getParentId() ){
						flag = true;
						break;
					}
				}
			    //存在，修改赏金分配
				if(flag){
					//根据当前机构的编号及上级机构修改
					bsStoreExtRewardDAO.updateStoreRewardByParentId(entity,bsMerchantsExtRewardEntity.getId(),bsMerchantsExtRewardEntity.getParentId());
				}else{
					//保存数据
					bsStoreExtRewardDAO.save(entity);
				}
			
			}			
			
		}		
	}

	public List<BsMerchantsRewardStoreEntity> getRewardStoreList(String orgCode)throws Exception{
		return bsMerchantsRewardStoreDAO.getList(orgCode);
	}
	
	
}
