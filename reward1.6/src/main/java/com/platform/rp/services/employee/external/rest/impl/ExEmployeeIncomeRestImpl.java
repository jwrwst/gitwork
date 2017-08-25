package com.platform.rp.services.employee.external.rest.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.services.employee.external.rest.IExEmployeeIncomeRest;
import com.platform.rp.services.employee.external.service.IExBsEmployeeExtEvaluateService;
import com.platform.rp.services.employee.external.service.IExBsEmployeeExtRewardService;
import com.platform.rp.services.employee.external.service.IExItRecordEmpLogService;
import com.platform.rp.services.employee.external.vo.EmployeeIncomeBaseInfoVo;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExEmployeeIncomeRestImpl implements IExEmployeeIncomeRest {
	
	private Log log = LogFactory.getLog(ExEmployeeIncomeRestImpl.class);
	
	@Autowired
	private IExBsEmployeeExtRewardService empExtRewardService;
	
	@Autowired
	private IExBsEmployeeExtEvaluateService empExtEvaluateService;
	
	@Autowired
	private IExItRecordEmpLogService itRecordEmpLogService;

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
			page = itRecordEmpLogService.getPage(page, params);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			log.info(e.getMessage());
			
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult getEmployeeIncomeBaseInfo(long empID) {
		
		try {
			HashMap<String, Object> returnMap = new HashMap<String, Object>();
			
			EmployeeIncomeBaseInfoVo rewardBaseInfo = empExtRewardService.getEmployeeIncomeBaseInfo(empID);
			EmployeeIncomeBaseInfoVo evaBaseInfo = empExtEvaluateService.getEmployeeIncomeBaseInfo(empID);
			
			returnMap.put("empid", rewardBaseInfo.getEmployeeID());
			returnMap.put("empnickname", rewardBaseInfo.getEmpNickname());
			returnMap.put("remainmoney", rewardBaseInfo.getRemainMoney());
			returnMap.put("headpic", rewardBaseInfo.getHeadPic());
			returnMap.put("level", rewardBaseInfo.getLevel());
			returnMap.put("totalmoney", rewardBaseInfo.getTotalMoney());
			returnMap.put("empname",  rewardBaseInfo.getEmpName());
			returnMap.put("evaluatecount", evaBaseInfo.getEvaluateCount());
			returnMap.put("avgevaluate", evaBaseInfo.getAvgEvaluateScore());
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(returnMap));
		} catch (Exception e) {
			log.info(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
}
