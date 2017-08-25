package com.wxboot.wx.framework.config;

public class BaseURL {
	
	/**获取access_token URL[GET请求]*/
	public static String getAccessTokenUrl(){
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
		url.append("&appid="+Setting.appId);
		url.append("&secret="+Setting.appsecret);
		return url.toString();
	}
	
	/**获取媒体信息 [POST/FORM 请求]*/
	public static String getImageMediaInfo(String msgType, String accessToken){
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/media/upload?");
		url.append("access_token=" + accessToken);
		url.append("&type=" + msgType);
		return url.toString();
	}
	
	/**获取创建菜单URL [POST请求]*/
	public static String getCreateMenuUrl(String accessToken){
		StringBuffer url = new StringBuffer();
		url.append("https://api.weixin.qq.com/cgi-bin/menu/create?");
		url.append("access_token="+accessToken);
		return url.toString();
	}
	
	
}
