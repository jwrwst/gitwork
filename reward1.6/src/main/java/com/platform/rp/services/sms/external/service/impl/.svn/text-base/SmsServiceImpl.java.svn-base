package com.platform.rp.services.sms.external.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.sms.external.entity.SmsAuthCode;
import com.platform.rp.services.sms.external.entity.SmsResult;
import com.platform.rp.services.sms.external.exception.SmsException;
import com.platform.rp.services.sms.external.service.ISmsService;
import com.platform.rp.services.sms.external.utils.SDKHttpClient;
import com.platform.rp.services.sms.external.utils.SmsTemplateUtils;
import com.platform.rp.util.DateUtil;
import com.platform.rp.util.Properties;
import com.platform.rp.util.Tools;
import com.platform.rp.util.cache.CacheConstant;
import com.platform.rp.util.cache.ICache;
import com.platform.rp.util.info.codes.DataCode;

@Service
public class SmsServiceImpl implements ISmsService {

    private static final Logger logger = Logger.getLogger(SmsServiceImpl.class);

    @Resource(name="systemProperties")
    private Properties properties;
    
	@Autowired
	 private ICache cache;
	/**
	 * 发送短信验证码
	 * @param phone
	 * @param addserial
	 * @return	0 成功
	 * @throws IOException 
	 * @throws HttpException 
	 */
	public SmsResult sendSmsAuthCode(String phone) throws HttpException, IOException{
		String code = null;
		SmsAuthCode sac = (SmsAuthCode) cache.get(CacheConstant.KEY_SMS,phone);
		if(sac==null){
			code = Tools.stringGen(4);
			sac = new SmsAuthCode();
			sac.setDate(new Date());
			sac.setMessage(code);
			cache.save(CacheConstant.KEY_SMS,phone, sac);
		}else{
			//60秒内只能发送一次
			Date sendDate = sac.getDate();
			int diff = DateUtil.getTimesbetween(sendDate, new Date());
			if(diff<60){
				throw new SmsException(DataCode.SMS_OFTEM.getCode());
			//超过500秒重新生成
			}else if(diff>300){
				code = Tools.stringGen(4);
				sac = new SmsAuthCode();
				sac.setDate(new Date());
				sac.setMessage(code);
				cache.save(CacheConstant.KEY_SMS,phone, sac);
			}
			sac.addCount();
		}
		SmsResult result = null;
		if(sac.getCount()>11){
			result = new SmsResult();
			result.setDescription("已经超过每日发送的最大限额!");
			result.setStatusCode("1000");
		}else{
			//logger.info("["+phone+"]sendSmsAuthCode:"+code==null?sac.getMessage():code);
			System.out.println("["+phone+"]sendSmsAuthCode:"+code==null?sac.getMessage():code);
			result =SmsTemplateUtils.mobileValidate(phone, code==null?sac.getMessage():code);
		}
		return result;
	}

	@Override
	public String getSmsAuthCode(String phone) {//校验验证码
		SmsAuthCode sac = (SmsAuthCode) cache.get(CacheConstant.KEY_SMS,phone);
		if(sac==null){
			throw new SmsException("验证码输入错误或已失效");
		}else{
			Date sendDate = sac.getDate();
			int diff = DateUtil.getTimesbetween(sendDate, new Date());
			if(diff>500){
				throw new SmsException("验证码输入错误或已失效");
			}
			//使用后失效
			//cache.remove(CacheConstant.KEY_SMS,phone);
			return sac.getMessage();
		}
	}

	@Override
	public boolean checkSmsAuthCode(String phone, String code) {
		String sessionCode = getSmsAuthCode(phone);
		boolean retu = sessionCode.equals(code);
		if(retu){
			cache.remove(CacheConstant.KEY_SMS,phone);
		}
		return retu;
	}
}
