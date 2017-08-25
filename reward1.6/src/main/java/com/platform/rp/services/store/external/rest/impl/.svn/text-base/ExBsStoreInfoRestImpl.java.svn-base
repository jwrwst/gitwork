package com.platform.rp.services.store.external.rest.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.services.sms.external.exception.SmsException;
import com.platform.rp.services.sms.external.service.ISmsService;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.services.store.external.rest.IExBsStoreInfoRest;
import com.platform.rp.services.store.external.service.IExBsStoreInfoService;
import com.platform.rp.services.wechat.external.vo.LoginVo;
import com.platform.rp.util.exception.RepeatBindException;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExBsStoreInfoRestImpl extends BaseController implements IExBsStoreInfoRest {

	private static final  Logger log=Logger.getLogger(ExBsStoreInfoRestImpl.class);
	
	@Autowired
	IExBsStoreInfoService bsStoreInfoService;
	
	@Autowired
	private ISmsService smsService;
	
	@Context
	HttpServletRequest request;

	@Override
	public RestfulResult save(BsStoreInfoEntity entity) {
		try {
			//验证手机验证码
			if (!smsService.checkSmsAuthCode(entity.getTelphone(), entity.getTelphoneCode())) {
				return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);
			}
			//获取昵称
			try{
				Object obj=request.getSession().getAttribute("loginVo");
				if(obj!=null){
					LoginVo vo=(LoginVo) obj;
					entity.setNickname(vo.getNickname());
				}
			}catch(Exception e){}
			
			bsStoreInfoService.save(entity);	
		} catch (SmsException e) {
			return RestfulResultProvider.buildCodeResult(CommonCode.CODE_EXECEPTION);		
		} catch (Exception e) {
			log.error("保存店铺",e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
	
	@Override
	public RestfulResult saveStore(BsStoreInfoEntity entity) {
		try {
			bsStoreInfoService.saveStore(entity);			
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
	}
	
	@Override
	public RestfulResult update(BsStoreInfoEntity entity) {
		try {
			bsStoreInfoService.update(entity);			
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
	
	@Override
	public RestfulResult getStoreInfo(long storeId) {
		try {
			BsStoreInfoEntity entity=bsStoreInfoService.get(storeId);	
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}
	
	@Override
	public RestfulResult deleteStore(long storeId,String orgCode) {
		try {
			bsStoreInfoService.deleteStore(storeId,orgCode);
			return RestfulResultProvider.buildSuccessResult();
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}
	
	public RestfulResult getStoreExtReward(long storeId) {
		try {
			BsStoreInfoEntity entity=bsStoreInfoService.getStoreExtReward(storeId);	
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}	
	}
	
	
	public RestfulResult updateStoreExtReward(BsStoreInfoEntity entity){
		try {
			bsStoreInfoService.updateStoreExtReward(entity);
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			log.error("保存赏金分配失败：",e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}	
	}
	
	public RestfulResult saveManagerStore(BsStoreInfoEntity entity){
		try {
			try{
				Object obj=request.getSession().getAttribute("loginVo");
				if(obj!=null){
					LoginVo vo=(LoginVo) obj;
					entity.setNickname(vo.getNickname());
				}
			}catch(Exception e){}
			
			bsStoreInfoService.saveManagerStore(entity);
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}	
	}

	public RestfulResult saveDividedStore(BsStoreInfoEntity entity){
		try {
			try{
				Object obj=request.getSession().getAttribute("loginVo");
				if(obj!=null){
					LoginVo vo=(LoginVo) obj;
					entity.setNickname(vo.getNickname());
				}
			}catch(Exception e){}
			
			bsStoreInfoService.saveDividedStore(entity);
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}	
	}

	public RestfulResult saveMerManage(BsStoreInfoEntity entity){
		try {
			try{
				Object obj=request.getSession().getAttribute("loginVo");
				if(obj!=null){
					LoginVo vo=(LoginVo) obj;
					entity.setNickname(vo.getNickname());
				}
			}catch(Exception e){}
			
			bsStoreInfoService.saveMerManage(entity);
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		}catch(RepeatBindException e1){
			//重复绑定
			return RestfulResultProvider.buildCodeResult(CommonCode.REPEEAT_BIND);
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}	
	}
	@Override
	public RestfulResult updateStatus(Map<String,Object> params){
		try {				
			bsStoreInfoService.updateStatus((Integer)params.get("status"),params.get("storeIds")+"");
			return RestfulResultProvider.buildSuccessResult(new ResultData(SUCCESS_CODE));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
	/**
	 * 根据openId获取创建单店店铺数
	 */
	public RestfulResult getCountByOpenIdAndStoreCode(String openId){
		try {				
			Map<String,Integer> countMap = bsStoreInfoService.getCountByOpenIdAndStoreCode(openId);
			return RestfulResultProvider.buildSuccessResult(new ResultData(countMap));
		} catch (Exception e) {
			log.error(e,e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
}
