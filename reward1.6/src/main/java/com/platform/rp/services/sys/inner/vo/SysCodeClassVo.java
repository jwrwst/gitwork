/**
 * 
 */
package com.platform.rp.services.sys.inner.vo;

import java.util.Date;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.platform.rp.framework.mvcface.vo.formparser.FormParser;

/**
 * <pre>
 * 
 * Created Date： 2015年7月7日 下午5:19:24
 * 
 * Updator：
 * 
 * Updated Date：
 * 
 * Decription：
 * 
 * Version： 1.0.1
 * </pre>
 */
public class SysCodeClassVo implements FormParser {

	private int id;
	private String name;
	private String value;
	private boolean useable;
	private String description;
	private Date updatedtime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isUseable() {
		return useable;
	}

	public void setUseable(boolean useable) {
		this.useable = useable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUpdatedtime() {
		return updatedtime;
	}

	public void setUpdatedtime(Date updatedtime) {
		this.updatedtime = updatedtime;
	}

	/**
	 * 解析参数对象
	 */
	public void parseFormValue(Map<String, String> map, Object obj) {
		SysCodeClassVo classifyVo = (SysCodeClassVo) obj;
		classifyVo.setId(MapUtils.getIntValue(map, "id", 0));
		classifyVo.setName(MapUtils.getString(map, "name", ""));
		classifyVo.setValue(MapUtils.getString(map, "value", ""));
		classifyVo.setUseable(MapUtils.getIntValue(map, "useable", 0) > 0 ? true : false);
		classifyVo.setDescription(MapUtils.getString(map, "description", ""));
	}
}
