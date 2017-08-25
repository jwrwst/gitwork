package com.platform.rp.util;

import java.io.LineNumberReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.util.StringUtils;

import com.platform.rp.framework.mvcface.vo.formparser.FormParser;

/**
 * 
 * @author TangJun
 * 
 */
@SuppressWarnings("rawtypes")
public class ApplicationUtils {

	/**
	 * 保留小数位
	 * 
	 * @param val
	 * @param len
	 * @return
	 */
	public static String formatDouble(Double val, int... len) {
		BigDecimal dec = new BigDecimal(val.doubleValue());
		int size = 2;
		if (len.length != 0) {
			size = len[0];
		}
		return dec.setScale(size, RoundingMode.HALF_DOWN).toString();
	}

	public static String formatDate(Timestamp date) {
		Date newdate = new Date(date.getTime());
		return formatYMD(newdate, "yyyy.MM.dd");
	}

	public static String formatDate(Date date) {
		return formatYMD(date, "yyyy.MM.dd");
	}

	public static String formatDateByLine(Date date) {
		return formatYMD(date, "yyyy-MM-dd");
	}

	public static String formatYMD(Date date) {
		return formatYMD(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatYMDHmsSSS(Date date) {
		return formatYMD(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}

	public static String formatYMD(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat myFormatter = new SimpleDateFormat(pattern);
		return myFormatter.format(date);
	}

	public static long getSaveNullLong(String param) {
		long value = 0;
		if (param == null) {
			return 0;
		}
		try {
			value = Long.parseLong(param);
		} catch (Exception e) {
		}
		return value;
	}

	public static Long getSaveNullLongWrapper(String param) {
		Long value = null;
		if (param == null || param.trim().equals("")) {
			return null;
		}
		try {
			value = Long.parseLong(param);
		} catch (Exception e) {
		}

		return value;
	}

	public static String base64Encoder(String s) {
		if (s != null) {
			return new String(Base64.encodeBase64(s.getBytes()));
		} else {
			return "";
		}
	}

	public static String base64Decoder(String s) {
		if (s != null) {
			return new String(Base64.decodeBase64(s.getBytes()));
		} else {
			return "";
		}
	}

	public static Integer getSaveNullIntegerWrapper(String param) {
		Integer value = null;
		if (param == null || "".equals(param.trim())) {
			return null;
		}
		try {
			value = new Integer(param);
		} catch (Exception e) {
		}

		return value;
	}

	public static int getSaveNullInteger(String param) {
		int value = 0;
		if (param == null) {
			return 0;
		}
		try {
			value = Integer.parseInt(param);
		} catch (Exception e) {
		}

		return value;
	}

	public static String getSaveString(String param) {
		if (param == null) {
			return "";
		} else {
			try {
				return param;
			} catch (Exception e) {
			}
		}

		return "";
	}

	public static String getTrimedString(String param) {
		if (param == null) {
			return null;
		}
		return param.trim();
	}

	public static String ctrlToBr(String param) {
		if (param == null) {
			return "";
		} else {
			return param.replace("\r\n", "<br/>");
		}
	}

	public static String md5(String s) {
		byte[] defaultBytes = s.getBytes();
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
			for (byte aMessageDigest : messageDigest) {
				String hex = Integer.toHexString(0xFF & aMessageDigest);
				if (hex.length() == 1) {
					hexString.append('0');
				}

				hexString.append(hex);
			}
			// *** Testausgabe
			System.out.println("pass " + s + "   md5 version is "
					+ hexString.toString());
			s = hexString + "";
		} catch (NoSuchAlgorithmException ignored) {
		}
		return hexString.toString();
	}

	/**
	 * 判断空或null
	 * 
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if (value == null || value.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Integer value) {
		if (value == null || value == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object value) {
		if (value == null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 两个数字相加，如果其和溢出，即大于最大值或小于最小值时返回最大值或最小值 该方法仅对Long和Integer测试，其它类型不保证
	 * 
	 * @param a
	 *            加数
	 * @param b
	 *            被加数
	 * @param max
	 *            最大值
	 * @param min
	 *            最小值
	 * @return 两数之和，如果其和溢出，即大于最大值或小于最小值时返回最大值或最小值
	 */
	public static Number numberAdd(Number a, Number b, Number max, Number min) {
		BigInteger aa, bb, mx, mn;
		aa = new BigInteger(a.toString());
		bb = new BigInteger(b.toString());
		mx = new BigInteger(max.toString());
		mn = new BigInteger(min.toString());
		BigInteger sum = aa.add(bb);
		if (sum.compareTo(mx) >= 0) // 结果溢出
		{
			return max;
		}
		if (sum.compareTo(mn) <= 0) // 结果溢出
		{
			return min;
		}

		return sum;
	}

	public static Boolean getSaveBoolean(String param) {
		if (param == null || param.trim().equals("0")) {
			return false;
		} else {
			return true;
		}
	}

	public static String arrTOstring(String[] arrIn) {
		StringBuffer rtn = new StringBuffer();
		if (arrIn == null || arrIn.length < 1) {
			return "";
		}
		for (int i = 0; i < arrIn.length; i++) {
			if (i > 0) {
				rtn.append(",");
			}
			rtn.append(arrIn[i]);
		}
		return rtn.toString();
	}

	/**
	 * 将字符转化为日期
	 * 
	 * @param 字符时间
	 * @return 日期时间
	 */
	public static Date strToDate(String strDate) {
		Date date = new Date();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = sdf.parse(strDate);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return date;

	}

	/**
	 * 将字符转化为日期（格式为yyyy-MM-dd）
	 * 
	 * @param 字符时间
	 * @return 日期时间
	 */
	public static Date strToDate2(String strDate) {
		Date date = new Date();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(strDate);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return date;

	}
	
	public static Map<String, String> populate(Reader reader){
		try{
			LineNumberReader lineReader = new LineNumberReader(reader);
			StringBuffer buff = new StringBuffer();
			String line;
			while ((line = lineReader.readLine()) != null) {
				buff.append(line);
			}
			String[] str = buff.toString().split("&");
			if (str != null){
				Map<String, String> map = new HashMap<String, String>();
				for (String sp : str) {
					String[] arr = sp.split("=");
					map.put(arr[0],
							arr.length == 1 ? "" : URLDecoder.decode(arr[1],
									"utf-8"));
				}
				
				return map;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * @see 解析form参数
	 * @param reader
	 * @param formParser
	 * @throws Exception
	 */
	public static void parserFormReader(Reader reader, FormParser formParser)
			throws Exception {
		try {
			formParser.parseFormValue(populate(reader), formParser);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * @see 解析form参数
	 */
	public static <T> T parserFormReader(Reader reader, T bean)
			throws Exception {
		BeanUtils.populate(bean, populate(reader));
		
		return bean;
	}
	
	/**
	 * @see 解析form参数
	 */
	public static Object parserFormReader(Reader reader, Class<?> clazz)
			throws Exception {
		Object target = clazz.newInstance();
		BeanUtils.populate(target, populate(reader));
		
		return target;
	}

	/**
	 * 解析参数对象
	 * 
	 * @param map
	 *            源数据封装
	 * @param obj
	 *            目标对象
	 */
	public static void parseFormValue(Map<String, String> map, Object obj) {
		BeanMap beanMap = BeanMap.create(obj);
		for (Entry<String, String> entry : map.entrySet()) {
			if (StringUtils.isEmpty(entry.getKey()))
				continue;

			Type genericType = null;
			if (entry.getKey().contains(".")) {
				String[] keys = entry.getKey().split("\\.");
				if (!beanMap.containsKey(keys[0]))
					continue;

				genericType = beanMap.getPropertyType(keys[0]);

				if (!ArrayUtils.contains(
						(Class[]) ((Class) genericType).getInterfaces(),
						FormParser.class))
					continue;

				try {
					Object childEntry = ((Class) genericType).newInstance();
					BeanMap childEntryBeanMap = BeanMap.create(childEntry);
					analysisEntityProperty(childEntryBeanMap, keys[1],
							entry.getValue(),
							childEntryBeanMap.getPropertyType(keys[1]));

					beanMap.put(keys[0], childEntry);
				} catch (Exception e) {
					throw new IllegalArgumentException(e);
				}
				continue;
			}

			if (!beanMap.containsKey(entry.getKey()))
				continue;
			genericType = beanMap.getPropertyType(entry.getKey());
			if (genericType instanceof ParameterizedType)
				continue;

			Class[] classArray = (Class[]) Array.newInstance(Class.class, 10);
			int index = 0;
			Class genericTypeTemp;
			for (genericTypeTemp = (Class) genericType;; genericTypeTemp = genericTypeTemp
					.getSuperclass()) {
				Array.set(classArray, index++, genericTypeTemp);
				if (genericTypeTemp.getSuperclass() == null)
					break;
			}
			if (ArrayUtils.contains(classArray, null)) {
				Integer newLength = ArrayUtils.indexOf(classArray, null);
				Class[] classArrayTemp = (Class[]) Array.newInstance(
						Class.class, newLength);
				System.arraycopy(classArray, 0, classArrayTemp, 0,
						Math.min(classArray.length, newLength));
				classArray = classArrayTemp;
			}

			if (ArrayUtils.contains(classArray, Date.class)) {
				beanMap.put(entry.getKey(),
						ApplicationUtils.strToDate(entry.getValue()));
			} else {
				analysisEntityProperty(beanMap, entry.getKey(),
						entry.getValue(), (Class) genericType);
			}
		}
	}

	private static void analysisEntityProperty(BeanMap beanMap, String key,
			String value, Class genericType) {
		if (StringUtils.isEmpty(value))
			return;

		try {
			if (String.class.equals((Class) genericType)) {
				beanMap.put(key, value);
			} else if (Integer.class.equals((Class) genericType)
					|| int.class.equals((Class) genericType)) {
				beanMap.put(key, Integer.parseInt(value));
			} else if (Long.class.equals((Class) genericType)
					|| long.class.equals((Class) genericType)) {
				beanMap.put(key, Long.parseLong(value));
			} else if (Float.class.equals((Class) genericType)
					|| float.class.equals((Class) genericType)) {
				beanMap.put(key, Float.parseFloat(value));
			} else if (Double.class.equals((Class) genericType)
					|| double.class.equals((Class) genericType)) {
				beanMap.put(key, Double.parseDouble(value));
			}
		} catch (java.lang.Exception e) {
			throw new ClassCastException(key);
		}
	}
	
}
