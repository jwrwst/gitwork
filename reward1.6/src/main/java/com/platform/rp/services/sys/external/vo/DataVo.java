package com.platform.rp.services.sys.external.vo;

import java.util.List;

public class DataVo {
/**
 * value: '110000',
	text: '北京市',
	children: [{
 */
	public String value;
	public String text;
	public List<DataVo> children;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<DataVo> getChildren() {
		return children;
	}
	public void setChildren(List<DataVo> children) {
		this.children = children;
	}
	
	
}
