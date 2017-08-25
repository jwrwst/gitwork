package com.platform.rp.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
	public static int YYYYYMMDD = 0;
	public static int YYYY_MM_DD = 1;
	public static int YYYY_MM_DD_HH_MM_SS = 2;
	
	private static DateFormat COMMON_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat COMMON_FORMAT_DA = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat COMMON_FORMAT_ALL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 日期格式化工厂
	 * DateUtils.getFormat(DateUtils.YYYY_MM_DD);
	 * DateUtils.getFormat(DateUtils.YYYYYMMDD);
	 * DateUtils.getFormat(DateUtils.YYYY_MM_DD_HH_MM_SS);
	 * @param type
	 * @return
	 */
	public static DateFormat getFormat(int type){
		switch (type) {
		case 0:
			return COMMON_FORMAT_DA;
		case 1:
			return COMMON_FORMAT;
		default:
			return COMMON_FORMAT_ALL;
		}
	}
	
	/**
	 * 获取偏移的日期，提供的日期字符串跟返回相同类型
	 * @param Date	日期
	 * @param off	偏移的日期
	 * @param type	格式化类型
	 * @return
	 * @throws ParseException 
	 */
	public static String getOffDate(String sDate,int off,int type) throws ParseException{
		Date date = getFormat(type).parse(sDate);
		return getOffDate(date, off, type);
	}
	/**
	 * 获取偏移的日期
	 * @param Date	日期
	 * @param off	偏移的日期
	 * @param type	格式化类型
	 * @return
	 */
	public static String getOffDate(Date date,int off,int type){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, off);
		date = calendar.getTime();
		return getFormat(type).format(date);
	}
	/**
	 * 计算距离今天相差的时间
	 * @param off	相差几天
	 * @param type	类型
	 */
	public static String getOffToday(int off,int type){
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, off);
		date = calendar.getTime();
		return getFormat(type).format(date);
	}
	public static String getOffToday(int off){
		return getOffToday(off, 1);
	}
	/**
	 * 昨天
	 * 
	 * @return
	 */
	public static String getPerDate(int type) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -1);
		date = calendar.getTime();
		return getFormat(type).format(date);
	}

	/**
	 * 今天
	 *
	 * @return
	 */
	public static String getToday(int type) {
		return getFormat(type).format(new Date());
	}

	/**
	 * 明天
	 *
	 * @return
	 */
	public static String getTomorrow(int type) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		date = calendar.getTime();
		return getFormat(type).format(date);
	}
	
	/**
	 * 以当前月为准，获取第一天的日期
	 * 
	 * @param step
	 *            0 本月，1 下月， -1 上月
	 * @return
	 */
	public static String getMonthFirstDay(int step,int type) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, step);
		calendar.set(Calendar.DATE, 1);
		date = calendar.getTime();
		return getFormat(type).format(date);
	}
	/**
	 * 以当前月为准，最后一天的日期
	 * 
	 * @param step
	 *            0 本月，1 下月， -1 上月
	 * @return
	 */
	public static String getMonthLastDay(int step,int type) {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, step);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		date = calendar.getTime();
		return getFormat(type).format(date);
	}
	/**
	 * 以当前月为准，获得周一的日期
	 * 
	 * @param step
	 *            0 本周，1 下周， -1 上周
	 * @return yyyy-MM-dd
	 */
	public static String getMondayOfWeek(int step,int type) {
		Calendar cal = Calendar.getInstance();
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		// System.out.println(day_of_week);
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		int diff = -day_of_week + 1;
		cal.add(Calendar.DATE, step * 7 + diff);
		return getFormat(type).format(cal.getTime());
	}
	/**
	 * 以当前月为准，获得周一的日期
	 * 用于sql查询所以加一天
	 * @param step
	 *            0 本周，1 下周， -1 上周
	 * @return yyyy-MM-dd
	 */
	public static String getSundayOfWeek(int step,int type) {
		Calendar cal = Calendar.getInstance();
		int day_of_week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week == 0) {
			day_of_week = 7;
		}
		int diff = -day_of_week + 7;
		cal.add(Calendar.DATE, step * 7 + diff);
		//加一天
		cal.roll(Calendar.DATE, 1);
		return getFormat(type).format(cal.getTime());
	}

	public static void main(String[] args) throws ParseException {
		// System.out.println(getMonthFirstDay(0));
		// System.out.println(getMonthLastDay(0));
		//System.out.println(getMondayOfWeek(1));
		//System.out.println(getSundayOfWeek(1));
		System.out.println(getOffDate("20160430", 1, YYYYYMMDD));
	}
}
