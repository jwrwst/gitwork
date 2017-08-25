package com.platform.rp.services.sys.core.dao.entity;


/**
 * @see 权限表
 * @author TangJun
 *
 */
public class SysAuthInfoEntity {
	private int sysAuthId;
	
	private String authName;
	
	private String authCode;
	
	private int parentId;
	
	private String authType;
	
	private String authUrl;
	
	private String createTime;
	
	private String updTime;
	
	private String createUserId;
	
	private String updUserId;
	
	private int sort;

	public int getSysAuthId() {
		return sysAuthId;
	}

	public void setSysAuthId(int sysAuthId) {
		this.sysAuthId = sysAuthId;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdTime() {
		return updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdUserId() {
		return updUserId;
	}

	public void setUpdUserId(String updUserId) {
		this.updUserId = updUserId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	

	
	
	
}
