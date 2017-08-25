package com.platform.rp.services.store.external.rest.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.framework.mvcface.vo.RequestModel;
import com.platform.rp.services.employee.external.service.IExItRecordEmpLogService;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.services.store.core.dao.entity.ItEvaluateInfoEntity;
import com.platform.rp.services.store.external.rest.IExStoreDynamicInfoRest;
import com.platform.rp.services.store.external.service.IExBsStoreInfoService;
import com.platform.rp.services.store.external.vo.StoreDynamicInfoVo;
import com.platform.rp.util.ApplicationUtils;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExStoreDynamicInfoRestImpl implements IExStoreDynamicInfoRest {
	
	private Log log = LogFactory.getLog(ExStoreDynamicInfoRestImpl.class);
	
	@Autowired
	private IExBsStoreInfoService storeInfoService;
	
	@Autowired
	private IExItRecordEmpLogService recordEmpLogService;
	
	@Autowired
	private IExBsMerchantsInfoService merchantsInfoService;
	
	
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
			page = recordEmpLogService.getPage(page, params);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			log.error(e.getMessage());
			
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	/**
	 * 根据openID获取店铺信息
	 */
	@Override
	public RestfulResult getStoreListByEmpId(long empID,String orgCode) {
		
		try {
			
			List<BsStoreInfoEntity> storeInfoList = storeInfoService.getStoreListByEmpId(empID,orgCode);
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(storeInfoList));
		} catch (Exception e) {
			log.info(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult getStoreDynamicByStoreId(long storeID) {
		try {
			//当日打赏和昨日打赏数据
			StoreDynamicInfoVo storeRewardDyn = storeInfoService.getStoreEmpExtRewardByStoreId(storeID);
			//当日评价和昨日评价数据
			StoreDynamicInfoVo storeEvaluateDyn = storeInfoService.getStoreEmpExtEvaluateByStoreId(storeID);
			
			HashMap<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("rewarddaycount", 0);
			returnMap.put("rewardyesterdaycount", 0);
			returnMap.put("rewarddaymoney", 0);
			returnMap.put("rewardyesterdaymoney", 0);
			returnMap.put("evaluatedaycount", 0);
			returnMap.put("evaluateyesterdaycount", 0);
			returnMap.put("evaluatedayscore", 0);
			returnMap.put("evaluateyesterdayscore", 0);
			
			if(storeRewardDyn != null)
			{
				returnMap.put("rewarddaycount", storeRewardDyn.getDayCount());
				returnMap.put("rewardyesterdaycount", storeRewardDyn.getYesterdayCount());
				returnMap.put("rewarddaymoney", storeRewardDyn.getDayData());
				returnMap.put("rewardyesterdaymoney", storeRewardDyn.getYesterdayData());
			}
			
			if(storeEvaluateDyn != null)
			{
				returnMap.put("evaluatedaycount", storeEvaluateDyn.getDayCount());
				returnMap.put("evaluateyesterdaycount", storeEvaluateDyn.getYesterdayCount());
				returnMap.put("evaluatedayscore", storeEvaluateDyn.getDayData());
				returnMap.put("evaluateyesterdayscore", storeEvaluateDyn.getYesterdayData());
			}
			
			return RestfulResultProvider.buildSuccessResult(new ResultData(returnMap));
		} catch (Exception e) {
			log.info(e.getMessage());
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	
	public static void main(String[] args) 
	{
//		BsEmployeeExtEvaluateEntity entity = new BsEmployeeExtEvaluateEntity();
//		entity.setDayCount(2);
//		entity.setDayScore(new BigDecimal(5.00));
//		entity.setWeekCount(9);
//		entity.setWeekScore(new BigDecimal(4.00));
//		entity.setMonthCount(16);
//		entity.setMonthScore(new BigDecimal(4.00));
//		entity.setYesterdayCount(0);
//		entity.setYesterdayScore(new BigDecimal(5.00));
//		entity.setLastweekCount(8);
//		entity.setLastweekScore(new BigDecimal(4.00));
//		entity.setLastmonthCount(10);
//		entity.setLastmonthScore(new BigDecimal(4.00));
//		entity.setEmpId(43);
//		entity.setStoreId(10);
//		entity.setTotalCount(55);
//		entity.setTotalScore(new BigDecimal(4.00));
		
//		BsEmployeeExtRewardEntity entity = new BsEmployeeExtRewardEntity();
//		entity.setDayCount(2);
//		entity.setDayMoney(new BigDecimal(5.88));
//		entity.setWeekCount(9);
//		entity.setWeekMoney(new BigDecimal(14.01));
//		entity.setMonthCount(16);
//		entity.setMonthMoney(new BigDecimal(56.43));
//		entity.setYesterdayCount(4);
//		entity.setYesterdayMoney(new BigDecimal(6.25));
//		entity.setLastweekCount(8);
//		entity.setLastweekMoney(new BigDecimal(9.98));
//		entity.setLastmonthCount(10);
//		entity.setLastmonthMoney(new BigDecimal(34.99));
//		entity.setEmpId(43);
//		entity.setStoreId(10);
//		entity.setTotalCount(59);
//		entity.setTotalMoney(new BigDecimal(124.36));
//		entity.setCashCount(7);
//		entity.setCashMoney(new BigDecimal(35.82));
		
		ItEvaluateInfoEntity entity = new ItEvaluateInfoEntity();
		entity.setEmpId(43);
		entity.setServiceScore(5);
		entity.setCustomerId("bbb");
		entity.setEvaluate("不错不错不错");
		entity.setStoreId(10);

//		ItRewardInfoEntity entity = new ItRewardInfoEntity();
//		entity.setCustomerId("mmm");
//		entity.setEmpId(43);
//		entity.setStoreId(10);
//		entity.setRewardAmount(0.01);
		
//		Map<String, Object> entity = new HashMap<String, Object>();
//		entity.put("pageNum", 1);
//		entity.put("numPerPage", 10);
//		entity.put("storeid", 9);
//		entity.put("filter", "day");
		
    	try {
    		System.out.println(JSONObject.fromObject(entity).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
