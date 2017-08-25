package com.platform.rp.services.employee.external.rest.impl;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.services.employee.core.dao.entity.ItRecordEmpLogEntity;
import com.platform.rp.services.employee.external.rest.IExItRecordEmpLogRest;
import com.platform.rp.services.employee.external.service.IExItRecordEmpLogService;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExItRecordEmpLogRestImpl implements IExItRecordEmpLogRest {
	
	private Log log = LogFactory.getLog(ExItRecordEmpLogRestImpl.class);
	
	@Autowired
	private IExItRecordEmpLogService empLogService;

	/**
	 * 根据店铺ID获取该店店员打赏及评价记录
	 */
	@Override
	public Object getRecordLogListByStoreId(long storeid) {
		return empLogService.getRecordLogListByStoreId(storeid);
	}

	@Override
	public RestfulResult save(ItRecordEmpLogEntity vo) {
		
		try {
			empLogService.add(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}

	@Override
	public RestfulResult edit(ItRecordEmpLogEntity vo) {
		
		try {
			empLogService.update(vo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
		
		return RestfulResultProvider.buildCodeResult(CommonCode.SUCCESS);
	}

	@Override
	public RestfulResult toListPage(Map<String, String> params) {
		return this.getAll(params);
	}

	@Override
	public RestfulResult getAll(Map<String, String> params){
		try {
			// 初始化参数
			RequestModel model = new RequestModel();
			ApplicationUtils.parseFormValue(params, model);
			
			Page page = model.getPage();
			page = empLogService.getPage(page, params);
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			log.info(e.getMessage());
			
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
}
