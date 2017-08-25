package com.wxboot.web.framework.exception;

import com.wxboot.web.framework.common.CodeEnum;

/**
 * 家家帮
 * @author wang
 * 2017年4月7日 下午2:05:01
 * 类描述：
 */
public class SysException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	//properties
	private String code;
	private String message;
	//constructor
	public SysException(String code, String message) {
		this.code = code;
		this.message = message;
	}
	public SysException(CodeEnum codeEnum) {
		this.code = codeEnum.getCode();
		this.message = codeEnum.getMessage();
	}
	//getter and setter
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

 