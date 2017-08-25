package com.platform.rp.util;



import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class Tools {
	
	private  static String dateFormat="yyyyMMddHHmmssSSS";
	
	/**
	 * 随机生成固定长度字串
	 * @param  length 随机数长�?
	 * @return 固定长度字串
	 * */
	public static String stringGen(int length) {
		char[] number = ("1234567890").toCharArray();
		Random random = new Random();
		char[] genNumber = new char[length];
		for (int i = 0; i < length; i++) {
			genNumber[i] = number[random.nextInt(10)];
		}
		number = null;
		return new String(genNumber);
	}
	public static String stringGen2(int length) {
		char[] number = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		Random random = new Random();
		char[] genNumber = new char[length];
		for (int i = 0; i < length; i++) {
			genNumber[i] = number[random.nextInt(52)];
		}
		number = null;
		return new String(genNumber);
	}
	public static String getCurrentDateToString(String dformat, Date date) {
		if (dformat!= "") {
			dateFormat = dformat;
		}
		if (date == null) {
			date = new Date();
		}
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		String dateStr = format.format(date);
		return dateStr;
	}
	
	
	/**
	 * 生成订单ID
	 * �?WX 微信 前缀+时间�?yyyyMMddHHmmssSSS) + 位随机数�?
	 * @param reqFrom name of {@link ReqFrom}
	 * @return
	 */
	public static String generateOrderID(String reqFrom){
		return reqFrom + getCurrentDateToString(dateFormat,null)+stringGen(5);
	}
	

	/***
	 * �?��字符串不为空 true不为�?false 为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean notEmpty(String str) {
		return str != null && !"".equals(str) && !"null".equals(str);
	}

	/***
	 * �?��字符串为�?true 代表是空 false 代表不为�?
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str) || "null".equals(str);
	}

	/***
	 * �?��对象是否是空 集合 对象 Map 对象数组 判断对象或对象数组中每一个对象是否为�?
	 * 对象为null，字符序列长度为0，集合类、Map为empty true代表是空 false代表不为�?
	 * 
	 * @param obj
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static boolean notEntity(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof Collection) {
			return ((Collection) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		}
		if (obj instanceof Object[]) {
			Object objs[] = (Object[]) obj;
			if (objs.length == 0) {
				return false;
			}
			boolean empty = true;
			for (int i = 0; i < objs.length; i++) {
				if (notEntity(objs[i])) {
					empty = false;
					break;
				}
				return empty;
			}
		}
		return false;
	}
	/***
	 * 验证时间格式
	 * @param str
	 * @return
	 */
	public static  boolean isDate(String str) {  
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
        try{  
            Date date = (Date)formatter.parse(str);  
            return str.equals(formatter.format(date));  
        }catch(Exception e){ 
        	return false;  
        }
    }  

	public static String initAccountID(int cardType){
		/*String prefix = "";
		switch (cardType) {
			case 1:
				prefix = "P";
				break;
			case 2:
				prefix = "M";
				break;
			case 3:
				prefix = "G";
				break;
			default:
				prefix = "N";
				break;
		}*/
		return getCurrentDateToString(dateFormat,null)+stringGen(6);	
	}
	/** 
	 * 流水�?
	 * */
	public static String initTranantSn(){
		return getCurrentDateToString(dateFormat,null)+stringGen(6);	
	}

	
	
	public static String parseStreamToString(InputStream in) {
		StringBuffer str = new StringBuffer();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					       new BufferedInputStream(in), "UTF-8"));
			String  line = null;
			while ((line = reader.readLine()) != null) {
				   str.append(line);
			}
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		return str.toString().trim();
	}

	public static String parseAgeBracket(Date birthday){
		int[] range = {20, 25, 30, 35, 40, 45, 50, 55, 60, 65};
		int bracket = (int) ((new Date().getTime() - birthday.getTime())/(24*60*60*1000)/365);
		
		for(int i=0, l=range.length; i<l; i++){
			if(bracket < range[i]){
				if(i == 0)
					return "20以下";
				else
					return range[i-1] +"~"+ range[i];
			}
		}
		
		return "65以上";
	}
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}
	
	public static void main(String[] args) {
		//System.out.println(Tools.parseAgeBracket(DateUtil.parseSimple("1998-06-06")));
		System.out.println(getUUID());
		System.out.println(TinyUrlGenerater.generatorByUUID());
		//System.out.println(System.currentTimeMillis());
	}
}
