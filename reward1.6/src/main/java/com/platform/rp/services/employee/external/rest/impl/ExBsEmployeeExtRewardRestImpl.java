package com.platform.rp.services.employee.external.rest.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtRewardEntity;
import com.platform.rp.services.employee.external.rest.IExBsEmployeeExtRewardRest;
import com.platform.rp.services.employee.external.service.IExBsEmployeeExtRewardService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExBsEmployeeExtRewardRestImpl implements IExBsEmployeeExtRewardRest {
	
	private Log log = LogFactory.getLog(ExBsEmployeeExtRewardRestImpl.class);
	
	@Autowired
	private IExBsEmployeeExtRewardService extRewardService;

	@Override
	public RestfulResult save(BsEmployeeExtRewardEntity vo) {
		
		try {
			extRewardService.add(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}

	@Override
	public RestfulResult edit(BsEmployeeExtRewardEntity vo) {
		
		try {
			extRewardService.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
}
