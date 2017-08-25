package com.platform.rp.services.store.external.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.store.core.dao.BsStoreAuthDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreAuthEntity;
import com.platform.rp.services.store.external.service.IExStoreAuthService;

@Service
public class ExStoreAuthServiceImpl implements IExStoreAuthService {

	@Autowired
	private BsStoreAuthDAO<BsStoreAuthEntity> bsStoreAuthDAO;

	public List<BsStoreAuthEntity> getList(String groupCode)throws Exception{
		try {
			return bsStoreAuthDAO.getList(groupCode);
		} catch (Exception e) {
			throw e;
		}
	}

}
