package com.platform.rp.services.store.external.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

//店铺打赏统计
public class StoreRewardStatisticVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int perDayCount;              //每日打赏次数
	private BigDecimal perDayMoney;       //每日打赏总额
	private int perDayEmpCount;           //每日收到打赏的店员数
	private Timestamp rewardTime;       //打赏日期
	private int busDate;          //结算日期

	public StoreRewardStatisticVo() {
		perDayMoney = new BigDecimal("0.00");
	}

	public int getPerDayCount() {
		return perDayCount;
	}

	public void setPerDayCount(int perDayCount) {
		this.perDayCount = perDayCount;
	}

	public BigDecimal getPerDayMoney() {
		return perDayMoney;
	}

	public void setPerDayMoney(BigDecimal perDayMoney) {
		this.perDayMoney = perDayMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getPerDayEmpCount() {
		return perDayEmpCount;
	}

	public void setPerDayEmpCount(int perDayEmpCount) {
		this.perDayEmpCount = perDayEmpCount;
	}

	public Timestamp getRewardTime() {
		return rewardTime;
	}

	public void setRewardTime(Timestamp rewardTime) {
		this.rewardTime = rewardTime;
	}

	public int getBusDate() {
		return busDate;
	}

	public void setBusDate(int busDate) {
		this.busDate = busDate;
	}
	
	
}