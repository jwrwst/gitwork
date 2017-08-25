package com.platform.rp.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期工具类
 * @author kf052
 *@since 2014-03-06
 */
public final class DateUtil {
   
    public static final long SECOND = 1000;

    public static final long MINUTE = SECOND * 60;

    public static final long HOUR = MINUTE * 60;

    public static final long DAY = HOUR * 24;

    public static final long WEEK = DAY * 7;

    public static final long YEAR = DAY * 365;

    private static Log log = LogFactory.getLog(DateUtil.class);

    public static final String DATATIME_PARTTERN_FILE = "yyyyMMddHHmmssS";
    
//    private static DateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 解析日期
     * 
     * @param date
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return
     */
    public static Date parse(String date, String pattern) {
        Date resultDate = null;
        try {
            resultDate = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            log.error(e);
        }
        return resultDate;
    }

    /**
     * 解析日期 yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     *            日期字符串
     * @param pattern
     *            日期格式
     * @return
     */
    public static Date parseSimple(String date) {
        Date resultDate = null;
        DateFormat format  = new SimpleDateFormat("yyyy-MM-dd");
        try {
            resultDate = format.parse(date);
        } catch (ParseException e) {
            log.error(e);
        }
        return resultDate;
    }
    
    public static String parseStrDate(String strDate) {
		SimpleDateFormat smf  = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat smf2  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String resultDate = "";
		try {
			resultDate = smf2.format(smf.parse(strDate));
		} catch (ParseException e) {
			log.error(e);
		}
        return resultDate;
    }

    /**
     * 格式化日期字符串
     * 
     * @param date
     *            日期
     * @param pattern
     *            日期格式
     * @return
     */
    public static String format(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    /**
     * 取得当前日期
     * 
     * @return
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * @param offsetYear
     * @return 当前时间 + offsetYear
     */
    public static Timestamp getTimestampExpiredYear(int offsetYear) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.YEAR, offsetYear);
        return new Timestamp(now.getTime().getTime());
    }

    /**
     * @param offsetMonth
     * @return 当前时间 + offsetMonth
     */
    public static Timestamp getCurrentTimestampExpiredMonth(int offsetMonth) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MONTH, offsetMonth);
        return new Timestamp(now.getTime().getTime());
    }

    /**
     * @param offsetDay
     * @return 当前时间 + offsetDay
     */
    public static Timestamp getCurrentTimestampExpiredDay(int offsetDay) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, offsetDay);
        return new Timestamp(now.getTime().getTime());
    }

    /**
     * @param offsetSecond
     * @return 当前时间 + offsetSecond
     */
    public static Timestamp getCurrentTimestampExpiredSecond(int offsetSecond) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.SECOND, offsetSecond);
        return new Timestamp(now.getTime().getTime());
    }

    /**
     * @param offsetDay
     * @return 指定时间 + offsetDay
     */
    public static Timestamp getTimestampExpiredDay(Date givenDate, int offsetDay) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.DATE, offsetDay);
        return new Timestamp(date.getTime().getTime());
    }

    /**
     * 实现ORACLE中ADD_MONTHS函数功能
     * 
     * @param offsetMonth
     * @return 指定时间 + offsetMonth
     */
    public static Timestamp getTimestampExpiredMonth(Date givenDate, int offsetMonth) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.MONTH, offsetMonth);
        return new Timestamp(date.getTime().getTime());
    }

    /**
     * @param offsetSecond
     * @return 指定时间 + offsetSecond
     */
    public static Timestamp getTimestampExpiredSecond(Date givenDate, int offsetSecond) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.SECOND, offsetSecond);
        return new Timestamp(date.getTime().getTime());
    }
    
    /**
     * @param offsetSecond
     * @return 指定时间 + offsetSecond
     */
    public static Timestamp getTimestampExpiredHour(Date givenDate, int offsetHour) {
        Calendar date = Calendar.getInstance();
        date.setTime(givenDate);
        date.add(Calendar.HOUR, offsetHour);
        return new Timestamp(date.getTime().getTime());
    }

    /**
     * @return 当前日期 yyyy-MM-dd
     */
    public static String getCurrentDay() {
        return DateUtil.format(new Date(), "yyyy-MM-dd");
    }

    /**
     * @return 当前的时间戳 yyyy-MM-dd HH:mm:ss
     */
    public static String getNowTime() {
        return DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @return 给出指定日期的月份的第一天
     */
    public static Date getMonthFirstDay(Date givenDate) {
        Date date = DateUtil.parse(DateUtil.format(givenDate, "yyyy-MM"), "yyyy-MM");
        return date;
    }

    /**
     * @return 给出指定日期的月份的最后一天
     */
    public static Date getMonthLastDay(Date givenDate) {
        Date firstDay = getMonthFirstDay(givenDate);
        Date lastMonthFirstDay = DateUtil.getTimestampExpiredMonth(firstDay, 1);
        Date lastDay = DateUtil.getTimestampExpiredDay(lastMonthFirstDay, -1);
        return lastDay;
    }

    /**
     * 当前时间加offsetMinut分钟
     * @param offsetMinut
     * @return 当前时间 + offsetMinut
     */
    public static Date getCurrentDateExpiredMinut(int offsetMinut) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.add(Calendar.MINUTE, offsetMinut);
        return rightNow.getTime();
    }
    
    /**
     * 
     * @param specifyDate	需要判断的时间
     * @param timeStart		日结开始时间
     * @param timeEnd		日结结束时间
     * @param filter		是不是今日
     * @return	0 今日  1 昨日
     */
    public static int InTime(Date specifyDate, String timeStart, String timeEnd, String filter)
    {
    	//判断给定时间是否在当天或昨天的开始和结束时间范围内
    	if(filter.equals("day"))
    	{
    		//获取当天/昨天开始和结束日期
        	Map<String, Object> dayAndYesterday = getDayAndYesterday(false, timeStart, timeEnd);
        	String dayStart = dayAndYesterday.get("startDay").toString();
        	String dayEnd = dayAndYesterday.get("endDay").toString();
        	String yesterdayStart = dayAndYesterday.get("startYesterday").toString();
        	String yesterdayEnd = dayAndYesterday.get("endYesterday").toString();
        	
	    	int inDay = dateInSpecifyPeriod(specifyDate, dayStart, dayEnd);
	    	if(0 == inDay)
	    		return 0;
	    	int inYesterday = dateInSpecifyPeriod(specifyDate, yesterdayStart, yesterdayEnd);
	    	if(0 == inYesterday)
	    		return 1;
    	}
    	if(filter.equals("week"))
    	{
    		//获取本周/上周开始和结束日期
        	Map<String, Object> weekAndLastWeek = getWeekAndLastWeek(false, timeStart, timeEnd);
        	String weekStart = weekAndLastWeek.get("startWeek").toString();
        	String weekEnd = weekAndLastWeek.get("endWeek").toString();
        	String lastWeekStart = weekAndLastWeek.get("startLastWeek").toString();
        	String lastWeekEnd = weekAndLastWeek.get("endLastWeek").toString();
        	
	    	int inWeek = dateInSpecifyPeriod(specifyDate, weekStart, weekEnd);
	    	if(0 == inWeek)
	    		return 2;
	    	int inLastWeek = dateInSpecifyPeriod(specifyDate, lastWeekStart, lastWeekEnd);
	    	if(0 == inLastWeek)
	    		return 3;
    	}
    	if(filter.equals("month"))
    	{
    		//获取本月/上月开始和结束日期
    		Map<String, Object> monthAndLastMonth = getMonthAndLastMonth(false, timeStart, timeEnd);
        	String monthStart = monthAndLastMonth.get("startMonth").toString();
        	String monthEnd = monthAndLastMonth.get("endMonth").toString();
        	String lastMonthStart = monthAndLastMonth.get("startLastMonth").toString();
        	String lastMonthEnd = monthAndLastMonth.get("endLastMonth").toString();
        	
	    	int inMonth = dateInSpecifyPeriod(specifyDate, monthStart, monthEnd);
	    	if(0 == inMonth)
	    		return 4;
	    	int inLastMonth = dateInSpecifyPeriod(specifyDate, lastMonthStart, lastMonthEnd);
	    	if(0 == inLastMonth)
	    		return 5;
    	}
    	
    	return -1;
    	
//    	int specifyHour = specifyCal.get(Calendar.HOUR_OF_DAY);   //给定时间的小时
//  		int specifyMinute = specifyCal.get(Calendar.MINUTE);     //给定时间的分钟
//  		int specifySecond = specifyCal.get(Calendar.SECOND);     //给定时间的秒
//  		
//  		int dayStartHour = 0, dayEndHour = 0;          //统计周期的起始和结束时间对应的小时
//  		int dayStartMinute = 0, dayEndMinute = 0;  //统计周期的起始和结束时间对应的分钟
//  		int dayStartSecond = 0, dayEndSecond = 0;  //统计周期的起始和结束时间对应的秒
//  		
//  		if((timeStart != null) && (timeEnd != null))  //只有给定了起始和结束时间，才做判断
//		{
//  			// 截取开始时间时分秒  
//  			dayStartHour = Integer.parseInt(timeStart.substring(0, 2));  
//  			dayStartMinute = Integer.parseInt(timeStart.substring(3, 5));  
//  			dayStartSecond = Integer.parseInt(timeStart.substring(6, 8));  
//			// 截取结束时间时分秒  
//			dayEndHour = Integer.parseInt(timeEnd.substring(0, 2));  
//			dayEndMinute = Integer.parseInt(timeEnd.substring(3, 5));  
//			dayEndSecond = Integer.parseInt(timeEnd.substring(6, 8)); 
//  					
//			if(dayStartHour > dayEndHour)   //下午16:30:30至凌晨4:30:30的情况
//			{
//				if(specifyHour > dayStartHour)      //17点之后的数据
//				{
//					retVal = 1;
//				}
//				else if(specifyHour == dayStartHour)    //16点之后，17点之前的数据
//				{
//					if(specifyMinute > dayStartMinute)
//						retVal = 1;
//					else if(specifyMinute == dayStartMinute)
//					{
//						if(specifySecond >= dayStartSecond)
//							retVal = 1;
//					}
//				}
//				else if(specifyHour < dayEndHour)   //4点之前的数据
//				{
//					retVal = 2;
//				}
//				else if(specifyHour == dayEndHour) //4点到5点间的数据
//				{
//					if(specifyMinute < dayEndMinute)
//					{
//						retVal = 2;
//					}
//					else if(specifyMinute == dayEndMinute)
//					{
//						if(specifySecond <= dayEndSecond)
//						{
//							retVal = 2;
//						}
//					}
//				}
//			}
//			else     //上午10:30:30点到晚上23:30:30的情况
//			{
//				if((specifyHour > dayStartHour) && (specifyHour < dayEndHour))   //11点到23点之间的数据
//				{
//					retVal = 0;
//				}
//				else if(specifyHour == dayStartHour)   //10点到11点间的数据
//				{
//					if(specifyMinute > dayStartMinute)
//						retVal = 0;
//					else if(specifyMinute == dayStartMinute)
//					{
//						if(specifySecond >= dayStartSecond)
//							retVal = 0;
//					}
//				}
//				else if(specifyHour == dayEndHour)   //23点到24点之间的数据
//				{
//					if(specifyMinute < dayEndMinute)
//						retVal = 0;
//					else if(specifyMinute == dayEndMinute)
//					{
//						if(specifySecond <= dayEndSecond)
//							retVal = 0;
//					}
//				}
//			}
//		}
    }
    
//    //判断给定日期是否属于当日/昨日/本周/上周/本月/上月（带时间修正参数）
//  	public static Map<String, Boolean> getDiffDay(Date baseDate, Date specifyDate, String timeStart, String timeEnd)
//  	{
//  		Map<String, Boolean> returnMap = new HashMap<String, Boolean>();
//  		
//  		returnMap.put("day", false);
//  		returnMap.put("yesterday", false);
//  		returnMap.put("week", false);
//  		returnMap.put("lastWeek", false);
//  		returnMap.put("month", false);
//  		returnMap.put("lastMonth", false);
//
//  		Calendar specifyCal = Calendar.getInstance();
//  		specifyCal.setTime(specifyDate);
//  		
//  		String specifyTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(specifyDate);
//  		
//  		Map<String, Object> dayAndYesterday = getDayAndYesterday(true, timeStart, timeEnd);
//  		Map<String, Object> weekAndLastWeek = getWeekAndLastWeek(timeStart, timeEnd);
//  		Map<String, Object> monthAndLastMonth = getMonthAndLastMonth(timeStart, timeEnd);
//  		
//  		//给定时间是否为今日
//  		String dayStart = dayAndYesterday.get("startDay").toString();
//  		String dayEnd = dayAndYesterday.get("endDay").toString();
//  		
//  		int compare = compareDate(specifyTime, dayStart);
//  		if(compare >= 0)  //指定时间大于当日开始时间
//  		{
//  			compare = compareDate(specifyTime, dayEnd);
//  			if(compare <= 0)  //指定时间小于当日结束时间
//  				returnMap.put("day", true);
//  		}
//
//  		//给定时间是否为昨日
//  		String yesterdayStart = dayAndYesterday.get("startYesterday").toString();
//  		String yesterdayEnd = dayAndYesterday.get("endYesterday").toString();
//  		
//  		compare = compareDate(specifyTime, yesterdayStart);
//  		if(compare >= 0)  //指定时间大于昨日开始时间
//  		{
//  			compare = compareDate(specifyTime, yesterdayEnd);
//  			if(compare <= 0)  //指定时间小于昨日结束时间
//  				returnMap.put("yesterday", true);
//  		}
//  		
//  		//给定时间是否为本周
//  		String weekStart= weekAndLastWeek.get("startWeek").toString();
//  		String weekEnd = weekAndLastWeek.get("endWeek").toString();
//  		
//  		compare = compareDate(specifyTime, weekStart);
//  		if(compare >= 0)  //指定时间大于昨日开始时间
//  		{
//  			compare = compareDate(specifyTime, weekEnd);
//  			if(compare <= 0)  //指定时间小于昨日结束时间
//  				returnMap.put("week", true);
//  		}
//  		
//  		//给定时间是否为上周
//  		String lastWeekStart = weekAndLastWeek.get("startLastWeek").toString();
//  		String lastWeekEnd = weekAndLastWeek.get("endLastWeek").toString();
//  		
//  		compare = compareDate(specifyTime, lastWeekStart);
//  		if(compare >= 0)  //指定时间大于昨日开始时间
//  		{
//  			compare = compareDate(specifyTime, lastWeekEnd);
//  			if(compare <= 0)  //指定时间小于昨日结束时间
//  				returnMap.put("lastWeek", true);
//  		}
//  		
//  		//给定时间是否为本月
//  		String monthStart = monthAndLastMonth.get("startMonth").toString();
//  		String monthEnd = monthAndLastMonth.get("endMonth").toString();
//  		
//  		compare = compareDate(specifyTime, monthStart);
//  		if(compare >= 0)  //指定时间大于昨日开始时间
//  		{
//  			compare = compareDate(specifyTime, monthEnd);
//  			if(compare <= 0)  //指定时间小于昨日结束时间
//  				returnMap.put("month", true);
//  		}
//  		
//  		//给定时间是否为上月
//  		String lastMonthStart = monthAndLastMonth.get("startLastMonth").toString();
//  		String lastMonthEnd = monthAndLastMonth.get("endLastMonth").toString();
//  		
//  		compare = compareDate(specifyTime, lastMonthStart);
//  		if(compare >= 0)  //指定时间大于昨日开始时间
//  		{
//  			compare = compareDate(specifyTime, lastMonthEnd);
//  			if(compare <= 0)  //指定时间小于昨日结束时间
//  				returnMap.put("lastMonth", true);
//  		}
//  		
//  		return returnMap;
//  	}
    
    //判断给定日期是否属于当日/昨日/本周/上周/本月/上月（带时间修正参数）
  	public static Map<String, Boolean> getDiffDay(Date specifyDate, String timeStart, String timeEnd)
  	{
  		Map<String, Boolean> returnMap = new HashMap<String, Boolean>();
  		
  		returnMap.put("day", false);
  		returnMap.put("yesterday", false);
  		returnMap.put("week", false);
  		returnMap.put("lastWeek", false);
  		returnMap.put("month", false);
  		returnMap.put("lastMonth", false);

  		Calendar specifyCal = Calendar.getInstance();
  		specifyCal.setTime(specifyDate);
  		
  		String specifyTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(specifyDate);
  		
  		Map<String, Object> dayAndYesterday = getDayAndYesterday(true, timeStart, timeEnd);
  		Map<String, Object> weekAndLastWeek = getWeekAndLastWeek(true, timeStart, timeEnd);
  		Map<String, Object> monthAndLastMonth = getMonthAndLastMonth(true, timeStart, timeEnd);
  		
  		//给定时间是否为今日
  		String dayStart = dayAndYesterday.get("startDay").toString();
  		String dayEnd = dayAndYesterday.get("endDay").toString();
  		
  		int compare = compareDate(specifyTime, dayStart);
  		if(compare >= 0)  //指定时间大于当日开始时间
  		{
  			compare = compareDate(specifyTime, dayEnd);
  			if(compare <= 0)  //指定时间小于当日结束时间
  				returnMap.put("day", true);
  		}

  		//给定时间是否为昨日
  		String yesterdayStart = dayAndYesterday.get("startYesterday").toString();
  		String yesterdayEnd = dayAndYesterday.get("endYesterday").toString();
  		
  		compare = compareDate(specifyTime, yesterdayStart);
  		if(compare >= 0)  //指定时间大于昨日开始时间
  		{
  			compare = compareDate(specifyTime, yesterdayEnd);
  			if(compare <= 0)  //指定时间小于昨日结束时间
  				returnMap.put("yesterday", true);
  		}
  		
  		//给定时间是否为本周
  		String weekStart= weekAndLastWeek.get("startWeek").toString();
  		String weekEnd = weekAndLastWeek.get("endWeek").toString();
  		
  		compare = compareDate(specifyTime, weekStart);
  		if(compare >= 0)  //指定时间大于昨日开始时间
  		{
  			compare = compareDate(specifyTime, weekEnd);
  			if(compare <= 0)  //指定时间小于昨日结束时间
  				returnMap.put("week", true);
  		}
  		
  		//给定时间是否为上周
  		String lastWeekStart = weekAndLastWeek.get("startLastWeek").toString();
  		String lastWeekEnd = weekAndLastWeek.get("endLastWeek").toString();
  		
  		compare = compareDate(specifyTime, lastWeekStart);
  		if(compare >= 0)  //指定时间大于昨日开始时间
  		{
  			compare = compareDate(specifyTime, lastWeekEnd);
  			if(compare <= 0)  //指定时间小于昨日结束时间
  				returnMap.put("lastWeek", true);
  		}
  		
  		//给定时间是否为本月
  		String monthStart = monthAndLastMonth.get("startMonth").toString();
  		String monthEnd = monthAndLastMonth.get("endMonth").toString();
  		
  		compare = compareDate(specifyTime, monthStart);
  		if(compare >= 0)  //指定时间大于昨日开始时间
  		{
  			compare = compareDate(specifyTime, monthEnd);
  			if(compare <= 0)  //指定时间小于昨日结束时间
  				returnMap.put("month", true);
  		}
  		
  		//给定时间是否为上月
  		String lastMonthStart = monthAndLastMonth.get("startLastMonth").toString();
  		String lastMonthEnd = monthAndLastMonth.get("endLastMonth").toString();
  		
  		compare = compareDate(specifyTime, lastMonthStart);
  		if(compare >= 0)  //指定时间大于昨日开始时间
  		{
  			compare = compareDate(specifyTime, lastMonthEnd);
  			if(compare <= 0)  //指定时间小于昨日结束时间
  				returnMap.put("lastMonth", true);
  		}
  		
  		return returnMap;
  	}
    
//    //判断给定日期是否属于当日/昨日/本周/上周/本月/上月（带时间修正参数）
//  	public static Map<String, Boolean> getDiffDay(Date specifyDate, String timeStart, String timeEnd)
//  	{
//  		Map<String, Boolean> returnMap = new HashMap<String, Boolean>();
//  		returnMap.put("day", false);
//  		returnMap.put("yesterday", false);
//  		returnMap.put("week", false);
//  		returnMap.put("lastWeek", false);
//  		returnMap.put("month", false);
//  		returnMap.put("lastMonth", false);
//  		
//  		Calendar specifyCal = Calendar.getInstance();
//  		Calendar currentCal = Calendar.getInstance();
//
//  		specifyCal.setTime(specifyDate);
//  		currentCal.setTime(new Date());
//  		
//  		int specifyYear = specifyCal.get(Calendar.YEAR);   //给定时间年份
//  		int currentYear = currentCal.get(Calendar.YEAR); //当前时间年份
//  		
//  		int specifyDayOfYear = specifyCal.get(Calendar.DAY_OF_YEAR);   //计算给定时间是一年中的第几天
//  		int currentDayOfYear = currentCal.get(Calendar.DAY_OF_YEAR);  //当前时间是一年中的第几天
//  		
//  		int specifyWeek = specifyCal.get(Calendar.WEEK_OF_YEAR);   //给定时间是一年中的第几周
//  		int currentWeek = currentCal.get(Calendar.WEEK_OF_YEAR);  //当前时间是一年中的第几周
//  		
//  		int specifyMonth = specifyCal.get(Calendar.MONTH) + 1;   //给定时间月份
//  		int currentMonth = currentCal.get(Calendar.MONTH) + 1;  //当前时间月份
//
//  		int diffYear = currentYear - specifyYear;    //当前时间和给定时间年份差值
//  		int diffDay = Math.abs(specifyDayOfYear - currentDayOfYear);   //当前时间和给定时间日期相差多少天
//  		
//  		int specifyInTime = InTime(specifyDate, timeStart, timeEnd);
//  		
//  		//给定时间和当前时间处于同一年或相差一年
//  		if((0 == diffYear) || (1 == diffYear))
//  		{
//  			if(1 == diffYear)    //给定时间和当前时间相差一年
//  			{
//  				//获取给定时间所在年份的总天数
//  				int totalDayOfYear = 0;   
//  				if(new GregorianCalendar().isLeapYear(specifyYear))
//  				{
//  					totalDayOfYear = 366;
//  				}
//  				else
//  					totalDayOfYear = 365;
//  				
//  				//计算给定时间和当前时间相差天数
//  				diffDay = totalDayOfYear - diffDay;
//  				
//  				if(specifyWeek == currentWeek)    //同一周
//  				{
//  					//判断给定时间是否本周第一天
//					int specifyDayOfWeek = specifyCal.get(Calendar.DAY_OF_WEEK);
//					if(1 == specifyDayOfWeek)
//					{
//						if(2 == specifyInTime)
//							returnMap.put("lastWeek", true);
//						else if(-1 != specifyInTime)
//							returnMap.put("week", true);
//					}
//					else
//						returnMap.put("week", true);
//  				}
//  				else if((53 == specifyWeek) && (1 == currentWeek))    //相差一周
//  				{
//  					//判断给定时间是否上周第一天
//					int specifyDayOfWeek = specifyCal.get(Calendar.DAY_OF_WEEK);
//					if(1 == specifyDayOfWeek)
//					{
//						if((1 == specifyInTime) || (0 == specifyInTime))
//							returnMap.put("lastWeek", true);
//					}
//					else
//						returnMap.put("lastWeek", true);
//  				}
//  				
//  				//相差一个月
//  				if((1 == currentMonth) && (12 == specifyMonth))
//  				{
//  					//判断给定时间是否上月第一天
//					int specifyDayOfMonth = specifyCal.get(Calendar.DAY_OF_MONTH);
//					if(1 == specifyDayOfMonth)
//					{
//						if((1 == specifyInTime) || (0 == specifyInTime))
//							returnMap.put("lastMonth", true);
//					}
//					else
//						returnMap.put("lastMonth", true);
//  				}
//  			}
//  			else    //给定时间和当前时间是同一年
//  			{
//  				if(specifyWeek == currentWeek)           //同一周
//  				{
//  					//判断给定时间是否本周第一天
//					int specifyDayOfWeek = specifyCal.get(Calendar.DAY_OF_WEEK);
//					if(1 == specifyDayOfWeek)
//					{
//						if(2 == specifyInTime)
//							returnMap.put("lastWeek", true);
//						else if(-1 != specifyInTime)
//							returnMap.put("week", true);
//					}
//					else
//						returnMap.put("week", true);
//  				}
//  				else if(1 == (currentWeek - specifyWeek))  //相差一周
//  				{
//  					//判断给定时间是否上周第一天
//					int specifyDayOfWeek = specifyCal.get(Calendar.DAY_OF_WEEK);
//					if(1 == specifyDayOfWeek)
//					{
//						if((1 == specifyInTime) || (0 == specifyInTime))
//							returnMap.put("lastWeek", true);
//					}
//					else
//						returnMap.put("lastWeek", true);
//  				}
//  				
//  				if(currentMonth == specifyMonth)            //同一个月
//  				{
//  					//判断给定时间是否本月第一天
//					int specifyDayOfMonth = specifyCal.get(Calendar.DAY_OF_MONTH);
//					if(1 == specifyDayOfMonth)
//					{
//						if(2 == specifyInTime)
//							returnMap.put("lastMonth", true);
//						else if(-1 != specifyInTime)
//							returnMap.put("month", true);
//					}
//					else
//						returnMap.put("month", true);
//  				}
//  				else if(1 == (currentMonth - specifyMonth))   //相差一个月
//  				{
//  					//判断给定时间是否上月第一天
//					int specifyDayOfMonth = specifyCal.get(Calendar.DAY_OF_MONTH);
//					if(1 == specifyDayOfMonth)
//					{
//						if((1 == specifyInTime) || (0 == specifyInTime))
//							returnMap.put("lastMonth", true);
//					}
//					else
//						returnMap.put("lastMonth", true);
//  				}
//  			}
//
//  			if(0 == diffDay)    //给定时间和当前时间是同一天
//  			{
//  				if(2 == specifyInTime)
//  					returnMap.put("yesterday", true);
//  				else if(-1 != specifyInTime)
//  					returnMap.put("day", true);
//  			}
//  			if(1 == diffDay)   //给定时间和当前时间相差一天
//  			{
//  				if((1 == specifyInTime) || ((0 == specifyInTime)))
//  					returnMap.put("yesterday", true);
//  			}
//  		}
//  		
//  		return returnMap;
//  	}
  	
  	//判断给定日期是否属于当日/昨日/本周/上周/本月/上月
  	public static Map<String, Boolean> getDiffDay(Date specifyDate)
  	{
  		Map<String, Boolean> returnMap = new HashMap<String, Boolean>();
  		returnMap.put("day", false);
  		returnMap.put("yesterday", false);
  		returnMap.put("week", false);
  		returnMap.put("lastWeek", false);
  		returnMap.put("month", false);
  		returnMap.put("lastMonth", false);
  		
  		Calendar specifyCal = Calendar.getInstance();
  		Calendar currentCal = Calendar.getInstance();

  		specifyCal.setTime(specifyDate);
  		currentCal.setTime(new Date());
  		
  		int specifyYear = specifyCal.get(Calendar.YEAR);   //给定时间年份
  		int currentYear = currentCal.get(Calendar.YEAR); //当前时间年份
  		
  		int specifyDayOfYear = specifyCal.get(Calendar.DAY_OF_YEAR);   //计算给定时间是一年中的第几天
  		int currentDayOfYear = currentCal.get(Calendar.DAY_OF_YEAR);  //当前时间是一年中的第几天
  		
  		int specifyWeek = specifyCal.get(Calendar.WEEK_OF_YEAR);   //给定时间是一年中的第几周
  		int currentWeek = currentCal.get(Calendar.WEEK_OF_YEAR);  //当前时间是一年中的第几周
  		
  		int specifyMonth = specifyCal.get(Calendar.MONTH) + 1;   //给定时间月份
  		int currentMonth = currentCal.get(Calendar.MONTH) + 1;  //当前时间月份

  		int diffYear = currentYear - specifyYear;    //当前时间和给定时间年份差值
  		int diffDay = Math.abs(specifyDayOfYear - currentDayOfYear);   //当前时间和给定时间日期相差多少天
  		
  		//给定时间和当前时间处于同一年或相差一年
  		if((0 == diffYear) || (1 == diffYear))
  		{
  			if(1 == diffYear)    //给定时间和当前时间相差一年
  			{
  				//获取给定时间所在年份的总天数
  				int totalDayOfYear = 0;   
  				if(new GregorianCalendar().isLeapYear(specifyYear))
  				{
  					totalDayOfYear = 366;
  				}
  				else
  					totalDayOfYear = 365;
  				
  				//计算给定时间和当前时间相差天数
  				diffDay = totalDayOfYear - diffDay;
  				
  				if(specifyWeek == currentWeek)    //同一周
  					returnMap.put("week", true);
  				else if((53 == specifyWeek) && (1 == currentWeek))    //相差一周
  					returnMap.put("lastWeek", true);
  				
  				//相差一个月
  				if((1 == currentMonth) && (12 == specifyMonth))
  					returnMap.put("lastMonth", true);
  			}
  			else    //给定时间和当前时间是同一年
  			{
  				if(specifyWeek == currentWeek)           //同一周
  					returnMap.put("week", true);
  				else if(1 == (currentWeek - specifyWeek))  //相差一周
  					returnMap.put("lastWeek", true);
  				
  				if(currentMonth == specifyMonth)            //同一个月
  					returnMap.put("month", true);
  				else if(1 == (currentMonth - specifyMonth))   //相差一个月
  					returnMap.put("lastMonth", true);
  			}

  			if(0 == diffDay)    //给定时间和当前时间是同一天
  			{
  					returnMap.put("day", true);
  			}
  			if(1 == diffDay)   //给定时间和当前时间相差一天
  			{
  					returnMap.put("yesterday", true);
  			}
  		}
  		
  		return returnMap;
  	}
  	
    //根据给定日期判断是星期几
  	public static String getWeekDay(Date date)
  	{
  		String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
  		Calendar cal = Calendar.getInstance();
          
  		//获取本条数据是星期几
  		cal.setTime(date);
  		int weekIndex = cal.get(Calendar.DAY_OF_WEEK)  - 1;
  		if(weekIndex < 0)
  		{
  			weekIndex = 0;
  		}
  		
  		return weeks[weekIndex];
  	}

  	//获取指定时间上一周的周一日期
  	public static Date getLastWeekMonday(Date date)
  	{
  		Date dt = DateUtils.addDays(date, -1);
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(dt);
  		cal.add(Calendar.WEEK_OF_YEAR, -1);
  		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
  		return cal.getTime();
   }
  	
  	//获取指定时间上一周的周日日期
  	public static Date  getLastWeekSunday(Date date) 
  	{
  		Date dt = DateUtils.addDays(date, -1);
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(dt);
  		cal.set(Calendar.DAY_OF_WEEK, 1);
  		return cal.getTime();
    }
  	
  	//获取指定时间所在周的周一日期
  	public static Date getCurrentWeekMonday(Date date)
  	{
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(date);
  		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)  - 1;
  		if (dayOfWeek == 0)
  			dayOfWeek = 7;
  		cal.add(Calendar.DATE, 1 - dayOfWeek);
  		
  		return cal.getTime();
    }
  	
  	//获取指定时间所在周的周日日期
  	public static Date getCurrentWeekSunday(Date date)
  	{
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(date);
  		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)  - 1;   //避免指定日期是周日造成数据不对的问题
  		if (dayOfWeek == 0)
  			dayOfWeek = 7;
  		cal.add(Calendar.DATE, 7 - dayOfWeek);
  		
  		return cal.getTime();
  	}
  	
  	//获取指定时间下一周的周一日期
  	public static Date getNextWeekMonday(Date date)
  	{
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(date);
  		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)  - 1;
  		if (dayOfWeek == 0)
  			dayOfWeek = 7;
  		cal.add(Calendar.DATE, 8  - dayOfWeek);
  		
  		return cal.getTime();
    }
  	
  	//获取给定时间所在月第一天日期
  	public static Date getCurrentMonthFirstDay(Date date)
  	{
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(date);
  		cal.set(Calendar.DAY_OF_MONTH, 1);
  		
  		return cal.getTime();
  	}
  	
  	//获取给定时间所在月最后一天日期
  	public static Date getCurrentMonthLastDay(Date date)
  	{
  		Calendar cal = Calendar.getInstance();    
  		cal.setTime(date);
  		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
  		
  		return cal.getTime();
  	}
  	
  	//获取给定时间上一月第一天日期
  	public static Date getLastMonthFirstDay(Date date)
  	{
  		Calendar cal = Calendar.getInstance();
  		cal.setTime(date);
  		cal.add(Calendar.MONTH, -1);
  		cal.set(Calendar.DAY_OF_MONTH, 1);
  		
  		return cal.getTime();
  	}
  	
  	//获取给定时间上一月最后一天日期
  	public static Date getLastMonthLastDay(Date date)
  	{
  		Calendar cal = Calendar.getInstance();    
  		cal.setTime(date);
  		cal.add(Calendar.MONTH, -1);
  		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

  		return cal.getTime();
  	}
  	
  	//获取给定时间下一月第一天日期
  	public static Date getNextMonthFirstDay(Date date)
  	{
  		Calendar cal = Calendar.getInstance();    
  		cal.setTime(date);
  		cal.add(Calendar.MONTH, 1);
  		cal.set(Calendar.DAY_OF_MONTH, 1);
  		
  		return cal.getTime();
  	}
  	
//  	//根据修正时间计算店铺统计周期今日和昨日的起始和结束时间
//  	public static Map<String, Object> getDayAndYesterday(Date date, String timeStart, String timeEnd)
//	{
//  		Map<String, Object> retMap = new HashMap<String, Object>();
//  		
//  		int inTime = InTime(date, timeStart, timeEnd, "day");
//  		
//		try {
//			Calendar cal = Calendar.getInstance();
//			if(1 == inTime)
//				cal.add(Calendar.DATE,  -1);
//			String dayDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
//			cal.add(Calendar.DATE,  1);
//			String tomrrowDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
//			cal = Calendar.getInstance();
//			if(1 == inTime)
//				cal.add(Calendar.DATE,  -1);
//			cal.add(Calendar.DATE,  -1);
//			String yesterdayDate = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
//			
//			// 截取开始时间时分秒  
//			int startHour = Integer.parseInt(timeStart.substring(0, 2));  
//			int startMinute = Integer.parseInt(timeStart.substring(3, 5));  
//			int startSecond = Integer.parseInt(timeStart.substring(6, 8));  
//			// 截取结束时间时分秒  
//			int endHour = Integer.parseInt(timeEnd.substring(0, 2));  
//			int endMinute = Integer.parseInt(timeEnd.substring(3, 5));  
//			int endSecond = Integer.parseInt(timeEnd.substring(6, 8)); 
//			
//			//计算今日和昨日起始与结束时间
//			String dayStart = "", dayEnd = "";
//			String yesterdayStart = "", yesterdayEnd = "";
//			if(startHour > endHour)   //统计周期开始和结束时间不在同一天
//			{
//				dayStart = dayDate + " " + timeStart;
//				dayEnd = tomrrowDate + " " + timeEnd;
//				
//				yesterdayStart = yesterdayDate + " " + timeStart;
//				yesterdayEnd = dayDate + " " + timeEnd;
//			}
//			else if(startHour == endHour)
//			{
//				if(startMinute > endMinute)   //统计周期开始和结束时间不在同一天
//				{
//					dayStart = dayDate + " " + timeStart;
//					dayEnd = tomrrowDate + " " + timeEnd;
//					
//					yesterdayStart = yesterdayDate + " " + timeStart;
//					yesterdayEnd = dayDate + " " + timeEnd;
//				}
//				else if(startMinute == endMinute)
//				{
//					if(startSecond >= endSecond)   //统计周期开始和结束时间不在同一天
//					{
//						dayStart = dayDate + " " + timeStart;
//						dayEnd = tomrrowDate + " " + timeEnd;
//						
//						yesterdayStart = yesterdayDate + " " + timeStart;
//						yesterdayEnd = dayDate + " " + timeEnd;
//					}
//					else   //统计周期开始和结束时间在同一天
//					{
//						dayStart = dayDate + " " + timeStart;
//						dayEnd = dayDate + " " + timeEnd;
//						
//						yesterdayStart = yesterdayDate + " " + timeStart;
//						yesterdayEnd = yesterdayDate + " " + timeEnd;
//					}
//				}
//				else   //统计周期开始和结束时间在同一天
//				{
//					dayStart = dayDate + " " + timeStart;
//					dayEnd = dayDate + " " + timeEnd;
//					
//					yesterdayStart = yesterdayDate + " " + timeStart;
//					yesterdayEnd = yesterdayDate + " " + timeEnd;
//				}
//			}
//			else     //统计周期开始和结束时间在同一天
//			{
//				dayStart = dayDate + " " + timeStart;
//				dayEnd = dayDate + " " + timeEnd;
//				
//				yesterdayStart = yesterdayDate + " " + timeStart;
//				yesterdayEnd = yesterdayDate + " " + timeEnd;
//			}
//			
//			retMap.put("startDay", dayStart);
//			retMap.put("endDay", dayEnd);
//			retMap.put("startYesterday", yesterdayStart);
//			retMap.put("endYesterday", yesterdayEnd);
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
//		return retMap;
//	}
  	
  	//根据修正时间计算店铺统计周期今日和昨日的起始和结束时间
  	public static Map<String, Object> getDayAndYesterday(boolean needJudgeCurrent, String timeStart, String timeEnd)
	{
  		Map<String, Object> retMap = new HashMap<String, Object>();
  		
		try {
			// 截取开始时间时分秒  
			int startHour = Integer.parseInt(timeStart.substring(0, 2));
			int startMinute = Integer.parseInt(timeStart.substring(3, 5));
			int startSecond = Integer.parseInt(timeStart.substring(6, 8));
			// 截取结束时间时分秒  
			int endHour = Integer.parseInt(timeEnd.substring(0, 2));  
			int endMinute = Integer.parseInt(timeEnd.substring(3, 5));  
			int endSecond = Integer.parseInt(timeEnd.substring(6, 8)); 
			
			int inTime = 0;
			if(needJudgeCurrent)    //需要对当前时间日期处于昨天还是今天进行判断
			{
				inTime = InTime(new Date(), timeStart, timeEnd, "day");
			}
			
			Calendar cal = Calendar.getInstance();
			if(1 == inTime)
				cal.add(Calendar.DATE,  -1);
			String dayDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			cal.add(Calendar.DATE,  1);
			String tomrrowDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			cal = Calendar.getInstance();
			if(1 == inTime)
				cal.add(Calendar.DATE,  -1);
			cal.add(Calendar.DATE,  -1);
			String yesterdayDate = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());

//			Calendar cal = Calendar.getInstance();
//			String dayDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
//			cal.add(Calendar.DATE,  1);
//			String tomrrowDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
//			cal = Calendar.getInstance();
//			cal.add(Calendar.DATE,  -1);
//			String yesterdayDate = new SimpleDateFormat( "yyyy-MM-dd").format(cal.getTime());
			
			//计算今日和昨日起始与结束时间
			String dayStart = "", dayEnd = "";
			String yesterdayStart = "", yesterdayEnd = "";
			if(startHour > endHour)   //统计周期开始和结束时间不在同一天
			{
				dayStart = dayDate + " " + timeStart;
				dayEnd = tomrrowDate + " " + timeEnd;
				
				yesterdayStart = yesterdayDate + " " + timeStart;
				yesterdayEnd = dayDate + " " + timeEnd;
			}
			else if(startHour == endHour)
			{
				if(startMinute > endMinute)   //统计周期开始和结束时间不在同一天
				{
					dayStart = dayDate + " " + timeStart;
					dayEnd = tomrrowDate + " " + timeEnd;
					
					yesterdayStart = yesterdayDate + " " + timeStart;
					yesterdayEnd = dayDate + " " + timeEnd;
				}
				else if(startMinute == endMinute)
				{
					if(startSecond >= endSecond)   //统计周期开始和结束时间不在同一天
					{
						dayStart = dayDate + " " + timeStart;
						dayEnd = tomrrowDate + " " + timeEnd;
						
						yesterdayStart = yesterdayDate + " " + timeStart;
						yesterdayEnd = dayDate + " " + timeEnd;
					}
					else   //统计周期开始和结束时间在同一天
					{
						dayStart = dayDate + " " + timeStart;
						dayEnd = dayDate + " " + timeEnd;
						
						yesterdayStart = yesterdayDate + " " + timeStart;
						yesterdayEnd = yesterdayDate + " " + timeEnd;
					}
				}
				else   //统计周期开始和结束时间在同一天
				{
					dayStart = dayDate + " " + timeStart;
					dayEnd = dayDate + " " + timeEnd;
					
					yesterdayStart = yesterdayDate + " " + timeStart;
					yesterdayEnd = yesterdayDate + " " + timeEnd;
				}
			}
			else     //统计周期开始和结束时间在同一天
			{
				dayStart = dayDate + " " + timeStart;
				dayEnd = dayDate + " " + timeEnd;
				
				yesterdayStart = yesterdayDate + " " + timeStart;
				yesterdayEnd = yesterdayDate + " " + timeEnd;
			}
			
			retMap.put("startDay", dayStart);
			retMap.put("endDay", dayEnd);
			retMap.put("startYesterday", yesterdayStart);
			retMap.put("endYesterday", yesterdayEnd);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return retMap;
	}
  	
  	//根据修正时间计算店铺统计周期本周和上周的起始和结束时间
  	public static Map<String, Object> getWeekAndLastWeek(boolean needJudgeCurrent,  String timeStart, String timeEnd)
	{
  		Map<String, Object> retMap = new HashMap<String, Object>();
  		
		try {
			// 截取开始时间时分秒  
			int startHour = Integer.parseInt(timeStart.substring(0, 2));  
			int startMinute = Integer.parseInt(timeStart.substring(3, 5));  
			int startSecond = Integer.parseInt(timeStart.substring(6, 8));  
			// 截取结束时间时分秒  
			int endHour = Integer.parseInt(timeEnd.substring(0, 2));  
			int endMinute = Integer.parseInt(timeEnd.substring(3, 5));  
			int endSecond = Integer.parseInt(timeEnd.substring(6, 8)); 
			
			//需要对当前时间日期处于昨天还是今天进行判断
			int inTime = 0;
			if(needJudgeCurrent)    
			{
				inTime = InTime(new Date(), timeStart, timeEnd, "day");
			}
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date()); 
			
			//如果当前时间是周一，且处于昨天的统计周期内
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if((1 == inTime) && (1 == dayOfWeek))
				cal.add(Calendar.DATE,  -7);
			
			//获取当前时间所在周的周一和周日日期
			Date weekMondayDate = getCurrentWeekMonday(cal.getTime());
			Date weekSundayDate = getCurrentWeekSunday(cal.getTime());
			
			//获取当前时间下一周的周一日期
			Date nextWeekMondayDate = getNextWeekMonday(cal.getTime());
			
			//获取当前时间上一周的周一和周日日期
			Date lastWeekMondayDate = getLastWeekMonday(cal.getTime());
			Date lastWeekSundayDate = getLastWeekSunday(cal.getTime());
			
			String weekMonday = new SimpleDateFormat("yyyy-MM-dd").format(weekMondayDate);
			String weekSunday = new SimpleDateFormat("yyyy-MM-dd").format(weekSundayDate);
			String nextWeekMonday = new SimpleDateFormat("yyyy-MM-dd").format(nextWeekMondayDate);
			String lastWeekMonday = new SimpleDateFormat("yyyy-MM-dd").format(lastWeekMondayDate);
			String lastWeekSunday = new SimpleDateFormat("yyyy-MM-dd").format(lastWeekSundayDate);
			
			//计算本周和上周起始与结束时间
			String weekStart = "", weekEnd = "";
			String lastWeekStart = "", lastWeekEnd = "";
			if(startHour > endHour)   //统计周期开始和结束时间不在同一天
			{
				weekStart = weekMonday + " " + timeStart;
				weekEnd = nextWeekMonday + " " + timeEnd;
				
				lastWeekStart = lastWeekMonday + " " + timeStart;
				lastWeekEnd = weekMonday + " " + timeEnd;
			}
			else if(startHour == endHour)
			{
				if(startMinute > endMinute)   //统计周期开始和结束时间不在同一天
				{
					weekStart = weekMonday + " " + timeStart;
					weekEnd = nextWeekMonday + " " + timeEnd;
					
					lastWeekStart = lastWeekMonday + " " + timeStart;
					lastWeekEnd = weekMonday + " " + timeEnd;
				}
				else if(startMinute == endMinute)
				{
					if(startSecond >= endSecond)   //统计周期开始和结束时间不在同一天
					{
						weekStart = weekMonday + " " + timeStart;
						weekEnd = nextWeekMonday + " " + timeEnd;
						
						lastWeekStart = lastWeekMonday + " " + timeStart;
						lastWeekEnd = weekMonday + " " + timeEnd;
					}
					else   //统计周期开始和结束时间在同一天
					{
						weekStart = weekMonday + " " + timeStart;
						weekEnd = weekSunday + " " + timeEnd;
						
						lastWeekStart = lastWeekMonday + " " + timeStart;
						lastWeekEnd = lastWeekSunday + " " + timeEnd;
					}
				}
				else   //统计周期开始和结束时间在同一天
				{
					weekStart = weekMonday + " " + timeStart;
					weekEnd = weekSunday + " " + timeEnd;
					
					lastWeekStart = lastWeekMonday + " " + timeStart;
					lastWeekEnd = lastWeekSunday + " " + timeEnd;
				}
			}
			else     //统计周期开始和结束时间在同一天
			{
				weekStart = weekMonday + " " + timeStart;
				weekEnd = weekSunday + " " + timeEnd;
				
				lastWeekStart = lastWeekMonday + " " + timeStart;
				lastWeekEnd = lastWeekSunday + " " + timeEnd;
			}
			
			retMap.put("startWeek", weekStart);
			retMap.put("endWeek", weekEnd);
			retMap.put("startLastWeek", lastWeekStart);
			retMap.put("endLastWeek", lastWeekEnd);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} 
		
		return retMap;
	}
  	
  	//根据修正时间计算店铺统计周期本月和上月的起始和结束时间
  	public static Map<String, Object> getMonthAndLastMonth(boolean needJudgeCurrent, String timeStart, String timeEnd)
	{
  		Map<String, Object> retMap = new HashMap<String, Object>();
  		
		try {
			// 截取开始时间时分秒  
			int startHour = Integer.parseInt(timeStart.substring(0, 2));
			int startMinute = Integer.parseInt(timeStart.substring(3, 5));
			int startSecond = Integer.parseInt(timeStart.substring(6, 8));
			// 截取结束时间时分秒  
			int endHour = Integer.parseInt(timeEnd.substring(0, 2));
			int endMinute = Integer.parseInt(timeEnd.substring(3, 5));
			int endSecond = Integer.parseInt(timeEnd.substring(6, 8));
			
			//需要对当前时间日期处于昨天还是今天进行判断
			int inTime = 0;
			if(needJudgeCurrent)    
			{
				inTime = InTime(new Date(), timeStart, timeEnd, "day");
			}
			
			//当前时间
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			
			//如果当前时间是本月第一天，且处于昨天的统计周期内
			int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
			if((1 == inTime) && (1 == dayOfMonth))
				cal.add(Calendar.MONTH, -1);
			
			//获取当前时间所在月的第一天和最后一天日期
			Date monthFirstDayDate = getCurrentMonthFirstDay(cal.getTime());
			Date monthLastDayDate = getCurrentMonthLastDay(cal.getTime());
			
			//获取当前时间下一月的第一天和最后一天日期
			Date nextMonthFirstDayDate = getNextMonthFirstDay(cal.getTime());
			
			//获取当前时间前一月的第一天和最后一天日期
			Date lastMonthFirstDayDate = getLastMonthFirstDay(cal.getTime());
			Date lastMonthLastDayDate = getLastMonthLastDay(cal.getTime());
			
			String monthFirstDay = new SimpleDateFormat("yyyy-MM-dd").format(monthFirstDayDate);
			String monthLastDay = new SimpleDateFormat("yyyy-MM-dd").format(monthLastDayDate);
			String nextMonthFirstDay = new SimpleDateFormat("yyyy-MM-dd").format(nextMonthFirstDayDate);
			String lastMonthFirstDay = new SimpleDateFormat("yyyy-MM-dd").format(lastMonthFirstDayDate);
			String lastMonthLastDay = new SimpleDateFormat("yyyy-MM-dd").format(lastMonthLastDayDate);
			
			//计算本月和上月起始与结束时间
			String monthStart = "", monthEnd = "";
			String lastMonthStart = "", lastMonthEnd = "";
			if(startHour > endHour)   //统计周期开始和结束时间不在同一天
			{
				monthStart = monthFirstDay + " " + timeStart;
				monthEnd = nextMonthFirstDay + " " + timeEnd;
				
				lastMonthStart = lastMonthFirstDay + " " + timeStart;
				lastMonthEnd = monthFirstDay + " " + timeEnd;
			}
			else if(startHour == endHour)
			{
				if(startMinute > endMinute)   //统计周期开始和结束时间不在同一天
				{
					monthStart = monthFirstDay + " " + timeStart;
					monthEnd = nextMonthFirstDay + " " + timeEnd;
					
					lastMonthStart = lastMonthFirstDay + " " + timeStart;
					lastMonthEnd = monthFirstDay + " " + timeEnd;
				}
				else if(startMinute == endMinute)
				{
					if(startSecond >= endSecond)   //统计周期开始和结束时间不在同一天
					{
						monthStart = monthFirstDay + " " + timeStart;
						monthEnd = nextMonthFirstDay + " " + timeEnd;
						
						lastMonthStart = lastMonthFirstDay + " " + timeStart;
						lastMonthEnd = monthFirstDay + " " + timeEnd;
					}
					else   //统计周期开始和结束时间在同一天
					{
						monthStart = monthFirstDay + " " + timeStart;
						monthEnd = monthLastDay + " " + timeEnd;
						
						lastMonthStart = lastMonthFirstDay + " " + timeStart;
						lastMonthEnd = lastMonthLastDay + " " + timeEnd;
					}
				}
				else   //统计周期开始和结束时间在同一天
				{
					monthStart = monthFirstDay + " " + timeStart;
					monthEnd = monthLastDay + " " + timeEnd;
					
					lastMonthStart = lastMonthFirstDay + " " + timeStart;
					lastMonthEnd = lastMonthLastDay + " " + timeEnd;
				}
			}
			else     //统计周期开始和结束时间在同一天
			{
				monthStart = monthFirstDay + " " + timeStart;
				monthEnd = monthLastDay + " " + timeEnd;
				
				lastMonthStart = lastMonthFirstDay + " " + timeStart;
				lastMonthEnd = lastMonthLastDay + " " + timeEnd;
			}
			
			retMap.put("startMonth", monthStart);
			retMap.put("endMonth", monthEnd);
			retMap.put("startLastMonth", lastMonthStart);
			retMap.put("endLastMonth", lastMonthEnd);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return retMap;
	}
  	
  	//判断给定时间是否在指定时间范围内
  	public static int dateInSpecifyPeriod(Date specifyDate, String dateStart, String dateEnd) 
  	{
  		String specifyTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(specifyDate);
  		
  		int compare = compareDate(specifyTime, dateStart);
  		if(compare >= 0)
  		{
  			compare = compareDate(specifyTime, dateEnd);
  			if(compare <= 0)
  				return 0;
  		}
  		
  		return -1;
  	}
  	
	//比较两个时间的大小
	public static int compareDate(String date1, String date2) 
	{
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	        Date dt1 = df.parse(date1);
	        Date dt2 = df.parse(date2);
	        if (dt1.getTime() > dt2.getTime()) {
	            return 1;
	        } else if (dt1.getTime() < dt2.getTime()) {
	            return -1;
	        } else {
	            return 0;
	        }
	     } catch (Exception exception) {
	         exception.printStackTrace();
	    }
	    return 0;
	}
	/**
	 * 计算两个日期间相隔的天数
	 * @param d1  开始时间
	 * @param d2  结束时间
	 * @return 天数
	 */
	public static int getDaysbetween(Date d1,Date d2) {
		   Calendar calendar1 = new GregorianCalendar();
		   Calendar calendar2 = new GregorianCalendar();
		   calendar1.setTime(d1);
		   calendar2.setTime(d2);
		if (calendar1.after(calendar2)) {
			java.util.Calendar swap = calendar1;
			calendar1 = calendar2;
			calendar2 = swap;
		}
		int days = calendar2.get(java.util.Calendar.DAY_OF_YEAR)
				- calendar1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = calendar2.get(java.util.Calendar.YEAR);
		if (calendar1.get(java.util.Calendar.YEAR) != y2) {
			calendar1 = (java.util.Calendar) calendar1.clone();
			do {
				days += calendar1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
				calendar1.add(java.util.Calendar.YEAR, 1);
			} while (calendar1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
	}

	/**
	 * 计算两个日期间相隔的时间
	 * @param d1  开始时间
	 * @param d2  结束时间
	 * @return 天数
	 */
	public static int getTimesbetween(Date d1,Date d2) {
		long off = d2.getTime()-d1.getTime();
		return (int) (off/1000);
	}
	
}
