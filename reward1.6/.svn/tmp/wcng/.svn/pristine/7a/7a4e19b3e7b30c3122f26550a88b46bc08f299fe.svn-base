package com.platform.rp.services.store.external.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

//单条每日评价统计
public class StoreEvaluateStatisticVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int perDayCount;         //每日评价人数
	private BigDecimal perDayScore;  //每日评价均值
	private Timestamp evaluateTime;  //评价日期
	private int busDate;          //结算日期

	public StoreEvaluateStatisticVo() {
		perDayScore = new BigDecimal("0.00");
	}

	public int getPerDayCount() {
		return perDayCount;
	}

	public void setPerDayCount(int perDayCount) {
		this.perDayCount = perDayCount;
	}

	public BigDecimal getPerDayScore() {
		return perDayScore;
	}

	public void setPerDayScore(BigDecimal perDayScore) {
		this.perDayScore = perDayScore.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public Timestamp getTimstamp() {
		return evaluateTime;
	}

	public void setTimstamp(Timestamp timstamp) {
		this.evaluateTime = timstamp;
	}

	public int getBusDate() {
		return busDate;
	}

	public void setBusDate(int busDate) {
		this.busDate = busDate;
	}
	
	
}