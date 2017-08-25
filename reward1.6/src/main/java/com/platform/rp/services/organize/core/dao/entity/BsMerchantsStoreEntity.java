package com.platform.rp.services.organize.core.dao.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 商户与店铺关系表
 * @author tangjun
 * 
 * 2016年3月19日
 */
public class BsMerchantsStoreEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String orgCode;
	private long storeId;	
	private String storeName;
	private Date updateTime;
	
	private String schema;
	
	private int status;
	
	private int storeStatus;

	public int getStoreStatus() {
		return storeStatus;
	}


	public void setStoreStatus(int storeStatus) {
		this.storeStatus = storeStatus;
	}


	public BsMerchantsStoreEntity() {
	}


	public String getSchema() {
		return schema;
	}



	public void setSchema(String schema) {
		this.schema = schema;
	}



	public String getStoreName() {
		return storeName;
	}



	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
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


	

}