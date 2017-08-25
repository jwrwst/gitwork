package com.platform.rp.util;

public class Constant {
	//已生产元素
	public static final int USERG = 3;
	//已用
	public static final int USER = 2;	
	//未使用
	public static final int NOTUSER = 1;
	
	//默认权限
	public static final String DEFAUTHCODE="1002";
	
	//店长
	public static final int EMPLEVEL1 = 1;
	//店员
	public static final int EMPLEVEL2 = 2;
	//店长和店员
	public static final int EMPLEVEL3 = 3;
	
	//有效
	public static final int ABLE=1;
	//无效
	public static final int UNABLE=2;
	//删除
	public static final int DELETE  = 3;
	
	///支付状态
	//下订单状态
	public static final int PAYORDER =1;
	//支付完成
	public static final int PAYING=2;
	
	//审核状态
	//提交审核
	public static final int AUDITING=1;
	//审核通过
	public static final int AUDIED=2;
	//审核未通过
	public static final int UNAUDIT  = 3;
	//审核未通过
	public static final int AUDITDELETE  = 4;

	//测试信息
	public static boolean IS_DEBUG = false;
	public static String DUF_OPENID = "o3TzSv4jkZduiWYTOME_mUujr1Ys";
	//组长的
	//public static String DUF_OPENID = "o3TzSv8m3gBiVEw3jKHuJuyH7Bck";
	public static long COUNT = 100000000;
}
