package com.platform.rp.services.employee.external.rest.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtEvaluateEntity;
import com.platform.rp.services.employee.external.rest.IExBsEmployeeExtEvaluateRest;
import com.platform.rp.services.employee.external.service.IExBsEmployeeExtEvaluateService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExBsEmployeeExtEvaluateRestImpl implements IExBsEmployeeExtEvaluateRest {
	
	private Log log = LogFactory.getLog(ExBsEmployeeExtEvaluateRestImpl.class);
	
	@Autowired
	private IExBsEmployeeExtEvaluateService extEvaluateService;

	@Override
	public RestfulResult save(BsEmployeeExtEvaluateEntity vo) {
		
		try {
			extEvaluateService.add(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}

	@Override
	public RestfulResult edit(BsEmployeeExtEvaluateEntity vo) {
		
		try {
			extEvaluateService.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
}
