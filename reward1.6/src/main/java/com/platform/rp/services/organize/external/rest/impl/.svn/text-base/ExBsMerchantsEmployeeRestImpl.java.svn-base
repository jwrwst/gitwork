package com.platform.rp.services.organize.external.rest.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.organize.external.rest.IExBsMerchantsEmployeeRest;
import com.platform.rp.services.organize.external.service.IExBsMerchantsEmployeeService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExBsMerchantsEmployeeRestImpl implements IExBsMerchantsEmployeeRest {
	
	private Log log = LogFactory.getLog(ExBsMerchantsEmployeeRestImpl.class);
	
	@Autowired
	private IExBsMerchantsEmployeeService bsMerchantsEmployeeService;
	

	
	public  RestfulResult save(BsMerchantsEmployeeEntity entity){
		try {
			bsMerchantsEmployeeService.save(entity);
			return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
		
	}
	
	
	
	public RestfulResult getList(String orgCode){
		try {
			List<BsMerchantsEmployeeEntity> list=bsMerchantsEmployeeService.getList(orgCode);
			return RestfulResultProvider.buildSuccessResult(new ResultData(list));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
	
	public RestfulResult getPage(Map map){
		try {
			Page page=bsMerchantsEmployeeService.getPage(map);
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
	public RestfulResult deleteMerchantsEmployee(String orgCode,long empId){
		try {
			bsMerchantsEmployeeService.deleteMerchantsEmployee(orgCode, empId);
			return RestfulResultProvider.buildSuccessResult();
		} catch (Exception e) {
			log.error(e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SERVICE_EXECEPTION);
		}
	}
	
}
