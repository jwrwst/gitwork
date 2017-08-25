package com.platform.rp.services.wechat.external.vo;


import java.sql.Timestamp;

/**
 * TWeixinToken generated by hbm2java
 */

public class TWeixinToken implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;// 主键
	private String accessToken;
	private String expires_in;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public TWeixinToken() {
	}

	public TWeixinToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public TWeixinToken(String accessToken, Timestamp createdAt,
			Timestamp updatedAt) {
		this.accessToken = accessToken;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}