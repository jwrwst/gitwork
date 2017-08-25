package com.platform.rp.services.organize.core.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 商户赏分配方案
 * @author tangjun
 * 
 * 2016年3月19日
 */
public class BsMerchantsExtRewardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String orgCode;     //机构编号
	private String allotPlan;	//分成方案
	private BigDecimal percent; //分成百分比
	private long empId;	        //分成人员
	private BigDecimal money;	//分成金额
	private int parentId;     //上一级分配规则编号
	private String remark;    //备注
	private String updateUser;
	private Date updateTime;	
	
	//////////
	private long empName;  //员工姓名
	private int flag = 0;   //增加标志  0  修改，1：增加

	public BsMerchantsExtRewardEntity() {
	}	

	public int getFlag() {
		return flag;
	}



	public void setFlag(int flag) {
		this.flag = flag;
	}



	public int getParentId() {
		return parentId;
	}


	public void setParentId(int parentId) {
		this.parentId = parentId;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public String getAllotPlan() {
		return allotPlan;
	}


	public void setAllotPlan(String allotPlan) {
		this.allotPlan = allotPlan;
	}


	public BigDecimal getPercent() {
		return percent;
	}


	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}


	public long getEmpId() {
		return empId;
	}


	public void setEmpId(long empId) {
		this.empId = empId;
	}


	public BigDecimal getMoney() {
		return money;
	}


	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public long getEmpName() {
		return empName;
	}


	public void setEmpName(long empName) {
		this.empName = empName;
	}


	public String getUpdateUser() {
		return updateUser;
	}


	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}



}