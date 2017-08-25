package com.platform.rp.services.wechat.service.impl;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.threadPool.ThreadPoolProvider;
import com.platform.rp.services.wechat.external.common.wxtemplate.WxTemplateMessage;
import com.platform.rp.services.wechat.service.IWeixinTokenService;
import com.platform.rp.services.wechat.service.IWxSendMessageService;
import com.platform.rp.util.HttpsClient;
import com.platform.rp.util.Properties;


@Service
public class WxSendMessageServiceImpl implements IWxSendMessageService{

	Logger logger = Logger.getLogger(WxSendMessageServiceImpl.class);
	
	@Resource(name="systemProperties")
	private Properties properties;
	
	@Resource(name="weixinTokenServiceImpl")
	private IWeixinTokenService weixinTokenService;
	
	/**
	 * 收入提醒
	 */
	public void sendIncomeTemplate(JSONObject data,String toUser){
		try {
			String token = weixinTokenService.getWeixinToken(false);
			String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
			
			WxTemplateMessage message=new WxTemplateMessage();
			message.setTemplate_id("XThkwfRCJdYJDR0mheVef5L0My_sDlzzX21XOBxwyPU");
			message.setTopcolor("#FF0000");
			message.setUrl(properties.host+properties.wxLogin+"?state=2");			
			message.setTouser(toUser);
			message.setData(data);

			String infoStr = HttpsClient.sendHttpsPost(url, JSONObject.fromObject(message).toString());
			logger.info("推送信息："+infoStr);
			
		} catch (Exception e) {
			logger.error("推送错误:",e);
		}
	}
	
	/**
	 * 审核提醒
	 */
	public void sendAuditTemplate(final JSONObject data,final String toUser){
		
			ThreadPoolProvider.getInstance().execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						String token = weixinTokenService.getWeixinToken(false);
						String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
						
						WxTemplateMessage message=new WxTemplateMessage();
						message.setTemplate_id("_O3wCdWSOYko51UL109RiFgitiLFIcAcpsZibMFNL0s");
						message.setTopcolor("#FF0000");
						message.setUrl(properties.host+properties.wxUrl5+"?state=5");			
						message.setTouser(toUser);
						message.setData(data);

						String infoStr = HttpsClient.sendHttpsPost(url, JSONObject.fromObject(message).toString());
						logger.info("推送信息："+infoStr);
						
					} catch (Exception e) {
						logger.error("推送错误:",e);
					}
					
				}
			});
		
	}
}
