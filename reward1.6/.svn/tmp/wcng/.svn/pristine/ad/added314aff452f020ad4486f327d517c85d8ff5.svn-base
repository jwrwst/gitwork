package com.platform.rp.services.store.external.rest.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsStoreEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.organize.external.service.IExBsMerchantsStoreService;
import com.platform.rp.services.store.core.dao.entity.BsStoreAuthDetailEntity;
import com.platform.rp.services.store.core.dao.entity.BsStoreInfoEntity;
import com.platform.rp.services.store.external.rest.IExStoreStatisticInfoRest;
import com.platform.rp.services.store.external.service.IExItEvaluateInfoService;
import com.platform.rp.services.store.external.service.IExItRewardInfoService;
import com.platform.rp.services.store.external.service.IExStoreAuthDetailService;
import com.platform.rp.services.store.external.vo.StoreEvaluateStatisticVo;
import com.platform.rp.services.store.external.vo.StorePeriodEvaluateScoreVo;
import com.platform.rp.services.store.external.vo.StoreRewardStatisticVo;
import com.platform.rp.services.store.external.vo.StoreStatisticVo;
import com.platform.rp.util.info.codes.CommonCode;
import com.platform.rp.util.response.RestfulResultProvider;
import com.platform.rp.util.response.data.ResultData;
import com.platform.rp.util.response.result.RestfulResult;

@Controller
public class ExStoreStatisticInfoRestImpl implements IExStoreStatisticInfoRest {
	
	private Log log = LogFactory.getLog(ExStoreStatisticInfoRestImpl.class);
	
	@Autowired
	private IExItEvaluateInfoService exItEvaluateInfoService;
	@Autowired
	private IExItRewardInfoService exItRewardInfoService;
	@Autowired
	private IExStoreAuthDetailService storeAuthDetailService;
	@Autowired
	private IExBsMerchantsInfoService merchantsInfoService;

	@Autowired
	private IExBsMerchantsStoreService merchantsStoreService;
	@Override
	public RestfulResult getStoreStatisticInfoByStoreID(long storeID, String filter) {
		try {
			//获取店铺开放权限（评价/打赏/评价及打赏）
			String authCode = "";
			List<BsStoreAuthDetailEntity> storeAuthDetailList = storeAuthDetailService.getStoreAuthDetailByStoreId(storeID);
			for(BsStoreAuthDetailEntity entity : storeAuthDetailList)
			{
				authCode = entity.getAuthCode();
			}
			
			//存储统计数据的三个List
			List<StoreEvaluateStatisticVo> evaluateStaList = null;
			StorePeriodEvaluateScoreVo peroidScore = null;
			List<StoreRewardStatisticVo> rewardStaList = null;
			
			//获取统计数据
			if(authCode.equals("1001"))  //仅评价
			{
				evaluateStaList = exItEvaluateInfoService.getEvaluateStatisticByStoreID(storeID+"", filter,null,null);
				peroidScore = exItEvaluateInfoService.getPeriodEvaluateScoreByStoreID(storeID+"", filter,null,null);
			}
			else if(authCode.equals("1002"))  //仅打赏
			{
				rewardStaList = exItRewardInfoService.getStoreRewardStatisticByStoreID(storeID+"", filter,null,null);
			}
			else if(authCode.equals("1003"))  //评价和打赏
			{
				evaluateStaList = exItEvaluateInfoService.getEvaluateStatisticByStoreID(storeID+"", filter,null,null);
				peroidScore = exItEvaluateInfoService.getPeriodEvaluateScoreByStoreID(storeID+"", filter,null,null);
				rewardStaList = exItRewardInfoService.getStoreRewardStatisticByStoreID(storeID+"", filter,null,null);
			}

			List<StoreStatisticVo> storeStatisticList = buildDate(filter, authCode, evaluateStaList, peroidScore, rewardStaList);
			return RestfulResultProvider.buildSuccessResult(new ResultData(storeStatisticList));
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	public RestfulResult getStoreStatisticInfoByOrgCode(String orgCode,String startTime,String endTime) {
		try {
			//List<BsStoreAuthDetailEntity> storeAuthDetailList = storeAuthDetailService.getStoreAuthDetailByStoreId(storeID);
			BsMerchantsInfoEntity merchantsInfoEntity = merchantsInfoService.findByOrgCode(orgCode);
			//获取店铺开放权限（评价/打赏/评价及打赏）
			String authCode = merchantsInfoEntity.getAuthType();
			
			//存储统计数据的三个List
			List<StoreEvaluateStatisticVo> evaluateStaList = null;
			StorePeriodEvaluateScoreVo peroidScore = null;
			List<StoreRewardStatisticVo> rewardStaList = null;
		
			String storeID = "";
			List<BsMerchantsStoreEntity>  mses = merchantsStoreService.getAllListByOrgCode(orgCode);
			for (int i = 0; i < mses.size(); i++) {
				storeID += mses.get(i).getStoreId()+",";
			}
			if(storeID.length()>0){
				storeID = storeID.substring(0, storeID.length()-1);
			}
			//获取统计数据
			if(authCode.equals("1001"))  //仅评价
			{
				evaluateStaList = exItEvaluateInfoService.getEvaluateStatisticByStoreID(storeID+"", "custom", startTime, endTime);
				peroidScore = exItEvaluateInfoService.getPeriodEvaluateScoreByStoreID(storeID+"",  "custom", startTime, endTime);
			}
			else if(authCode.equals("1002"))  //仅打赏
			{
				rewardStaList = exItRewardInfoService.getStoreRewardStatisticByStoreID(storeID+"",  "custom", startTime, endTime);
			}
			else if(authCode.equals("1003"))  //评价和打赏
			{
				evaluateStaList = exItEvaluateInfoService.getEvaluateStatisticByStoreID(storeID+"",  "custom", startTime, endTime);
				peroidScore = exItEvaluateInfoService.getPeriodEvaluateScoreByStoreID(storeID+"",  "custom", startTime, endTime);
				rewardStaList = exItRewardInfoService.getStoreRewardStatisticByStoreID(storeID+"",  "custom", startTime, endTime);
			}


			//List<StoreStatisticVo> storeStatisticList = buildDate("custom", authCode, evaluateStaList, peroidScore, rewardStaList);

			List<StoreStatisticVo> storeStatisticList = buildCustomDate("custom", authCode, evaluateStaList, peroidScore, rewardStaList,
					startTime, endTime);
			return RestfulResultProvider.buildSuccessResult(new ResultData(storeStatisticList));
		} catch (Exception e) {
			log.error(e.getMessage()+"orgCode",e);
			return RestfulResultProvider.buildCodeResult(CommonCode.SYS_EXECEPTION, e.getMessage());
		}
	}
	private List<StoreStatisticVo> buildCustomDate(String filter,String authCode,List<StoreEvaluateStatisticVo> evaluateStaList,
			StorePeriodEvaluateScoreVo peroidScore,	List<StoreRewardStatisticVo> rewardStaList,String startTime,String endTime) throws ParseException{


		//按照前端需要的格式组织数据
		List<StoreStatisticVo> storeStatisticList = new ArrayList<StoreStatisticVo>();
//		//计算月份
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		int month = cal.get(Calendar.MONTH) + 1;   //本月
//		if(filter .equals("lastmonth"))   //上月
//		{
//			if(1 == month)
//				month = 12;
//			else
//				month -= 1;
//		}

		//打赏次数&打赏金额&收到打赏店员数
		//rewardStaList为空有两种情况，一种是店铺权限为仅评价，另一种是数据库中没有当日或昨日数据
		if((rewardStaList != null)  || ((null == rewardStaList) && !authCode.equals("1001")))
		{
			//初始化存储打赏次数&打赏金额&收到打赏店员数的Map
			HashMap<String, String> perDayCountMap = makeMonthMap(startTime, endTime, "0");
			HashMap<String, String> perDayMoneyMap = makeMonthMap(startTime, endTime, "0.00");
			HashMap<String, String> perDayEmpCountMap = makeMonthMap(startTime, endTime, "0");
			
			double count = 0,empCount = 0;
			BigDecimal money = new BigDecimal(0);
			//用数据库中取出的数据填充打赏次数&打赏金额&收到打赏店员数的Map
			if(rewardStaList != null && rewardStaList.size() > 0) 
			{
				for(StoreRewardStatisticVo vo : rewardStaList)
				{
					//获取本条数据的日期
					String date = getDate(vo.getBusDate());
					int pcou = vo.getPerDayCount();
					count+=pcou;
					int ecou = vo.getPerDayEmpCount();
					empCount+=ecou;
					BigDecimal dmon = vo.getPerDayMoney();
					money = money.add(dmon);
			        //填充Map
					perDayCountMap.put(date, "" + pcou);
					perDayMoneyMap.put(date,   dmon+"");
					perDayEmpCountMap.put(date,  "" + ecou);
				}
			}
			
			//根据格式组织打赏次数&打赏金额&收到打赏店员数的数据
			StoreStatisticVo rewardCountVo = getVoFromMap("打赏次数    "+count, perDayCountMap);
			StoreStatisticVo rewardMoneyVo = getVoFromMap("打赏金额    "+money, perDayMoneyMap);
			StoreStatisticVo rewardEmpCountVo = getVoFromMap("收到打赏店员数  "+empCount, perDayEmpCountMap);
			
			//添加到返回数据的List中
			storeStatisticList.add(rewardCountVo);
			storeStatisticList.add(rewardMoneyVo);
			storeStatisticList.add(rewardEmpCountVo);
		}
		
		//服务评级&评价人数 
		//evaluateStaList为空有两种情况，一种是店铺权限为仅打赏，另一种是数据库中没有当日或昨日数据
		if((evaluateStaList != null)  || ((null == evaluateStaList) && !authCode.equals("1002")))
		{
			//初始化存储服务评级&评价人数的Map
			HashMap<String, String> serviceScroeMap = makeMonthMap(startTime, endTime, "0");
			HashMap<String, String> evaluatePersonMap = makeMonthMap(startTime, endTime, "0");

			//double count = 0,empCount = 0;
			//BigDecimal score = new BigDecimal(0);
			//用数据库中取出的数据填充服务评级&评价人数的Map
			if(evaluateStaList != null && evaluateStaList.size() > 0) 
	        {
				for(StoreEvaluateStatisticVo vo : evaluateStaList)
				{
					//获取本条数据的日期
					String date = getDate(vo.getBusDate());
					
					int dcoun = vo.getPerDayCount();
					//count += dcoun;
					BigDecimal dscore = vo.getPerDayScore();
					//score.add(dscore);
			        //填充Map
					serviceScroeMap.put(date, dscore.toString());
					evaluatePersonMap.put(date,  "" + dcoun);
				}
	        }
			
			//根据格式组织服务评级&评价人数的数据
			StoreStatisticVo serviceScoreVo = getVoFromMap("服务评级", serviceScroeMap);
			StoreStatisticVo evaluatePersonVo = getVoFromMap("评价人数", evaluatePersonMap);
			
			//添加到返回数据的List中
			storeStatisticList.add(serviceScoreVo);
			storeStatisticList.add(evaluatePersonVo);
		}
		
		
		//评价分布
		if(peroidScore != null)
		{
			StoreStatisticVo scoreDistributeVo = new StoreStatisticVo();
			
			scoreDistributeVo.setTitle("评价分布");
			
			List<String> rowList = new ArrayList<String>();
			rowList.add("1");
			rowList.add("2");
			rowList.add("3");
			rowList.add("4");
			rowList.add("5");
			scoreDistributeVo.setRows(rowList);
			
			List<String> dataList = new ArrayList<String>();
			dataList.add("" + peroidScore.getOneScoreCount());
			dataList.add("" + peroidScore.getTwoScoreCount() );
			dataList.add("" + peroidScore.getThreeScoreCount());
			dataList.add("" + peroidScore.getFourScoreCount() );
			dataList.add("" + peroidScore.getFiveScoreCount() );
			scoreDistributeVo.setData(dataList);
						
			//添加到返回数据的List中
			storeStatisticList.add(scoreDistributeVo);
		}
		return storeStatisticList;
	}
	private List<StoreStatisticVo> buildDate(String filter,String authCode,List<StoreEvaluateStatisticVo> evaluateStaList,
			StorePeriodEvaluateScoreVo peroidScore,	List<StoreRewardStatisticVo> rewardStaList) throws ParseException{

		//按照前端需要的格式组织数据
		List<StoreStatisticVo> storeStatisticList = new ArrayList<StoreStatisticVo>();
		
		//本周或上周数据
		if(filter.equals("week") || filter .equals("lastweek"))
		{
			//打赏次数&打赏金额&收到打赏店员数
			//rewardStaList为空有两种情况，一种是店铺权限为仅评价，另一种是数据库中没有当日或昨日数据
			if((rewardStaList != null)  || ((null == rewardStaList) && !authCode.equals("1001")))
			{
				//初始化存储打赏次数&打赏金额&收到打赏店员数的Map
				HashMap<String, String> perDayCountMap = makeWeekMap("0");
				HashMap<String, String> perDayMoneyMap = makeWeekMap("0.00");
				HashMap<String, String> perDayEmpCountMap = makeWeekMap("0");
				
				//用数据库中取出的数据填充打赏次数&打赏金额&收到打赏店员数的Map
				if(rewardStaList != null && rewardStaList.size() > 0) 
				{
					String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
			        Calendar cal = Calendar.getInstance();
			        
					for(StoreRewardStatisticVo vo : rewardStaList)
					{
						//获取本条数据是周几
						SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
						Date date = sdf.parse(Integer.toString(vo.getBusDate()));
//						cal.setTime(vo.getRewardTime());
						cal.setTime(date);
						
						int weekIndex = cal.get(Calendar.DAY_OF_WEEK)  - 1;
				        if(weekIndex < 0)
				        {
				        	weekIndex = 0;
				        }
				        
				        //填充Map
				        perDayCountMap.put(weeks[weekIndex], "" + vo.getPerDayCount());
				        perDayMoneyMap.put(weeks[weekIndex],   vo.getPerDayMoney().toString());
				        perDayEmpCountMap.put(weeks[weekIndex],  "" + vo.getPerDayEmpCount());
					}
				}
				
				//根据格式组织打赏次数&打赏金额&收到打赏店员数的数据
				StoreStatisticVo rewardCountVo = getVoFromMap("打赏次数", perDayCountMap);
				StoreStatisticVo rewardMoneyVo = getVoFromMap("打赏金额", perDayMoneyMap);
				StoreStatisticVo rewardEmpCountVo = getVoFromMap("收到打赏店员数", perDayEmpCountMap);
				
				//添加到返回数据的List中
				storeStatisticList.add(rewardCountVo);
				storeStatisticList.add(rewardMoneyVo);
				storeStatisticList.add(rewardEmpCountVo);
			}
			
			//服务评级&评价人数 
			//evaluateStaList为空有两种情况，一种是店铺权限为仅打赏，另一种是数据库中没有当日或昨日数据
			if((evaluateStaList != null)  || ((null == evaluateStaList) && !authCode.equals("1002")))
			{
				//初始化存储服务评级&评价人数的Map
				HashMap<String, String> serviceScroeMap = makeWeekMap("0");
				HashMap<String, String> evaluatePersonMap = makeWeekMap("0");

		        //用数据库中取出的数据填充服务评级&评价人数的Map
		        if(evaluateStaList != null && evaluateStaList.size() > 0) 
		        {
		        	String[] weeks = {"周日","周一","周二","周三","周四","周五","周六"};
			        Calendar cal = Calendar.getInstance();
			        
					for(StoreEvaluateStatisticVo vo : evaluateStaList)
					{
						//获取本条数据是周几
						SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMdd");
						Date date = sdf.parse(Integer.toString(vo.getBusDate()));
//						cal.setTime(vo.getTimstamp());
						cal.setTime(date);
						
						int weekIndex = cal.get(Calendar.DAY_OF_WEEK)  - 1;
				        if(weekIndex < 0)
				        {
				        	weekIndex = 0;
				        }
				        
				        //填充Map
				        serviceScroeMap.put(weeks[weekIndex], vo.getPerDayScore().toString());
				        evaluatePersonMap.put(weeks[weekIndex],  "" + vo.getPerDayCount());
					}
		        }
				
				//根据格式组织服务评级&评价人数的数据
				StoreStatisticVo serviceScoreVo = getVoFromMap("服务评级", serviceScroeMap);
				StoreStatisticVo evaluatePersonVo = getVoFromMap("评价人数", evaluatePersonMap);
				
				//添加到返回数据的List中
				storeStatisticList.add(serviceScoreVo);
				storeStatisticList.add(evaluatePersonVo);
			}
		}
		
		//本月或上月数据
		else if(filter.equals("month") || filter .equals("lastmonth"))
		{
			//计算月份
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int month = cal.get(Calendar.MONTH) + 1;   //本月
			if(filter .equals("lastmonth"))   //上月
			{
				if(1 == month)
					month = 12;
				else
					month -= 1;
			}

			//打赏次数&打赏金额&收到打赏店员数
			//rewardStaList为空有两种情况，一种是店铺权限为仅评价，另一种是数据库中没有当日或昨日数据
			if((rewardStaList != null)  || ((null == rewardStaList) && !authCode.equals("1001")))
			{
				//初始化存储打赏次数&打赏金额&收到打赏店员数的Map
				HashMap<String, String> perDayCountMap = makeMonthMap(month, "0");
				HashMap<String, String> perDayMoneyMap = makeMonthMap(month, "0.00");
				HashMap<String, String> perDayEmpCountMap = makeMonthMap(month, "0");
				
				//用数据库中取出的数据填充打赏次数&打赏金额&收到打赏店员数的Map
				if(rewardStaList != null && rewardStaList.size() > 0) 
				{
					for(StoreRewardStatisticVo vo : rewardStaList)
					{
						//获取本条数据的日期
						String date = getDate(vo.getBusDate());
				        
				        //填充Map
						perDayCountMap.put(date, "" + vo.getPerDayCount());
						perDayMoneyMap.put(date,   vo.getPerDayMoney().toString());
						perDayEmpCountMap.put(date,  "" + vo.getPerDayEmpCount());
					}
				}
				
				//根据格式组织打赏次数&打赏金额&收到打赏店员数的数据
				StoreStatisticVo rewardCountVo = getVoFromMap("打赏次数", perDayCountMap);
				StoreStatisticVo rewardMoneyVo = getVoFromMap("打赏金额", perDayMoneyMap);
				StoreStatisticVo rewardEmpCountVo = getVoFromMap("收到打赏店员数", perDayEmpCountMap);
				
				//添加到返回数据的List中
				storeStatisticList.add(rewardCountVo);
				storeStatisticList.add(rewardMoneyVo);
				storeStatisticList.add(rewardEmpCountVo);
			}
			
			//服务评级&评价人数 
			//evaluateStaList为空有两种情况，一种是店铺权限为仅打赏，另一种是数据库中没有当日或昨日数据
			if((evaluateStaList != null)  || ((null == evaluateStaList) && !authCode.equals("1002")))
			{
				//初始化存储服务评级&评价人数的Map
				HashMap<String, String> serviceScroeMap = makeMonthMap(month, "0");
				HashMap<String, String> evaluatePersonMap = makeMonthMap(month, "0");
				
				//用数据库中取出的数据填充服务评级&评价人数的Map
				if(evaluateStaList != null && evaluateStaList.size() > 0) 
		        {
					for(StoreEvaluateStatisticVo vo : evaluateStaList)
					{
						//获取本条数据的日期
						String date = getDate(vo.getBusDate());
				        
				        //填充Map
						serviceScroeMap.put(date, vo.getPerDayScore().toString());
						evaluatePersonMap.put(date,  "" + vo.getPerDayCount());
					}
		        }
				
				//根据格式组织服务评级&评价人数的数据
				StoreStatisticVo serviceScoreVo = getVoFromMap("服务评级", serviceScroeMap);
				StoreStatisticVo evaluatePersonVo = getVoFromMap("评价人数", evaluatePersonMap);
				
				//添加到返回数据的List中
				storeStatisticList.add(serviceScoreVo);
				storeStatisticList.add(evaluatePersonVo);
			}
		}
		
		//评价分布
		if(peroidScore != null)
		{
			StoreStatisticVo scoreDistributeVo = new StoreStatisticVo();
			
			scoreDistributeVo.setTitle("评价分布");
			
			List<String> rowList = new ArrayList<String>();
			rowList.add("1");
			rowList.add("2");
			rowList.add("3");
			rowList.add("4");
			rowList.add("5");
			scoreDistributeVo.setRows(rowList);
			
			List<String> dataList = new ArrayList<String>();
			dataList.add("" + peroidScore.getOneScoreCount());
			dataList.add("" + peroidScore.getTwoScoreCount() );
			dataList.add("" + peroidScore.getThreeScoreCount());
			dataList.add("" + peroidScore.getFourScoreCount() );
			dataList.add("" + peroidScore.getFiveScoreCount() );
			scoreDistributeVo.setData(dataList);
						
			//添加到返回数据的List中
			storeStatisticList.add(scoreDistributeVo);
		}
		return storeStatisticList;
	}
	//根据星期初始化Map数据
	private LinkedHashMap<String, String> makeWeekMap(String defaultVal)
	{
		LinkedHashMap<String, String> weekMap = new LinkedHashMap<String, String>();
		
		weekMap.put("周一", defaultVal);
		weekMap.put("周二", defaultVal);
		weekMap.put("周三", defaultVal);
		weekMap.put("周四", defaultVal);
		weekMap.put("周五", defaultVal);
		weekMap.put("周六", defaultVal);
		weekMap.put("周日", defaultVal);
		
		return weekMap;
	}
	public static void main(String[] args) {
		try {
			LinkedHashMap<String, String> monthMap = new ExStoreStatisticInfoRestImpl().makeMonthMap("20160301", "20160332", "0");
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根据月份初始化Map数据
	private LinkedHashMap<String, String> makeMonthMap(String startTime,String endTime, String defaultVal) throws ParseException
	{
		LinkedHashMap<String, String> monthMap = new LinkedHashMap<String, String>();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
		
		Date dBegin = sFormat.parse(startTime); 
        Calendar calBegin = Calendar.getInstance();  
        calBegin.setTime(dBegin);  

		Date dEnd = sFormat.parse(endTime);
        Calendar calEnd = Calendar.getInstance();  
        calEnd.setTime(dEnd);  
        //System.out.println("calEnd:"+calEnd.get(Calendar.YEAR)+"-" +getDateKey(calBegin));
        //把当天算上
        calBegin.add(Calendar.DAY_OF_MONTH, -1);
        calEnd.add(Calendar.DAY_OF_MONTH, -1);
        // 测试此日期是否在指定日期之后    
        int i = 0;
        while (calEnd.after(calBegin)) {  
        	i++;
        	if(i>=3000){
        		break;
        	}
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            String key = getDateKey(calBegin);
            monthMap.put(key, defaultVal);
          // System.out.println(calBegin.get(Calendar.YEAR)+"-" +key);
        }  
		return monthMap;
	}
	private String getDateKey( Calendar cal){

        int mon = cal.get(Calendar.MONTH)+1;

        String smon = mon+"";
        if(mon<10){
        	smon = "0"+mon;
        }
        int da = cal.get(Calendar.DATE);
        if(da<10){
        	return smon+"-0"+da;
        }else{
            return smon+"-"+da;
        	
        }
	}
	//根据月份初始化Map数据
	private LinkedHashMap<String, String> makeMonthMap(int month, String defaultVal)
	{
		LinkedHashMap<String, String> monthMap = new LinkedHashMap<String, String>();

		if((1 == month) || (3 == month) || (5 == month) || (7 == month) || (8 == month) || (10 == month) || (12 == month))   //大月
		{
			for(int i = 1; i <= 31; i++)
			{
				String date = formatDate(month, i);
				monthMap.put(date, defaultVal);
			}
		}
		else if((4 == month) || (6 == month) || (9 == month) || (11 == month))  //小月
		{
			for(int i = 1; i <= 30; i++)
			{
				String date = formatDate(month, i);
				monthMap.put(date, defaultVal);
			}
		}
		else  //2月
		{
			//根据当前年份判断是否闰月
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			int year = cal.get(Calendar.YEAR);
			
			if(0 == (year % 4)) //闰月
			{
				for(int i = 1; i <= 29; i++)
				{
					String date = formatDate(month, i);
					monthMap.put(date, defaultVal);
				}
			}
			else
			{
				for(int i = 1; i <= 28; i++)
				{
					String date = formatDate(month, i);
					monthMap.put(date, defaultVal);
				}
			}
		}
		
		return monthMap;
	}
	
	//将给定月份和日期格式化为“MM-DD"的格式
	private String formatDate(int month, int day)
	{
		String strMonth = "" + month;
		String strDay = "" + day;
		
		if(month < 10)
			strMonth = "0" + month;
		if(day < 10)
			strDay = "0" + day;
		
		return strMonth + "-" + strDay;
	}
	
	//将指定时间格式化为“MM-dd”
	private String getDate(int date)
	{
		String dateStr = Integer.toString(date);
		String retDateStr = dateStr.substring(4, 6) + "-" + dateStr.substring(6, 8);

		return retDateStr;
	}
	
	//根据Map内容组织StoreStatisticVo数据
	private StoreStatisticVo getVoFromMap(String title, HashMap<String, String> dataMap)
	{
		StoreStatisticVo vo = new StoreStatisticVo();
		vo.setTitle(title);
		
		List<String> rowList = new ArrayList<String>();
		List<String> dataList = new ArrayList<String>();
		
		Set<String> keys = dataMap.keySet( );  
		if(keys != null) 
		{ 
			Iterator<String> iterator = keys.iterator( );
			while(iterator.hasNext( )) 
			{  
				String key = iterator.next( );
				rowList.add(key);

				String value = dataMap.get(key);
				dataList.add(value);
			}
		}
		
		vo.setRows(rowList);
		vo.setData(dataList);
		
		return vo;
	}
}
