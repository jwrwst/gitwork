package com.platform.rp.services.store.external.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class StoreRankVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rankCount;         //打赏（评价）次数
	private BigDecimal rankData;   //打赏总额（评价均值）
	private String empNickname;   //店员名称
	private int employeeID;        //店员ID
	private String empName;         //店员名称
	private Date updateTime;     //数据更新时间
	private int alterRankCount;               //备选打赏（评价）次数      （对于昨日/上周/上月数据，需要同时取出当日/本周/本月数据，然后根据更新时间判断使用哪个数据）
	private BigDecimal alterRankData;   //备选打赏总额（评价均值）（对于昨日/上周/上月数据，需要同时取出当日/本周/本月数据，然后根据更新时间判断使用哪个数据）
	
	public StoreRankVo()
	{
		rankData = new BigDecimal("0.00");
		alterRankData = new BigDecimal("0.00");
	}
	
	public int getRankCount() {
		return rankCount;
	}
	
	public void setRankCount(int rankCount) {
		this.rankCount = rankCount;
	}
	
	public BigDecimal getRankData() {
		return rankData;
	}
	
	public void setRankData(BigDecimal rankData) {
		this.rankData = rankData.setScale(2, BigDecimal.ROUND_HALF_UP);
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

	public void setEmpNickname(String empNickname) {
		this.empNickname = empNickname;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getAlterRankCount() {
		return alterRankCount;
	}

	public void setAlterRankCount(int alterRankCount) {
		this.alterRankCount = alterRankCount;
	}

	public BigDecimal getAlterRankData() {
		return alterRankData;
	}

	public void setAlterRankData(BigDecimal alterRankData) {
		this.alterRankData = alterRankData.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
}