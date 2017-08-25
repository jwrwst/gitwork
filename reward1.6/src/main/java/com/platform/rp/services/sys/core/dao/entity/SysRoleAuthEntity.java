package com.platform.rp.services.sys.core.dao.entity;


/**
 * @see 角色权限表
 * @author TangJun
 *
 */
public class SysRoleAuthEntity {
	private int sysRoleAuthId;
	
	private int roleId;
	
	private int authId;
	
	private String createTime;

	public int getSysRoleAuthId() {
		return sysRoleAuthId;
	}

	public void setSysRoleAuthId(int sysRoleAuthId) {
		this.sysRoleAuthId = sysRoleAuthId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getAuthId() {
		return authId;
	}

	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
}
