package com.platform.rp.services.store.external.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class StoreDynamicInfoVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int dayCount;              //当日打赏（评价）次数
	private int yesterdayCount;        //昨日打赏（评价）次数
	private BigDecimal dayData;        //当日打赏总额(评价均值）
	private BigDecimal yesterdayData;  //昨日打赏总额（评价均值）

	public StoreDynamicInfoVo() {
		dayData = new BigDecimal("0.00");
		yesterdayData = new BigDecimal("0.00");
	}

	public int getDayCount() {
		return dayCount;
	}

	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	public int getYesterdayCount() {
		return yesterdayCount;
	}

	public void setYesterdayCount(int yesterdayCount) {
		this.yesterdayCount = yesterdayCount;
	}

	public BigDecimal getDayData() {
		return dayData;
	}

	public void setDayData(BigDecimal dayData) {
		this.dayData = dayData.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getYesterdayData() {
		return yesterdayData;
	}

	public void setYesterdayData(BigDecimal yesterdayData) {
		this.yesterdayData = yesterdayData.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

}