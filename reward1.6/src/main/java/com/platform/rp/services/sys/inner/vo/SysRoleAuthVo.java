package com.platform.rp.services.sys.inner.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @see 角色权限表
 * @author TangJun
 * 
 */
@XmlRootElement
public class SysRoleAuthVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private int sysRoleAuthId;

	private int roleId;

	private int authId;

	private String createTime;

	// 功能
	private String authCode;

	private String authName;

	private int parentId;

	private String authType;

	private String authUrl;

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

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public SysRoleAuthVo(int sysRoleAuthId, int roleId, int authId, String createTime) {
		super();
		this.sysRoleAuthId = sysRoleAuthId;
		this.roleId = roleId;
		this.authId = authId;
		this.createTime = createTime;
	}

	public SysRoleAuthVo(int sysRoleAuthId, int roleId, int authId, String createTime, String authCode, String authName, int parentId, String authType,
			String authUrl) {
		super();
		this.sysRoleAuthId = sysRoleAuthId;
		this.roleId = roleId;
		this.authId = authId;
		this.createTime = createTime;
		this.authCode = authCode;
		this.authName = authName;
		this.parentId = parentId;
		this.authType = authType;
		this.authUrl = authUrl;
	}

	public SysRoleAuthVo() {
		super();
	}

}
