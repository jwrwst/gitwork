package com.platform.rp.services.store.core.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the bs_store_ext_reward database table.
 * 
 */
public class BsStoreExtRewardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String allotPlan;
	private String couponName;
	private long empId;
	private int isable;
	private BigDecimal money;
	private BigDecimal percent;
	private String qrcodeUrl;
	private long storeId;
	private Date updateTime;
	private String updateUser;
	
	private int parentId;     //上一级分配规则编号
	private String remark;    //备注
	
	private BigDecimal rewardMoney;
	

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

	public BigDecimal getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(BigDecimal rewardMoney) {
		this.rewardMoney = rewardMoney;
	}

	public BsStoreExtRewardEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAllotPlan() {
		return allotPlan;
	}

	public void setAllotPlan(String allotPlan) {
		this.allotPlan = allotPlan;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public int getIsable() {
		return isable;
	}

	public void setIsable(int isable) {
		this.isable = isable;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getPercent() {
		return percent;
	}

	public void setPercent(BigDecimal percent) {
		this.percent = percent;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}



}