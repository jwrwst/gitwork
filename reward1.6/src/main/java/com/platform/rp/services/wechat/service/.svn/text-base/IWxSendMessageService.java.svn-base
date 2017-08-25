package com.platform.rp.services.wechat.service;

import net.sf.json.JSONObject;

public interface IWxSendMessageService {

	/**
	 * 收入提醒
	 * data:
	 * {{first.DATA}}
	 * 收入类型：{{keyword1.DATA}}
	 * 收入金额：{{keyword2.DATA}}
	 * 到账时间：{{keyword3.DATA}}
	 * {{remark.DATA}}
	 */
	public void sendIncomeTemplate(JSONObject data,String toUser);
	
	/**
	 * 审核提醒
	 * data:
	 * {{first.DATA}}
	 * 审核内容：{{keyword1.DATA}}
	 * 审核结果：{{keyword2.DATA}}
	 * {{remark.DATA}}
	 */
	public void sendAuditTemplate(JSONObject data,String toUser);

}