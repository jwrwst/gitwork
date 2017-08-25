/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 微信消息模板Entity
 * @author WHW
 * @version 2016-08-18
 */
public class WeixinTemplate extends DataEntity<WeixinTemplate> {
	
	private static final long serialVersionUID = 1L;
	private String templateId;		// 模板ID
	private String title;		// 模板标题
	private String primaryIndustry;		// 模板所属行业的一级行业
	private String deputyIndustry;		// 模板所属行业的二级行业
	private String count;		// 模板内容
	private String example;		// 模板示例
	
	public WeixinTemplate() {
		super();
	}

	public WeixinTemplate(String id){
		super(id);
	}

	@Length(min=0, max=64, message="模板ID长度必须介于 0 和 64 之间")
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
	@Length(min=0, max=64, message="模板标题长度必须介于 0 和 64 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=64, message="模板所属行业的一级行业长度必须介于 0 和 64 之间")
	public String getPrimaryIndustry() {
		return primaryIndustry;
	}

	public void setPrimaryIndustry(String primaryIndustry) {
		this.primaryIndustry = primaryIndustry;
	}
	
	@Length(min=0, max=64, message="模板所属行业的二级行业长度必须介于 0 和 64 之间")
	public String getDeputyIndustry() {
		return deputyIndustry;
	}

	public void setDeputyIndustry(String deputyIndustry) {
		this.deputyIndustry = deputyIndustry;
	}
	
	@Length(min=0, max=1000, message="模板内容长度必须介于 0 和 1000 之间")
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	@Length(min=0, max=1000, message="模板示例长度必须介于 0 和 1000 之间")
	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}
	
}