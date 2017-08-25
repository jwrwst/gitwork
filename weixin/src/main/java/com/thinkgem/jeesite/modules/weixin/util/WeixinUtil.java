package com.thinkgem.jeesite.modules.weixin.util;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.pojo.Menu;
import com.thinkgem.jeesite.common.util.EmojiFilter;
import com.thinkgem.jeesite.common.util.MyX509TrustManager;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinQrcode;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTemplate;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinUser;
import com.thinkgem.jeesite.modules.weixin.entity.constant.WXConstant;


/**
 * 公众平台通用接口工具类
 * 
 * @author WHW
 * @date 2014年12月3日11:30:39
 */
@Repository
public class WeixinUtil {
	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);

	/**
	 * 获取微信ACCESS_TOKEN
	 * @param appid
	 * @param appSecret
	 * @return
	 */
	public static String getAccessToken(String appid,String appSecret) {
		String accessToken = null;
		try {
			//redis中获取access_token信息
			//accessToken = JedisUtils.get(WXConstant.ACCESS_TOKEN);
		} catch (Exception e) {
			log.debug("Reids报错："+e.getMessage());
		}
		//如果redis中没有access_token则从新获取存放在redis中
		if(StringUtils.isEmpty(accessToken)){
			//为获取token的url赋参数
			String requestUrl = ConfigUtils.ACCESS_TOKEN_URL.replace("APPID", appid).replace(
					"APPSECRET", appSecret);
			JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
			// 如果请求成功
			if (null != jsonObject) {
				try {
					accessToken = jsonObject.getString("access_token");
					JedisUtils.set(WXConstant.ACCESS_TOKEN,accessToken,3600);
					return accessToken;
				} catch (JSONException e) {
					// 获取token失败
					log.error("获取token失败 errcode:{} errmsg:{}",
							jsonObject.getIntValue("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		}
		return accessToken;
	}
	
	/**
	 * 返回openId
	 * @date:2016年8月19日
	 * @user:WHW
	 * @return_type:String
	 */
	public static String getUserOpenId(String appid,String appsecret, String code) {
		String requestUrl = ConfigUtils.GET_OPENID_URL.replace("APPID", appid).replace("APPSECRET", appsecret).replace("CODE", code);
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		// 如果请求成功
		if (null != jsonObject) {
			try {
				return jsonObject.getString("openid");
			} catch (JSONException e) {
				// 获取token失败
				log.error("获取user_token失败 errcode:{} errmsg:{}",jsonObject.getIntValue("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return null;
	}


	/**
	 * 创建菜单
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(Menu menu, String accessToken) {
		int result = 0;
		// 拼装创建菜单的url
		String url = ConfigUtils.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 将菜单对象转换成json字符串
		String jsonMenu = JSONObject.toJSONString(menu);
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);
		if (null != jsonObject) {
			if (0 != jsonObject.getIntValue("errcode")) {
				result = jsonObject.getIntValue("errcode");
				log.error("创建菜单失败 errcode:{} errmsg:{}",jsonObject.getIntValue("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return result;
	}
	
	/**
	 * 获取所有关注者
	 * @date:2014年12月31日
	 * @user:Zhang Jingtao
	 * @return_type:int
	 */
	public static JSONObject getAllForker(String accessToken,String append) {
		// 拼装创建菜单的url
		String url = ConfigUtils.GET_ALLUSER_URL.replace("ACCESS_TOKEN", accessToken)+append;
		// 调用接口创建菜单
		JSONObject jsonObject = httpRequest(url, "GET", "");
		return jsonObject;
	}
	
	/**
	 * 获取微信用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static WeixinUser getWerxinUser(String accessToken,String openId){
		WeixinUser weixinUser = null;
		//为获取微信信息URL赋参数
		String url = ConfigUtils.GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		//发送请求
		JSONObject jsonObject = httpRequest(url, "GET", "");
		if(jsonObject!=null){
			//将返回值赋予实体类weixinuser
			weixinUser = new WeixinUser();
			weixinUser.setAddress(jsonObject.getString("country")+jsonObject.getString("province")+jsonObject.getString("city"));
			weixinUser.setNickname(EmojiFilter.filterEmoji(jsonObject.getString("nickname")));
			weixinUser.setSex(jsonObject.getString("sex"));
			weixinUser.setOpenId(openId);
			weixinUser.setUrl(jsonObject.getString("headimgurl"));
			weixinUser.setCreateTime(new Date(jsonObject.getLongValue("subscribe_time")*1000));
		}
		return weixinUser;
	} 
	
	/**
	 * 获取消息模板列表
	 * @param accessToken 授权凭证
	 * @return
	 */
	public static List<WeixinTemplate> geTemplates(String accessToken){
		List<WeixinTemplate> list = null;
		String url = ConfigUtils.GET_ALL_TEMPLATE.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = httpRequest(url, "GET", "");
		if(jsonObject!=null){
			list = new ArrayList<WeixinTemplate>();
			JSONArray array = jsonObject.getJSONArray("template_list");
			for(int i = 0;i<array.size();i++){
				JSONObject json = array.getJSONObject(i);
				WeixinTemplate template = new WeixinTemplate();
				template.setTemplateId(json.getString("template_id"));
				template.setTitle(json.getString("title"));
				template.setPrimaryIndustry(json.getString("primary_industry"));
				template.setDeputyIndustry(json.getString("deputy_industry"));
				template.setCount(json.getString("content"));
				template.setExample("example");
				list.add(template);
			}
		}
		return list;
	}
	
	/**
	 * 获取微信二维码
	 * @param accessToken 微信接口凭证
	 * @param weixinQrcode 微信二维码实体类
	 * @return
	 */
	public static WeixinQrcode getWXQrCode(String accessToken,WeixinQrcode weixinQrcode){
		String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
		JSONObject json = new JSONObject();
		json.put("expire_seconds", 120);
		json.put("action_name", "QR_SCENE");
		JSONObject sceneJson = new JSONObject();
		sceneJson.put("scene_id", IdGen.randomLong());
		JSONObject actionJson = new JSONObject();
		actionJson.put("scene", sceneJson);
		json.put("action_info", actionJson);
		JSONObject jsonObject = httpRequest(url, "POST", json.toJSONString());
		System.out.println(jsonObject.toJSONString());
		System.out.println("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+jsonObject.getString("ticket"));
		return weixinQrcode;
	}

	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param outputStr
	 *            提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl,
			String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url
					.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);
			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
}
