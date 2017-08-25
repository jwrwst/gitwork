package com.platform.rp.services.sys.inner.service;

import java.util.List;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.sys.core.dao.entity.SysRoleInfoEntity;
import com.platform.rp.services.sys.inner.vo.SysRoleInfoVo;

public interface ISysRoleInfoService {

	/**
	 * @see 用户列表分页
	 * @param page
	 * @param roleName
	 * @return
	 * @throws Exception
	 */
	public abstract Page getSysRoleInfoPage(Page page, String roleName) throws Exception;

	/**
	 * @see 获取用户列表
	 * @return
	 * @throws Exception
	 */
	public abstract List<SysRoleInfoVo> getSysRoleInfoList() throws Exception;

	public abstract void save(SysRoleInfoEntity entity, String authList) throws Exception;

	public abstract void delete(int id) throws Exception;

	public abstract SysRoleInfoEntity get(int id);

}