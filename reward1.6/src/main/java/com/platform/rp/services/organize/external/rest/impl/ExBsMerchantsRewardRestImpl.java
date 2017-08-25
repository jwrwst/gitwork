package com.platform.rp.services.organize.external.rest.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.organize.core.dao.entity.BsMerchantsExtRewardEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsRewardStoreEntity;
import com.platform.rp.services.organize.external.rest.IExBsMerchantsRewardRest;
import com.platform.rp.services.organize.external.service.IExBsMerchantsRewardService;
import com.platform.rp.services.organize.external.vo.BsMerchantsVo;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExBsMerchantsRewardRestImpl implements IExBsMerchantsRewardRest {
	
	private Log log = LogFactory.getLog(ExBsMerchantsRewardRestImpl.class);
	
	@Autowired
	private IExBsMerchantsRewardService bsMerchantsRewardService;
	
	@Context
	HttpServletRequest request;
	
	public RestfulResult save(BsMerchantsVo vo){
		try {
			bsMerchantsRewardService.save(vo);
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	}
	
	public RestfulResult getList(String orgCode){
		try {
			List<BsMerchantsExtRewardEntity> list=bsMerchantsRewardService.getInfo(orgCode);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	}
	
	public RestfulResult getRewardStoreList(String orgCode){
		try {
			List<BsMerchantsRewardStoreEntity> list=bsMerchantsRewardService.getRewardStoreList(orgCode);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	}
	
	
	
}
