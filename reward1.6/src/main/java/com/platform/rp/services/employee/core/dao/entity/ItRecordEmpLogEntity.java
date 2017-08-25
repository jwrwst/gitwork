package com.platform.rp.services.employee.core.dao.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the it_record_emp_log database table.
 * 
 */
@XmlRootElement
public class ItRecordEmpLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private Date createTime;
	private long empId;
	private BigInteger fid;
	private String nickname;
	private String paid;
	private String receivable;
	private int recordType;
	private String remark;
	private long storeId;
	
	private int busdate;//日结日期

	public ItRecordEmpLogEntity() {
	}
	
	
	
	public int getBusdate() {
		return busdate;
	}



	public void setBusdate(int busdate) {
		this.busdate = busdate;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public BigInteger getFid() {
		return fid;
	}

	public void setFid(BigInteger fid) {
		this.fid = fid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getReceivable() {
		return receivable;
	}

	public void setReceivable(String receivable) {
		this.receivable = receivable;
	}

	public int getRecordType() {
		return recordType;
	}

	public void setRecordType(int recordType) {
		this.recordType = recordType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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