package com.platform.rp.services.employee.external.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeRankDetailVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal dayData;    //当日打赏金额（服务评级）
	private BigDecimal weekData;   //本周打赏总额（服务评级）
	private BigDecimal monthData;  //本月打赏总额（服务评级）
	private String empNickname;   //店员昵称
	private String empName;      //店员名称
	private String jobNumber;      //店员工号
	private Date updateTime;      //最后更新时间
	
	public EmployeeRankDetailVo()
	{
		dayData = new BigDecimal("0.00");
		weekData = new BigDecimal("0.00");
		monthData = new BigDecimal("0.00");
	}
	
	public BigDecimal getDayData() {
		return dayData;
	}
	
	public void setDayData(BigDecimal dayData) {
		this.dayData = dayData.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal getWeekData() {
		return weekData;
	}
	
	public void setWeekData(BigDecimal weekData) {
		this.weekData = weekData.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal getMonthData() {
		return monthData;
	}
	
	public void setMonthData(BigDecimal monthData) {
		this.monthData = monthData.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public String getJobNumber() {
		return jobNumber;
	}
	
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getEmpNickname() {
		return empNickname;
	}

	public void setEmpNickname(String empNickname) {
		this.empNickname = empNickname;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empname) {
		this.empName = empname;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}