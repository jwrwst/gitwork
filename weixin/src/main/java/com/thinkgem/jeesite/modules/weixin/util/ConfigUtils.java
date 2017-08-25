package com.thinkgem.jeesite.modules.weixin.util;

import com.thinkgem.jeesite.common.config.Global;

public class ConfigUtils {

	/**
	 * 微信token令牌
	 */
	public final static String WEIXINTOKEN = Global.getConfig("WX_TOKEN");

	/**
	 * 公众号id
	 */
	public final static String APPID = Global.getConfig("WX_APPID");
	
	/**
	 * 公众号秘钥
	 */
	public final static String APPSECRET = Global.getConfig("WX_APPSECRET");
	
	/**
	 * 获取access_token的接口地址（GET） 限10000（次/天）
	 */
	public final static String ACCESS_TOKEN_URL = Global.getConfig("WX_ACCESS_TOKEN_URL");
	
	/**
	 * 获取用户openid
	 */
	public final static String GET_OPENID_URL = Global.getConfig("WX_GET_OPENID_URL");
	
	/**
	 * 菜单创建（POST）限100（次/天）
	 */
	public final static String MENU_CREATE_URL = Global.getConfig("WX_MENU_CREATE_URL");
	
	/**
	 * 获取1W名关注者URL
	 */
	public final static String GET_ALLUSER_URL = Global.getConfig("WX_GET_ALLUSER_URL");
	
	/**
	 * 获取用户信息
	 */
	public final static String GET_USERINFO_URL = Global.getConfig("WX_GET_USERINFO_URL");
	
	/**
	 * 获取消息模板
	 */
	public final static String GET_ALL_TEMPLATE = Global.getConfig("WX_GET_ALL_TEMPLATE");
	
}
