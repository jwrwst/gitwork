package com.platform.rp.services.wechat.service.impl;

import java.sql.Timestamp;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.platform.rp.services.wechat.common.StringMagusUtils;
import com.platform.rp.services.wechat.common.WebClientDevWrapper;
import com.platform.rp.services.wechat.external.vo.TWeixinToken;
import com.platform.rp.services.wechat.external.vo.WeixinJSTicket;
import com.platform.rp.services.wechat.service.IWeixinTokenService;
import com.platform.rp.util.Properties;


@Service("weixinTokenServiceImpl")
public class WeixinTokenServiceImpl  implements IWeixinTokenService {

	Logger log = Logger.getLogger(WeixinTokenServiceImpl.class);
	
	private static TWeixinToken weixinToken;
	
	private static WeixinJSTicket ticket;

	@Resource(name="systemProperties")
	private Properties properties;
	
	@Override
	public String getWeixinToken(boolean updateNow) {

		TWeixinToken weixinToken = isNeedUpdateTokenFromApplication(updateNow);
		if (weixinToken != null) {
			return weixinToken.getAccessToken();
		} else {
			return "";
		}
	}


	
	/**
	 * 是否需要更新
	 * 
	 * @param updateNow
	 *            是否立刻刷新并保存到application中缓存 true:立刻刷新 false:未超时从数据库中读出
	 */
	private TWeixinToken isNeedUpdateTokenFromApplication(boolean updateNow) {

		if (null == weixinToken) {
			return updateWeixinToken();
		} else {
			Timestamp nowTime = new Timestamp(System.currentTimeMillis());
			try {
				// 得到上次获取的access_token的有效时间
				String expires_in = weixinToken.getExpires_in();
				if (StringUtils.isBlank(expires_in)) {
					// expires_in为空的话 取默认时间7200秒
					expires_in = "7200";
				}
				if (nowTime.getTime() - weixinToken.getUpdatedAt().getTime() >= Long
						.parseLong(expires_in) * 1000 || updateNow) {
					log.info("access_token过期，重新获取！");
					return updateWeixinToken();
				} else {
					return weixinToken;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 请求微信接口，获得最新的access_token
	 * 
	 * @param weixinToken
	 * @return
	 */
	private TWeixinToken updateWeixinToken() {
		String accessToken = "";
		String expires_in = "";
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";
		try {
			requestUrl += "&appid=" + properties.appId;
			requestUrl += "&secret=" + properties.secret;
			HttpClient client = new DefaultHttpClient();
			client = WebClientDevWrapper.wrapClient(client);
			HttpGet get = new HttpGet(requestUrl);
			HttpResponse res = client.execute(get);
			HttpEntity entity = res.getEntity();
			String responseText = EntityUtils.toString(entity);
			Map<String, String> resultMap = StringMagusUtils
					.gsonstrToMap(responseText);
			log.info("获取accessToken，响应的信息为：" + responseText);
			if (StringUtils.isNotBlank((String) resultMap.get("access_token"))) {
				accessToken = resultMap.get("access_token");
				expires_in = resultMap.get("expires_in");
				if (weixinToken == null) {
					weixinToken = new TWeixinToken();
					weixinToken.setCreatedAt(new Timestamp(System
							.currentTimeMillis()));
					log.info("新增accessToken");
				}
				weixinToken.setExpires_in(expires_in);
				weixinToken.setUpdatedAt(new Timestamp(System
						.currentTimeMillis()));
				weixinToken.setAccessToken(accessToken);
				
				log.info("保存accessToken结束");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		return weixinToken;
	}
	@Deprecated
	@Override
	public String getAccessTokenFromWeixin() {
		String accessToken = "";
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&";
		try {
			requestUrl += "&appid=" + properties.appId;
			requestUrl += "&secret=" + properties.secret;
			HttpClient client = new DefaultHttpClient();
			client = WebClientDevWrapper.wrapClient(client);
			HttpGet get = new HttpGet(requestUrl);
			HttpResponse res = client.execute(get);
			HttpEntity entity = res.getEntity();
			String responseText = EntityUtils.toString(entity);
			Map<String, String> resultMap = StringMagusUtils
					.gsonstrToMap(responseText);
			if (resultMap.get("access_token") != null) {
				log.info("获取accessToken成功，响应的信息为：" + responseText);
				accessToken = resultMap.get("access_token").toString();
			} else {
				log.info("获取accessToken失败，错误信息为：" + responseText);
			}
		} catch (Exception e) {
			log.info(e);
			e.printStackTrace();
		}

		return accessToken;
	}

	@Override
	public String sendKefuMessage(Object object) {
		String requestStr = StringMagusUtils.objToGsonstr(object);
		String access_token = this.getWeixinToken(false);

		HttpClient client = new DefaultHttpClient();
		client = WebClientDevWrapper.wrapClient(client);
		String requestUrl = "kefuurl" + "?access_token="
				+ access_token;// 增加菜单请求地址
		try {
			log.info("Send Kefu Message:"+requestStr);
			HttpPost post = new HttpPost(requestUrl);
			StringEntity postentity = new StringEntity(requestStr.toString(),
					"UTF-8");
			post.setEntity(postentity);
			HttpResponse res = client.execute(post);
			HttpEntity entity = res.getEntity();
			String responseText = EntityUtils.toString(entity);
			log.info("发送客服消息第一次响应:" + responseText);
			Map<String, String> gmap = StringMagusUtils
					.gsonstrToMap(responseText);
			if (!StringMagusUtils.judgeErrcode(gmap)) {
				log.info("access_token异常，重新请求接口");
				access_token = this.getWeixinToken(true);
				requestUrl = "kefuurl" + "?access_token="
						+ access_token;// 客服接口地址
				post = new HttpPost(requestUrl);
				post.setEntity(postentity);
				res = client.execute(post);
				entity = res.getEntity();
				responseText = EntityUtils.toString(entity, "utf-8");
				// 第二次查询 无论是否成功都返回响应数据
				log.info("发送客服消息第二次响应:" + responseText);
			}
			return responseText;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String getJsTicket(boolean updateNow) {
		WeixinJSTicket ticket = isNeedUpdateTicketFromApplication(updateNow);
		if (ticket != null) {
			return ticket.getJsTicket();
		} else {
			return "";
		}
	}

	
	/**
	 * 是否需要更新
	 * 
	 * @param updateNow
	 *            是否立刻刷新并保存到application中缓存 true:立刻刷新 false:未超时从数据库中读出
	 */
	private WeixinJSTicket isNeedUpdateTicketFromApplication(boolean updateNow) {

		if (null == ticket) {
			return updateTicket();
		} else {
			Timestamp nowTime = new Timestamp(System.currentTimeMillis());
			try {
				// 得到上次获取的access_token的有效时间
				String expires_in = ticket.getExpires_in();
				if (StringUtils.isBlank(expires_in)) {
					// expires_in为空的话 取默认时间7200秒
					expires_in = "7200";
				}
				if (nowTime.getTime() - ticket.getUpdatedAt().getTime() >= Long
						.parseLong(expires_in) * 1000 || updateNow) {
					log.debug("access_token过期，重新获取！");
					return updateTicket();
				} else {
					return ticket;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 请求微信接口，获得最新的jsapi_ticket
	 * 
	 * @param weixinToken
	 * @return
	 */
	private WeixinJSTicket updateTicket() {
		String jsTicket = "";
		String expires_in ="";
		String access_token = getWeixinToken(false);
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";
		try {
			HttpClient client = new DefaultHttpClient();
			client = WebClientDevWrapper.wrapClient(client);
			HttpGet get = new HttpGet(requestUrl+access_token);
			HttpResponse res = client.execute(get);
			HttpEntity entity = res.getEntity();
			String responseText = EntityUtils.toString(entity);
			Map<String, String> resultMap = StringMagusUtils
					.gsonstrToMap(responseText);
			log.info("获取jsTicket，响应的信息为：" + responseText);
			if (StringUtils.isNotBlank((String) resultMap.get("ticket"))) {
				jsTicket = resultMap.get("ticket");
				expires_in = resultMap.get("expires_in");
				if (ticket == null) {
					ticket = new WeixinJSTicket();
					ticket.setCreatedAt(new Timestamp(System
							.currentTimeMillis()));
					log.info("新增jsTicket");
				}
				ticket.setExpires_in(expires_in);
				ticket.setJsTicket(jsTicket);
				ticket.setUpdatedAt(new Timestamp(System
							.currentTimeMillis()));
				
				log.info("保存jsTicket结束");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		return ticket;
	}

}
