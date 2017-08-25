package com.platform.rp.services.store.core.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * 店铺表
 * 
 */
@XmlRootElement
public class BsStoreInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private long storeId;
	private String address;
	private String createMobile;
	private String create_openId;
	private Date createTime;
	private String createUser;
	private int isUpdate;
	private String qrcode;
	private BigDecimal rewardMoney;
	private String signature;
	private String sketch;
	private String storeCode;
	private String storeName;
	private String storeType;
	private String codeType;
	private String telphone;
	private Date updateTime;
	private String updateUser;	
	private String wish;
	
	private String areaId;			//区域code
	private String cityId;			//城市code
	private String provinceId;		//省code
	private String countryId;		//国家code

	//非数据库属性
	private String typeName;		//类型名称
	private String codeTypeName;	//二级类型名称
	private String areaName;		//区域名称
	private String cityName;		//城市名称
	private String provinceName;	//省名称
	private String countryName;		//国家名称
	//用户信息
	private String nickname = "";//昵称
	private String sex = "";//性别
	private String province = "" ; //省份
	private String city = "";//城市
	private String country = "" ;//国家
	private String headimgurl = "" ;//头像
	private long empId;
	
	private String orgCode;//商户编号
	private String schema;//根节点商户
	
		
	private String telphoneCode;//短信验证码


	public String getTelphoneCode() {
		return telphoneCode;
	}


	public void setTelphoneCode(String telphoneCode) {
		this.telphoneCode = telphoneCode;
	}


	public String getAreaId() {
		return areaId;
	}


	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}


	public String getCityId() {
		return cityId;
	}


	public void setCityId(String cityId) {
		this.cityId = cityId;
	}


	public String getProvinceId() {
		return provinceId;
	}


	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}


	public String getCountryId() {
		return countryId;
	}


	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}


	public String getSchema() {
		return schema;
	}


	public void setSchema(String schema) {
		this.schema = schema;
	}


	public String getWish() {
		return wish;
	}


	public void setWish(String wish) {
		this.wish = wish;
	}


	public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public long getEmpId() {
		return empId;
	}


	public void setEmpId(long empId) {
		this.empId = empId;
	}



	private List<BsStoreAuthDetailEntity> authlist;
	
	private String authType;
	
	private String allotPlan;
	private List<BsStoreExtRewardEntity> extRewardlist;

	public String getAuthType() {
		return authType;
	}


	public List<BsStoreExtRewardEntity> getExtRewardlist() {
		return extRewardlist;
	}



	public String getAllotPlan() {
		return allotPlan;
	}


	public void setAllotPlan(String allotPlan) {
		this.allotPlan = allotPlan;
	}


	public void setExtRewardlist(List<BsStoreExtRewardEntity> extRewardlist) {
		this.extRewardlist = extRewardlist;
	}



	public void setAuthType(String authType) {
		this.authType = authType;
	}



	public List<BsStoreAuthDetailEntity> getAuthlist() {
		return authlist;
	}



	public void setAuthlist(List<BsStoreAuthDetailEntity> authlist) {
		this.authlist = authlist;
	}



	public BsStoreInfoEntity() {
	}



	public long getStoreId() {
		return storeId;
	}



	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreateMobile() {
		return createMobile;
	}

	public void setCreateMobile(String createMobile) {
		this.createMobile = createMobile;
	}

	public String getCreate_openId() {
		return create_openId;
	}

	public void setCreate_openId(String create_openId) {
		this.create_openId = create_openId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public int getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public BigDecimal getRewardMoney() {
		return rewardMoney;
	}

	public void setRewardMoney(BigDecimal rewardMoney) {
		this.rewardMoney = rewardMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getSketch() {
		return sketch;
	}

	public void setSketch(String sketch) {
		this.sketch = sketch;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}


	//
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


	public String getCodeType() {
		return codeType;
	}


	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	public String getCodeTypeName() {
		return codeTypeName;
	}


	public void setCodeTypeName(String codeTypeName) {
		this.codeTypeName = codeTypeName;
	}


	public String getAreaName() {
		return areaName;
	}


	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public String getCityName() {
		return cityName;
	}


	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public String getProvinceName() {
		return provinceName;
	}


	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}


	public String getCountryName() {
		return countryName;
	}


	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	//
	

}