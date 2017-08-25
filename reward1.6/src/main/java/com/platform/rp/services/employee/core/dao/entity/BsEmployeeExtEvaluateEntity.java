package com.platform.rp.services.employee.core.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the bs_employee_ext_evaluate database table.
 * 
 */
@XmlRootElement
public class BsEmployeeExtEvaluateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private int dayCount;
	private BigDecimal dayScore;
	private long empId;
	private int lastmonthCount;
	private BigDecimal lastmonthScore;
	private int lastweekCount;
	private BigDecimal lastweekScore;
	private int monthCount;
	private BigDecimal monthScore;
	private int status;
	private long storeId;
	private int totalCount;
	private BigDecimal totalScore;
	private Date updateTime;
	private int weekCount;
	private BigDecimal weekScore;
	private int yesterdayCount;
	private BigDecimal yesterdayScore;

	public BsEmployeeExtEvaluateEntity() {
	}



	public int getDayCount() {
		return dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	public BigDecimal getDayScore() {
		return dayScore;
	}

	public void setDayScore(BigDecimal dayScore) {
		this.dayScore = dayScore.setScale(2, BigDecimal.ROUND_HALF_UP);
	}


	public int getLastmonthCount() {
		return lastmonthCount;
	}

	public void setLastmonthCount(int lastmonthCount) {
		this.lastmonthCount = lastmonthCount;
	}

	public BigDecimal getLastmonthScore() {
		return lastmonthScore;
	}

	public void setLastmonthScore(BigDecimal lastmonthScore) {
		this.lastmonthScore = lastmonthScore.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getLastweekCount() {
		return lastweekCount;
	}

	public void setLastweekCount(int lastweekCount) {
		this.lastweekCount = lastweekCount;
	}

	public BigDecimal getLastweekScore() {
		return lastweekScore;
	}

	public void setLastweekScore(BigDecimal lastweekScore) {
		this.lastweekScore = lastweekScore.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(int monthCount) {
		this.monthCount = monthCount;
	}

	public BigDecimal getMonthScore() {
		return monthScore;
	}

	public void setMonthScore(BigDecimal monthScore) {
		this.monthScore = monthScore.setScale(2, BigDecimal.ROUND_HALF_UP);
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

	public BigDecimal getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore.setScale(2, BigDecimal.ROUND_HALF_UP);
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

	public BigDecimal getWeekScore() {
		return weekScore;
	}

	public void setWeekScore(BigDecimal weekScore) {
		this.weekScore = weekScore.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getYesterdayCount() {
		return yesterdayCount;
	}

	public void setYesterdayCount(int yesterdayCount) {
		this.yesterdayCount = yesterdayCount;
	}

	public BigDecimal getYesterdayScore() {
		return yesterdayScore;
	}

	public void setYesterdayScore(BigDecimal yesterdayScore) {
		this.yesterdayScore = yesterdayScore.setScale(2, BigDecimal.ROUND_HALF_UP);
	}


	
}