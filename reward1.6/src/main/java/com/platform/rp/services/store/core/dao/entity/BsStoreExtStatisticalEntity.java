package com.platform.rp.services.store.core.dao.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the bs_store_ext_statistical database table.
 * 
 */
public class BsStoreExtStatisticalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private int shopassistantCount;	//服务员
	private int shopkeeperCount;	//店长
	private int dividedCount;		//分成人员数量
	private long storeId;
	private Date updateTime;

	public BsStoreExtStatisticalEntity() {
	}

	public int getDividedCount() {
		return dividedCount;
	}

	public void setDividedCount(int dividedCount) {
		this.dividedCount = dividedCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopassistantCount() {
		return shopassistantCount;
	}

	public void setShopassistantCount(int shopassistantCount) {
		this.shopassistantCount = shopassistantCount;
	}

	public int getShopkeeperCount() {
		return shopkeeperCount;
	}

	public void setShopkeeperCount(int shopkeeperCount) {
		this.shopkeeperCount = shopkeeperCount;
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