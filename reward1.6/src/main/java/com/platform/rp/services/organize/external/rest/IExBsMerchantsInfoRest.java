package com.platform.rp.services.organize.external.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.RegisterMerchantsEntity;
import com.platform.rp.util.response.result.RestfulResult;

/**
 * 店铺统计信息
 *
 */
@Path("/external/merchants")
@Produces(MediaType.APPLICATION_JSON)
public interface IExBsMerchantsInfoRest {


	//根据过滤条件查询店铺评价统计
	@POST
    @Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult update(BsMerchantsInfoEntity bsMerchantsInfoEntity);
	
	//根据过滤条件查询店铺评价统计
	@POST
    @Path("/getInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getInfo(BsMerchantsInfoEntity bsMerchantsInfoEntity);
	
	
	@POST
    @Path("/wap/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public  RestfulResult save(BsMerchantsInfoEntity bsMerchantsInfoEntity);
	
	
	
	/**
	 * 描述：生成图片验证码
	 * Administrator 2016年6月2日 上午10:52:59
	 */
	@GET
	@Path("/wap/randomCode")
	@Consumes(MediaType.APPLICATION_JSON)
	public void randomCode();
	/**
	 * 描述：【找回密码的下一步】校验验证码输入是否正确、手机号是否存在
	 * Administrator 2016年6月2日 上午11:10:18
	 */
	@GET
	@Path("/wap/nextRefindPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult nextRefindPassword(@QueryParam("phone")String phone,@QueryParam("verificationCode")String verificationCode);
	/**
	 * 描述：更改密码【验证短信验证码和图片验证码的有效性】
	 * Administrator 2016年6月4日 下午9:17:35
	 * @param password
	 * @param imputCode
	 * @param inputImgCode
	 * @return
	 */
	@GET
	@Path("/wap/updatePasswordByPhone")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult updatePasswordByPhone(@QueryParam("password")String password,@QueryParam("imputCode")String imputCode,@QueryParam("inputImgCode")String inputImgCode);
	/**
	 * 描述：修改密码【校验短信验证码有效性】
	 * Administrator 2016年6月4日 下午9:17:40
	 * @param phone
	 * @param password
	 * @param msgCode
	 * @return
	 */
	@POST
	@Path("/modifyPassword")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult modifyPassword(Map<String,String> entity);
	/**
	 * 描述：更换绑定手机号
	 * Administrator 2016年6月4日 下午10:05:21
	 * @param newMobile
	 * @param msgCode
	 * @return
	 */
	@POST
	@Path("/updateBindMobile")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult updateBindMobile(Map<String,String> entity);
	
	
	
	@POST
    @Path("/wap/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public  RestfulResult login(BsMerchantsInfoEntity bsMerchantsInfoEntity);
	
	@GET
    @Path("/wap/loginAdmin")
	@Consumes(MediaType.APPLICATION_JSON)
	public void loginAdmin(@QueryParam("orgCode")String orgCode);
	
	/**
	 * 获取店铺列表
	 * @param orgCode
	 * @return
	 */
	@GET
    @Path("/getStoreList")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getStoreList(@QueryParam("orgCode")String orgCode,@QueryParam("status")int status,
			@QueryParam("provinceId")String proId,@QueryParam("cityId")String cityId,@QueryParam("areaId")String areaId);
	
	
	/**
	 * 获取店铺列表
	 * @param orgCode
	 * @return
	 */
	@POST
    @Path("/searchStoreList")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult searchStoreList(Map<String,String> entity);

	/**
	 * 根据父编码获取店铺列表
	 * @param orgCode
	 * @return
	 */
	@POST
    @Path("/searchStoreByParentCode")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult searchStoreByParentCode(Map<String,String> entity);
	
	@GET
    @Path("/toRegister")     
	public void toRegister();
	
	@GET
    @Path("/getMerchantsByStoreId")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getInfoByStoreID(@QueryParam("storeId")long storeId);

	/**
	 * 注册商户
	 */
	@POST
    @Path("/registerMerchants")     
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult registerMerchants(RegisterMerchantsEntity registerMerchantsModel);
	/**

	
	@GET
    @Path("/wap/registerMerForm")     
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public void registerMerForm2() throws UnsupportedEncodingException ;
	/**
	 * 创建用户
	 * @param username
	 * @param password
	 * @param orgCode
	 * @param verificationCode
	 * @return
	 */
	@GET
    @Path("/createUser")     
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult createUser(@QueryParam("username")String username,
	@QueryParam("password")String password,@QueryParam("orgCode")String orgCode,@QueryParam("verificationCode")String verificationCode);
	
	
	/**
	 * 查询微信账号下的所有商户
	 * @return
	 */
	@GET
    @Path("/findMerchantsInfoEntity")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult findMerchantsInfoEntity();
	/**
	 * 查询微信账号下的所有商户
	 * @return
	 */
	@GET
    @Path("/wap/findMerchantsInfoByOrgCode")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult findMerchantsInfoByOrgCode();

	/**
	 * 查询微信账号下的所有父级商户
	 * @return
	 */
	@GET
    @Path("/findParentOrg")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult findParentOrg();
	
	/**
	 * 查询商户信息
	 * @return
	 */
	@GET
    @Path("/getRegisterMerchantsEntity")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getRegisterMerchantsEntity(@QueryParam("orgCode")String orgCode,@QueryParam("openId")String openId,@QueryParam("status")String status);
	
	/**
	 * 查询商户信息
	 * @return
	 */
	@GET
    @Path("/checkMobile")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult checkMobile(@QueryParam("mobile")String mobile);

	/**
	 * 进入商户管理
	 */
	@GET
    @Path("/toMerchantsManage")
	public void toMerchantsManage();
	/**
	 * 更新用户密码
	 * @param params
	 * @return
	 */
	@POST
    @Path("/updatePassword")     
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult updatePassword(Map params);
	

	/**
	 * 进入商户管理
	 */
	@GET
    @Path("/delete")
	public RestfulResult delete(@QueryParam("orgCode")String orgCode);
	
	
	/**
	 * 商户扫码登录获取时间戳
	 */
	@POST
    @Path("/wap/getRandId")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getRandId();

	/**
	 * 商户扫码登录获取empId
	 */
	@GET
    @Path("/getEmpId")
	@Consumes(MediaType.APPLICATION_JSON)
	public void getEmpId();	

	/**
	 * 商户扫码登录获取第一个商户信息
	 */
	@POST
    @Path("/wap/getMerchantInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getMerchantInfo(@QueryParam("uid")String uid);
	
	/**
	 * 校验账号唯一
	 */
	@GET
    @Path("/wap/checkAccount")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult checkAccount(@QueryParam("account")String account,@QueryParam("merId")String merId);
	/**
	 * 校验品牌名称唯一
	 */
	@POST
    @Path("/wap/checkOrgName")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult checkOrgName(Map<String,String> entity);
	/**
	 * 获取用户信息
	 */
	@POST
    @Path("/getLoingInfo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getLoingInfo();
}
