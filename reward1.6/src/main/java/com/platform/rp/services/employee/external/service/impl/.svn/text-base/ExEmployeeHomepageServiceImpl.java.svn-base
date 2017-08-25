package com.platform.rp.services.employee.external.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.employee.core.dao.BsEmployeeExtEvaluateDAO;
import com.platform.rp.services.employee.core.dao.BsEmployeeExtRewardDAO;
import com.platform.rp.services.employee.core.dao.BsEmployeeInfoDAO;
import com.platform.rp.services.employee.core.dao.ItRecordEmpLogDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtEvaluateEntity;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtRewardEntity;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.employee.core.dao.entity.ItRecordEmpLogEntity;
import com.platform.rp.services.employee.external.service.IExEmployeeHomepageService;
import com.platform.rp.services.employee.external.vo.EmployeeHomepageBaseInfoVo;
import com.platform.rp.services.organize.core.dao.BsMerchantsInfoDAO;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.store.core.dao.BsStoreExtRewardDAO;
import com.platform.rp.services.store.core.dao.ItEvaluateInfoDAO;
import com.platform.rp.services.store.core.dao.ItRewardInfoDAO;
import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.services.store.core.dao.entity.ItEvaluateInfoEntity;
import com.platform.rp.services.store.core.dao.entity.ItRewardInfoEntity;
import com.platform.rp.services.wechat.service.IWxSendMessageService;
import com.platform.rp.util.DateUtil;

@Service
public class ExEmployeeHomepageServiceImpl implements IExEmployeeHomepageService {
	@Autowired
	private BsEmployeeInfoDAO<BsEmployeeInfoEntity> employeeInfoDao;
	
	@Autowired
	private ItEvaluateInfoDAO<ItEvaluateInfoEntity> evaluateInfoDao;
	
	@Autowired
	private ItRewardInfoDAO<ItRewardInfoEntity> rewardInfoDao;
	
	@Autowired
	private ItRecordEmpLogDAO<ItRecordEmpLogEntity> recordEmpLogDao;
	
	@Autowired
	private BsEmployeeExtEvaluateDAO<BsEmployeeExtEvaluateEntity> empExtEvaluateDao;
	
	@Autowired
	private BsEmployeeExtRewardDAO<BsEmployeeExtRewardEntity> empExtRewardDao;
	
	@Autowired
	private BsStoreExtRewardDAO<BsStoreExtRewardEntity> storeExtReward;
	
	@Autowired
	private IWxSendMessageService sendMessageService;
	
	@Autowired
	private BsMerchantsInfoDAO<BsMerchantsInfoEntity> mechantsInfoDao;
	
	@Autowired
	private IExBsMerchantsInfoService merchantsInfoService;
	
	
	public static Map<String, String> mapUser=new HashMap<String, String>();

	@Override
	public EmployeeHomepageBaseInfoVo getEmpHomepageBaseInfoByEmpId(
			long empID) {
		return employeeInfoDao.getEmpHomepageBaseInfoByEmpId(empID);
	}

	@Override
	public synchronized void addEvaluate(ItEvaluateInfoEntity itEvaluateInfo) {
		try{
			if(mapUser.containsKey(itEvaluateInfo.getCustomerId()))return;
			
			mapUser.put(itEvaluateInfo.getCustomerId(), "1");
			
			//处理日结时间
        	try{
        		Date dt = new Date();
	        	String[] startAndEnd=new String[2];
	        	merchantsInfoService.getStartAndEndTime(itEvaluateInfo.getStoreId(), startAndEnd);
	        	int check=DateUtil.InTime(dt, startAndEnd[0], startAndEnd[0],"day");
	        	if(check==0){        	
	        		itEvaluateInfo.setBusdate(Integer.parseInt(DateUtil.format(dt, "yyyyMMdd")));
	        	}else if(check == 1){
	        		dt.setDate(dt.getDate()-1);
	        		itEvaluateInfo.setBusdate(Integer.parseInt(DateUtil.format(dt, "yyyyMMdd")));
	        	}
	        	
        	}catch(Exception e){
        		System.out.println("设置日结日期错误:"+e.getMessage());
        	}
			
			
			evaluateInfoDao.save(itEvaluateInfo);
			
			//同步员工打赏日志表（it_record_emp_log）
			syncItRecordEmpLog(itEvaluateInfo, 0);
			
			//同步员工评价统计扩展表（bs_employee_ext_evaluate）
			syncEmpExtEvaluate(itEvaluateInfo);
		}finally{
			mapUser.remove(itEvaluateInfo.getCustomerId());
		}
	} 

	@Override
	public synchronized void addReward(ItRewardInfoEntity itRewardInfo) {
		rewardInfoDao.update(itRewardInfo);
		
		//同步员工打赏统计扩展表（bs_employee_ext_reward）
		syncEmpExtReward(itRewardInfo);

		//同步员工打赏日志表（it_record_emp_log）
		syncItRecordEmpLog(itRewardInfo, 1);
	}

	private void syncEmpExtEvaluate(ItEvaluateInfoEntity itEvaluateInfo) {
		//获取商铺统计开始时间和结束时间
		String[] startAndEnd = new String[2];
		merchantsInfoService.getStartAndEndTime(itEvaluateInfo.getStoreId(), startAndEnd);
		
		//判断当前时间是否在指定时间段内
		int inTime = DateUtil.InTime(new Date(), startAndEnd[0], startAndEnd[1], "day");
		//当前时间不在指定时间段内，数据存入虚拟店铺
		long storeID = itEvaluateInfo.getStoreId();
		if(-1 == inTime)
		{
			storeID = -2;
		}
				
		//从数据库中获取已有数据
		List<BsEmployeeExtEvaluateEntity> empExtEvaluateList = empExtEvaluateDao.getEmployeeEvaluate(storeID, itEvaluateInfo.getEmpId());
		
		//数据库中有数据，更新
		if((empExtEvaluateList != null) && (empExtEvaluateList.size() > 0))
		{
			BsEmployeeExtEvaluateEntity empExtEvaluateEntity = empExtEvaluateList.get(0);
			
			//updateTime与当前时间比对，是否属于当天/本周/本月/
			Date updateTime = empExtEvaluateEntity.getUpdateTime();
			Map<String, Boolean> dayDiffMap = null;
			if(-2 == storeID)
				dayDiffMap = DateUtil.getDiffDay(updateTime);
			else
				dayDiffMap = DateUtil.getDiffDay(updateTime, startAndEnd[0], startAndEnd[1]);
			
			//是否当天
			if(dayDiffMap.get("day"))  //当天
			{
				//总星数 = 平均数 * 次数 + 新增星数
				BigDecimal totalDay = empExtEvaluateEntity.getDayScore().multiply(new BigDecimal(empExtEvaluateEntity.getDayCount())).add(new BigDecimal(itEvaluateInfo.getServiceScore()));
				//平均得分 = 总星数 / 总次数（四舍五入取小数点后两位）
				BigDecimal avgDay = totalDay.divide(new BigDecimal(empExtEvaluateEntity.getDayCount() + 1), 2, BigDecimal.ROUND_HALF_EVEN);
				
				empExtEvaluateEntity.setDayScore(avgDay);
				empExtEvaluateEntity.setDayCount(empExtEvaluateEntity.getDayCount() + 1);
			}
			else if(dayDiffMap.get("yesterday"))   //昨天
			{
//				if(1 == inTime)    //当前新数据时间处于昨天
//				{
//					//总星数 = 平均数 * 次数 + 新增星数
//					BigDecimal totalDay = empExtEvaluateEntity.getDayScore().multiply(new BigDecimal(empExtEvaluateEntity.getDayCount())).add(new BigDecimal(itEvaluateInfo.getServiceScore()));
//					//平均得分 = 总星数 / 总次数（四舍五入取小数点后两位）
//					BigDecimal avgDay = totalDay.divide(new BigDecimal(empExtEvaluateEntity.getDayCount() + 1), 2, BigDecimal.ROUND_HALF_EVEN);
//					
//					empExtEvaluateEntity.setDayScore(avgDay);
//					empExtEvaluateEntity.setDayCount(empExtEvaluateEntity.getDayCount() + 1);
//				}
//				else
//				{
					//更新yesterday数据
					empExtEvaluateEntity.setYesterdayCount(empExtEvaluateEntity.getDayCount());
					empExtEvaluateEntity.setYesterdayScore(empExtEvaluateEntity.getDayScore());
					
					//更新day数据
					empExtEvaluateEntity.setDayCount(1);
					empExtEvaluateEntity.setDayScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
//				}
			}
			else    //既不是今天也不是昨天
			{
				empExtEvaluateEntity.setDayCount(1);
				empExtEvaluateEntity.setDayScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
				
				empExtEvaluateEntity.setYesterdayCount(0);
				empExtEvaluateEntity.setYesterdayScore(new BigDecimal("0.00"));
			}
			
			//是否本周
			if(dayDiffMap.get("week"))  //本周
			{
				//总星数 = 平均数 * 次数 + 新增星数
				BigDecimal totalWeek = empExtEvaluateEntity.getWeekScore().multiply(new BigDecimal(empExtEvaluateEntity.getWeekCount())).add(new BigDecimal(itEvaluateInfo.getServiceScore()));
				//平均得分 = 总星数 / 总次数（四舍五入取小数点后两位）
				BigDecimal avgWeek = totalWeek.divide(new BigDecimal(empExtEvaluateEntity.getWeekCount() + 1), 2, BigDecimal.ROUND_HALF_EVEN);
				
				empExtEvaluateEntity.setWeekScore(avgWeek);
				empExtEvaluateEntity.setWeekCount(empExtEvaluateEntity.getWeekCount() + 1);
			}
			else if(dayDiffMap.get("lastWeek"))   //上周
			{
				//判断当前时间是否在指定时间段内
				int currentInTime = DateUtil.InTime(new Date(), startAndEnd[0], startAndEnd[1], "week");
				if(3 == currentInTime) //当前新数据时间处于上周
				{
					//总星数 = 平均数 * 次数 + 新增星数
					BigDecimal totalWeek = empExtEvaluateEntity.getWeekScore().multiply(new BigDecimal(empExtEvaluateEntity.getWeekCount())).add(new BigDecimal(itEvaluateInfo.getServiceScore()));
					//平均得分 = 总星数 / 总次数（四舍五入取小数点后两位）
					BigDecimal avgWeek = totalWeek.divide(new BigDecimal(empExtEvaluateEntity.getWeekCount() + 1), 2, BigDecimal.ROUND_HALF_EVEN);
					
					empExtEvaluateEntity.setWeekScore(avgWeek);
					empExtEvaluateEntity.setWeekCount(empExtEvaluateEntity.getWeekCount() + 1);
				}
				else
				{
					//更新lastweek数据
					empExtEvaluateEntity.setLastweekCount(empExtEvaluateEntity.getWeekCount());
					empExtEvaluateEntity.setLastweekScore(empExtEvaluateEntity.getWeekScore());
					
					//更新week数据
					empExtEvaluateEntity.setWeekCount(1);
					empExtEvaluateEntity.setWeekScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
				}
			}
			else  //既不是本周也不是上周
			{
				empExtEvaluateEntity.setWeekCount(1);
				empExtEvaluateEntity.setWeekScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
				
				empExtEvaluateEntity.setLastweekCount(0);
				empExtEvaluateEntity.setLastweekScore(new BigDecimal("0.00"));
			}
			
			//是否本月
			if(dayDiffMap.get("month"))   //本月
			{
				//总星数 = 平均数 * 次数 + 新增星数
				BigDecimal totalMonth = empExtEvaluateEntity.getMonthScore().multiply(new BigDecimal(empExtEvaluateEntity.getMonthCount())).add(new BigDecimal(itEvaluateInfo.getServiceScore()));
				//平均得分 = 总星数 / 总次数（四舍五入取小数点后两位）
				BigDecimal avgMonth = totalMonth.divide(new BigDecimal(empExtEvaluateEntity.getMonthCount() + 1), 2, BigDecimal.ROUND_HALF_EVEN);
				
				empExtEvaluateEntity.setMonthScore(avgMonth);
				empExtEvaluateEntity.setMonthCount(empExtEvaluateEntity.getMonthCount() + 1);
			}
			else  if(dayDiffMap.get("lastMonth")) //上月
			{
				//判断当前时间是否在指定时间段内
				int currentInTime = DateUtil.InTime(new Date(), startAndEnd[0], startAndEnd[1], "month");
				if(5 == currentInTime)  //当前新数据时间处于上月
				{
					//总星数 = 平均数 * 次数 + 新增星数
					BigDecimal totalMonth = empExtEvaluateEntity.getMonthScore().multiply(new BigDecimal(empExtEvaluateEntity.getMonthCount())).add(new BigDecimal(itEvaluateInfo.getServiceScore()));
					//平均得分 = 总星数 / 总次数（四舍五入取小数点后两位）
					BigDecimal avgMonth = totalMonth.divide(new BigDecimal(empExtEvaluateEntity.getMonthCount() + 1), 2, BigDecimal.ROUND_HALF_EVEN);
					
					empExtEvaluateEntity.setMonthScore(avgMonth);
					empExtEvaluateEntity.setMonthCount(empExtEvaluateEntity.getMonthCount() + 1);
				}
				else
				{
					//更新lastmonth数据
					empExtEvaluateEntity.setLastmonthCount(empExtEvaluateEntity.getMonthCount());
					empExtEvaluateEntity.setLastmonthScore(empExtEvaluateEntity.getMonthScore());
					
					//更新month数据
					empExtEvaluateEntity.setMonthCount(1);
					empExtEvaluateEntity.setMonthScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
				}
			}
			else  //既不是本月也不是上月
			{
				empExtEvaluateEntity.setMonthCount(1);
				empExtEvaluateEntity.setMonthScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
				
				empExtEvaluateEntity.setLastmonthCount(0);
				empExtEvaluateEntity.setLastmonthScore(new BigDecimal("0.00"));
			}

			//总星数 = 平均数 * 次数 + 新增星数
			BigDecimal totalScore = empExtEvaluateEntity.getTotalScore().multiply(new BigDecimal(empExtEvaluateEntity.getTotalCount())).add(new BigDecimal(itEvaluateInfo.getServiceScore()));
			//平均得分 = 总星数 / 总次数（四舍五入取小数点后两位）
			BigDecimal avgScore = totalScore.divide(new BigDecimal(empExtEvaluateEntity.getTotalCount() + 1), 2, BigDecimal.ROUND_HALF_EVEN);
			
			empExtEvaluateEntity.setTotalScore(avgScore);
			empExtEvaluateEntity.setTotalCount(empExtEvaluateEntity.getTotalCount() + 1);	
			
			empExtEvaluateEntity.setUpdateTime(new Date());
			
			empExtEvaluateDao.update(empExtEvaluateEntity);
		}
		else   //新增
		{
			BsEmployeeExtEvaluateEntity empExtEvaluateEntity = new BsEmployeeExtEvaluateEntity();
			
			empExtEvaluateEntity.setEmpId(itEvaluateInfo.getEmpId());
			empExtEvaluateEntity.setStoreId(itEvaluateInfo.getStoreId());
			empExtEvaluateEntity.setDayCount(1);
			empExtEvaluateEntity.setYesterdayCount(0);
			empExtEvaluateEntity.setWeekCount(1);
			empExtEvaluateEntity.setLastweekCount(0);
			empExtEvaluateEntity.setMonthCount(1);
			empExtEvaluateEntity.setLastmonthCount(0);
			empExtEvaluateEntity.setTotalCount(1);
			empExtEvaluateEntity.setDayScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
			empExtEvaluateEntity.setYesterdayScore(new BigDecimal(0));
			empExtEvaluateEntity.setWeekScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
			empExtEvaluateEntity.setLastweekScore(new BigDecimal(0));
			empExtEvaluateEntity.setMonthScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
			empExtEvaluateEntity.setLastmonthScore(new BigDecimal(0));
			empExtEvaluateEntity.setTotalScore(new BigDecimal(itEvaluateInfo.getServiceScore()));
			empExtEvaluateEntity.setStatus(1);
			empExtEvaluateEntity.setUpdateTime(new Date());
			
			empExtEvaluateDao.save(empExtEvaluateEntity);
		}
	}
	
	//同步员工打赏日志表（it_record_emp_log）
	private void syncItRecordEmpLog(Object object, int dataType)
	{
		if(0 == dataType)
		{
			ItEvaluateInfoEntity itEvaluateInfo = (ItEvaluateInfoEntity)object;
			BsEmployeeInfoEntity empInfo = employeeInfoDao.getEmployeeInfoByEmpId(itEvaluateInfo.getEmpId());
			
			//格式化数据，四舍五入后保留小数点后两位
			DecimalFormat df = new java.text.DecimalFormat("0.00");
			df.setRoundingMode(RoundingMode.HALF_UP);
			
			ItRecordEmpLogEntity itRecordEmpLogEntity = new ItRecordEmpLogEntity();
			
			itRecordEmpLogEntity.setEmpId(itEvaluateInfo.getEmpId());
			itRecordEmpLogEntity.setStoreId(itEvaluateInfo.getStoreId());
			itRecordEmpLogEntity.setNickname(empInfo.getName());
			itRecordEmpLogEntity.setCreateTime(itEvaluateInfo.getCreatedtime());
			itRecordEmpLogEntity.setFid(BigInteger.valueOf(itEvaluateInfo.getId()));
			itRecordEmpLogEntity.setReceivable(df.format(itEvaluateInfo.getServiceScore()));
			itRecordEmpLogEntity.setPaid(df.format(itEvaluateInfo.getServiceScore()));
			itRecordEmpLogEntity.setRecordType(1);
			itRecordEmpLogEntity.setRemark(itEvaluateInfo.getEvaluate());
			itRecordEmpLogEntity.setCreateTime(new Date());
			itRecordEmpLogEntity.setBusdate(itEvaluateInfo.getBusdate());
			
			recordEmpLogDao.save(itRecordEmpLogEntity);
		}
		else
		{
			ItRewardInfoEntity itRewardInfo = (ItRewardInfoEntity)object;
			
			//同步店员打赏数据
			//获取店员基本信息
			BsEmployeeInfoEntity empInfo = employeeInfoDao.getEmployeeInfoByEmpId(itRewardInfo.getEmpId());
			
			//发送消息推送
			JSONObject jsonObject = new JSONObject();
            //jsonObject.element("first", new JSONObject().element("value", "您好，收到顾客4.56元打赏，店长分成0.5元，总监分成0.06元\n").element("color", "#173177"));
            jsonObject.element("keyword1", new JSONObject().element("value", "来自众赏的打赏收入").element("color", "#173177"));
		    //jsonObject.element("keyword2", new JSONObject().element("value", "0.5").element("color", "#FA1A2B"));
            
            jsonObject.element("keyword3", new JSONObject().element("value", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss")).element("color", "#173177"));
		    jsonObject.element("remark", new JSONObject().element("value", "\n众赏打赏平台，众赏之下，人人勇夫").element("color", "#173177"));
			
			//根据分配方案计算总监/店长/店员分别获得的打赏金额
			BigDecimal empReward = new BigDecimal(Double.toString(itRewardInfo.getRewardAmount()));
			BigDecimal empPaid = new BigDecimal(Double.toString(itRewardInfo.getRewardAmount()));
			
			//被打赏员工备注
			StringBuffer allotStr = new StringBuffer();
			
			//从表bs_store_ext_reward中获取分成方案及每个人的分成比例
			List<BsStoreExtRewardEntity> storeExtRewardList = storeExtReward.getStoreExtRewardByStoreId(itRewardInfo.getStoreId());
			if(storeExtRewardList != null && storeExtRewardList.size() > 0)
			{				
				for(BsStoreExtRewardEntity entity : storeExtRewardList)
				{
					//判断未选择人员
					if(entity.getEmpId() == 0){
						continue;
					}
					//获取店铺分配方案
					String allotPlan = entity.getAllotPlan();
					if(null == allotPlan)
						continue;
					
					//全额分配给店员
					if(allotPlan.equals("allot1001"))
					{
						break;
					}
					else if(allotPlan.equals("allot1002"))  //统一账号代收
					{
						break;
					}
					else if(allotPlan.equals("allot1003"))   //店长、店员按比例分成
					{
						if(null == entity.getPercent())
							continue;
						
						//店长和店员为同一人，不进行分成操作
						if(entity.getEmpId() == itRewardInfo.getEmpId())
							continue;
						
						//分成比例大于100%
						if(entity.getPercent().compareTo(new BigDecimal(1)) > 0)
						{
							entity.setPercent(new BigDecimal(1));
						}
						
						//计算店长分成金额
						BigDecimal getReward = empReward.multiply(entity.getPercent());
						
						//如果店长分成金额小于0.01，不记录数据
						if(getReward.compareTo(new BigDecimal(0.009)) < 0)
						{
							continue;
						}
						
						//计算店长分成后店员剩余金额
						empPaid = empPaid.subtract(getReward);
						
						//获取店长基本信息
						BsEmployeeInfoEntity managerInfo = employeeInfoDao.getEmployeeInfoByEmpId(entity.getEmpId());
						if(null!=managerInfo){
	
							//同步店长打赏分成数据
							//格式化数据，保留小数点后两位
							DecimalFormat df = new java.text.DecimalFormat("0.00");
							//组织要加入数据库中的数据
							ItRecordEmpLogEntity logEntity = new ItRecordEmpLogEntity();
							logEntity.setEmpId(entity.getEmpId());
							logEntity.setStoreId(entity.getStoreId());
							logEntity.setNickname(managerInfo.getName());
							logEntity.setCreateTime(itRewardInfo.getCreatedtime());
							logEntity.setFid(BigInteger.valueOf(itRewardInfo.getId()));
							logEntity.setReceivable(df.format(getReward));
							logEntity.setPaid(df.format(getReward));
							logEntity.setRecordType(2);
							String remark = "收到"+empInfo.getName()+"分成"+logEntity.getPaid()+"元";
							allotStr.append(managerInfo.getName()+"分成"+logEntity.getPaid()+"元    ");
							logEntity.setRemark(remark);
							//处理日结时间
							logEntity.setBusdate(itRewardInfo.getBusdate());
							
							recordEmpLogDao.save(logEntity);
							
							//微信通知
							jsonObject.element("first", new JSONObject().element("value", "您好，"+empInfo.getName()+"收到顾客"+df.format(empReward)+"元打赏，您获取分成"+logEntity.getPaid()+"元.\n").element("color", "#173177"));
						    jsonObject.element("keyword2", new JSONObject().element("value", logEntity.getPaid()).element("color", "#FA1A2B"));
							sendMessageService.sendIncomeTemplate(jsonObject, managerInfo.getWxAccount());
						}
						//分成后，如果店员剩余打赏金额小于0.01，不再进行分成计算
						if(empPaid.compareTo(new BigDecimal(0.01)) < 0)
						{
							break;
						}
					}
				}
			}
			
			

			//格式化数据，保留小数点后两位
			DecimalFormat df = new java.text.DecimalFormat("0.00");
			
			//组织要加入数据库中的数据
			ItRecordEmpLogEntity itRecordEmpLogEntity = new ItRecordEmpLogEntity();
			itRecordEmpLogEntity.setEmpId(itRewardInfo.getEmpId());
			itRecordEmpLogEntity.setStoreId(itRewardInfo.getStoreId());
			itRecordEmpLogEntity.setNickname(empInfo.getName());
			itRecordEmpLogEntity.setCreateTime(itRewardInfo.getCreatedtime());
			itRecordEmpLogEntity.setFid(BigInteger.valueOf(itRewardInfo.getId()));
			itRecordEmpLogEntity.setReceivable(df.format(empReward));
			itRecordEmpLogEntity.setPaid(df.format(empPaid));
			itRecordEmpLogEntity.setRecordType(0);
			itRecordEmpLogEntity.setRemark(allotStr.toString());
			itRecordEmpLogEntity.setCreateTime(new Date());
			//日结时间
			itRecordEmpLogEntity.setBusdate(itRewardInfo.getBusdate());
			recordEmpLogDao.save(itRecordEmpLogEntity);
			
			//微信通知
			jsonObject.element("first", new JSONObject().element("value", "您好，收到顾客"+df.format(empReward)+"元打赏"+allotStr.toString()+"\n").element("color", "#173177"));
		    jsonObject.element("keyword2", new JSONObject().element("value", df.format(empPaid)).element("color", "#FA1A2B"));
			sendMessageService.sendIncomeTemplate(jsonObject, empInfo.getWxAccount());
		}
	}
	
	//同步员工打赏统计扩展表（bs_employee_ext_reward）
	private void syncEmpExtReward(ItRewardInfoEntity itRewardInfo)
	{
//		//根据分配方案计算总监/店长/店员分别获得的打赏金额
//		BigDecimal empReward = new BigDecimal(Double.toString(itRewardInfo.getRewardAmount()));
//		BigDecimal empPaid = new BigDecimal(Double.toString(itRewardInfo.getRewardAmount()));
//		
//		//从表bs_store_ext_reward中获取店铺打赏分配方案
//		String allotPlan = null;
//		BsStoreExtRewardEntity extRewardEntity = null;
//		List<BsStoreExtRewardEntity> storeExtRewardList = storeExtReward.getStoreExtRewardByStoreId(itRewardInfo.getStoreId());
//		if(storeExtRewardList != null && storeExtRewardList.size() > 0)
//		{
//			extRewardEntity = storeExtRewardList.get(0);
//			allotPlan = extRewardEntity.getAllotPlan();
//			
//			//如果是店长/店员按比例分成
//			if(allotPlan.equals("allot1003"))
//			{
//				for(BsStoreExtRewardEntity entity : storeExtRewardList)
//				{
//					//店长和店员为同一人，不进行分成操作
//					if(entity.getEmpId() == itRewardInfo.getEmpId())
//						continue;
//					
//					//分成比例大于100%
//					if(entity.getPercent().compareTo(new BigDecimal(1)) > 0)
//					{
//						entity.setPercent(new BigDecimal(1));
//					}
//					
//					//计算店长分成金额
//					BigDecimal getReward = empReward.multiply(entity.getPercent());
//					
//					//如果店长分成金额小于0.01，不记录数据
//					if(getReward.compareTo(new BigDecimal(0.009)) < 0)
//					{
//						continue;
//					}
//					
//					//保存店长分成数据
//					saveAllot(entity.getStoreId(), entity.getEmpId(), getReward);
//					
//					//计算店长分成后店员剩余金额
//					empPaid = empPaid.subtract(getReward);
//				}
//			}
//		}
		
		saveEmpExtRecord(itRewardInfo);
//			//从数据库中获取已有数据
//			List<BsEmployeeExtRewardEntity> empExtRewardList = empExtRewardDao.getEmployeeReward(itRewardInfo.getStoreId(), itRewardInfo.getEmpId(), 0);
//			
//			//数据库中有数据，更新
//			if((empExtRewardList != null) && (empExtRewardList.size() > 0))
//			{
//				BsEmployeeExtRewardEntity empExtRewardEntity = empExtRewardList.get(0);
//				
//				//updateTime与当前时间比对，是否属于当天/本周/本月/
//				Date updateTime = empExtRewardEntity.getUpdateTime();
//				Map<String, Boolean> dayDiffMap = DateUtil.getDiffDay(updateTime, startAndEnd[0], startAndEnd[1]);
//
//				//是否当天
//				if(dayDiffMap.get("day"))  //当天
//				{
//					empExtRewardEntity.setDayCount(empExtRewardEntity.getDayCount() + 1);
//					
//					BigDecimal totalDay = empExtRewardEntity.getDayMoney().add(new BigDecimal(itRewardInfo.getRewardAmount()));	
//					empExtRewardEntity.setDayMoney(totalDay);
//				}
//				else if (dayDiffMap.get("yesterday"))  //昨天
//				{
//					//更新yesterday数据
//					empExtRewardEntity.setYesterdayCount(empExtRewardEntity.getDayCount());
//					empExtRewardEntity.setYesterdayMoney(empExtRewardEntity.getDayMoney());
//					
//					//更新day数据
//					empExtRewardEntity.setDayCount(1);
//					empExtRewardEntity.setDayMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
//				}
//				else  //既不是当天也不是昨天
//				{
//					empExtRewardEntity.setDayCount(1);
//					empExtRewardEntity.setDayMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
//					
//					empExtRewardEntity.setYesterdayCount(0);
//					empExtRewardEntity.setYesterdayMoney(new BigDecimal("0.00"));
//				}
//				
//				//是否本周
//				if(dayDiffMap.get("week"))  //本周
//				{
//					empExtRewardEntity.setWeekCount(empExtRewardEntity.getWeekCount() + 1);
//					
//					BigDecimal totalWeek = empExtRewardEntity.getWeekMoney().add(new BigDecimal(itRewardInfo.getRewardAmount()));
//					empExtRewardEntity.setWeekMoney(totalWeek);
//				}
//				else  if(dayDiffMap.get("lastWeek")) //上周
//				{
//					empExtRewardEntity.setWeekCount(1);
//					empExtRewardEntity.setWeekMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
//					
//					empExtRewardEntity.setLastweekCount(0);
//					empExtRewardEntity.setLastweekMoney(new BigDecimal("0.00"));
//				}
//				
//				//是否本月
//				if(dayDiffMap.get("month"))   //本月
//				{
//					empExtRewardEntity.setMonthCount(empExtRewardEntity.getMonthCount() + 1);
//					
//					BigDecimal totalMonth = empExtRewardEntity.getMonthMoney().add(new BigDecimal(itRewardInfo.getRewardAmount()));
//					empExtRewardEntity.setMonthMoney(totalMonth);
//				}
//				else if(dayDiffMap.get("lastMonth"))  //上月
//				{
//					//更新lastmonth数据
//					empExtRewardEntity.setLastmonthCount(empExtRewardEntity.getMonthCount());
//					empExtRewardEntity.setLastmonthMoney(empExtRewardEntity.getMonthMoney());
//					
//					//更新month数据
//					empExtRewardEntity.setMonthCount(1);
//					empExtRewardEntity.setMonthMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
//				}
//				else  //非本月
//				{
//					empExtRewardEntity.setMonthCount(1);
//					empExtRewardEntity.setMonthMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
//					
//					empExtRewardEntity.setLastmonthCount(0);
//					empExtRewardEntity.setLastmonthMoney(new BigDecimal("0.00"));
//				}
//				
//				empExtRewardEntity.setTotalCount(empExtRewardEntity.getTotalCount() + 1);
//				
//				//店铺打赏分配方案为统一账号代收
//				if((allotPlan != null) && (allotPlan.equals("allot1002")) && (extRewardEntity.getEmpId() != empExtRewardEntity.getEmpId()))
//				{
//					saveAllot(extRewardEntity.getStoreId(), extRewardEntity.getEmpId(), new BigDecimal(itRewardInfo.getRewardAmount()));
//				}
//				else
//				{
//					BigDecimal totalTotal = empExtRewardEntity.getTotalMoney().add(empPaid);
//					empExtRewardEntity.setTotalMoney(totalTotal);
//				}
//				
//				empExtRewardEntity.setUpdateTime(new Date());
//				
//				empExtRewardDao.update(empExtRewardEntity);
//			}
//			else   //新增
//			{
//				BsEmployeeExtRewardEntity empExtRewardEntity = new BsEmployeeExtRewardEntity();
//				
//				empExtRewardEntity.setEmpId(itRewardInfo.getEmpId());
//				empExtRewardEntity.setStoreId(itRewardInfo.getStoreId());
//				empExtRewardEntity.setDayCount(1);
//				empExtRewardEntity.setYesterdayCount(0);
//				empExtRewardEntity.setWeekCount(1);
//				empExtRewardEntity.setLastweekCount(0);
//				empExtRewardEntity.setMonthCount(1);
//				empExtRewardEntity.setLastmonthCount(0);
//				empExtRewardEntity.setTotalCount(1);
//				empExtRewardEntity.setCashCount(0);
//				empExtRewardEntity.setDayMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
//				empExtRewardEntity.setYesterdayMoney(new BigDecimal(0));
//				empExtRewardEntity.setWeekMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
//				empExtRewardEntity.setLastweekMoney(new BigDecimal(0));
//				empExtRewardEntity.setMonthMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
//				empExtRewardEntity.setLastmonthMoney(new BigDecimal(0));		
//				
//				//店铺打赏分配方案为统一账号代收
//				if((allotPlan != null) && (allotPlan.equals("allot1002")) && (extRewardEntity.getEmpId() != empExtRewardEntity.getEmpId()))
//				{
//					saveAllot(extRewardEntity.getStoreId(), extRewardEntity.getEmpId(), new BigDecimal(itRewardInfo.getRewardAmount()));
//					empExtRewardEntity.setTotalMoney(new BigDecimal(0));
//				}
//				else
//				{
//					empExtRewardEntity.setTotalMoney(empPaid);
//				}
//				
//				empExtRewardEntity.setCashMoney(new BigDecimal(0));
//				empExtRewardEntity.setStatus(1);
//				empExtRewardEntity.setUpdateTime(new Date());
//				
//				empExtRewardDao.save(empExtRewardEntity);
//			}
	}
	
	//店铺打赏方式为统一账号代收，存储代收账号数据
	private void saveAllot(long storeID, long empID, BigDecimal rwardAmount)
	{
		//获取商铺统计开始时间和结束时间
		String[] startAndEnd = new String[2];
		merchantsInfoService.getStartAndEndTime(storeID, startAndEnd);
				
		BsEmployeeExtRewardEntity keeperExtRewardEntity = null;
		List<BsEmployeeExtRewardEntity> keeperExtRewardList = empExtRewardDao.getEmployeeReward(storeID, empID, 0);
		if((keeperExtRewardList != null) && (keeperExtRewardList.size() > 0))
		{
			keeperExtRewardEntity = keeperExtRewardList.get(0);
			
			//updateTime与当前时间比对，是否属于当天/本周/本月/
			Date updateTime = keeperExtRewardEntity.getUpdateTime();
			Map<String, Boolean> dayDiffMap = DateUtil.getDiffDay(updateTime, startAndEnd[0], startAndEnd[1]);
			
			//是否当天
			if(dayDiffMap.get("day"))  //当天
			{
			}
			else if (dayDiffMap.get("yesterday"))  //昨天
			{
				//更新yesterday数据
				keeperExtRewardEntity.setYesterdayCount(keeperExtRewardEntity.getDayCount());
				keeperExtRewardEntity.setYesterdayMoney(keeperExtRewardEntity.getDayMoney());
			}
			else  //既不是当天也不是昨天
			{
				keeperExtRewardEntity.setDayCount(0);
				keeperExtRewardEntity.setDayMoney(new BigDecimal("0.00"));
				keeperExtRewardEntity.setYesterdayCount(0);
				keeperExtRewardEntity.setYesterdayMoney(new BigDecimal("0.00"));
			}
			
			//是否本周
			if(dayDiffMap.get("week"))  //本周
			{
			}
			else  if(dayDiffMap.get("lastWeek")) //上周
			{
				//更新lastweek数据
				keeperExtRewardEntity.setLastweekCount(keeperExtRewardEntity.getWeekCount());
				keeperExtRewardEntity.setLastweekMoney(keeperExtRewardEntity.getWeekMoney());
			}
			else  //既不是本周也不是上周
			{
				keeperExtRewardEntity.setWeekCount(0);
				keeperExtRewardEntity.setWeekMoney(new BigDecimal("0.00"));
				keeperExtRewardEntity.setLastweekCount(0);
				keeperExtRewardEntity.setLastweekMoney(new BigDecimal("0.00"));
			}
			
			//是否本月
			if(dayDiffMap.get("month"))   //本月
			{
			}
			else if(dayDiffMap.get("lastMonth"))  //上月
			{
				//更新lastmonth数据
				keeperExtRewardEntity.setLastmonthCount(keeperExtRewardEntity.getMonthCount());
				keeperExtRewardEntity.setLastmonthMoney(keeperExtRewardEntity.getMonthMoney());
			}
			else  //既不是本月也不是上月
			{
				keeperExtRewardEntity.setMonthCount(0);
				keeperExtRewardEntity.setMonthMoney(new BigDecimal("0.00"));
				keeperExtRewardEntity.setLastmonthCount(0);
				keeperExtRewardEntity.setLastmonthMoney(new BigDecimal("0.00"));
			}
			
			BigDecimal totalTotal = keeperExtRewardEntity.getTotalMoney().add(rwardAmount);
			keeperExtRewardEntity.setTotalMoney(totalTotal);
			
			empExtRewardDao.update(keeperExtRewardEntity);
		}
		else
		{
			keeperExtRewardEntity = new BsEmployeeExtRewardEntity();
			keeperExtRewardEntity.setStoreId(storeID);
			keeperExtRewardEntity.setEmpId(empID);
			keeperExtRewardEntity.setTotalMoney(rwardAmount);
			keeperExtRewardEntity.setStatus(1);
			keeperExtRewardEntity.setCashMoney(new BigDecimal("0.00"));
			keeperExtRewardEntity.setDayMoney(new BigDecimal("0.00"));
			keeperExtRewardEntity.setLastmonthMoney(new BigDecimal("0.00"));
			keeperExtRewardEntity.setLastweekMoney(new BigDecimal("0.00"));
			keeperExtRewardEntity.setMonthMoney(new BigDecimal("0.00"));
			keeperExtRewardEntity.setWeekMoney(new BigDecimal("0.00"));
			keeperExtRewardEntity.setYesterdayMoney(new BigDecimal("0.00"));
			keeperExtRewardEntity.setUpdateTime(new Date());
			
			empExtRewardDao.save(keeperExtRewardEntity);
		}
	}
	
	//存储打赏扩展表数据
	private void saveEmpExtRecord(ItRewardInfoEntity itRewardInfo)
	{	
		//根据分配方案计算总监/店长/店员分别获得的打赏金额
		BigDecimal empReward = new BigDecimal(Double.toString(itRewardInfo.getRewardAmount()));
		BigDecimal empPaid = new BigDecimal(Double.toString(itRewardInfo.getRewardAmount()));
		
		//从表bs_store_ext_reward中获取店铺打赏分配方案
		String allotPlan = null;
		BsStoreExtRewardEntity extRewardEntity = null;
		List<BsStoreExtRewardEntity> storeExtRewardList = storeExtReward.getStoreExtRewardByStoreId(itRewardInfo.getStoreId());
		if(storeExtRewardList != null && storeExtRewardList.size() > 0)
		{
			extRewardEntity = storeExtRewardList.get(0);
			allotPlan = extRewardEntity.getAllotPlan();
			
			//如果是店长/店员按比例分成
			if("allot1003".equals(allotPlan))
			{
				for(BsStoreExtRewardEntity entity : storeExtRewardList)
				{
					//判断不为0的数据
					if(entity.getEmpId()==0){
						continue;
					}
					//店长和店员为同一人，不进行分成操作
					if(entity.getEmpId() == itRewardInfo.getEmpId())
						continue;
					
					//分成比例大于100%
					if(entity.getPercent().compareTo(new BigDecimal(1)) > 0)
					{
						entity.setPercent(new BigDecimal(1));
					}
					
					//计算店长分成金额
					BigDecimal getReward = empReward.multiply(entity.getPercent());
					
					//如果店长分成金额小于0.01，不记录数据
					if(getReward.compareTo(new BigDecimal(0.009)) < 0)
					{
						continue;
					}
					
					//保存店长分成数据
					saveAllot(entity.getStoreId(), entity.getEmpId(), getReward);
					
					//计算店长分成后店员剩余金额
					empPaid = empPaid.subtract(getReward);
				}
			}
		}
		
		//获取商铺统计开始时间和结束时间
		String[] startAndEnd = new String[2];
		merchantsInfoService.getStartAndEndTime(itRewardInfo.getStoreId(), startAndEnd);
		
		//获取商铺统计开始时间和结束时间
		int inTime = DateUtil.InTime(new Date(), startAndEnd[0], startAndEnd[1], "day");
		//从数据库中获取已有数据
		long storeID =  itRewardInfo.getStoreId();
		if(-1 == inTime)
			storeID =  -2;
		List<BsEmployeeExtRewardEntity> empExtRewardList = empExtRewardDao.getEmployeeReward(storeID, itRewardInfo.getEmpId(), 0);
		
		//数据库中有数据，更新
		if((empExtRewardList != null) && (empExtRewardList.size() > 0))
		{
			BsEmployeeExtRewardEntity empExtRewardEntity = empExtRewardList.get(0);
			
			//updateTime与当前时间比对，是否属于当天/本周/本月/
			Date updateTime = empExtRewardEntity.getUpdateTime();
			Map<String, Boolean> dayDiffMap = null;
			if(inTime != -1)
				dayDiffMap = DateUtil.getDiffDay(updateTime, startAndEnd[0], startAndEnd[1]);
			else   //存储在虚拟店铺中的数据
				dayDiffMap = DateUtil.getDiffDay(updateTime);
			
			//是否当天
			if(dayDiffMap.get("day"))  //当天
			{
				empExtRewardEntity.setDayCount(empExtRewardEntity.getDayCount() + 1);
				
				BigDecimal totalDay = empExtRewardEntity.getDayMoney().add(new BigDecimal(itRewardInfo.getRewardAmount()));	
				empExtRewardEntity.setDayMoney(totalDay);
			}
			else if (dayDiffMap.get("yesterday"))  //更新时间为昨天
			{
//				if(1 == inTime)    //当前新数据时间处于昨天
//				{
//					empExtRewardEntity.setDayCount(empExtRewardEntity.getDayCount() + 1);
//					
//					BigDecimal totalDay = empExtRewardEntity.getDayMoney().add(new BigDecimal(itRewardInfo.getRewardAmount()));	
//					empExtRewardEntity.setDayMoney(totalDay);
//				}
//				else
//				{
					//更新yesterday数据
					empExtRewardEntity.setYesterdayCount(empExtRewardEntity.getDayCount());
					empExtRewardEntity.setYesterdayMoney(empExtRewardEntity.getDayMoney());
					
					//更新day数据
					empExtRewardEntity.setDayCount(1);
					empExtRewardEntity.setDayMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
//				}
			}
			else  //既不是当天也不是昨天
			{
				empExtRewardEntity.setDayCount(1);
				empExtRewardEntity.setDayMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
				
				empExtRewardEntity.setYesterdayCount(0);
				empExtRewardEntity.setYesterdayMoney(new BigDecimal("0.00"));
			}
			
			//是否本周
			if(dayDiffMap.get("week"))  //本周
			{
				empExtRewardEntity.setWeekCount(empExtRewardEntity.getWeekCount() + 1);
				
				BigDecimal totalWeek = empExtRewardEntity.getWeekMoney().add(new BigDecimal(itRewardInfo.getRewardAmount()));
				empExtRewardEntity.setWeekMoney(totalWeek);
			}
			else  if(dayDiffMap.get("lastWeek")) //上周
			{
				//判断当前时间是否在指定时间段内
				int currentInTime = DateUtil.InTime(new Date(), startAndEnd[0], startAndEnd[1], "week");
				if(3 == currentInTime) //当前新数据时间处于上周
				{
					empExtRewardEntity.setWeekCount(empExtRewardEntity.getWeekCount() + 1);
					
					BigDecimal totalWeek = empExtRewardEntity.getWeekMoney().add(new BigDecimal(itRewardInfo.getRewardAmount()));
					empExtRewardEntity.setWeekMoney(totalWeek);
				}
				else
				{
					//更新lastweek数据
					empExtRewardEntity.setLastweekCount(empExtRewardEntity.getWeekCount());
					empExtRewardEntity.setLastweekMoney(empExtRewardEntity.getWeekMoney());
					
					//更新week数据
					empExtRewardEntity.setWeekCount(1);
					empExtRewardEntity.setWeekMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
				}
			}
			else  //既不是本周也不是上周
			{
				empExtRewardEntity.setWeekCount(1);
				empExtRewardEntity.setWeekMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
				
				empExtRewardEntity.setLastweekCount(0);
				empExtRewardEntity.setLastweekMoney(new BigDecimal("0.00"));
			}
			
			//是否本月
			if(dayDiffMap.get("month"))   //本月
			{
				empExtRewardEntity.setMonthCount(empExtRewardEntity.getMonthCount() + 1);
				
				BigDecimal totalMonth = empExtRewardEntity.getMonthMoney().add(new BigDecimal(itRewardInfo.getRewardAmount()));
				empExtRewardEntity.setMonthMoney(totalMonth);
			}
			else if(dayDiffMap.get("lastMonth"))  //上月
			{
				//判断当前时间是否在指定时间段内
				int currentInTime = DateUtil.InTime(new Date(), startAndEnd[0], startAndEnd[1], "month");
				if(5 == currentInTime)  //当前新数据时间处于上月
				{
					empExtRewardEntity.setMonthCount(empExtRewardEntity.getMonthCount() + 1);
					
					BigDecimal totalMonth = empExtRewardEntity.getMonthMoney().add(new BigDecimal(itRewardInfo.getRewardAmount()));
					empExtRewardEntity.setMonthMoney(totalMonth);
				}
				else
				{
					//更新lastmonth数据
					empExtRewardEntity.setLastmonthCount(empExtRewardEntity.getMonthCount());
					empExtRewardEntity.setLastmonthMoney(empExtRewardEntity.getMonthMoney());
					
					//更新month数据
					empExtRewardEntity.setMonthCount(1);
					empExtRewardEntity.setMonthMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
				}
			}
			else  //非本月
			{
				empExtRewardEntity.setMonthCount(1);
				empExtRewardEntity.setMonthMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
				
				empExtRewardEntity.setLastmonthCount(0);
				empExtRewardEntity.setLastmonthMoney(new BigDecimal("0.00"));
			}
			
			empExtRewardEntity.setTotalCount(empExtRewardEntity.getTotalCount() + 1);
			
			//店铺打赏分配方案为统一账号代收
			if((allotPlan != null) && (allotPlan.equals("allot1002")) && (extRewardEntity.getEmpId() != empExtRewardEntity.getEmpId()))
			{
				saveAllot(extRewardEntity.getStoreId(), extRewardEntity.getEmpId(), new BigDecimal(itRewardInfo.getRewardAmount()));
			}
			else
			{
				BigDecimal totalTotal = empExtRewardEntity.getTotalMoney().add(empPaid);
				empExtRewardEntity.setTotalMoney(totalTotal);
			}
			
			empExtRewardEntity.setUpdateTime(new Date());
			
			empExtRewardDao.update(empExtRewardEntity);
		}
		else   //新增
		{
			BsEmployeeExtRewardEntity empExtRewardEntity = new BsEmployeeExtRewardEntity();
			
			empExtRewardEntity.setEmpId(itRewardInfo.getEmpId());
			empExtRewardEntity.setStoreId(storeID);
			empExtRewardEntity.setDayCount(1);
			empExtRewardEntity.setYesterdayCount(0);
			empExtRewardEntity.setWeekCount(1);
			empExtRewardEntity.setLastweekCount(0);
			empExtRewardEntity.setMonthCount(1);
			empExtRewardEntity.setLastmonthCount(0);
			empExtRewardEntity.setTotalCount(1);
			empExtRewardEntity.setCashCount(0);
			empExtRewardEntity.setDayMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
			empExtRewardEntity.setYesterdayMoney(new BigDecimal(0));
			empExtRewardEntity.setWeekMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
			empExtRewardEntity.setLastweekMoney(new BigDecimal(0));
			empExtRewardEntity.setMonthMoney(new BigDecimal(itRewardInfo.getRewardAmount()));
			empExtRewardEntity.setLastmonthMoney(new BigDecimal(0));
			
			//店铺打赏分配方案为统一账号代收
			if((allotPlan != null) && (allotPlan.equals("allot1002")) && (extRewardEntity.getEmpId() != empExtRewardEntity.getEmpId()))
			{
				saveAllot(extRewardEntity.getStoreId(), extRewardEntity.getEmpId(), new BigDecimal(itRewardInfo.getRewardAmount()));
				empExtRewardEntity.setTotalMoney(new BigDecimal(0));
			}
			else
			{
				empExtRewardEntity.setTotalMoney(empPaid);
			}
			
			empExtRewardEntity.setCashMoney(new BigDecimal(0));
			empExtRewardEntity.setStatus(1);
			empExtRewardEntity.setUpdateTime(new Date());
			
			empExtRewardDao.save(empExtRewardEntity);
		}
	}
}
