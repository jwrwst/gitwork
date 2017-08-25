/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信配置Entity
 * @author WHW
 * @version 2016-08-18
 */
public class WeixinConfig extends DataEntity<WeixinConfig> {
	
	private static final long serialVersionUID = 1L;
	private String configDescribe;		// 描述
	private String configKey;		// 键
	private String configValue;		// 值
	
	public WeixinConfig() {
		super();
	}

	public WeixinConfig(String id){
		super(id);
	}

	@Length(min=0, max=40, message="描述长度必须介于 0 和 40 之间")
	public String getConfigDescribe() {
		return configDescribe;
	}

	public void setConfigDescribe(String configDescribe) {
		this.configDescribe = configDescribe;
	}
	
	@Length(min=0, max=64, message="键长度必须介于 0 和 64 之间")
	public String getConfigKey() {
		return configKey;
	}

	public void setConfigKey(String configKey) {
		this.configKey = configKey;
	}
	
	@Length(min=0, max=128, message="值长度必须介于 0 和 128 之间")
	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	
}