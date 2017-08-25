package com.wxboot.web.userinfo.model;

import java.io.Serializable;

/**
 * <p>Discription:[用户信息实体类]</p>
 * Created on 2017年04月07日
 * @author wang
 */
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String id;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 1：正常使用；2：账号被停用
	 */
	private Integer status;
	/**
	 * 微信的openid
	 */
	private String openid;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * token
	 */
	private String token;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 用户头像
	 */
	private String userFace;
	/**
	 * 用户真实姓名
	 */
	private String userName;
	/**
	 * 用户身份证号
	 */
	private String userCard;
	/**
	 * 省份
	 */
	private Integer province;
	/**
	 * 城市
	 */
	private Integer city;
	/**
	 * 区域
	 */
	private Integer area;
	/**
	 * 小区地址
	 */
	private String address;
	/**
	 * 地址维度
	 */
	private String latitude;
	/**
	 * 地址经度
	 */
	private String longitude;
	/**
	 * 能量值
	 */
	private Integer energy;
	/**
	 * 性别（1男2女）
	 */
	private Integer sex;
	/**
	 * 毕业院校
	 */
	private String school;
	/**
	 * 个性签名
	 */
	private String sign;
	/**
	 * 年龄
	 */
	private String age;
	
	// setter and getter
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	public String getPhone(){
		return phone;
	}
	
	public void setPhone(String phone){
		this.phone = phone;
	}
	public Integer getStatus(){
		return status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	public String getOpenid(){
		return openid;
	}
	
	public void setOpenid(String openid){
		this.openid = openid;
	}
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getToken(){
		return token;
	}
	
	public void setToken(String token){
		this.token = token;
	}
	public String getNickName(){
		return nickName;
	}
	
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	public String getUserFace(){
		return userFace;
	}
	
	public void setUserFace(String userFace){
		this.userFace = userFace;
	}
	public String getUserName(){
		return userName;
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}
	public String getUserCard(){
		return userCard;
	}
	
	public void setUserCard(String userCard){
		this.userCard = userCard;
	}
	public Integer getProvince(){
		return province;
	}
	
	public void setProvince(Integer province){
		this.province = province;
	}
	public Integer getCity(){
		return city;
	}
	
	public void setCity(Integer city){
		this.city = city;
	}
	public Integer getArea(){
		return area;
	}
	
	public void setArea(Integer area){
		this.area = area;
	}
	public String getAddress(){
		return address;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	public String getLatitude(){
		return latitude;
	}
	
	public void setLatitude(String latitude){
		this.latitude = latitude;
	}
	public String getLongitude(){
		return longitude;
	}
	
	public void setLongitude(String longitude){
		this.longitude = longitude;
	}
	public Integer getEnergy(){
		return energy;
	}
	
	public void setEnergy(Integer energy){
		this.energy = energy;
	}
	public Integer getSex(){
		return sex;
	}
	
	public void setSex(Integer sex){
		this.sex = sex;
	}
	public String getSchool(){
		return school;
	}
	
	public void setSchool(String school){
		this.school = school;
	}
	public String getSign(){
		return sign;
	}
	
	public void setSign(String sign){
		this.sign = sign;
	}
	public String getAge(){
		return age;
	}
	
	public void setAge(String age){
		this.age = age;
	}
}
