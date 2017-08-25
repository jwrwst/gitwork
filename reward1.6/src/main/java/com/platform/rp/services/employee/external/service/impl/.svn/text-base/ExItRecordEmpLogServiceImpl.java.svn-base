package com.platform.rp.services.employee.external.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.ItRecordEmpLogDAO;
import com.platform.rp.services.employee.core.dao.entity.ItRecordEmpLogEntity;
import com.platform.rp.services.employee.external.service.IExItRecordEmpLogService;
import com.platform.rp.services.employee.external.vo.RecordEmpLogVo;
import com.platform.rp.services.organize.external.service.IExBsMerchantsInfoService;
import com.platform.rp.services.store.core.dao.entity.BsStoreAuthDetailEntity;
import com.platform.rp.services.store.external.service.IExStoreAuthDetailService;
import com.platform.rp.util.DateUtil;
import com.platform.rp.util.StringUtils;

@Service
public class ExItRecordEmpLogServiceImpl implements IExItRecordEmpLogService {
	private Log log = LogFactory.getLog(ExItRecordEmpLogServiceImpl.class);

	@Autowired
	private ItRecordEmpLogDAO<ItRecordEmpLogEntity> dao;
	
	@Autowired
	private IExStoreAuthDetailService storeAuthDetailService;
	
	@Autowired
	private IExBsMerchantsInfoService merchantsInfoService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Page getPage(Page page, Map params) throws Exception {
		try {
			params.put("start", page.getStart());
//			params.put("end", page.getEnd());
			params.put("pageSize", page.getPageSize());
			
			boolean needTimeFixed = false;
			Object authCodeObj = params.get("authCode");
			//参数authCode为空，
			if(null == authCodeObj)    //店铺动态页面
			{
				//店铺动态页面下方的实时数据列表中，需要对今日和昨日数据进行时间修正
				needTimeFixed = true;
				
				//获取店铺开放权限（评价/打赏/评价及打赏）
				String authCode = "";
				if(StringUtils.isEmpty(params.get("storeId")+"")){
					return page;
				}
				List<BsStoreAuthDetailEntity> storeAuthDetailList = storeAuthDetailService.getStoreAuthDetailByStoreId(Long.parseLong(params.get("storeId").toString()));
				for(BsStoreAuthDetailEntity entity : storeAuthDetailList)
				{
					authCode = entity.getAuthCode();
				}
				
				if(authCode.equals("1001"))            //仅评价
				{
					params.put("recordType", 1);
				}
				else if(authCode.equals("1002"))   //仅打赏
				{
					params.put("recordType", 0);
				}
				else if(authCode.equals("1003"))   //评价和打赏
				{
					params.put("recordType", -1);
				}
			}
			else    //我的收入页面
			{
				String authCode = authCodeObj.toString();
				
				if(authCode.equals("1001"))            //仅评价
				{
					params.put("recordType", 1);
				}
				else if(authCode.equals("1002"))   //仅打赏
				{
					params.put("recordType", -2);
				}
			}
			
			//当前时间处于昨天范围还是今天范围
			String startAndEnd[] = new String[2];
//			int currentInTime = 0;
			if(needTimeFixed)
			{
				merchantsInfoService.getStartAndEndTime(Long.parseLong(params.get("storeId").toString()), startAndEnd);
//				currentInTime = DateUtil.InTime(new Date(), startAndEnd[0], startAndEnd[1], "day");
			}
			
			// 查询总数
			int count = dao.count(params);
			
			//查询数据
			List<RecordEmpLogVo> recordEmpLogList = dao.getPage(params);
			if(recordEmpLogList != null)
			{
				for(RecordEmpLogVo vo : recordEmpLogList)
				{
					Date createTime =  vo.getCreateTime();
					
					if(createTime != null)
					{
						//判断日期是否为今日或昨日
						Map<String, Boolean> diffDayMap = null;
						if(needTimeFixed)
							diffDayMap = DateUtil.getDiffDay(createTime, startAndEnd[0], startAndEnd[1]);
						else
							diffDayMap = DateUtil.getDiffDay(createTime);
						if(diffDayMap.get("day"))    //今天
						{
							vo.setDate("今日");
						}
						else if(diffDayMap.get("yesterday"))   //昨天
						{
//							if(needTimeFixed && (1 == currentInTime))
//								vo.setDate("今日");
//							else
								vo.setDate("昨日");
						}
						else    //非今日或昨日
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
			}
			
			page.setResult(recordEmpLogList);
			page.setTotalCount(count);
			
			return page;
		} catch (Exception e) {
			log.error("业务处理失败" , e);
			throw e;
		}
	}

	@Override
	public void add(ItRecordEmpLogEntity vo) {
		dao.save(vo);
	}

	@Override
	public List<ItRecordEmpLogEntity> getRecordLogListByStoreId(long storeID) {
		return dao.getRecordLogListByStoreId(storeID);
	}

	@Override
	public void update(ItRecordEmpLogEntity vo) {
		dao.update(vo);
	}
}
