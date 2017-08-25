package com.wxboot.wx.message.resp;

import java.io.Serializable;

/**
 * 家家帮
 * @author wang
 * 2017年4月8日 下午1:58:12
 * 类描述：
 */
public class Image implements Serializable{
	private static final long serialVersionUID = 1L;
	private String MediaId; // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	
	
	
}

 