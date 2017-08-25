/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platform.rp.services.sys.inner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.sys.core.dao.SysUserInfoDAO;
import com.platform.rp.services.sys.core.dao.SysUserRoleDetailDAO;
import com.platform.rp.services.sys.core.dao.entity.SysUserInfoEntity;
import com.platform.rp.services.sys.core.dao.entity.SysUserRoleDetailEntity;
import com.platform.rp.services.sys.inner.service.ISysUserInfoService;
import com.platform.rp.services.sys.inner.vo.SysUserInfoVo;
import com.platform.rp.services.sys.inner.vo.SysUserRoleDetailVo;
import com.platform.rp.util.spec.MD5;

/**
 * 
 * @author TangJun
 * 
 */
@Service
public class SysUserInfoServiceImpl implements ISysUserInfoService {

	@Autowired
	SysUserInfoDAO<SysUserInfoEntity> sysUserInfoDAO;

	@Autowired
	SysUserRoleDetailDAO<SysUserRoleDetailEntity> userRoleDetailDAO;

	/**
	 * @see 用户列表分页
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page getSysUserInfoPage(Page page, String userName, String roleName) throws Exception {
		try {
			int start = page.getPageSize()*(page.getPageNo()-1);
    		int end = page.getPageSize()*page.getPageNo();
			List<SysUserInfoVo> volist = sysUserInfoDAO.getSysUserInfoPage(start, end, userName, roleName);

			SysUserRoleDetailVo userRoleVo;
			for (SysUserInfoVo sysUserInfoVo : volist) {
				userRoleVo = userRoleDetailDAO.getUserRoleDetailVo(sysUserInfoVo.getSysUserId());
				if (userRoleVo != null) {
					sysUserInfoVo.setRoleName(userRoleVo.getRoleName());
				} else {
					sysUserInfoVo.setRoleName("已删除");
				}
			}
			page.setResult(volist);

			return page;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @see 获取用户列表
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SysUserInfoVo> getSysUserInfoList() throws Exception {
		try {
			return sysUserInfoDAO.getSysUserInfoList();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @see 根据用户名和密码获取用户详细信息
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Override
	public SysUserInfoVo getSysUserInfo(String name, String password) throws Exception {
		try {
			MD5 md = new MD5();
			password = md.md5(password);
			return sysUserInfoDAO.getSysUserInfo(name, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @see 修改密码
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updatePwd(String name, String password) throws Exception {
		try {
			MD5 md = new MD5();
			password = md.md5(password);
			return sysUserInfoDAO.updatePwd(name, password);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public void save(SysUserInfoEntity entity, SysUserRoleDetailEntity userRoleDetailEntity) {
		// 更新用户
		if (entity.getSysUserId() > 0) 
			sysUserInfoDAO.update(entity);
		 else 
			sysUserInfoDAO.save(entity);
			
		// 更新用户角色
		if(userRoleDetailEntity.getUserRoleId() > 0 && userRoleDetailEntity.getUserId() > 0)
			userRoleDetailDAO.update(userRoleDetailEntity);
		else{
			userRoleDetailEntity.setUserId(entity.getSysUserId());
			userRoleDetailDAO.save(userRoleDetailEntity);
		}
	}

	@Override
	public void save(SysUserInfoEntity entity) {
		// 更新用户
		if (entity.getSysUserId() > 0)
			sysUserInfoDAO.update(entity);
		else
			sysUserInfoDAO.save(entity);

	}

	@Override
	public void delete(int id) throws Exception {
		try {
			// 删除用户角色关系
			SysUserRoleDetailVo vo = userRoleDetailDAO.getUserRoleDetailVo(id);
			if (null != vo) {
				userRoleDetailDAO.delete(vo.getUserRoleId());
			}
			// 删除用户
			sysUserInfoDAO.delete(id);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public SysUserInfoEntity get(int id) {
		return sysUserInfoDAO.get(id);
	}

	@Override
	public SysUserInfoVo authenticationExtrinsicUser(String name, String password) throws Exception {
		try {
			return sysUserInfoDAO.authenticationExtrinsicUser(name, password);
		} catch (Exception e) {
			throw e;
		}
	}
}
