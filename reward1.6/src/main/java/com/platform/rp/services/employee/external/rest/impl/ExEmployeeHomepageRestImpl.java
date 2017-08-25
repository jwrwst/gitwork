package com.platform.rp.services.employee.external.rest.impl;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.employee.external.rest.IExEmployeeHomepageRest;
import com.platform.rp.services.employee.external.service.IExEmployeeHomepageService;
import com.platform.rp.services.employee.external.vo.EmployeeHomepageBaseInfoVo;
import com.platform.rp.services.store.core.dao.entity.ItEvaluateInfoEntity;
import com.platform.rp.services.store.core.dao.entity.ItRewardInfoEntity;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExEmployeeHomepageRestImpl implements IExEmployeeHomepageRest {
	
	private Log log = LogFactory.getLog(ExEmployeeHomepageRestImpl.class);
	
	@Autowired
	private IExEmployeeHomepageService empHomeService;

	@Override
	public RestfulResult getEmpHomepageBaseInfoByEmpId(long empID) {
		
		try {
			HashMap<String, Object> returnMap = new HashMap<String, Object>();
			
			EmployeeHomepageBaseInfoVo empInfo = empHomeService.getEmpHomepageBaseInfoByEmpId(empID);
			
			returnMap.put("empid", empInfo.getEmployeeID());
			returnMap.put("storeid", empInfo.getStoreID());
			returnMap.put("empnickname", empInfo.getEmpNickname());
			returnMap.put("headpic", empInfo.getHeadPic());
			returnMap.put("storename", empInfo.getStoreName());
			returnMap.put("rewardmoney", empInfo.getRewardMoney());
			returnMap.put("isupdate", empInfo.getIsUpdate());
			returnMap.put("signature", empInfo.getSignature());
			returnMap.put("qrcode", empInfo.getQrCode());
			returnMap.put("authcode", empInfo.getAuthCode());
			returnMap.put("gratitude", empInfo.getGratitude());
			returnMap.put("empname",  empInfo.getEmpName());
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(returnMap));
		} catch (Exception e) {
			log.info(e.getMessage());
			
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult addEvaluate(ItEvaluateInfoEntity itEvaluateInfo) {		
		try {
			Date dt =new Date();
			itEvaluateInfo.setCreatedtime(dt);
			empHomeService.addEvaluate(itEvaluateInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}

	@Override
	public RestfulResult addReward(ItRewardInfoEntity itRewardInfo) {
		
		try {
			empHomeService.addReward(itRewardInfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
}
