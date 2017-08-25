package com.platform.rp.services.sys.inner.service;

import java.util.List;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.sys.core.dao.entity.SysUserInfoEntity;
import com.platform.rp.services.sys.core.dao.entity.SysUserRoleDetailEntity;
import com.platform.rp.services.sys.inner.vo.SysUserInfoVo;

public interface ISysUserInfoService {

	/**
	 * @see 用户列表分页
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page getSysUserInfoPage(Page page, String userName, String roleName) throws Exception;

	/**
	 * @see 获取用户列表
	 * @return
	 * @throws Exception
	 */
	public abstract List<SysUserInfoVo> getSysUserInfoList() throws Exception;

	/**
	 * @see 根据用户名和密码获取用户详细信息
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public abstract SysUserInfoVo getSysUserInfo(String name, String password) throws Exception;

	/**
	 * @see 修改密码
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public abstract boolean updatePwd(String name, String password) throws Exception;

	public abstract void save(SysUserInfoEntity entity, SysUserRoleDetailEntity userRoleDetailEntity);

	public void save(SysUserInfoEntity entity);

	public abstract void delete(int id) throws Exception;

	public abstract SysUserInfoEntity get(int id);

	/**
	 * <pre>
	 * 验证外来用户详细信息
	 * @param name     用户名
	 * @param password md5加密过后的密码
	 * @return SysUserInfoVo
	 * @throws Exception
	 * </pre>
	 */
	public abstract SysUserInfoVo authenticationExtrinsicUser(String name, String password) throws Exception;

}