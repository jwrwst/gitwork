package com.platform.rp.services.employee.external.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeIncomeBaseInfoVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int employeeID;          //店员ID
	private String empNickname;     //店员昵称
	private String empName;    //店员名称
	private BigDecimal remainMoney;  //可提现金额
	private String headPic;             //店员头像地址
	private BigDecimal totalMoney;   //获得打赏的总金额
	private int evaluateCount;     //评价总次数
	private BigDecimal avgEvaluateScore;   //评星的平均分
	
	private String level;//员工等级
	
	public EmployeeIncomeBaseInfoVo()
	{
		remainMoney = new BigDecimal("0.00");
		totalMoney = new BigDecimal("0.00");
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setEmpNickname(String empNickname) {
		this.empNickname = empNickname;
	}

	public int getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getEmpNickname() {
		return empNickname;
	}
	
	public void setEmptEmpNickname(String empNickname) {
		this.empNickname = empNickname;
	}
	
	public BigDecimal getRemainMoney() {
		return remainMoney;
	}
	
	public void setRemainMoney(BigDecimal remainMoney) {
		this.remainMoney = remainMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public String getHeadPic() {
		return headPic;
	}
	
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEvaluateCount() {
		return evaluateCount;
	}

	public void setEvaluateCount(int evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	public BigDecimal getAvgEvaluateScore() {
		return avgEvaluateScore;
	}

	public void setAvgEvaluateScore(BigDecimal avgEvaluateScore) {
		this.avgEvaluateScore = avgEvaluateScore.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

}