package com.platform.rp.services.statistics.external.rest.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.statistics.core.dao.entity.ComprehensiveSatisicsEntity;
import com.platform.rp.services.statistics.external.rest.IComprehensiveSatisicsRest;
import com.platform.rp.services.statistics.external.service.IComprehensiveSatisicsService;
import com.platform.rp.services.statistics.external.service.IWapSatisticsService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@SuppressWarnings("rawtypes")
@Controller
public class ComprehensiveSatisicsRestImpl extends BaseController implements IComprehensiveSatisicsRest{
	
	
	
	@Autowired
	IComprehensiveSatisicsService comprehensiveSatisicsService;

	@Autowired
	IWapSatisticsService wapSatisticsService;
	
	public RestfulResult findStorManagerRanking(Map<String,String> entity){

		try {
			int  searchType = Integer.parseInt( entity.get("searchType"));
			//服务员打赏排行
			if(searchType==0){
				return wapSatisticsService.findEmpRanking(entity);
			//店长分成排行
			}else if(searchType==1){
				return wapSatisticsService.findStorManagerRanking(entity);
			//区域经理（总监)排行
			}else{
				return wapSatisticsService.findMerManagerRanking(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	
	public RestfulResult findMerManagerRanking(Map<String,String> entity){
		try {
			return wapSatisticsService.findMerManagerRanking(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	public Object findRealTimeDynamicAll(){
		Map<String,String> entity = new HashMap<>();
		BsMerchantsInfoEntity logvo = (BsMerchantsInfoEntity)getSessionCahceVal("loginVo");
		if(logvo==null){
			entity.put("orgCode", "1001");
		}else{
			String orgCode = logvo.getOrgCode();
			entity.put("orgCode", orgCode);
		}
		try {
			ComprehensiveSatisicsEntity data = comprehensiveSatisicsService.findRealTimeDynamicAll(entity);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	
	public RestfulResult findRealTimeDynamic(Map<String,String> entity){

		try {
			BsMerchantsInfoEntity logvo = (BsMerchantsInfoEntity)getSessionCahceVal("loginVo");
			if(logvo==null){
				entity.put("orgCode", "1001");
			}else{
				String orgCode = logvo.getOrgCode();
				entity.put("orgCode", orgCode);
			}

			return comprehensiveSatisicsService.findRealTimeDynamic(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	
	@Override
	public List<ComprehensiveSatisicsEntity> findCharStatistcs(
			Map<String, String> entity) {
		return comprehensiveSatisicsService.findCharStatistcs(entity);
	}

	@Override
	public ComprehensiveSatisicsEntity findCharStatistcsAll(
			Map<String, String> entity) {
		return comprehensiveSatisicsService.findCharStatistcsAll(entity);
	}

	@Override
	public RestfulResult findStoreRankingPage(Map params) {
		try {
			Page page =  comprehensiveSatisicsService.findStoreRankingPage(params);	
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage(),e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult findStaffRankingPage(Map params) {
		try {
			Page page = comprehensiveSatisicsService.findStaffRankingPage(params);
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public RestfulResult findLogsByData(Map params) {
		try {
			Page page = comprehensiveSatisicsService.findLogsByData(params);
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}

	@Override
	public List<ComprehensiveSatisicsEntity> findLogsByDataDetail(Map params) {
		return comprehensiveSatisicsService.findLogsByDataDetail(params);
	}
	public  RestfulResult findStoreSummaryPage(Map<String, Object> params){
		try {
			Page page = comprehensiveSatisicsService.findStoreSummaryPage(params);
			return RestfulResultProvider.buildSuccessResult(new ResultData(page));
		} catch (Exception e) {
			//e.printStackTrace();
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	public  List<ComprehensiveSatisicsEntity> findStoreSummaryDetail(Map<String, Object> params){
		return comprehensiveSatisicsService. findStoreSummaryDetail(params);
	}
}
