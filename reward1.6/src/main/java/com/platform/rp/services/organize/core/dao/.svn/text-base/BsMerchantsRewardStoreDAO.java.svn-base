package com.platform.rp.services.organize.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsRewardStoreEntity;

/**
 * 
 * @author TangJun
 *
 */
public interface BsMerchantsRewardStoreDAO<T> extends BaseDAO<T>{

	
	public void saveList(List<BsMerchantsRewardStoreEntity> list);
	
	public void deleteList(@Param("orgCode") String orgCode);
	
	public List<BsMerchantsRewardStoreEntity> getList(@Param("orgCode") String orgCode);
	
	
}
