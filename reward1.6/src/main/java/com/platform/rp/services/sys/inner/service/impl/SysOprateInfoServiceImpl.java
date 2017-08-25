package com.platform.rp.services.sys.inner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.sys.core.dao.SysOprateInfoDAO;
import com.platform.rp.services.sys.core.dao.entity.SysOprateInfoEntity;
import com.platform.rp.services.sys.inner.service.ISysOprateInfoService;

@Service
public class SysOprateInfoServiceImpl implements ISysOprateInfoService {

	@Autowired
	SysOprateInfoDAO<SysOprateInfoEntity> sysOprateInfoDAO;
	
	@Override
	public void add(SysOprateInfoEntity entity) {
		sysOprateInfoDAO.save(entity);
	}

}
