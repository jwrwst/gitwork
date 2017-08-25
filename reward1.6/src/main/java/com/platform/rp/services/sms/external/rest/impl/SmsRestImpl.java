package com.platform.rp.services.sms.external.rest.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.qrcodeinfo.external.rest.impl.ExItFileInfoRestImpl;
import com.platform.rp.services.sms.external.entity.SmsResult;
import com.platform.rp.services.sms.external.exception.SmsException;
import com.platform.rp.services.sms.external.rest.ISmsRest;
import com.platform.rp.services.sms.external.service.ISmsService;
import com.platform.rp.util.Constant;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.info.codes.DataCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class SmsRestImpl implements ISmsRest{

	private static final  Logger logger=Logger.getLogger(ExItFileInfoRestImpl.class);
	@Autowired
	ISmsService smsService;

	@Context
	protected HttpServletRequest request;
	
	public RestfulResult wapSend(String phone){
		logger.debug("**********33333*******************");
		return send(phone);
	}
		@Override
	public RestfulResult send(String phone) {
		try {
			logger.debug("*****************************");
			if(StringUtils.isEmpty(phone)){
				return RestfulResultProvider.buildSuccessResult(new ResultData("请输入手机号!"));
			}
			SmsResult resutl = smsService.sendSmsAuthCode(phone);
			return RestfulResultProvider.buildSuccessResult(new ResultData(resutl));
		} catch (SmsException e) {
			String message = e.getMessage();
			logger.error(e.getMessage(),e);
			//太频繁
			if(DataCode.SMS_OFTEM.getCode().equals(message)){
				return RestfulResultProvider.buildCodeResult(DataCode.SMS_OFTEM);
			}else {
				return RestfulResultProvider.buildCodeResult(DataCode.SMS_ERROR);
			}
		}catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(),e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		} catch (HttpException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}

	/**
	 * 短信验证码信息验证
	 */
	public RestfulResult validateInfo(String inputCode,String randomCodeInput){
		try {
			//验证上一步图片验证码是否正确
			String randomString=(String)request.getSession().getAttribute("randomString");
			if(!randomString.equalsIgnoreCase(randomCodeInput)){
				return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);
			}
			//验证短信验证码
			String phone=(String)request.getSession().getAttribute("phone");

			if(!Constant.IS_DEBUG){
				String msg=smsService.getSmsAuthCode(phone);
				if (!msg.equals(inputCode)) {
					return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);
				}
			}	
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestfulResultProvider.buildFailureResult(new ResultData(e.getMessage()));
		}
	}
	/**
	 * 短信验证码信息验证
	 */
	public RestfulResult validateCode(Map<String,String> entity){
		try {
			String inputCode=entity.get("msgCode");
			String phone=entity.get("phone");
			if(!Constant.IS_DEBUG){
				//验证
				String msg=smsService.getSmsAuthCode(phone);
				if (!msg.equals(inputCode)) {
					//return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);
					return RestfulResultProvider.buildFailureResult(new ResultData(CommonCode.CODE_EXECEPTION.getDesc()));
				}
			}
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestfulResultProvider.buildFailureResult(new ResultData(e.getMessage()));
		}
	}
}
