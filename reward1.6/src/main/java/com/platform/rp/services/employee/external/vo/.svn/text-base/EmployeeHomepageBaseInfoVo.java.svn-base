package com.platform.rp.services.employee.external.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class EmployeeHomepageBaseInfoVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private long employeeID;          //店员ID
	private long storeID;      //店铺ID
	private String empNickname;     //店员昵称
	private String empName;    //店员名称
	private String headPic;          //店员头像地址
	private String storeName;        //店员所属店铺名称
	private BigDecimal rewardMoney;  //默认打赏金额
	private int isUpdate;            //默认打赏金额是否可修改
	private String signature;     //个性签名
	private String qrCode;        //二维码
	private String authCode;    //店铺权限
	private String gratitude;    //感谢语
	private String wish;        //祝福语
	
	public EmployeeHomepageBaseInfoVo()
	{
		rewardMoney = new BigDecimal("0.00");
	}
	
	
	
	public String getWish() {
		return wish;
	}



	public void setWish(String wish) {
		this.wish = wish;
	}



	public long getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getHeadPic() {
		return headPic;
	}
	
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public BigDecimal getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(BigDecimal rewardMoney) {
		this.rewardMoney = rewardMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getEmpNickname() {
		return empNickname;
	}

	public void setEmpNickname(String empNickname) {
		this.empNickname = empNickname;
	}

	public long getStoreID() {
		return storeID;
	}

	public void setStoreID(long storeID) {
		this.storeID = storeID;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getGratitude() {
		return gratitude;
	}

	public void setGratitude(String gratitude) {
		this.gratitude = gratitude;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empname) {
		this.empName = empname;
	}
	
	
}