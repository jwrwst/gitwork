package com.platform.rp.services.store.core.dao.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the bs_store_employee_divided database table.
 * 门店分成人员表
 * 
 */
public class BsStoreEmployeeDividedEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private long empId;
	private int status;
	private long storeId;
	private Date updateTime;

	public BsStoreEmployeeDividedEntity() {
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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