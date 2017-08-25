package com.platform.rp.services.wechat.service;

public interface IWxPromotionService {

	/**
	 * 企业付款
	 * @param orderId
	 * @param openId
	 * @param amount
	 * @param desc
	 */
	public abstract boolean promationPay(String orderId, String openId,
			String amount, String desc);

}