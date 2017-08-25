package com.platform.rp.services.wechat.service;


/**
 * 
 * @author Owner
 *
 */
public interface IWeixinTokenService  {
	
	/**
	 * 获取微信token 
	 * @param updateNow  是否立刻刷新并保存到数据库   true:立刻刷新 false:未超时从数据库中读出
	 * @return
	 */
	public String getWeixinToken(boolean updateNow);
	
	/**
	 * 获取微信access_token
	 * 不建议每次都从微信获取token
	 * @return
	 */
	@Deprecated
	public String getAccessTokenFromWeixin();
	
	/**
	 * 发送客服消息
	 * @param message
	 * @return
	 */
	public String sendKefuMessage(Object object);
	
	/**
	 * 获取jsapi ticket
	 * @param updateNow
	 * @return
	 */
	public String getJsTicket(boolean updateNow);
	
}
