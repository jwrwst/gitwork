package com.platform.rp.util;



import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SHA1MessageSigner {

	private static final String MAC_NAME = "HmacSHA1";  
    private static final String ENCODING = "UTF-8";
    
    
	public static String sign(String signKey, TreeMap<String, String> params) {
		if (signKey == null || "".equals(signKey)) {
			throw new IllegalArgumentException("参数signkey不能为空");
		}
		if (params == null) {
			throw new IllegalArgumentException("参数params不能为空");
		}
		return sign(signKey, mapToUrlParam(params));
	}

	public static String sign(String signKey, String content) {
		if (signKey == null || "".equals(signKey)) {
			throw new IllegalArgumentException("参数signkey不能为空");
		}
		if (content == null || "".equals(content)) {
			throw new IllegalArgumentException("参数content不能为空");
		}
		try {
			byte[] signContent = sign(signKey.getBytes(ENCODING),
					content.getBytes(ENCODING));
			return bytesToHexString(signContent).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncodingException"
					+ ENCODING);
		}
	}    
    
	public static String bytesToHexString(byte[] src) {

		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	
	public static byte[] sign(byte[] key, byte[] content) {
		// 根据给定的字节数组构造一个密�?第二参数指定�?��密钥算法的名�?
		try {
			SecretKey secretKey = new SecretKeySpec(key, MAC_NAME);
			// 生成�?��指定 Mac 算法 �?Mac 对象
			Mac mac = Mac.getInstance(MAC_NAME);
			// 用给定密钥初始化 Mac 对象
			    mac.init(secretKey);
			return mac.doFinal(content);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String mapToUrlParam(TreeMap<String, String> params) {
		Set<String> set = params.keySet();
		StringBuffer urlParam = new StringBuffer();
		for (String key : set) {
			String value = params.get(key);
			if (value == null || "".equals(value)) {
				continue;
			}
			urlParam.append("&").append(key).append("=").append(value);
		}
		return urlParam.toString().substring(1);
	}
}
