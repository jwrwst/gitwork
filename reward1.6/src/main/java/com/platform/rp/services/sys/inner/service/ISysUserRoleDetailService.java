package com.platform.rp.services.sys.inner.service;

import com.platform.rp.services.sys.inner.vo.SysUserRoleDetailVo;

public interface ISysUserRoleDetailService {

	public abstract SysUserRoleDetailVo getUserRoleDetailVo(int sysUserId) throws Exception;

}