package com.thinkgem.jeesite.modules.weixin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则验证
 * @author WHW
 *
 */
public class ValidationUtil {
	
	
	/** 数字  */
	private final static String REGULAR_NUMBER = "^-?[0-9]*$";
	
	/** 手机号码正则  */
	private final static String REGULAR_PHONE = "^[1][3-8]\\d{9}$";
	
	/** 浮点数 */
	private final static String REGULAR_FLOAT= "^(-?\\d+)(\\.\\d+)?$";
	
	/** 汉字 */
	private final static String REGULAR_STR_CN = "^[\\u4e00-\\u9fa5]{0,}$";
	
	/** 英文 */
	private final static String REGULAR_STR_EN = "^[A-Za-z]+$";
	
	/** 由数字、26个英文字母或者下划线组成的字符串 */
	private final static String REGULAR_STR_A = "^\\w+$";
		
	/** 中文、英文、数字包括下划线 */
	private final static String REGULAR_STR_B = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$";
	
	/** email */
	private final static String REGULAR_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>暴露的get方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	
	/** 手机号码 */
	public static String getPhonePeg(){
		return REGULAR_PHONE;
	}
	
	/** 整数(包含0开头,不包含小数) */
	public static String getNumberPeg(){
		return REGULAR_NUMBER;
	}
	
	/** N位的整数(包含0开头,不包含小数) */
	public static String getNumberPeg(int length){
		return "^-?\\d{"+length+"}$";
	}

	/** m-n位的整数(包含0开头,不包含小数) */
	public static String getNumberPeg(int sta,int end){
		return "^-?\\d{"+sta+","+end+"}$";
	}
	
	/** 浮点数 */
	public static String getFloatPeg(){
		return REGULAR_FLOAT;
	}
	
	/** 汉字 */
	public static String getCNPeg(){
		return REGULAR_STR_CN;
	}

	/** 英文 */
	public static String getENPeg(){
		return REGULAR_STR_EN;
	}

	/** 由数字、26个英文字母或者下划线组成的字符串  */
	public static String getStrPegA(){
		return REGULAR_STR_A;
	}

	/** 中文、英文、数字包括下划线  */
	public static String getStrPegB(){
		return REGULAR_STR_B;
	}
	
	/** email */
	public static String getEmailPeg(){
		return REGULAR_EMAIL;
	}
	
	
	public static boolean validation(String regular , String parameter){
		try {
			Pattern pattern = Pattern.compile(regular);
			Matcher matcher = pattern.matcher(parameter);
			return matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
