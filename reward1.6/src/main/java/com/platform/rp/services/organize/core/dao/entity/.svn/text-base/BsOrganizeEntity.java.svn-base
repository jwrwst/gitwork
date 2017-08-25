package com.platform.rp.services.organize.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import com.platform.rp.util.DateUtil;
import com.platform.rp.util.StringUtils;


/**
 * The persistent class for the bs_platform_reward database table.
 * 
 */
public class BsOrganizeEntity implements Serializable {
	public static int STAUTS_UNDERWAY = 1;			//1：资料正在审核，
	public static int STAUTS_SUCCESS = 2;			//2：审核通过，
	public static int STAUTS_FAILD = 3;				//3：被驳回
	
	private static final long serialVersionUID = 1L;
	private int orgId;
	private String orgCode;
	private String parentCode;
	private String orgName;
	
	private String qrcode;
	private String m_key;
	private Integer status;
	private String schema;			//根节点，最上一级商户
	private Date createTime;
	private Date updateTime;
	
	private int leap=0; //节点
	
	private String account;
	
	private String formatTime;//格式化日期
	 
	private int last;
	private String remark;			//审核信息
	

	public String getRemark() {
		return remark;
	}

	

	public int getLeap() {
		return leap;
	}



	public void setLeap(int leap) {
		this.leap = leap;
	}



	public String getAccount() {
		return account;
	}



	public void setAccount(String account) {
		this.account = account;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}

	


	public String getFormatTime() {
		if(null!=createTime){
			formatTime = DateUtil.format(createTime, "yyyy-MM-dd HH:mm:ss");
		}
		return formatTime;
	}


	public void setFormatTime(String formatTime) {
		this.formatTime = formatTime;
	}



	public BsOrganizeEntity() {
	}

	

	public int getLast() {
		return last;
	}



	public void setLast(int last) {
		this.last = last;
	}



	public String getSchema() {
		return schema;
	}



	public void setSchema(String schema) {
		this.schema = schema;
	}



	public int getOrgId() {
		return orgId;
	}



	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}



	public String getOrgCode() {
		return orgCode;
	}



	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}



	public String getParentCode() {
		return parentCode;
	}



	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}



	public String getOrgName() {
		return orgName;
	}



	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}



	public String getQrcode() {
		return qrcode;
	}



	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}



	public String getM_key() {
		return m_key;
	}



	public void setM_key(String m_key) {
		this.m_key = m_key;
	}


	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public Date getUpdateTime() {
		return updateTime;
	}



	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}

	

	

}