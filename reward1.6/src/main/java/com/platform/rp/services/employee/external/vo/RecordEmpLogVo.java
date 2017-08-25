package com.platform.rp.services.employee.external.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the it_record_emp_log database table.
 * 
 */
@XmlRootElement
public class RecordEmpLogVo implements Serializable {
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
	private String date;     //createTime格式化后的日期（yyyy-MM-dd EEEE)
	private String time;     //createTime格式化后的时间（HH:mm)
	
	private int busdate;
	
	

	public RecordEmpLogVo() {
	}

	
	
	public int getBusdate() {
		return busdate;
	}



	public void setBusdate(int busdate) {
		this.busdate = busdate;
	}



	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPaid() {
		return paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public BigInteger getFid() {
		return fid;
	}

	public void setFid(BigInteger fid) {
		this.fid = fid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

}