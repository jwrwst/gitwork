/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.platform.rp.framework.mvcface.vo;

import javax.xml.bind.annotation.XmlRootElement;

import com.platform.rp.util.info.ResponseCode;

/**
 * 
 * @author TangJun
 *
 */
@XmlRootElement
public class ResponseResult {

    private String statusCode;
    
    private String message;
    
    private String navTabId="main";
    
    private String rel;
    
    private String callbackType;
    
    private String forwardUrl;
    
    private Object body;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	/**
	 * 
	 * @param code 返回状态
	 * @param navTabId   
	 * @param rel
	 * @param callbackType 
	 * @param forwardUrl
	 */
	public ResponseResult(ResponseCode code, String navTabId,
			String rel, String callbackType, String forwardUrl) {
		super();
		this.statusCode = code.getCode();
		this.message = code.getDesc();
		this.navTabId = navTabId;
		this.rel = rel;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
	}

	public ResponseResult(ResponseCode code) {
		super();
		this.statusCode = code.getCode();
		this.message = code.getDesc();
	}
	
	public ResponseResult(ResponseCode code, Object body) {
		super();
		this.statusCode = code.getCode();
		this.message = code.getDesc();
		this.body = body;
	}

	public ResponseResult() {
		super();
	}
    
	/**
	 * 
	 * @param code 返回状态
	 * @param navTabId
	 * @param rel
	 * @param forwardUrl
	 */
	public ResponseResult(ResponseCode code, String navTabId,
			String rel, String forwardUrl) {
		super();
		this.statusCode = code.getCode();
		this.message = code.getDesc();
		this.navTabId = navTabId;
		this.rel = rel;
		this.forwardUrl = forwardUrl;
	}
	
    
}
