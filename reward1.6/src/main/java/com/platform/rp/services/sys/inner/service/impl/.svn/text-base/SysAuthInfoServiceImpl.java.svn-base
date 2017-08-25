/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platform.rp.services.sys.inner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.sys.core.dao.SysAuthInfoDAO;
import com.platform.rp.services.sys.core.dao.entity.SysAuthInfoEntity;
import com.platform.rp.services.sys.inner.service.ISysAuthInfoService;
import com.platform.rp.services.sys.inner.vo.SysAuthInfoVo;

/**
 * @see 角色处理
 * @author TangJun
 *
 */
@Service
public class SysAuthInfoServiceImpl implements ISysAuthInfoService{

    @Autowired
    SysAuthInfoDAO<SysAuthInfoEntity> sysAuthInfoDAO;

    
    /**
     * @see 获取列表
     * @return
     * @throws Exception
     */
	@Override
	public List<SysAuthInfoVo> getSysAuthInfoList()throws Exception {
        try {
			return sysAuthInfoDAO.getSysAuthInfoList();
		} catch (Exception e) {
			throw e;
		}
    }

	
}
