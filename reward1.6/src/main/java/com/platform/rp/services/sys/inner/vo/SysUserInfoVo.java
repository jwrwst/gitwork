package com.platform.rp.services.sys.inner.vo;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.platform.rp.framework.mvcface.vo.formparser.FormParser;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.RegularExpression;

/**
 * @see 用户表
 * @author TangJun
 * 
 */
@XmlRootElement
public class SysUserInfoVo implements FormParser {
	private int sysUserId;

	private String userName;

	private String realName;

	private String pwd;

	private String status;

	private String createTime;

	private String updTime;

	private String createUserId;

	private String updUserId;

	// 角色
	private int empid;

	private int sysRoleId;

	private String roleName;

	private String empPostion;

	private int empLevel;// 等级

	private String softaccount;

	private String softpwd;

	// 系统登录标示，供其他系统调用，相当于钥匙。
	private String ssoid;

	public String getSsoid() {
		return ssoid;
	}

	public void setSsoid(String ssoid) {
		this.ssoid = ssoid;
	}

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

	public int getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(int sysUserId) {
		this.sysUserId = sysUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public SysUserInfoVo(int sysUserId, String userName, String realName, String pwd, String status, String createTime, String updTime, String createUserId,
			String updUserId, int sysRoleId, String roleName) {
		super();
		this.sysUserId = sysUserId;
		this.userName = userName;
		this.realName = realName;
		this.pwd = pwd;
		this.status = status;
		this.createTime = createTime;
		this.updTime = updTime;
		this.createUserId = createUserId;
		this.updUserId = updUserId;
		this.sysRoleId = sysRoleId;
		this.roleName = roleName;
	}

	public SysUserInfoVo(int sysUserId, String userName, String realName, String pwd, String status, String createTime, String updTime, String createUserId,
			String updUserId) {
		super();
		this.sysUserId = sysUserId;
		this.userName = userName;
		this.realName = realName;
		this.pwd = pwd;
		this.status = status;
		this.createTime = createTime;
		this.updTime = updTime;
		this.createUserId = createUserId;
		this.updUserId = updUserId;
	}

	public SysUserInfoVo() {
		super();
	}

	public String getEmpPostion() {
		return empPostion;
	}

	public void setEmpPostion(String empPostion) {
		this.empPostion = empPostion;
	}

	public int getEmpLevel() {
		return empLevel;
	}

	public void setEmpLevel(int empLevel) {
		this.empLevel = empLevel;
	}

	public String getSoftaccount() {
		return softaccount;
	}

	public void setSoftaccount(String softaccount) {
		this.softaccount = softaccount;
	}

	public String getSoftpwd() {
		return softpwd;
	}

	public void setSoftpwd(String softpwd) {
		this.softpwd = softpwd;
	}

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	/**
	 * 解析参数对象
	 */
	public void parseFormValue(Map<String, String> map, Object obj) {
		SysUserInfoVo sysUserInfoVo = (SysUserInfoVo) obj;
		String sysUserId = map.get("sysUserId");
		String userName = map.get("userName");
		String realName = map.get("realName");
		String pwd = map.get("pwd");

		String sysRoleId = map.get("sysRoleId");
		String empid = map.get("sysEmployeeInfoVo.empid");
		if (!ApplicationUtils.isEmpty(empid) && RegularExpression.checkZS(empid)) {
			sysUserInfoVo.setEmpid(Integer.parseInt(empid));
		}
		if (!ApplicationUtils.isEmpty(sysUserId) && RegularExpression.checkZS(sysUserId)) {
			sysUserInfoVo.setSysUserId(Integer.parseInt(sysUserId));
		}
		if (!ApplicationUtils.isEmpty(sysRoleId) && RegularExpression.checkZS(sysRoleId)) {
			sysUserInfoVo.setSysRoleId(Integer.parseInt(sysRoleId));
		}
		if (!ApplicationUtils.isEmpty(userName) && RegularExpression.checkNum_Eng(userName)) {
			sysUserInfoVo.setUserName(userName);
		}
		if (!ApplicationUtils.isEmpty(realName)) {
			sysUserInfoVo.setRealName(realName);
		}
		if (!ApplicationUtils.isEmpty(pwd) && RegularExpression.checkNum_Eng(pwd)) {
			sysUserInfoVo.setPwd(pwd);
		}
	}

}