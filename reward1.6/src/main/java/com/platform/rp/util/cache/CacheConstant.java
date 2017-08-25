package com.platform.rp.util.cache;

public interface CacheConstant {
	public static String KEY_SMS = "SMS_AUTH_CODE_";						//短信验证码
	
	public static String SYS_CODE_LIST = "SYS_CODE_LIST_";					//数据字典_根据classid查询
	public static String SYS_CODE_MAP = "SYS_CODE_MAP_";					//数据字典_根据classid查询

	public static String SYS_CODE_LIST_PARENT = "SYS_CODE_LIST_PAR_";		//数据字典_根据父编码查询
	public static String SYS_CODE_MAP_PARENT = "SYS_CODE_MAP_PAR_";			//数据字典_根据父编码查询
	
	public static String SYS_AREA_LIST = "SYS_CODE_LIST_";					//数据字典_根据父编码查询
	public static String SYS_AREA_MAP = "SYS_CODE_MAP_";					//数据字典_根据父编码查询
	
	public static String KEY_QRCODE_ID = "QRCODE_ID_";                      //二维码扫码产生随机用户ID
	
	public static String KEY_QRCODE_EMPID = "QRCODE_EMPID_";                      //二维码扫码存放EMPID
}
