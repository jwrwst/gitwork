package com.platform.rp.services.store.external.rest.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.store.external.rest.IExItRewardInfoRest;
import com.platform.rp.services.store.external.service.IExItRewardInfoService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExItRewardInfoRestImpl implements IExItRewardInfoRest {

	private static final  Logger log=Logger.getLogger(ExItRewardInfoRestImpl.class);
	
	@Autowired
	IExItRewardInfoService exItRewardInfoService;
	

	@Override
	public RestfulResult deleteByOrderNum(String orderNum) {
		try {
			exItRewardInfoService.deleteByOrderNum(orderNum);
			return RestfulResultProvider.buildSuccessResult();
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}

}
