package com.platform.rp.util.exception;

import com.platform.rp.util.info.ResponseCode;
import com.platform.rp.util.info.codes.CommonCode;


/**
 * 
 * @author tangjun
 * 创建日期：2014年11月24日
 */
public class DefaultException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ResponseCode returnCode=CommonCode.SYS_EXECEPTION;
	
	public DefaultException(){
	}
	
	public DefaultException(ResponseCode returnCode) {
		this.returnCode=returnCode;
	}
	
	public DefaultException(String errorMsg){
		returnCode=CommonCode.PLATFORM_OTHER_ERROR;
		returnCode.setDesc(errorMsg);
	}

	public ResponseCode getReturnCode() {
		return returnCode;
	}

}
