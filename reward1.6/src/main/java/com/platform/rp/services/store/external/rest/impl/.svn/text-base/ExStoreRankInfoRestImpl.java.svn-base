package com.platform.rp.services.store.external.rest.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.services.employee.external.service.IExBsEmployeeExtEvaluateService;
import com.platform.rp.services.employee.external.service.IExBsEmployeeExtRewardService;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;
import com.platform.rp.services.store.external.rest.IExStoreRankInfoRest;
import com.platform.rp.services.store.external.service.IExItEvaluateInfoService;
import com.platform.rp.services.store.external.service.IExItRewardInfoService;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExStoreRankInfoRestImpl implements IExStoreRankInfoRest {
	
	private Log log = LogFactory.getLog(ExStoreRankInfoRestImpl.class);
	
	@Autowired
	private IExBsEmployeeExtRewardService empExtRewardService;
	
	@Autowired
	private IExBsEmployeeExtEvaluateService empExtEvaluateService;
	
	@Autowired
	private IExItRewardInfoService rewardInfoService;
	
	@Autowired
	private IExItEvaluateInfoService evaluateInfoService;
	
	@Override
	public RestfulResult toListPage(Map<String, String> params){
		return this.getAll(params);
	}
	
	@Override
	public RestfulResult getAll(Map<String, String> params) {

		try {
			String queryType = params.get("querytype");
			
			// 初始化参数
			RequestModel model = new RequestModel();
			ApplicationUtils.parseFormValue(params, model);

			Page page = model.getPage();
			if(queryType.equals("storeReward"))
				page = empExtRewardService.getPage(page, params);
			if(queryType.equals("storeEvaluate"))
				page = empExtEvaluateService.getPage(page, params);
			if(queryType.equals("empReward"))
				page = rewardInfoService.getPage(page, params);
			if(queryType.equals("empEvaluate"))
				page = evaluateInfoService.getPage(page, params);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			log.error(e.getMessage());
			
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult getEmployeeRewardRankDetail(long storeID, long empID) {
		try {
			return getEmployeeRankDetail(storeID, empID, 1);
		} catch (Exception e) {
			log.info(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult getEmployeeEvaluateRankDetail(long storeID, long empID) {
		try {
			return getEmployeeRankDetail(storeID, empID, 0);
		} catch (Exception e) {
			log.info(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	private RestfulResult getEmployeeRankDetail(long storeID, long empID, int queryType) {
		HashMap<String, Object> returnMap = new HashMap<String, Object>();
		
		EmployeeRankDetailVo rankDetail = null;
		if(0 == queryType)
			rankDetail = evaluateInfoService.getEmployeeEvaluateRankDetail(storeID+"", empID);
		else
			rankDetail = rewardInfoService.getEmployeeRewardRankDetail(storeID, empID);
		
		if(rankDetail != null)
		{
			returnMap.put("daydata", rankDetail.getDayData());
			returnMap.put("weekdata", rankDetail.getWeekData());
			returnMap.put("monthdata", rankDetail.getMonthData());
			returnMap.put("empnickname", rankDetail.getEmpNickname());
			returnMap.put("jobnumber", rankDetail.getJobNumber());
			returnMap.put("empname", rankDetail.getEmpName());
		}
		
		return RestfulResultProvider.buildSuccessResult(new ResultData(returnMap));
	}
}
