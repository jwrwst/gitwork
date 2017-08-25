package com.platform.rp.services.store.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;

public interface BsStoreExtRewardDAO<T> extends BaseDAO<T> {
	
	//根据店铺ID获取店铺打赏扩展信息
	public List<BsStoreExtRewardEntity> getStoreExtRewardByStoreId(@Param("storeId") long storeId);
	
	/**
	 * 删除店铺下所有的分配规则
	 * @param storeId
	 */
	public void deleteByStoreId(@Param("storeId") long storeId);

	/**
	 * 根据员工编号和门店编号删除（改为修改）
	 * @param storeId
	 * @param empId
	 */
	public void deleteByStoreIdAndEmpId(@Param("storeId") long storeId,@Param("empId") long empId);
	
	/**
	 * 根据员工编号和门店编号删除（改为修改）
	 * @param storeId
	 * @param empId
	 */
	public void updateByStoreIdAndEmpId(@Param("storeId") long storeId,@Param("empId") long empId);
	
	/////////
	/**
	 * 根据上一级赏金分配规则删除
	 * @param parentId
	 */
	public void deleteByParentId(@Param("deleteIdList") List<Integer> deleteIdList);
	
	/**
	 * 根据店铺编号和上级编号 删除赏金分配规则
	 * @param parentId
	 */
	public void deleteByParentIdAndStoreId(@Param("storeList") List<BsStoreInfoEntity> storeList);
	
	/**
	 * 修改店铺赏金分配
	 * entity 修改实体
	 * storeId  店铺编号，rewardId： 规则编号，rewardParentId：规则上级编号
	 */
	public void updateStoreRewardByParentId(@Param("entity") BsStoreExtRewardEntity entity,@Param("rewardId") long rewardId,@Param("rewardParentId") int rewardParentId);
	
	

}
