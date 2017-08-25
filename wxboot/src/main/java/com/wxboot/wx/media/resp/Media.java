package com.wxboot.wx.media.resp;

import java.io.Serializable;

/**
 * 家家帮
 * @author wang
 * 2017年4月8日 下午1:20:12
 * 类描述：
 */
public class Media implements Serializable{
//	• 图片（image）: 2M，支持bmp/png/jpeg/jpg/gif格式 
//	• 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式 
//	• 视频（video）：10MB，支持MP4格式 
//	• 缩略图（thumb）：64KB，支持JPG格式 
//	• 媒体文件在后台保存时间为3天，即3天后media_id失效
	
	public static final Media dao = new Media();
	private static final long serialVersionUID = 1L;
	private String type;  //媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）  
	private String mediaId;  //媒体文件上传后，获取时的唯一标识  
	private String createdAt;  //媒体文件上传时间戳 
	/**初始化数据*/
	public Media initMedia(String mediaId, String msgType, String createdAt){
		Media media = new Media();
		media.setType(msgType);
		media.setMediaId(mediaId);
		media.setCreatedAt(createdAt);
		return media;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

//	private String accessToken; //  是  调用接口凭证  
//	private String type;	//  是  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）  
//	private String media;	//  是  form-data中媒体文件标识，有filename、filelength、content-type等信息  
//	//getter and setter
//	public String getAccessToken() {
//		return accessToken;
//	}
//	public void setAccessToken(String accessToken) {
//		this.accessToken = accessToken;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public String getMedia() {
//		return media;
//	}
//	public void setMedia(String media) {
//		this.media = media;
//	}

}

 