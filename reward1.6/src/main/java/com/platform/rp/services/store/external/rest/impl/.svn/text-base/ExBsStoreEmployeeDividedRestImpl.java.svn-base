package com.platform.rp.services.store.external.rest.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.store.external.rest.IExBsStoreEmployeeDividedRest;
import com.platform.rp.services.store.external.service.IExBsStoreEmpDividedService;
import com.platform.rp.services.store.external.service.IExStoreStatisticService;
import com.platform.rp.services.store.external.vo.BsStoreEmployeeDividedVo;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExBsStoreEmployeeDividedRestImpl implements IExBsStoreEmployeeDividedRest {

	private static final  Logger log=Logger.getLogger(ExBsStoreEmployeeDividedRestImpl.class);
	
	@Autowired
	IExBsStoreEmpDividedService bsStoreEmpDividedService;

	@Autowired
	private IExStoreStatisticService exStoreStatisticService;

	@Override
	public RestfulResult getDivEmpListByStoreId(long storeId) {
		try {
			List<BsStoreEmployeeDividedVo> entity=bsStoreEmpDividedService.getEmpListByStoreId(storeId);	
			return RestfulResultProvider.buildSuccessResult(new ResultData(entity));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}
	
	@Override
	public RestfulResult deleteManager(long storeId,long empId) {
		try {
			bsStoreEmpDividedService.deleteManager(storeId, empId);
			return RestfulResultProvider.buildSuccessResult();
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}		
	}

}
