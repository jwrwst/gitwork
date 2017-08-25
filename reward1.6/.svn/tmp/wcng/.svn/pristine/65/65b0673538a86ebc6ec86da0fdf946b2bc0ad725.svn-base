package com.platform.rp.services.store.external.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.store.core.dao.BsStoreExtRewardDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.services.store.external.service.IExStoreExtRewardService;

@Service
public class ExStoreExtRewardServiceImpl implements IExStoreExtRewardService {

	@Autowired
	private BsStoreExtRewardDAO<BsStoreExtRewardEntity> dao;

	@Override
	public void add(BsStoreExtRewardEntity vo) {
		dao.save(vo);
	}

	@Override
	public void update(BsStoreExtRewardEntity vo) {
		dao.update(vo);
	}

	@Override
	public List<BsStoreExtRewardEntity> getStoreExtReward(long storeId) {
		return dao.getStoreExtRewardByStoreId(storeId);
	}
	
}
