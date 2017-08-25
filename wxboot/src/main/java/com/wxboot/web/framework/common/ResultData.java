package com.wxboot.web.framework.common;

import java.io.Serializable;
import java.util.Date;


public class ResultData<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	
	private String message;
	
	private T data;
	
	private Date sysTime;
	
	public ResultData() {
		this.sysTime = new Date();
	}

	public ResultData(String code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
		this.sysTime = new Date();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}
 }
