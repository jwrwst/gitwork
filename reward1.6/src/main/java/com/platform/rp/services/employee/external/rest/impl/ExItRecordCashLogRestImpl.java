package com.platform.rp.services.employee.external.rest.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.services.employee.core.dao.entity.ItRecordCashLogEntity;
import com.platform.rp.services.employee.external.rest.IExItRecordCashLogRest;
import com.platform.rp.services.employee.external.service.IExItRecordCashLogService;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.info.codes.DataCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExItRecordCashLogRestImpl implements IExItRecordCashLogRest {
	
	private Log log = LogFactory.getLog(ExItRecordCashLogRestImpl.class);
	
	@Autowired
	private IExItRecordCashLogService cashLogService;

	@Override
	public RestfulResult toListPage(Map<String, String> params){
		return this.getAll(params);
	}
	
	@Override
	public RestfulResult getAll(Map<String, String> params) {

		try {
			// 初始化参数
			RequestModel model = new RequestModel();
			ApplicationUtils.parseFormValue(params, model);

			Page page = model.getPage();
			page = cashLogService.getPage(page, params);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			log.info(e.getMessage());
			
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult save(ItRecordCashLogEntity vo) {
		
		try {
			int retVal = cashLogService.add(vo);
			if(1 == retVal)
			{
				return RestfulResultProvider.buildCodeResult(DataCode.CC_ECHO_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}

	@Override
	public RestfulResult edit(ItRecordCashLogEntity vo) {
		
		try {
			cashLogService.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}
}
