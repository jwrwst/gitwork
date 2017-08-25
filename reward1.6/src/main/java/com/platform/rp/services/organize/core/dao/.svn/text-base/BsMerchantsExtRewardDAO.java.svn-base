package com.platform.rp.services.organize.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsExtRewardEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface BsMerchantsExtRewardDAO<T> extends BaseDAO<T>{

	
	public void saveList(List<BsMerchantsExtRewardEntity> list);
	
	public void deleteList(@Param("orgCode") String orgCode);
	
	public void deleteById(@Param("id") int id,@Param("parentId") int parentId);
	
	public List<BsMerchantsExtRewardEntity> getList(@Param("orgCode") String orgCode);
	
	/////////////
	/**
	 * 根据上一级赏金分配规则删除
	 * @param parentId
	 */
	public void deleteByParentId(@Param("deleteIdList") List<Integer> deleteIdList);
	
	/**
	 * 根据店铺编号和上级编号 删除赏金分配规则  parentId = 0
	 * @param parentId
	 */
	public void deleteByParentIdAndOrgCode(@Param("orgList") List<BsOrganizeEntity> orgList);
	
	/**
	 * 修改机构赏金分配
	 * entity 修改实体
	 * orgCode  机构编号，rewardId： 规则编号，rewardParentId：规则上级编号
	 */
	public void updateOrgRewardByParentId(@Param("entity") BsMerchantsExtRewardEntity entity,@Param("rewardId") long rewardId,@Param("rewardParentId") int rewardParentId);

	
}
