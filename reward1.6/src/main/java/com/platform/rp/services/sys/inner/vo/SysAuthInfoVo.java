package com.platform.rp.services.sys.inner.vo;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @see 权限表
 * @author TangJun
 * 
 */
@XmlRootElement
public class SysAuthInfoVo {
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

	private String ischeck = "false";

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

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	// public SysAuthInfoVo(int sysAuthId, String authName, String authCode,
	// int parentId, String authType, String authUrl, String createTime,
	// String updTime, String createUserId, String updUserId, int sort) {
	// super();
	// this.sysAuthId = sysAuthId;
	// this.authName = authName;
	// this.authCode = authCode;
	// this.parentId = parentId;
	// this.authType = authType;
	// this.authUrl = authUrl;
	// this.createTime = createTime;
	// this.updTime = updTime;
	// this.createUserId = createUserId;
	// this.updUserId = updUserId;
	// this.sort = sort;
	// }

}
