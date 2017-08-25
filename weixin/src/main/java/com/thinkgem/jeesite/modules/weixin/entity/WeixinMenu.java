/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.TreeEntity;

/**
 * 微信菜单管理Entity
 * @author WHW
 * @version 2016-08-17
 */
public class WeixinMenu extends TreeEntity<WeixinMenu> {
	
	private static final long serialVersionUID = 1L;
	private WeixinMenu parent;		// 父级编号
	private String parentIds;		// 所有父级编号
	private String name;		// 名称
	private String type;		// 类型
	private String val;		// 回调值
	private Integer sort;		// 排序
	
	public WeixinMenu() {
		super();
	}

	public WeixinMenu(String id){
		super(id);
	}

	@JsonBackReference
	public WeixinMenu getParent() {
		return parent;
	}

	public void setParent(WeixinMenu parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=2000, message="所有父级编号长度必须介于 0 和 2000 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	@Length(min=1, max=20, message="名称长度必须介于 1 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="类型长度必须介于 0 和 20 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="回调值长度必须介于 0 和 255 之间")
	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
	@NotNull(message="排序不能为空")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}