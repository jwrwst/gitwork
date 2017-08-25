/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platform.rp.services.sys.inner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.sys.core.dao.SysRoleAuthDAO;
import com.platform.rp.services.sys.core.dao.entity.SysRoleAuthEntity;
import com.platform.rp.services.sys.inner.service.ISysRoleAuthService;
import com.platform.rp.services.sys.inner.vo.SysRoleAuthVo;

/**
 * 用户角色处理
 * @author TangJun
 *
 */
@Service
public class SysRoleAuthServiceImpl implements ISysRoleAuthService{

    @Autowired
    SysRoleAuthDAO<SysRoleAuthEntity> roleAuthDAO;
   
	@Override
	public List<SysRoleAuthVo> getRoleAuthList(int sysRoleId)throws Exception{
    	try {
			return roleAuthDAO.getRoleAuthList(sysRoleId);
		} catch (Exception e) {
			throw e;
		}
    }
	
	public List<SysRoleAuthEntity> getRoleAuthEntityList(int sysRoleId)throws Exception{
		try {
			return roleAuthDAO.getRoleAuthEntityList(sysRoleId);
		} catch (Exception e) {
			throw e;
		}
	}
}
