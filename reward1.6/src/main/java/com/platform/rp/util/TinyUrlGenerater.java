package com.platform.rp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <pre>
 * FileName : com.magus.couponcode.utils.ShortURLGenerater.java
 * 
 * 
 * Creator：
 * 
 * Created Date： Aug 3, 2015 12:47:00 PM
 * 
 * Updator：
 * 
 * Updated Date：
 * 
 * Decription：
 * 
 * Version： 1.0.1
 * </pre>
 */
public class TinyUrlGenerater {

	// 要使用生成 URL 的字符
	private final static String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
			"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z"

	};

	// 自定义生成 MD5 加密字符传前的混合 KEY
	private final static String MIXTURE_KEY = "platform";



	
	/**
	 * <pre>
	 * 短编码生成器
	 * 
	 * @param url
	 * @return String[] 返回4个不同的加密字符码。可以取其一作为短地址
	 * </pre>
	 */
	public static String[] generatorByUUID(String privateKey,int num) {
		String[] facebook = new String[num];
		//每组只能生成4个
		double klen=Math.ceil(num/4d);
		for(int k=0;k<klen;k++){
			String uuid=UUID.randomUUID().toString();
			// 进行 MD5 加密
			String hex = md5ByHex(MIXTURE_KEY + privateKey + uuid);	
			int len=(k==(klen-1) )?(num%4>0?num%4:4) : 4 ;
			for (int i = 0; i < len; i++) {
				// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
				String substr = hex.substring(i * 8, i * 8 + 8);
	
				// 这里需要使用 long 型来转换，因为 Inteper.parseInt() 只能处理 31 位 , 首位为符号位 ,
				// 如果不用long ，则会越界
				long hexLong = 0x3FFFFFFF & Long.parseLong(substr, 16);
				StringBuffer out = new StringBuffer();
				for (int j = 0; j < 6; j++) {
					// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
					long index = 0x0000003D & hexLong;
					// 把取得的字符相加
					out.append(chars[(int) index]);
					// 每次循环按位右移 5 位
					hexLong = hexLong >> 5;
				}
				// 把字符串存入对应索引的输出数组
				facebook[i+(k*4)] = out.toString();
			}
		}
		return facebook;
	}
	
	/**
	 * 生成单个唯一标识
	 * @return
	 */
	public static String generatorByUUID() {
		return generatorByUUID("",1)[0];
	}

	/**
	 * MD5加密(32位大写)
	 * 
	 * @param src
	 * @return
	 */
	private static String md5ByHex(String src) {
		try {
			// MD5转换器
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = src.getBytes();
			md.reset();
			md.update(bytes);
			byte[] hash = md.digest();
			StringBuffer hs = new StringBuffer();
			String stmp = null;
			for (int i = 0; i < hash.length; i++) {
				// 按16进制位移
				stmp = Integer.toHexString(hash[i] & 0xFF);
				if (stmp.length() == 1)
					hs.append("0").append(stmp);
				else
					hs.append(stmp);
			}

			return hs.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}



	public static void main(String[] args) throws NoSuchAlgorithmException {
	/*	String[] uuid = generatorByUUID("100100",1);// 将产生4组6位字符串
		System.out.println(uuid.length);
		Map<String,String> map =new HashMap<String,String>();
		for (int i = 0; i < uuid.length; i++) {
			if(map.containsKey(uuid[i])){
				System.out.println(uuid[i]);
				continue;
			}
			map.put(uuid[i], "1");
		}*/
		String[] u = generatorByUUID("http://www.haihaitech.com/waplogin.xhtml?state=3&data=qIbuY3",6);
		System.out.println(u[0]);
	}
}
