package com.platform.rp.services.sys.inner.service;

import java.util.List;

import com.platform.rp.services.sys.core.dao.entity.SysRoleAuthEntity;
import com.platform.rp.services.sys.inner.vo.SysRoleAuthVo;

public interface ISysRoleAuthService {

	public abstract List<SysRoleAuthVo> getRoleAuthList(int sysRoleId) throws Exception;

	public List<SysRoleAuthEntity> getRoleAuthEntityList(int sysRoleId) throws Exception;

}