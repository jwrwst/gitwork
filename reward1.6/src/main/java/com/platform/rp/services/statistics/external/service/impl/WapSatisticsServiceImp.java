package com.platform.rp.services.statistics.external.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.common.utils.DateUtils;
import com.platform.rp.common.utils.PageUtils;
import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.organize.core.dao.BsMerchantsInfoDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.statistics.core.dao.WapSatisticsDAO;
import com.platform.rp.services.statistics.core.dao.entity.ComprehensiveSatisicsEntity;
import com.platform.rp.services.statistics.core.dao.entity.WapSatisticsEntity;
import com.platform.rp.services.statistics.external.service.IWapSatisticsService;
import com.platform.rp.util.DateUtil;
import com.platform.rp.util.response.result.RestfulResult;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class WapSatisticsServiceImp implements IWapSatisticsService{

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private WapSatisticsDAO<ComprehensiveSatisicsEntity> wapSatisticsDAO;

	@Autowired
	private BsMerchantsInfoDAO<BsMerchantsInfoEntity> merchantsInfoDAO;
	

    @Autowired
	IExBsMerchantsInfoService bsMerchantsInfoService;
	
	@Override
	public RestfulResult findEmpRanking(Map params) {
		buildDateType(params);
		buildStoreType(params);
		
		Page page = PageUtils.initWapPage(params);	
		// 查询总数
		List<WapSatisticsEntity> list = wapSatisticsDAO.findEmpRawardRanking(params);
		// 查询总数
		int count = wapSatisticsDAO.findEmpRawardRankingCount(params);
		
		return PageUtils.returnPage(list, count, page);
	}

	@Override
	public RestfulResult findEmpScoreRanking(Map params) {
		buildDateType(params);
		buildStoreType(params);
		
		Page page = PageUtils.initWapPage(params);	
		// 查询总数
		List<WapSatisticsEntity> list = wapSatisticsDAO.findEmpScoreRanking(params);
		// 查询总数
		int count = wapSatisticsDAO.findEmpScoreRankingCount(params);
		
		return PageUtils.returnPage(list, count, page);
	}
	
	@Override
	public RestfulResult findStorManagerRanking(Map params) {
		buildDateType(params);
		buildStoreType(params);
		Page page = PageUtils.initWapPage(params);		
		// 查询总数
		List<WapSatisticsEntity> list = wapSatisticsDAO.findStorManagerRanking(params);
		// 查询总数
		int count = wapSatisticsDAO.findStorManagerRankingCount(params);
		//logger.debug(arg0);
		return PageUtils.returnPage(list, count, page);
	}

	@Override
	public RestfulResult findMerManagerRanking(Map params) {

		buildDateType(params);
		buildStoreType(params);
		Page page = PageUtils.initWapPage(params);		
		// 查询总数
		List<WapSatisticsEntity> list = wapSatisticsDAO.findMerManagerRanking(params);
		// 查询总数
		int count = wapSatisticsDAO.findMerManagerRankingCount(params);
		
		return PageUtils.returnPage(list, count, page);
	}
	/**
	 * 根据日结时间获取结束时间
	 * @param thisDate
	 * @param beforeDate
	 * @param storeId
	 * @return
	 */
	private String getOffTime(String beforeDate,String thisDate,Map params){
    	String[] startAndEnd=new String[2];
    	if(params.get("storeId")!=null){
    		int storeId = Integer.parseInt(params.get("storeId")+"");
        	bsMerchantsInfoService.getStartAndEndTime(Long.valueOf(storeId), startAndEnd);
    	}else{
    		String orgCode = (String) params.get("orgCode");
        	bsMerchantsInfoService.getStartAndEndTime(orgCode, startAndEnd);   		
    	}
		int inTime = DateUtil.InTime(new Date(), startAndEnd[0], startAndEnd[0], "day");
		if(inTime == 0){
			return thisDate;
		}else{
			return beforeDate;
		}
	}
	/**
	 * 处理时间参数
	 * @param params
	 */
	private void buildDateType(Map params){
		int dateType = Integer.parseInt(params.get("dateType")+"");
		//int storeId = Integer.parseInt(params.get("storeId")+"");

		int type = DateUtils.YYYYYMMDD;
		String endTime,startTime;
		//0:今天，1昨天，2：本周，3：上周，4：本月，5上月
		switch (dateType) {
		case 0:
			startTime = getOffTime( DateUtils.getPerDate(type), 
					DateUtils.getToday(type),params);
			params.put("startTime", startTime);
			//params.put("endTime", DateUtils.getTomorrow());
			endTime = getOffTime( DateUtils.getToday(type),
					DateUtils.getTomorrow(type), params);
			params.put("endTime", endTime);
			break;
		case 1:
			startTime = getOffTime(DateUtils.getOffToday(-2,type),
					DateUtils.getPerDate(type), 
					params);
			params.put("startTime", startTime);
			//params.put("startTime", DateUtils.getPerDate(type));
			//params.put("endTime", DateUtils.getToday());
			endTime = getOffTime( DateUtils.getPerDate(type), 
					DateUtils.getToday(type),params);
			params.put("endTime", endTime);
			break;
		case 2:
			params.put("startTime", DateUtils.getMondayOfWeek(0,type));
			//params.put("endTime", DateUtils.getTomorrow());
			endTime = getOffTime( DateUtils.getToday(type), DateUtils.getTomorrow(type), params);
			params.put("endTime", endTime);
			break;
		case 3:
			params.put("startTime", DateUtils.getMondayOfWeek(-1,type) );
			params.put("endTime",DateUtils.getSundayOfWeek(-1,type));
			break;
		case 4:
			params.put("startTime", DateUtils.getMonthFirstDay(0,type));
			//params.put("endTime", DateUtils.getTomorrow());
			endTime = getOffTime( DateUtils.getToday(type), 
					DateUtils.getTomorrow(type), params);
			params.put("endTime", endTime);
			break;

		case 5:
			params.put("startTime", DateUtils.getMonthFirstDay(-1,type));
			params.put("endTime", DateUtils.getMonthFirstDay(0,type));
			break;
		case 6:
			startTime = (String) params.get("startTime");
			endTime = (String) params.get("endTime");
			params.put("startTime",startTime);
			try {
				params.put("endTime", DateUtils.getOffDate(endTime,1,type));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		default:
			throw new RuntimeException("dateType["+dateType+"] 参数错误!0:今天，1昨天，2：本周，3：上周，4：本月，5上月。 ");
		}
	}
	/**
	 * 处理门店还是店铺
	 * @param params
	 */
	private void buildStoreType(Map params){

		int storeType = Integer.parseInt(params.get("storeType")+"");
		//店铺
		if(storeType==1){
			int storeId	 = Integer.parseInt(params.get("storeId")+"");
			params.remove("storeId");
			BsMerchantsInfoEntity store = merchantsInfoDAO.getInfoByStoreID(storeId);
			String storeCode = store.getSchema();
			params.put("storeCode", storeCode);
		//商户
		}else if(storeType==2 || storeType==3){
			params.put("storeCode", params.get("orgCode"));
		}
	}

}
