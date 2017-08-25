package com.wxboot.wx.message.resp;

/**
 * 家家帮
 * @author wang
 * 2017年4月2日 下午12:26:18
 * 类描述：
 */
public class TextMessage extends BaseMessage{
	private static final long serialVersionUID = 1L;
	//properties
	private String Content;  //文本消息内容  
	private String MsgId; //消息id，64位整型
	
	//getter and setter
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	
	
}

 