package com.platform.rp.util.info;

/**
 * @author tangjun
 * 创建日期：2014年11月28日
 */
public interface ResponseCode {

	Short getStatus();
	
	String getCode();
	
	String getDesc();
	
	void setDesc(String desc); 
}
