package com.platform.rp.util.info;

import java.io.Serializable;
import java.util.Date;

import com.platform.rp.util.DateUtil;

/**
 * @author tangjun
 * 创建日期：2014年11月18日
 */
public class ResultMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	//返回状态
    private	int status;
	//消息编号
	private String messageCode = "";
	//消息文本
	private String messageText = "";
	//当前时间
	private String servertime = "";

	public ResultMessage() {
	}
	
	public ResultMessage(ResponseCode ret){
		 this.status=ret.getStatus();
		 this.messageCode=ret.getCode();
		 this.messageText=ret.getDesc();
		 this.servertime=DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getServertime() {
		return servertime;
	}

	public void setServertime(String servertime) {
		this.servertime = servertime;
	}

	@Override
	public String toString() {
		return "ResultMessage [status=" + status + ", messageCode="
				+ messageCode + ", messageText=" + messageText
				+ ", servertime=" + servertime + "]";
	}
}
