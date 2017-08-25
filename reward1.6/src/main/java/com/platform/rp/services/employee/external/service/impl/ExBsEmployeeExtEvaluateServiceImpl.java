package com.platform.rp.services.employee.external.service.impl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.BsEmployeeExtEvaluateDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtEvaluateEntity;
import com.platform.rp.services.employee.external.service.IExBsEmployeeExtEvaluateService;
import com.platform.rp.services.employee.external.vo.EmployeeIncomeBaseInfoVo;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.store.external.vo.StoreRankVo;
import com.platform.rp.util.DateUtil;

@Service
public class ExBsEmployeeExtEvaluateServiceImpl implements IExBsEmployeeExtEvaluateService {
	private Log log = LogFactory.getLog(ExBsEmployeeExtEvaluateServiceImpl.class);

	@Autowired
	private BsEmployeeExtEvaluateDAO<BsEmployeeExtEvaluateEntity> empExtEvaluateDAO;
	
	@Autowired
	private IExBsMerchantsInfoService merchantsInfoService;

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
				params.put("startYesterday", dateMap.get("endYesterday"));
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
			int count = empExtEvaluateDAO.count(params);
			List<StoreRankVo> storeRankList = empExtEvaluateDAO.getPage(params);
			if((storeRankList != null) && (storeRankList.size() > 0))
			{
				//对于昨日/上周/上月数据，需要根据当前时间和数据库中更新时间做比较后，确定取哪个字段的数据
				for(int i = 0; i < storeRankList.size(); i++)
				{
					StoreRankVo vo = storeRankList.get(i);
					//本条数据更新时间
					Date updateTime = vo.getUpdateTime();
					if(null == updateTime)
						continue;
					
					//判断给定日期是否属于当日/昨日/本周/上周/本月/上月
					Map<String, Boolean> diffDayMap = DateUtil.getDiffDay(updateTime, startAndEnd[0], startAndEnd[1]);
					if(filter.equals("day"))
					{
						boolean day = diffDayMap.get("day");
						//更新时间不是当日
						if(!day)
						{
							vo.setRankCount(0);
							vo.setRankData(new BigDecimal("0.00"));
						}
					}
					else if(filter.equals("yesterday"))
					{
						boolean day = diffDayMap.get("day");
						boolean yesterday = diffDayMap.get("yesterday");
						
						//更新时间既不是当日也不是昨日
						if(!day && !yesterday)
						{
							vo.setRankCount(0);
							vo.setRankData(new BigDecimal("0.00"));
						}
						else if(yesterday)   //更新时间是昨日
						{
							vo.setRankCount(vo.getAlterRankCount());
							vo.setRankData(vo.getAlterRankData());
						}
					}
					else if(filter.equals("week"))
					{
						boolean week = diffDayMap.get("week");
						//更新时间不是本周
						if(!week)
						{
							vo.setRankCount(0);
							vo.setRankData(new BigDecimal("0.00"));
						}
					}
					else if(filter.equals("lastweek"))
					{	
						boolean week = diffDayMap.get("week");
						boolean lastWeek = diffDayMap.get("lastWeek");
						
						//更新时间既不是本周也不是上周
						if(!week && !lastWeek)
						{
							vo.setRankCount(0);
							vo.setRankData(new BigDecimal("0.00"));
						}
						else if(lastWeek)   //更新时间是上周
						{
							vo.setRankCount(vo.getAlterRankCount());
							vo.setRankData(vo.getAlterRankData());
						}
					}
					else if(filter.equals("month"))
					{
						boolean month = diffDayMap.get("month");
						//更新时间不是本月
						if(!month)
						{
							vo.setRankCount(0);
							vo.setRankData(new BigDecimal("0.00"));
						}
					}
					else if(filter.equals("lastmonth"))
					{
						boolean month = diffDayMap.get("month");
						boolean lastMonth = diffDayMap.get("lastMonth");
						
						//更新时间既不是本月也不是上月
						if(!month && !lastMonth)
						{
							vo.setRankCount(0);
							vo.setRankData(new BigDecimal("0.00"));
						}
						else if(lastMonth)   //更新时间是上月
						{
							vo.setRankCount(vo.getAlterRankCount());
							vo.setRankData(vo.getAlterRankData());
						}
					}
					
					//更新列表中数据
					storeRankList.set(i, vo);
				}
			}
			
			//排序
			Collections.sort(storeRankList, new Comparator<StoreRankVo>() {
	            public int compare(StoreRankVo arg0, StoreRankVo arg1) {
	            	if(0 != arg1.getRankData().compareTo(arg0.getRankData()))   //先根据打赏金额（评价星级）排序
	            		return arg1.getRankData().compareTo(arg0.getRankData());
	            	else    //打赏金额（评价星级）一样的，再根据打赏（评价）次数排序
	            	{
	            		Integer count1 = arg1.getRankCount();
	            		Integer count0 = arg0.getRankCount();
	            		
	            		return count1.compareTo(count0);
	            	}
	            }
	        });
			
			page.setResult(storeRankList);
			page.setTotalCount(count);
			
			return page;
		} catch (Exception e) {
			log.error("业务处理失败" + e.getMessage());
			throw e;
		}
	}

	@Override
	public void add(BsEmployeeExtEvaluateEntity vo) {
		empExtEvaluateDAO.save(vo);
	}

	@Override
	public EmployeeRankDetailVo getEmployeeEvaluateRankDetail(long storeID, long empID) 
	{
		//获取店铺的统计开始和结束时间
		String[] startAndEnd = new String[2];
		merchantsInfoService.getStartAndEndTime(storeID, startAndEnd);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("storeId", storeID);
		params.put("empId", empID);
		
		//获取今日/本周/本月起始和结束时间
		Map<String, Object> dayAndYesterday = DateUtil.getDayAndYesterday(true, startAndEnd[0], startAndEnd[1]);
		Map<String, Object> weekAndLastWeek = DateUtil.getWeekAndLastWeek(true, startAndEnd[0], startAndEnd[1]);
		Map<String, Object> monthAndLastMonth = DateUtil.getMonthAndLastMonth(true, startAndEnd[0], startAndEnd[1]);
		
		params.put("startMonth", monthAndLastMonth.get("startMonth"));
		params.put("endMonth", monthAndLastMonth.get("endMonth"));
		
		EmployeeRankDetailVo empRankDetailVo = empExtEvaluateDAO.getEmployeeEvaluateRankDetail(params);
		
		if(empRankDetailVo != null)
		{
			//根据update_time字段对今日和本周数据进行修正
			int inDay = DateUtil.dateInSpecifyPeriod(empRankDetailVo.getUpdateTime(), dayAndYesterday.get("startDay").toString(), dayAndYesterday.get("endDay").toString());
			if(-1 == inDay)
			{
				empRankDetailVo.setDayData(new BigDecimal("0.00"));
			}
			
			int inWeek = DateUtil.dateInSpecifyPeriod(empRankDetailVo.getUpdateTime(), weekAndLastWeek.get("startWeek").toString(), weekAndLastWeek.get("endWeek").toString());
			if(-1 == inWeek)
			{
				empRankDetailVo.setWeekData(new BigDecimal("0.00"));
			}
		}
		
		return empRankDetailVo;
	}

	@Override
	public void update(BsEmployeeExtEvaluateEntity vo) {
		empExtEvaluateDAO.update(vo);
	}
	
	@Override
	public EmployeeIncomeBaseInfoVo getEmployeeIncomeBaseInfo(long empId) {
		return empExtEvaluateDAO.getEmployeeIncomeBaseInfo(empId);
	}
}
