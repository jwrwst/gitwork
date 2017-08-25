package com.platform.rp.services.wechat.external.vo;


public class LoginVo{

	//微信信息
	private String accessToken="";
	private String openId=""; // 微信openId唯一标识	
	private String nickname = "";//昵称
	private String sex = "";//性别
	private String province = "" ; //省份
	private String city = "";//城市
	private String country = "" ;//国家
	private String headimgurl = "" ;//头像
	private String unionid = "";//唯一值
	private String mobile=""; // 手机号
	private String name = "" ;// 姓名
	
	private String data = "";//存储值
	
	private long empId=0;//员工编号	
	private long storeId=0;//店铺编号	
	private String storeName="";//店铺名称	
	private long customerId=0;//顾客编号
	private String state="";//状态
	
	public LoginVo() {
		
	}
	
	
	public LoginVo(String openId,long empId,long storeId){
		this.openId=openId;
		this.empId = empId;
		this.storeId = storeId;
	}
	
		
	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	


	
}
