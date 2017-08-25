package com.platform.rp.services.organize.external.service;

import java.util.List;

import com.platform.rp.services.organize.core.dao.entity.BsMerchantsExtRewardEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsRewardStoreEntity;
import com.platform.rp.services.organize.external.vo.BsMerchantsVo;


public interface IExBsMerchantsRewardService {

	public List<BsMerchantsExtRewardEntity> getInfo(String orgCode) throws Exception;
	
	public void save(BsMerchantsVo vo)
			throws Exception;
	
	public List<BsMerchantsRewardStoreEntity> getRewardStoreList(String orgCode)throws Exception;
	
}
