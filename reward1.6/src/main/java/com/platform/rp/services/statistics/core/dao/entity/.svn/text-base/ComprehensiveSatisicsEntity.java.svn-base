package com.platform.rp.services.statistics.core.dao.entity;

import com.platform.rp.util.excel.EColumn;

/**
 * 打赏综合统计
 * @author Administrator
 *
 */
public class ComprehensiveSatisicsEntity {
	private int id;
	private int storeId;
	private String dateVal;
	private String storeName;
	private String empName;				//店员名称
	private Integer empCount;			//员工数
	private Integer cusCount;			//人数
	private Integer count;				//次数
	private Double amountCount;			//金额
	private Double shopownerDivided;	//店长分成
	private Double merDivided;			//区域经理分成
	private Double empDivided;			//分成人员
	//概况
	private Integer type;
	private Double receivable;			//应收金额
	private String remark;				//备注
	
	//0：打赏，1：评价，2：分成
	public String getType() {
		if(type==null){
			return "";
		}
		switch (type) {
		case 0:
			return "打赏";
		case 1:
			return "评价";
		default:
			return "分成";
		}
	}
	
	
	public Double getMerDivided() {
		return merDivided;
	}


	public void setMerDivided(Double merDivided) {
		this.merDivided = merDivided;
	}


	public Double getEmpDivided() {
		return empDivided;
	}


	public void setEmpDivided(Double empDivided) {
		this.empDivided = empDivided;
	}


	public void setType(Integer type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getDateVal() {
		return dateVal;
	}
	public void setDateVal(String dateVal) {
		this.dateVal = dateVal;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Integer getEmpCount() {
		return empCount;
	}
	public void setEmpCount(Integer empCount) {
		this.empCount = empCount;
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
	public Integer getCusCount() {
		return cusCount;
	}
	public void setCusCount(Integer cusCount) {
		this.cusCount = cusCount;
	}
	public Double getReceivable() {
		return receivable;
	}
	public void setReceivable(Double receivable) {
		this.receivable = receivable;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
}
