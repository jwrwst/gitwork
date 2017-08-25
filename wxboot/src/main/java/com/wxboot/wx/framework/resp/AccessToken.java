package com.wxboot.wx.framework.resp;

import java.io.Serializable;

/**
 * 家家帮
 * @author wang
 * 2017年4月8日 上午11:16:27
 * 类描述：
 */
public class AccessToken implements Serializable{
	private static final long serialVersionUID = 1L;
	private String accessToken;	//获取到的凭证  
	private long expiresIn;		//凭证有效时间，单位：秒 
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}

 