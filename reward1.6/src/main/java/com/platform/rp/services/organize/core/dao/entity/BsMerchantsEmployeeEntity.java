package com.platform.rp.services.organize.core.dao.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 商户与员工关系表
 * @author tangjun
 * 
 * 2016年3月19日
 */
public class BsMerchantsEmployeeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String orgCode;
	private long empId;	
	private String empName;
	private Date updateTime;
	
	private String jobNumber;
	
	private int status;
	
	private String orgName;
	
	

	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public BsMerchantsEmployeeEntity() {
	}


	public String getJobNumber() {
		return jobNumber;
	}


	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
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


	public long getEmpId() {
		return empId;
	}


	public void setEmpId(long empId) {
		this.empId = empId;
	}


	public Date getUpdateTime() {
		return updateTime;
	}


	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	
	

	


}