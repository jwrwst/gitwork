package com.platform.rp.services.employee.core.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the bs_employee_info database table.
 * 
 */
@XmlRootElement
public class BsEmployeeInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long empId;
	private byte[] alipayAccount;
	private String avocation;
	private String bankCard;
	private String bankname;
	private String birthday;
	private String constellation;
	private Date createdtime;
	private int creator;
	private int emotion;
	private String empCode;
	private String entrydate;
	private String gender;
	private String gratitude;
	private String headPic;
	private String homeAddress;
	private String idcard;
	private String jobNumber;
	private int jobable;
	private int level;
	private String mobile;
	private int modifieder;
	private Date modifiedtime;
	private String name;
	private String nickname;
	private int postion;
	private String qqNumber;
	private String qrCode;
	private String signature;
	private long storeId;
	private String value1;
	private String value2;
	private String value3;
	private String value4;
	private String value5;
	private String weChatnumber;
	private String wxAccount;
	
	private String storeName;
	private String jobTitle;
	

	public String getJobTitle() {
		return jobTitle;
	}


	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}


	public String getStoreName() {
		return storeName;
	}


	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public BsEmployeeInfoEntity() {
	}

	
	public byte[] getAlipayAccount() {
		return alipayAccount;
	}

	public void setAlipayAccount(byte[] alipayAccount) {
		this.alipayAccount = alipayAccount;
	}

	public String getAvocation() {
		return avocation;
	}

	public void setAvocation(String avocation) {
		this.avocation = avocation;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}



	public String getBankname() {
		return bankname;
	}


	public void setBankname(String bankname) {
		this.bankname = bankname;
	}


	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public int getEmotion() {
		return emotion;
	}

	public void setEmotion(int emotion) {
		this.emotion = emotion;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEntrydate() {
		return entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGratitude() {
		return gratitude;
	}

	public void setGratitude(String gratitude) {
		this.gratitude = gratitude;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public int getModifieder() {
		return modifieder;
	}

	public void setModifieder(int modifieder) {
		this.modifieder = modifieder;
	}

	

	public Date getCreatedtime() {
		return createdtime;
	}


	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}


	public int getJobable() {
		return jobable;
	}


	public void setJobable(int jobable) {
		this.jobable = jobable;
	}


	public Date getModifiedtime() {
		return modifiedtime;
	}


	public void setModifiedtime(Date modifiedtime) {
		this.modifiedtime = modifiedtime;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPostion() {
		return postion;
	}

	public void setPostion(int postion) {
		this.postion = postion;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public long getEmpId() {
		return empId;
	}


	public void setEmpId(long empId) {
		this.empId = empId;
	}


	public long getStoreId() {
		return storeId;
	}


	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}


	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	public String getValue5() {
		return value5;
	}

	public void setValue5(String value5) {
		this.value5 = value5;
	}

	public String getWeChatnumber() {
		return weChatnumber;
	}

	public void setWeChatnumber(String weChatnumber) {
		this.weChatnumber = weChatnumber;
	}


	public String getWxAccount() {
		return wxAccount;
	}


	public void setWxAccount(String wxAccount) {
		this.wxAccount = wxAccount;
	}




}