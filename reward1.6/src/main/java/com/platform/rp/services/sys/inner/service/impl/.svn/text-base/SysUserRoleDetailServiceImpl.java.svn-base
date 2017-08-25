/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platform.rp.services.sys.inner.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.sys.core.dao.SysUserRoleDetailDAO;
import com.platform.rp.services.sys.core.dao.entity.SysUserRoleDetailEntity;
import com.platform.rp.services.sys.inner.service.ISysUserRoleDetailService;
import com.platform.rp.services.sys.inner.vo.SysUserRoleDetailVo;

/**
 * 用户角色处理
 * @author TangJun
 *
 */
@Service
public class SysUserRoleDetailServiceImpl implements ISysUserRoleDetailService{

    @Autowired
    SysUserRoleDetailDAO<SysUserRoleDetailEntity> userRoleDetailDAO;
   
    @Override
	public SysUserRoleDetailVo getUserRoleDetailVo(int sysUserId)throws Exception{
    	try {
			return userRoleDetailDAO.getUserRoleDetailVo(sysUserId);
		} catch (Exception e) {
			throw e;
		}
    }
    
}
