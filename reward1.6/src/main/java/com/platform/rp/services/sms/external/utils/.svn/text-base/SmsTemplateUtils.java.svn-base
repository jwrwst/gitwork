package com.platform.rp.services.sms.external.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.platform.rp.services.sms.external.entity.SmsResult;

/**
 * 
 * @author zhangsheng
 *
 */
public class SmsTemplateUtils {
	
	public static SmsResult mobileValidate(String mobile,String key) throws HttpException, IOException{
		SmsResult result = Sms.secretSend("【顶顶科技】验证码为"+key+"，请在页面中输入以完成验证，有问题请致电4000685456",mobile);
		return result;
	}
}
