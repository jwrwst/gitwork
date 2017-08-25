package com.platform.rp.services.store.external.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.external.service.impl.ExBsEmployeeExtRewardServiceImpl;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;
import com.platform.rp.services.employee.external.vo.EmployeeRankVo;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.store.core.dao.ItEvaluateInfoDAO;
import com.platform.rp.services.store.core.dao.entity.ItEvaluateInfoEntity;
import com.platform.rp.services.store.external.service.IExItEvaluateInfoService;
import com.platform.rp.services.store.external.vo.StoreEvaluateStatisticVo;
import com.platform.rp.services.store.external.vo.StorePeriodEvaluateScoreVo;
import com.platform.rp.util.DateUtil;

@Service
public class ExItEvaluateInfoServiceImpl implements IExItEvaluateInfoService {
	
	private Log log = LogFactory.getLog(ExBsEmployeeExtRewardServiceImpl.class);

	@Autowired
	private ItEvaluateInfoDAO<ItEvaluateInfoEntity> dao;
	
	@Autowired
	private IExBsMerchantsInfoService merchantsInfoService;

	@Override
	public void add(ItEvaluateInfoEntity vo) {
		dao.save(vo);
	}

	@Override
	public List<StoreEvaluateStatisticVo> getEvaluateStatisticByStoreID(String storeID, String filter,String startTime,String endTime) {
		
		Map<String, Object> params = getParameters(storeID, filter, startTime, endTime);
		
		return dao.getEvaluateStatisticByStoreID(params);
	}

	@Override
	public StorePeriodEvaluateScoreVo getPeriodEvaluateScoreByStoreID(String storeID, String filter,String startTime,String endTime) {
		
		Map<String, Object> params = getParameters(storeID, filter, startTime, endTime);
		
		return dao.getPeriodEvaluateScoreByStoreID(params);
	}
	
	private Map<String, Object> getParameters(String storeID, String filter,String startTime,String endTime)
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", storeID.split(","));
		params.put("filter", filter);
		if(filter.equals("custom")){

			params.put("busDateStart", startTime);
			params.put("busDateEnd", endTime);
		}else{
			Date startDate = new Date();
			Date endDate = new Date();
			
			if(filter.equals("week"))
			{
				startDate = DateUtil.getCurrentWeekMonday(new Date());
				endDate = DateUtil.getCurrentWeekSunday(new Date());
			}
			else if(filter.equals("lastweek"))
			{
				startDate = DateUtil.getLastWeekMonday(new Date());
				endDate = DateUtil.getLastWeekSunday(new Date());
			}
			else if(filter.equals("month"))
			{
				startDate = DateUtil.getCurrentMonthFirstDay(new Date());
				endDate = DateUtil.getCurrentMonthLastDay(new Date());
			}
			else if( filter.equals("lastmonth"))
			{
				startDate = DateUtil.getLastMonthFirstDay(new Date());
				endDate = DateUtil.getLastMonthLastDay(new Date());
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			int busDateStart = Integer.parseInt(sdf.format(startDate));
			int busDateEnd = Integer.parseInt(sdf.format(endDate));
			
			params.put("busDateStart", busDateStart);
			params.put("busDateEnd", busDateEnd);
		}
		return params;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page getPage(Page page, Map params) throws Exception {
		try {
			params.put("start", page.getStart());
//			params.put("end", page.getEnd());
			params.put("pageSize", page.getPageSize());
			
			//获取店铺的统计开始和结束时间
			String[] startAndEnd = new String[2];
			merchantsInfoService.getStartAndEndTime(Long.parseLong(params.get("storeId").toString()), startAndEnd);
			
			//根据过滤条件及时间修正范围计算今日/昨日/本周/上周/本月/上月的起始和结束时间
			String filter = params.get("filter").toString();
			if(filter.equals("day") || filter.equals("yesterday"))
			{
				Map<String, Object> dateMap = DateUtil.getDayAndYesterday(true, startAndEnd[0], startAndEnd[1]);
				params.put("startDay", dateMap.get("startDay"));
				params.put("endDay", dateMap.get("endDay"));
				params.put("startYesterday", dateMap.get("startYesterday"));
				params.put("endYesterday", dateMap.get("endYesterday"));
			}
			else if(filter.equals("week") || filter.equals("lastweek"))
			{
				Map<String, Object> dateMap = DateUtil.getWeekAndLastWeek(true, startAndEnd[0], startAndEnd[1]);
				params.put("startWeek", dateMap.get("startWeek"));
				params.put("endWeek", dateMap.get("endWeek"));
				params.put("startLastWeek", dateMap.get("startLastWeek"));
				params.put("endLastWeek", dateMap.get("endLastWeek"));
			}
			else if(filter.equals("month") || filter.equals("lastmonth"))
			{
				Map<String, Object> dateMap = DateUtil.getMonthAndLastMonth(true, startAndEnd[0], startAndEnd[1]);
				params.put("startMonth", dateMap.get("startMonth"));
				params.put("endMonth", dateMap.get("endMonth"));
				params.put("startLastMonth", dateMap.get("startLastMonth"));
				params.put("endLastMonth", dateMap.get("endLastMonth"));
			}
			
			// 查询总数
			int count = dao.count(params);
			
			//查询数据
			List<EmployeeRankVo> employeeRankList = dao.getPage(params);
			
			if(employeeRankList != null)
			{
				for(EmployeeRankVo vo : employeeRankList)
				{
					Date createTime =  vo.getCreateTime();
					
					//判断日期是否为今日或昨日
//					Map<String, Boolean> diffDayMap = DateUtil.getDiffDay(createTime);		
					Map<String, Boolean> diffDayMap = DateUtil.getDiffDay(createTime, startAndEnd[0], startAndEnd[1]);	
					if(diffDayMap.get("day"))    //今天
					{
						vo.setDate("今日");
					}
					else if(diffDayMap.get("yesterday"))   //昨天
					{
						vo.setDate("昨日");
					}
					else
					{
						String weekDay = DateUtil.getWeekDay(createTime);
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				        String date = sdf.format(createTime);
				        
				        vo.setDate(date + " " + weekDay);
					}
			        
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			        String time = sdf.format(createTime);
			        
			        vo.setTime(time);
				}
			}
			
			page.setResult(employeeRankList);
			page.setTotalCount(count);
			
			return page;
		} catch (Exception e) {
			log.error("业务处理失败" + e.getMessage());
			throw e;
		}
	}

	@Override
	public EmployeeRankDetailVo getEmployeeEvaluateRankDetail(String storeID, long empID) {
		
		//获取店铺的统计开始和结束时间
		String[] startAndEnd = new String[2];
		merchantsInfoService.getStartAndEndTime(storeID, startAndEnd);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", storeID);
		params.put("empId", empID);
		
		//获取今日/本周/本月起始时间
		Map<String, Object> dayAndYesterday = DateUtil.getDayAndYesterday(true, startAndEnd[0], startAndEnd[1]);
		Map<String, Object> weekAndLastWeek = DateUtil.getWeekAndLastWeek(true, startAndEnd[0], startAndEnd[1]);
		Map<String, Object> monthAndLastMonth = DateUtil.getMonthAndLastMonth(true, startAndEnd[0], startAndEnd[1]);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int dayStart = Integer.parseInt(sdf.format(new Date()));
		int weekStart = Integer.parseInt(sdf.format(new Date()));
		int monthStart = Integer.parseInt(sdf.format(new Date()));
		try {
			dayStart = Integer.parseInt(sdf.format(sdf1.parse(dayAndYesterday.get("startDay").toString())));
			weekStart = Integer.parseInt(sdf.format(sdf1.parse(weekAndLastWeek.get("startWeek").toString())));
			monthStart = Integer.parseInt(sdf.format(sdf1.parse(monthAndLastMonth.get("startMonth").toString())));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		params.put("dayStart", dayStart);
		params.put("weekStart", weekStart);
		params.put("monthStart", monthStart);
		
		EmployeeRankDetailVo empRankDetailVo = dao.getEmployeeEvaluateRankDetail(params);
				
		return empRankDetailVo;
	}

}
