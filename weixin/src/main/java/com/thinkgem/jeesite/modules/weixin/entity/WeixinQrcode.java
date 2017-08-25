/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信二维码Entity
 * @author WHW
 * @version 2016-08-18
 */
public class WeixinQrcode extends DataEntity<WeixinQrcode> {
	
	private static final long serialVersionUID = 1L;
	private String ticket;		// 二维码ticket
	private Long sceneId;		// 场景值ID
	private String type;		// 二维码类型
	private String expireSeconds;		// 过期时间（s）
	private String url;		// 解析地址
	private Date endDate;		// 过期时间
	
	public WeixinQrcode() {
		super();
	}

	public WeixinQrcode(String id){
		super(id);
	}

	@Length(min=0, max=255, message="二维码ticket长度必须介于 0 和 255 之间")
	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public Long getSceneId() {
		return sceneId;
	}

	public void setSceneId(Long sceneId) {
		this.sceneId = sceneId;
	}
	
	@Length(min=0, max=20, message="二维码类型长度必须介于 0 和 20 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=10, message="过期时间长度必须介于 0 和 10 之间")
	public String getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(String expireSeconds) {
		this.expireSeconds = expireSeconds;
	}
	
	@Length(min=0, max=255, message="解析地址长度必须介于 0 和 255 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}