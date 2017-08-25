package com.platform.rp.services.store.external.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.store.core.dao.BsStoreAuthDetailDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreAuthDetailEntity;
import com.platform.rp.services.store.external.service.IExStoreAuthDetailService;

@Service
public class ExStoreAuthDetailServiceImpl implements IExStoreAuthDetailService {

	@Autowired
	private BsStoreAuthDetailDAO<BsStoreAuthDetailEntity> dao;

	@Override
	public void add(BsStoreAuthDetailEntity vo) {
		dao.save(vo);
	}

	@Override
	public List<BsStoreAuthDetailEntity> getStoreAuthDetailByStoreId(
			long storeId) {
		return dao.getStoreAuthDetailByStoreId(storeId);
	}

	@Override
	public void update(BsStoreAuthDetailEntity vo) {
		dao.update(vo);
	}

}
