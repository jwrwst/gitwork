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
 * Created Date： 2015年7月7日 下午5:19:17
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
public class SysCodeInfoVo implements FormParser {

	private int id;
	private int classifyid;
	private String name;
	private String value;
	private boolean useable;
	private int sorter;
	private String description;
	private Date updatedtime;

	private String classname;
	private String classvalue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClassifyid() {
		return classifyid;
	}

	public void setClassifyid(int classifyid) {
		this.classifyid = classifyid;
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

	public int getSorter() {
		return sorter;
	}

	public void setSorter(int sorter) {
		this.sorter = sorter;
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

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getClassvalue() {
		return classvalue;
	}

	public void setClassvalue(String classvalue) {
		this.classvalue = classvalue;
	}

	/**
	 * 解析参数对象
	 */
	public void parseFormValue(Map<String, String> map, Object obj) {
		SysCodeInfoVo codeVo = (SysCodeInfoVo) obj;
		codeVo.setId(MapUtils.getIntValue(map, "id", 0));
		codeVo.setClassifyid(MapUtils.getIntValue(map, "classifyid", 0));
		codeVo.setName(MapUtils.getString(map, "name", ""));
		codeVo.setValue(MapUtils.getString(map, "value", ""));
		codeVo.setUseable(MapUtils.getIntValue(map, "useable", 0) > 0 ? true : false);
		codeVo.setSorter(MapUtils.getIntValue(map, "sorter", 0));
		codeVo.setDescription(MapUtils.getString(map, "description", ""));
	}
}
