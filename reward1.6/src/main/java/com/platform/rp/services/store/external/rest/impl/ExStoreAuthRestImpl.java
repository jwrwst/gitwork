package com.platform.rp.services.store.external.rest.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.store.core.dao.entity.BsStoreAuthEntity;
import com.platform.rp.services.store.external.rest.IExStoreAuthRest;
import com.platform.rp.services.store.external.service.IExStoreAuthService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExStoreAuthRestImpl implements IExStoreAuthRest {
	
	private Log log = LogFactory.getLog(ExStoreAuthRestImpl.class);

	@Autowired
	IExStoreAuthService storeAuthService;

	
	@Override
	public RestfulResult getList(String groupCode) {		
		try {
			List<BsStoreAuthEntity> list=storeAuthService.getList(groupCode);			
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}		
		
	}
	
}
