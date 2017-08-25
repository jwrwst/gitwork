package com.platform.rp.services.employee.core.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the bs_employee_ext_reward database table.
 * 
 */
@XmlRootElement
public class BsEmployeeExtRewardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private int dayCount;
	private BigDecimal dayMoney;
	private long empId;
	private int lastmonthCount;
	private BigDecimal lastmonthMoney;
	private int lastweekCount;
	private BigDecimal lastweekMoney;
	private int monthCount;
	private BigDecimal monthMoney;
	private int status;
	private long storeId;
	private int totalCount;
	private BigDecimal totalMoney;
	private Date updateTime;
	private int weekCount;
	private BigDecimal weekMoney;
	private int yesterdayCount;
	private BigDecimal yesterdayMoney;
	
	private int cashCount;
	private BigDecimal cashMoney;	
	

	public int getCashCount() {
		return cashCount;
	}

	public void setCashCount(int cashCount) {
		this.cashCount = cashCount;
	}

	public BigDecimal getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(BigDecimal cashMoney) {
		this.cashMoney = cashMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BsEmployeeExtRewardEntity() {
	}

	public int getDayCount() {
		return dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	public BigDecimal getDayMoney() {
		return dayMoney;
	}

	public void setDayMoney(BigDecimal dayMoney) {
		this.dayMoney = dayMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getLastmonthCount() {
		return lastmonthCount;
	}

	public void setLastmonthCount(int lastmonthCount) {
		this.lastmonthCount = lastmonthCount;
	}

	public BigDecimal getLastmonthMoney() {
		return lastmonthMoney;
	}

	public void setLastmonthMoney(BigDecimal lastmonthMoney) {
		this.lastmonthMoney = lastmonthMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getLastweekCount() {
		return lastweekCount;
	}

	public void setLastweekCount(int lastweekCount) {
		this.lastweekCount = lastweekCount;
	}

	public BigDecimal getLastweekMoney() {
		return lastweekMoney;
	}

	public void setLastweekMoney(BigDecimal lastweekMoney) {
		this.lastweekMoney = lastweekMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(int monthCount) {
		this.monthCount = monthCount;
	}

	public BigDecimal getMonthMoney() {
		return monthMoney;
	}

	public void setMonthMoney(BigDecimal monthMoney) {
		this.monthMoney = monthMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getWeekCount() {
		return weekCount;
	}

	public void setWeekCount(int weekCount) {
		this.weekCount = weekCount;
	}

	public BigDecimal getWeekMoney() {
		return weekMoney;
	}

	public void setWeekMoney(BigDecimal weekMoney) {
		this.weekMoney = weekMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getYesterdayCount() {
		return yesterdayCount;
	}

	public void setYesterdayCount(int yesterdayCount) {
		this.yesterdayCount = yesterdayCount;
	}

	public BigDecimal getYesterdayMoney() {
		return yesterdayMoney;
	}

	public void setYesterdayMoney(BigDecimal yesterdayMoney) {
		this.yesterdayMoney = yesterdayMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}


}