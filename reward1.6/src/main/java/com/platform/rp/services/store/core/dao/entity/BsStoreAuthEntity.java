package com.platform.rp.services.store.core.dao.entity;

import java.io.Serializable;


/**
 * The persistent class for the bs_store_auth database table.
 * 
 */
public class BsStoreAuthEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String authCode;
	private String authName;
	private String groupCode;

	public BsStoreAuthEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}


	

}