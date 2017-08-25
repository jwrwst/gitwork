package com.platform.rp.services.statistics.external.rest.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.statistics.external.rest.IWapSatisticsRest;
import com.platform.rp.services.statistics.external.service.IWapSatisticsService;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class WapSatisticsRest implements IWapSatisticsRest{

	
	@Autowired
	IWapSatisticsService wapSatisticsService;
	
	public RestfulResult findStorManagerRanking(Map<String,Object> entity){

		try {
			int  searchType = Integer.parseInt(entity.get("searchType")+"");
			//服务员打赏排行
			if(searchType==0){
				return wapSatisticsService.findEmpRanking(entity);
			//店长分成排行
			}else if(searchType==1){
				return wapSatisticsService.findEmpScoreRanking(entity);
			//店长分成排行
			}else if(searchType==2){
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
}
