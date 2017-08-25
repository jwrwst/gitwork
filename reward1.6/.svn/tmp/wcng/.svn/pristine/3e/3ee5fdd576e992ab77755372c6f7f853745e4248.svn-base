package com.platform.rp.services.store.core.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the it_reward_info database table.
 * 
 */
public class ItRewardInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private Date createdtime;
	private String customerId;
	private long empId;
	private int getType;
	private String orderNum;
	private String paytime;
	private double rewardAmount;
	private int rewardType;
	private int status;
	private long storeId;
	
	private int busdate;
	
	

	public int getBusdate() {
		return busdate;
	}

	public void setBusdate(int busdate) {
		this.busdate = busdate;
	}

	public ItRewardInfoEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public int getGetType() {
		return getType;
	}

	public void setGetType(int getType) {
		this.getType = getType;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getPaytime() {
		return paytime;
	}

	public void setPaytime(String paytime) {
		this.paytime = paytime;
	}

	public double getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(double rewardAmount) {
		BigDecimal bdVal = new BigDecimal(rewardAmount); 
		this.rewardAmount = bdVal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public int getRewardType() {
		return rewardType;
	}

	public void setRewardType(int rewardType) {
		this.rewardType = rewardType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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



	
}