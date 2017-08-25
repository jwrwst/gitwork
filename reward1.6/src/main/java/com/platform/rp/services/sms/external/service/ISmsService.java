package com.platform.rp.services.sms.external.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.HttpException;

import com.platform.rp.services.sms.external.entity.SmsResult;

public interface ISmsService {
	/**
	 * 发送短信验证
	 * @param phone
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public SmsResult sendSmsAuthCode(String phone) throws HttpException, IOException;
	/**
	 * 获取短信验证码
	 * @param phone
	 * @return
	 */
	public String getSmsAuthCode(String phone);
	

	/**
	 * 校验验证码是否正确
	 * @param phone
	 * @return 通过true ，不通过false
	 */
	public boolean checkSmsAuthCode(String phone,String code);
	/**
	 * 获取验证码
	 * @param phone
	 * @param addserial
	 * @return	
	 * @throws UnsupportedEncodingException
	 */
//	public String sendendSms(String phone,String addserial) throws UnsupportedEncodingException;
}
