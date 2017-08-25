package com.platform.rp.services.sys.inner.vo;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.platform.rp.framework.mvcface.vo.formparser.FormParser;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.RegularExpression;

/**
 * @see 角色表
 * @author TangJun
 * 
 */
@XmlRootElement
public class SysRoleInfoVo implements FormParser {
	private int sysRoleId;

	private String roleName;

	private String createTime;

	private String updTime;

	private String createUserId;

	private String updUserId;

	private String authList;

	public int getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(int sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdTime() {
		return updTime;
	}

	public void setUpdTime(String updTime) {
		this.updTime = updTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdUserId() {
		return updUserId;
	}

	public void setUpdUserId(String updUserId) {
		this.updUserId = updUserId;
	}

	public String getAuthList() {
		return authList;
	}

	public void setAuthList(String authList) {
		this.authList = authList;
	}

	public SysRoleInfoVo(int sysRoleId, String roleName, String createTime, String updTime, String createUserId, String updUserId) {
		super();
		this.sysRoleId = sysRoleId;
		this.roleName = roleName;
		this.createTime = createTime;
		this.updTime = updTime;
		this.createUserId = createUserId;
		this.updUserId = updUserId;
	}

	public SysRoleInfoVo() {
		super();
	}

	/**
	 * 解析参数对象
	 */
	public void parseFormValue(Map<String, String> map, Object obj) {
		SysRoleInfoVo sysRoleInfoVo = (SysRoleInfoVo) obj;
		String sysRoleId = map.get("sysRoleId");
		String roleName = map.get("roleName");
		String authList = map.get("authList");

		if (!ApplicationUtils.isEmpty(sysRoleId) && RegularExpression.checkZS(sysRoleId)) {
			sysRoleInfoVo.setSysRoleId(Integer.parseInt(sysRoleId));
		}
		if (!ApplicationUtils.isEmpty(roleName)) {
			sysRoleInfoVo.setRoleName(roleName);
		}
		if (!ApplicationUtils.isEmpty(authList)) {
			sysRoleInfoVo.setAuthList(authList);
		}
	}

}
