package com.platform.rp.services.qrcodeinfo.external.rest.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItFileInfoEntity;
import com.platform.rp.services.qrcodeinfo.external.rest.IExItFileInfoRest;
import com.platform.rp.services.qrcodeinfo.external.service.IExItFileInfoService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExItFileInfoRestImpl implements IExItFileInfoRest {

	private static final  Logger log=Logger.getLogger(ExItFileInfoRestImpl.class);
	
	@Autowired
	IExItFileInfoService fileInfoService;
	

	@Override
	public RestfulResult getByOrderNum(String orderNum) {
		try {
			ItFileInfoEntity entity=fileInfoService.getByOrderNum(orderNum);
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}

	@Override
	public RestfulResult save(ItFileInfoEntity entity) {
		try {
			String gurl=fileInfoService.save(entity);
			return RestfulResultProvider.buildSuccessResult(new ResultData(gurl));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}
	
}
