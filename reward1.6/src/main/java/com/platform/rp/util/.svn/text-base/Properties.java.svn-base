/*
 * @(#)Properties.java
 *
 * Copyright (c) 2013-2014 ZhongShiAn
 */
package com.platform.rp.util;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 属性配置
 * 
 * @author tangjun
 *
 * 2015年6月12日
 *
 */
@Component("systemProperties")
public class Properties {

    //web
    public @Value("#{system['web.url']}") String webUrl;
    public @Value("#{system['host']}") String host;

    //短地址   二维码
    public @Value("#{system['short.url']}") String shortUrl; 
    
    //图片路径
    public @Value("#{system['uploadfile']}") String uploadfile;
    
    //是否执行任务
    public @Value("#{system['isExecuteTask']}") String isExecuteTask;
      
    //支付宝账号信息
    public @Value("#{system['alipay.gateway']}") String alipayGateway;
    public @Value("#{system['alipay.notifyUrl']}") String alipayNotifyUrl;
    public @Value("#{system['alipay.callBackUrl']}") String alipayCallBackUrl;
    public @Value("#{system['alipay.merchantUrl']}") String alipayMerchantUrl;
    public @Value("#{system['alipay.seller_email']}") String alipaySellerEmail;
    
    //微信账号信息        
    public @Value("#{system['wx.appId']}") String appId;
    public @Value("#{system['wx.secret.key']}") String secret;
    public @Value("#{system['wx.oauthurl']}") String wxOauthurl;
    public @Value("#{system['wx.authorize']}") String wxAuthoriz;
    public @Value("#{system['wx.userinfo']}") String wxUserinfo;
    public @Value("#{system['wxpay.partnerKey']}") String wxpayPartnerKey;
    public @Value("#{system['wxpay.partner']}") String wxpayPartner;
    public @Value("#{system['wxpay.notifyUrl']}") String wxpayNotifyUrl;
    
    //转向地址
    public @Value("#{system['wx.login']}") String wxLogin;
    public @Value("#{system['wx.url1']}") String wxUrl1;
    public @Value("#{system['wx.url2']}") String wxUrl2;
    public @Value("#{system['wx.url3']}") String wxUrl3;
    public @Value("#{system['wx.url4']}") String wxUrl4;
    public @Value("#{system['wx.url5']}") String wxUrl5;
    public @Value("#{system['wx.url6']}") String wxUrl6;
    public @Value("#{system['wx.url61']}") String wxUrl61;//绑定分成人员
    public @Value("#{system['wx.url62']}") String wxUrl62;//绑定管理人员
    public @Value("#{system['wx.url7']}") String wxUrl7;
    public @Value("#{system['wx.url8']}") String wxUrl8;//区域管理
    public @Value("#{system['wx.url9']}") String wxUrl9;//授权登录管理
    
    //短信参数
    public @Value("#{system['sms.sn']}") String smsSn;
    public @Value("#{system['sms.key']}") String smsKey;
    public @Value("#{system['sms.password']}") String smsPassword;
    public @Value("#{system['sms.url']}") String smsUrl;
    
    //
    public static Map<String, String> menuMap = null;    
    public Map<String, String> getMenuInstance(){
    	if(null == menuMap){
    		menuMap = new HashMap<String, String>();
    		menuMap.put("url1", wxUrl1);
    		menuMap.put("url2", wxUrl2);
    		menuMap.put("url3", wxUrl3);
    		menuMap.put("url4", wxUrl4);
    		menuMap.put("url5", wxUrl5);
    		menuMap.put("url6", wxUrl6);
    		menuMap.put("url61", wxUrl61);
    		menuMap.put("url62", wxUrl62);
    		menuMap.put("url7", wxUrl7);
    		menuMap.put("url8", wxUrl8);
    		menuMap.put("url9", wxUrl9);
    	}
    	return menuMap;
    }
    
	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}


	
		
}
