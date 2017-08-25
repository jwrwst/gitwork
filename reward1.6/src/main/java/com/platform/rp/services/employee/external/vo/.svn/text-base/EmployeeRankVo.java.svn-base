package com.platform.rp.services.employee.external.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class EmployeeRankVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal rankData;   //打赏金额（评价值）
	private String empNickname;   //店员名称
	private String empName;         //店员名称
	private Timestamp createTime;   //打赏（评价）时间
	private String evaluate;       //评价内容
	private String date;     //createTime格式化后的日期（yyyy-MM-dd EEEE)
	private String time;     //createTime格式化后的时间（HH:mm)
	
	public EmployeeRankVo()
	{
		rankData = new BigDecimal("0.00");
	}
	
	public BigDecimal getRankData() {
		return rankData;
	}
	
	public void setRankData(BigDecimal rankData) {
		this.rankData = rankData.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getEmpNickname() {
		return empNickname;
	}

	public void setEmpNickname(String empNickname) {
		this.empNickname = empNickname;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
}