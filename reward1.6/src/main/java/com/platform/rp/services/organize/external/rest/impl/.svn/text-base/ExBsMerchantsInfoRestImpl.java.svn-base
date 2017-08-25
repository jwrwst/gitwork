package com.platform.rp.services.organize.external.rest.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.services.organize.core.dao.entity.RegisterMerchantsEntity;
import com.platform.rp.services.organize.external.rest.IExBsMerchantsInfoRest;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.organize.external.service.IExBsMerchantsStoreService;
import com.platform.rp.services.sms.external.exception.SmsException;
import com.platform.rp.services.sms.external.service.ISmsService;
import com.platform.rp.services.wechat.external.vo.LoginVo;
import com.platform.rp.util.Constant;
import com.platform.rp.util.RandomValidateCode;
import com.platform.rp.util.StringUtils;
import com.platform.rp.util.cache.CacheConstant;
import com.platform.rp.util.cache.ICache;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.info.codes.DataCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;
/**
 * 商户信息
 * @author zhangsheng
 *
 */
@Controller
public class ExBsMerchantsInfoRestImpl extends BaseController implements IExBsMerchantsInfoRest {
	
	private Log log = LogFactory.getLog(ExBsMerchantsInfoRestImpl.class);
	
	@Autowired
	private IExBsMerchantsInfoService bsMerchantsInfoService;
	
	@Autowired
	private IExBsMerchantsStoreService bsMerchantsStoreService;

	@Autowired
	private ISmsService smsService;
	
	//@Context
	//HttpServletRequest request;
	@Autowired
	private ICache cache;
	
	public  RestfulResult update(BsMerchantsInfoEntity bsMerchantsInfoEntity){
		try {
			bsMerchantsInfoService.update(bsMerchantsInfoEntity);
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	}
	
	public  RestfulResult save(BsMerchantsInfoEntity bsMerchantsInfoEntity){
		try {
			bsMerchantsInfoService.save(bsMerchantsInfoEntity);
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	}
	/**
	 * 描述：生成图片验证码
	 * wang 2016年6月2日 上午10:28:40
	 */
	public void randomCode(){
		try {
			// 设置相应类型,告诉浏览器输出的内容为图片
		    response.setContentType("image/jpeg");
			// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCode.dao.getRandcode(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	/**
	 * 描述：校验验证码输入是否正确、手机号是否存在
	 * wang 2016年6月2日 上午10:35:43
	 * @param phone
	 */
	public RestfulResult nextRefindPassword(String phone,String verificationCode){
		try {
			request.getSession().setAttribute("phone", phone);//手机号存储session
			String randomString=(String)request.getSession().getAttribute("randomString");//获取图片验证码
			BsMerchantsInfoEntity isExistEntity=bsMerchantsInfoService.queryMerchantInfoByPhone(phone);//根据手机号查询手机号是否存在
			//图片验证码是否正确、帐号是否存在
			if(randomString.equalsIgnoreCase(verificationCode) && isExistEntity!=null){
				return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS); 
			}else{
				CommonCode commonCode=isExistEntity==null?CommonCode.PHONE_EXCEPTION:CommonCode.CODE_EXECEPTION;
				return RestfulResultProvider.buildCodeResult(commonCode);
			}
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION); 
		} 
	}
	/**
	 * 登录页面-->找回密码
	 */
	public RestfulResult updatePasswordByPhone(String password,String imputCode,String inputImgCode){
		try {
			String cacheImgCode=(String)request.getSession().getAttribute("randomString");//获取缓存中图片验证码
			String phone=(String)request.getSession().getAttribute("phone");//缓存中提取手机号
			if(Constant.IS_DEBUG){
				bsMerchantsInfoService.updatePasswordByPhone(phone, password);
				return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
			}else{
				String message=smsService.getSmsAuthCode(phone);//根据手机号提取短信验证码
				//输入的短信验证码与缓存中短信验证码对比、输入的图片验证码与缓存中图片验证码对比
				if (message.equals(imputCode) && cacheImgCode.equals(inputImgCode.toUpperCase())) {
					bsMerchantsInfoService.updatePasswordByPhone(phone, password);
					cache.remove(CacheConstant.KEY_SMS,phone);
					request.getSession().removeAttribute("phone");
					return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return RestfulResultProvider.buildFailureResult(new ResultData("密码设置失败,请联系管理员"));
	}
	/**
	 * 描述：账户安全中的修改密码
	 * Administrator 2016年6月3日 下午6:57:07
	 * @return
	 */
	public RestfulResult modifyPassword(Map<String,String> entity){
		try {
			String phone=entity.get("phone");
			String password=entity.get("password");
			String msgCode=entity.get("msgCode");
			String messageCode=smsService.getSmsAuthCode(phone);//根据手机号提取短信验证码
			if (messageCode.equals(msgCode)) {
				bsMerchantsInfoService.updatePasswordByPhone(phone, password);
				cache.remove(CacheConstant.KEY_SMS,phone);
				return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
			}
			return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildFailureResult(new ResultData("密码找回失败,请联系管理员"));
		}
	}
	//更换手机
	public RestfulResult updateBindMobile(Map<String,String> entity){
		try {
			String oldPhone=entity.get("phone");
			String oldMsgCode=entity.get("msgCode");
			String newPhone=entity.get("newMobile");
			String newMsgCode=entity.get("newMsgCode");
			if (StringUtils.isEmpty(oldPhone) || StringUtils.isEmpty(oldMsgCode) || StringUtils.isEmpty(newPhone) || StringUtils.isEmpty(newMsgCode)) {
				return RestfulResultProvider.buildCodeResult(CommonCode.UPDATE_BIND_EXCEPTION); 
			}
			if(bsMerchantsInfoService.checkAccount(newPhone, null) != null){
				return RestfulResultProvider.buildCodeResult(CommonCode.USER_EXISTS); 
			}
			if(Constant.IS_DEBUG){
				bsMerchantsInfoService.updateBindMobile(oldPhone, newPhone);//更新绑定手机号
				return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
			}else{
			//验证短信验证码是否输入正确，与缓存中验证码进行对比，成功后移除 缓存中的code
				BsMerchantsInfoEntity isExistEntity=bsMerchantsInfoService.queryMerchantInfoByPhone(oldPhone);//根据手机号查询手机号是否存在
				String cacheOldCode=smsService.getSmsAuthCode(oldPhone);//根据手机号提取缓存短信验证码
				String cacheNewCode=smsService.getSmsAuthCode(newPhone);
				//
				if (cacheOldCode.equals(oldMsgCode) && isExistEntity!=null && cacheNewCode.equals(newMsgCode)) {
					bsMerchantsInfoService.updateBindMobile(oldPhone, newPhone);//更新绑定手机号
					cache.remove(CacheConstant.KEY_SMS,oldPhone);
					cache.remove(CacheConstant.KEY_SMS,newPhone);
					return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
				}else{
					return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);
					
				}
			}
			//return RestfulResultProvider.buildCodeResult(CommonCode.ERROR);
		}catch(SmsException sm){
			log.error(sm,sm);
			return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);
		}catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildFailureResult(new ResultData("重新绑定失败,请联系管理员"));
		}
	}
	
	
	/**
	 * 登录
	 */
	public  RestfulResult login(BsMerchantsInfoEntity bsMerchantsInfoEntity){
		try {
			if(bsMerchantsInfoEntity==null||StringUtils.isEmpty(bsMerchantsInfoEntity.getAccount())
					||StringUtils.isEmpty(bsMerchantsInfoEntity.getPassword())){
				return RestfulResultProvider.buildCodeResult(DataCode.DATASTREAM_ERROR);
			}
			
			BsMerchantsInfoEntity entity=bsMerchantsInfoService.getInfo(bsMerchantsInfoEntity);
			if(entity==null){
				return RestfulResultProvider.buildCodeResult(DataCode.DATA_NOEXSIST);
			}
			//返回正在审核数据
//			if(entity.getStatus()==1){
//				return RestfulResultProvider.buildSuccessResult(new ResultData(1));
//			}
			//request.getSession().setAttribute("orgCode", entity.getOrgCode());
			//request.getSession().setAttribute("loginVo", entity);
			
			setSessionCahceVal("orgCode", entity.getOrgCode());
			setSessionCahceVal("loginVo", entity);
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	} 
	
	/**
	 * 登录
	 */
	public void loginAdmin(String orgCode){
		try {
			//商户切换
			Object merchantsListObj =getSessionCahceVal("merchantsList");
			if(null!=merchantsListObj){
				List<BsMerchantsEmployeeEntity> merchantsList = (List<BsMerchantsEmployeeEntity>) merchantsListObj;
				for (BsMerchantsEmployeeEntity bsMerchantsEmployeeEntity : merchantsList) {
					if(bsMerchantsEmployeeEntity.getOrgCode().equals(orgCode)){
						BsMerchantsInfoEntity bsMerchantsInfoEntity =new BsMerchantsInfoEntity();
						bsMerchantsInfoEntity.setOrgCode(orgCode);
						bsMerchantsInfoEntity.setOrgName(bsMerchantsEmployeeEntity.getOrgName());
						setSessionCahceVal("orgCode", orgCode);
						setSessionCahceVal("loginVo", bsMerchantsInfoEntity);
						sendRedirect(properties.webUrl+"/views/template/cusAdmin/index.xhtml");
						return;
					}
				}				
			}
			
			Object obj=request.getSession().getAttribute("userid");
			if(null==obj){
				return ;
			}
			BsMerchantsInfoEntity bsMerchantsInfoEntity =new BsMerchantsInfoEntity();
			bsMerchantsInfoEntity.setOrgCode(orgCode);
			BsMerchantsInfoEntity entity=bsMerchantsInfoService.getInfo(bsMerchantsInfoEntity);
			if(entity==null){
				return ;
			}
			//返回正在审核数据
//			if(entity.getStatus()==1){
//				return;
//			}
			//request.getSession().setAttribute("orgCode", entity.getOrgCode());
			//request.getSession().setAttribute("loginVo", entity);
			
			setSessionCahceVal("orgCode", entity.getOrgCode());
			setSessionCahceVal("loginVo", entity);
			response.sendRedirect(properties.webUrl+"/views/template/cusAdmin/index.xhtml");
		} catch (Exception e) {
			log.error(e,e);
		}
		
	} 
	
	/**
	 *	注册
	 *//*
	public RestfulResult getResultStatus(){

		String openId = getOpenId();
		try {
			String status = bsMerchantsInfoService.getResultStatus(openId);
			//注册
			return RestfulResultProvider.buildSuccessResult(new ResultData(status));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}*/
	
	public void toRegister(){
		try {
			List<BsOrganizeEntity> entitys = bsMerchantsInfoService.findMerchantsInfoEntityByOpenId(getOpenId(),null);
			boolean isMer = false;
			for (BsOrganizeEntity bsOrganizeEntity : entitys) {
				if(bsOrganizeEntity.getSchema().equals(bsOrganizeEntity.getOrgCode()) ){
					isMer = true;
					break;
				}
			}
			//tips_tenant_auditing.xhtml
			//if(entitys.size()>0){
			if(isMer){
				sendRedirect(properties.host+"/views/template/web/tips_tenant_auditing.xhtml");
			}else{
				sendRedirect(properties.host+"/views/template/web/tips_tenant_registerMerchants.xhtml");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 注册商户
	 */
	public RestfulResult registerMerchants(RegisterMerchantsEntity model){
		try {
			if(Constant.IS_DEBUG){
				String openId = getOpenId();
				if(model.getEmpId()==null){
					bsMerchantsInfoService.saveRegisterMerchants(model,openId);
				}else{
					bsMerchantsInfoService.updateRegisterMerchants(model,openId);
				}
				return RestfulResultProvider.buildSuccessResult(new ResultData("注册成功"));
			}else{
				String openId = getOpenId();
				if(model.getMerId()==null){
					String account = model.getPhone() ==null?model.getAccount():model.getPhone();
					//验证验证码的有效性
					String code=smsService.getSmsAuthCode(account);
					if (!code.equals(model.getCode())) {
						return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);
					}
				}
				if(model.getEmpId()==null){
					bsMerchantsInfoService.saveRegisterMerchants(model,openId);
				}else{
					bsMerchantsInfoService.updateRegisterMerchants(model,openId);
				}
				return RestfulResultProvider.buildSuccessResult(new ResultData("注册成功"));
			}
		} catch (SmsException e) {
			log.error(e,e); 
			//e.printStackTrace();
//			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
			return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);
		} catch (Exception e) {
			log.error(e,e); 
			//e.printStackTrace();
//			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
			return RestfulResultProvider.buildFailureResult(new ResultData(e.getMessage()));
		}
	}

	
	/**
	 * 注册商户
	 */
	public void registerMerForm2() throws UnsupportedEncodingException {
	
	    logger.debug("............................");
	}
	
	/**
	 * 查看所有报名信息
	 * @param openId
	 * @return
	 */
	public RestfulResult getRegisterMerchantsEntity(String orgCode,String openId,String status){
		try {
			if(openId == null){
				openId = getOpenId();
			}
			if(orgCode == null){
				return RestfulResultProvider.buildCodeResult(CommonCode.INVALID_PARAM);
			}
			List<RegisterMerchantsEntity> list = bsMerchantsInfoService.findRegisterMerchantsEntity(openId, orgCode,status);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}

	}
	
	@Override
	public RestfulResult getInfo(BsMerchantsInfoEntity bsMerchantsInfoEntity) {
		try {
			if("0".equals(bsMerchantsInfoEntity.getOrgId())){
				//bsMerchantsInfoEntity.setOrgCode((String) request.getSession().getAttribute("orgCode"));
				bsMerchantsInfoEntity.setOrgCode((String) getSessionVal("orgCode"));
			}
			BsMerchantsInfoEntity entity=bsMerchantsInfoService.getInfo(bsMerchantsInfoEntity);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
	public RestfulResult getStoreList(String orgCode,int status,String proId,String cityId,String areaId){
		try {
			List<BsMerchantsStoreEntity> list=bsMerchantsStoreService.getList(orgCode, status, proId, cityId, areaId);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
	public RestfulResult searchStoreList(Map params){
		String orgCode = (String) params.get("orgCode");
		String storeName = (String) params.get("storeName");
		try {
			List<BsMerchantsStoreEntity> list=bsMerchantsStoreService.getSubListByOrgCode(orgCode,storeName);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
	public RestfulResult searchStoreByParentCode(Map params){
		String orgCode = (String) params.get("orgCode");
		String storeName = (String) params.get("storeName");
		try {
			List<BsMerchantsStoreEntity> list=bsMerchantsStoreService.searchStoreByParentCode(orgCode,storeName);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	public RestfulResult getInfoByStoreID(long storeId){
		try {
			BsMerchantsInfoEntity entity=bsMerchantsInfoService.getInfoByStoreID(storeId);
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
	/**
	 * 查询微信账号下的所有商户findParentMerchants
	 * @param openId
	 * @return
	 */
	public RestfulResult findMerchantsInfoEntity(){
		String openId = getOpenId();
		String status = getPar("status");
		try {
			List<BsOrganizeEntity> entity=bsMerchantsInfoService.findMerchantsInfoEntityByOpenId(openId,status);
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	/**
	 * 查询微信账号下的所有商户findParentMerchants
	 * @param openId
	 * @return
	 */
	public RestfulResult findMerchantsInfoByOrgCode(){
		
		try {
			String orgCode = (String) getSessionVal("orgCode");
			List<RegisterMerchantsEntity> entity;
			if(orgCode==null){
				entity = new ArrayList<RegisterMerchantsEntity>();
			}else{
				entity=bsMerchantsInfoService.findMerchantsInfoByOrgCode(orgCode);
			}
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	/**
	 * 查询微信账号下的所有商户
	 * @param openId
	 * @return
	 */
	public RestfulResult findParentOrg(){
		String openId = getOpenId();
		String status = getPar("status");
		try {
			List<BsOrganizeEntity> entity=bsMerchantsInfoService.findParentOrg(openId,status);
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	/**
	 * 创建用户
	 * @param username
	 * @param password
	 * @param orgCode
	 * @param verificationCode		验证码
	 * @return
	 */
	@Deprecated
	@Override
	public RestfulResult createUser(String username, String password,
			String orgCode,String verificationCode) {
		try {
			/*if(verificationCode==null){
				return RestfulResultProvider.buildCodeResult(CommonCode.INVALID_PARAM);
			}

			String srcCode = smsService.getSmsAuthCode(username);
			//验证码
			if(verificationCode.equals(srcCode)){
				bsMerchantsInfoService.updateUserName(username, password, orgCode);
				return RestfulResultProvider.buildSuccessResult(new ResultData("SUCCESS"));
			}else{
				return RestfulResultProvider.buildCodeResult(CommonCode.AUTHCODE_ERROR);
			}*/
			

			bsMerchantsInfoService.updateUserName(username, password, orgCode);
			return RestfulResultProvider.buildSuccessResult(new ResultData("SUCCESS"));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
/*	*//**
	 * 查询商户信息
	 * @return
	 *//*
	@GET
	public RestfulResult checkMobile(){
		try {
			BsMerchantsInfoEntity mer = bsMerchantsInfoService.getMerchantsByUsername("");
			if(mer==null){
				return RestfulResultProvider.buildSuccessResult(new ResultData("true"));
			}else{

				return RestfulResultProvider.buildSuccessResult(new ResultData("false"));
			}
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	*/
	
	/**
	 * 校验账号是否存在
	 * @param openId
	 * @return
	 */
	public RestfulResult checkMobile(String mobile){
		try {
			BsMerchantsInfoEntity mer = bsMerchantsInfoService.getMerchantsByUsername(mobile);
			if(mer==null){
				return RestfulResultProvider.buildSuccessResult(new ResultData("true"));
			}else{

				return RestfulResultProvider.buildSuccessResult(new ResultData("false"));
			}
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}

	}
	
	private RegisterMerchantsEntity getUserMerchants(){
		 List<RegisterMerchantsEntity> rmes = bsMerchantsInfoService.findRegisterMerchantsEntity(getOpenId(),null, null);
		 for (int i = 0; i < rmes.size(); i++) {
			 String account = rmes.get(i).getAccount();
			if(StringUtils.isNotEmpty(account)){
				return  rmes.get(i);
			}
		}
		 return null;
	}
	/*****************商户管理**************************/

	//http://localhost:8080/rs/external/merchants/toMerchantsManage
	public void toMerchantsManage(){

		try {
			List<BsOrganizeEntity> entitys = bsMerchantsInfoService.findMerchantsInfoEntityByOpenId(getOpenId(),null);
			//tips_tenant_auditing.xhtml
			if(entitys.size()>0){
				boolean hasSuccess = false;
				for (BsOrganizeEntity orgs : entitys) {
					int status = orgs.getStatus();
					if(status == BsOrganizeEntity.STAUTS_SUCCESS)	{
						hasSuccess = true;
						break;
					}
				}
				//存在成功的商户进入商户管理
				if(hasSuccess){
					//sendRedirect(properties.host+"/views/template/web/tips_merchants_manage.xhtml");
					sendRedirect(properties.host+"/views/template/web/tips_merchants_manage_list.xhtml");
				//进入审核页面
				}else{
					sendRedirect(properties.host+"/views/template/web/tips_tenant_auditing.xhtml");
				}
			}else{
				//未注册
				sendRedirect(properties.host+"/views/template/web/tips_merchants_empty.xhtml");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 修改密码
	 * @return
	 */
	@SuppressWarnings("unused")
	public RestfulResult updatePassword(Map params){
		try {		
			//RegisterMerchantsEntity userMerchants = getUserMerchants();
			//String username = userMerchants.getAccount();
			String username = (String) params.get("account");
			String password = (String)params.get("password");
			DataCode dc = bsMerchantsInfoService.updatePassword(username,(String)params.get("srcPassword"), password);
			if(dc.getCode().equals(SUCCESS_CODE)){
				return RestfulResultProvider.buildSuccessResult(new ResultData(dc));
			}else{
				return RestfulResultProvider.buildCodeResult(dc);
			}
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	}
	
	public RestfulResult loginWap(){
		try {				
			
			bsMerchantsInfoService.loginWap(getOpenId(),getPar("username"),getPar("password"));
			return RestfulResultProvider.buildSuccessResult(new ResultData(SUCCESS_CODE));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	public RestfulResult delete(String orgCode ){

		try {					 
			bsMerchantsInfoService.delete(orgCode);
			return RestfulResultProvider.buildSuccessResult(new ResultData(SUCCESS_CODE));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
	public RestfulResult getRandId(){
		long id = getId();
		bsMerchantsInfoService.saveUidToCache(Long.toString(id));
		return RestfulResultProvider.buildSuccessResult(new ResultData(id));
	}
	
	private synchronized long getId() {
        return new Date().getTime();
    }
	
	public void getEmpId(){
		try {
			LoginVo loginVo = (LoginVo) request.getSession().getAttribute("loginVo");
			String uid = loginVo.getData();
			String openId = loginVo.getOpenId();
			//openId = "o3TzSv0uuOdEZqDs0YK-0QfO3V3k";
			System.out.println("接口:getEmpId"+uid);
			//验证时间戳的有效性
			Object obj = bsMerchantsInfoService.getUidFromCache(uid);
			if(obj != null){
				//这里根据OpenId获取empId，并保存到缓存中
				long empId = bsMerchantsInfoService.saveEmpidToCache(uid, openId);	
				List<BsMerchantsEmployeeEntity> merchantsList = bsMerchantsInfoService.getMerchantsListByEmpId(empId);
				if(null!=merchantsList && merchantsList.size()>0){
					sendRedirect(properties.host+"/views/error/success.xhtml?param.data=0");
					return;
				}else{
					sendRedirect(properties.host+"/views/error/success.xhtml?param.data=1");
					return;
				}
			}
			sendRedirect(properties.host+"/views/error/success.xhtml?param.data=1");
		} catch (Exception e) {
			log.error("===",e);	
			try {
				sendRedirect(properties.host+"/views/error/success.xhtml?param.data=2");
			} catch (Exception e1) {
				log.error(e.getMessage(),e);
			}
		}		
	    
	}
	
	public RestfulResult getMerchantInfo(String uid){		
		try {	
			Object obj = bsMerchantsInfoService.getEmpidFromCache(uid);
			if(obj != null){
				long empId = Long.valueOf(obj+"");
				List<BsMerchantsEmployeeEntity> merchantsList = bsMerchantsInfoService.getMerchantsListByEmpId(empId);
				if(CollectionUtils.isNotEmpty(merchantsList)){
					BsMerchantsInfoEntity entity = new BsMerchantsInfoEntity();
					entity.setOrgName(merchantsList.get(0).getOrgName());
					entity.setOrgCode(merchantsList.get(0).getOrgCode());
					setSessionCahceVal("orgCode", entity.getOrgCode());
					setSessionCahceVal("loginVo", entity);
					setSessionCahceVal("merchantsList", merchantsList);
					return RestfulResultProvider.buildSuccessResult(new ResultData(merchantsList));
				}	
			}
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}	
	  return RestfulResultProvider.buildCodeResult(DataCode.DATA_NOEXSIST);
	}

	@Override
	public RestfulResult checkAccount(String account,String merId) {
		try {
			String obj = bsMerchantsInfoService.checkAccount(account,merId);
			if(obj==null){
				return RestfulResultProvider.buildSuccessResult(new ResultData(obj));
			}else{
				return RestfulResultProvider.buildCodeResult(CommonCode.USER_EXISTS);
			}
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}

	@Override
	public RestfulResult checkOrgName(Map<String,String> entity) {
		try {
			String obj = bsMerchantsInfoService.checkOrgName(entity.get("orgName"),entity.get("merId"));
			if(obj==null){
				return RestfulResultProvider.buildSuccessResult(new ResultData(obj));
			}else{
				return RestfulResultProvider.buildCodeResult(CommonCode.ORGNAME_EXISTS);
			}
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}


	public RestfulResult getLoingInfo(){
		try {
			String orgCode = (String) request.getSession().getAttribute("orgCode");
			BsMerchantsInfoEntity mer = bsMerchantsInfoService.findByOrgCode(orgCode);
			return RestfulResultProvider.buildSuccessResult(new ResultData(mer));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}

	}
}
