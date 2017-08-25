package com.platform.rp.services.customer.core.dao.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the bs_customer_info database table.
 * 
 */

public class BsCustomerInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String birthday;
	private Date createdtime;
	private String customerId;
	private String source;
	private String gender;
	private String headUrl;
	private String memberLevel;
	private String mobilePhone;
	private String name;
	private String openId;

	public BsCustomerInfoEntity() {
	}


	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}

    

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getCreatedtime() {
		return createdtime;
	}



	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}



	public String getBirthday() {
		return this.birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}



	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getHeadUrl() {
		return this.headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}


	public String getMemberLevel() {
		return this.memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}


	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}