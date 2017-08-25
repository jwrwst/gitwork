package com.wxboot.wx.framework.utils;

import java.security.MessageDigest;
import java.util.Arrays;

import com.wxboot.wx.framework.config.Setting;

/**
 * 家家帮
 * @author wang
 * 2017年4月2日 上午11:43:37
 * 类描述：
 */
public class SignUtils {
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		String arr [] = new String[]{Setting.token, timestamp, nonce};
		//排序
		Arrays.sort(arr);
		//sha1加密
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		String temp = getSha1(content.toString());
		return temp.equals(signature);
	}
	
	
	//
	//下面四个import放在类名前面 包名后面
	//import java.io.UnsupportedEncodingException;
	//import java.security.MessageDigest;
	//import java.security.NoSuchAlgorithmException;
	//import java.util.Arrays;
	public static String getSha1(String str){
	    if (null == str || 0 == str.length()){
	        return null;
	    }
	    char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
	            'a', 'b', 'c', 'd', 'e', 'f'};
	    try {
	        MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
	        mdTemp.update(str.getBytes("UTF-8"));
	         
	        byte[] md = mdTemp.digest();
	        int j = md.length;
	        char[] buf = new char[j * 2];
	        int k = 0;
	        for (int i = 0; i < j; i++) {
	            byte byte0 = md[i];
	            buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
	            buf[k++] = hexDigits[byte0 & 0xf];
	        }
	        return new String(buf);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}

 