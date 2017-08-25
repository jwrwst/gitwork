package com.platform.rp.services.statistics.core.dao.entity;
/**
 * 打赏综合统计
 * @author Administrator
 *
 */
public class WapSatisticsEntity {
	private int empId;
	private String storeName;
	private String empName;				//店员名称
	private Integer count;				//次数
	private Double amountCount;			//打赏金额
	private Double shopownerDivided;	//店长分成
	private Double score;				//评分
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Double getAmountCount() {
		return amountCount;
	}
	public void setAmountCount(Double amountCount) {
		this.amountCount = amountCount;
	}
	public Double getShopownerDivided() {
		return shopownerDivided;
	}
	public void setShopownerDivided(Double shopownerDivided) {
		this.shopownerDivided = shopownerDivided;
	}
	
	
	
	
}
