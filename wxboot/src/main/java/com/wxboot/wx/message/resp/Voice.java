package com.wxboot.wx.message.resp;

import java.io.Serializable;

/**
 * 家家帮
 * @author wang
 * 2017年4月10日 下午5:39:17
 * 类描述：
 */
public class Voice implements Serializable{
	private static final long serialVersionUID = 1L;
	public String MediaId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	 
}

 