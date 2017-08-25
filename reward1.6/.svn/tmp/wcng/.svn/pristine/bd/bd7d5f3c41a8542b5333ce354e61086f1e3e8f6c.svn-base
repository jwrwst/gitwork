package com.platform.rp.services.wechat.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.sf.json.JSONObject;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.platform.rp.services.wechat.external.vo.WxUserInfo;

public class StringMagusUtils {
	/**
	 * 生成指定位数的随机字符串
	 * 
	 * @param length
	 * @return
	 * 
	 * @author xiayang
	 */
	public static String stringGen(int length) {
		Random randGen = new Random();
		char[] numbersAndLetters = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
				.toCharArray();
		if (length < 1) {
			return null;
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(51)];
		}
		return new String(randBuffer);
	}

	/**
	 * 
	 * @Title : filter
	 * @Type : FilterStr
	 * @date : 2014年3月12日 下午9:17:22
	 * @Description : 过滤出字母、数字和中文
	 * @param character
	 * @return
	 */
	public static String filter(String character) {
		character = character.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
		return character;
	}

	/**
	 * dom转成xml
	 * 
	 * @param doc
	 * @return
	 * @throws TransformerException
	 * @throws IOException
	 */
	public static String DOM2XML(Document doc) throws TransformerException,
			IOException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.setOutputProperty("encoding", "utf-8");// 解决中文问题，试过用GBK不行
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		t.transform(new DOMSource(doc), new StreamResult(bos));
		String responseXml = bos.toString();
		bos.close();
		return responseXml;
	}

	/**
	 * 判断响应的消息内容代码 是否是access_token导致
	 * 
	 * @param gsonMap
	 * @return
	 */
	public static boolean judgeErrcode(Map<String, String> gsonMap) {
		String errorcode = ",40001,40014,41001,42001,";
		if (gsonMap.get("errcode") != null) {
			String errString = gsonMap.get("errcode");
			if (errorcode.indexOf("," + errString + ",") >= 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * 判断响应的消息内容代码 是否是access_token导致
	 * 
	 * @param JSONObject json
	 * @return
	 */
	public static boolean judgeErrcodeFormJSONObject(JSONObject json) {
		String errorcode = ",40001,40014,41001,42001,";
		if (json.has("errcode")) {
			String errString = json.getString("errcode");
			if (errorcode.indexOf("," + errString + ",") >= 0) {
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}

	/**
	 * 判断响应的消息内容代码 是否是成功
	 * 
	 * @param gsonMap
	 * @return
	 */
	public static boolean judgeSuccessCode(Map<String, String> gsonMap) {
		String successcode = ",0,";
		if (gsonMap.get("errcode") != null) {
			String errString = gsonMap.get("errcode");
			if (successcode.indexOf("," + errString + ",") >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public static boolean judgeSuccessCode(WxUserInfo wxInfo) {
		if (wxInfo == null) {
			return false;
		}
		if (wxInfo.getErrcode() != null) {
			if ("0".equals(wxInfo.getErrcode())) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	/**
	 * 把json字符串转为Map<String,String>的格式
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> gsonstrToMap(String jsonStr) {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping()
				.create();
		return (Map<String, String>) gson.fromJson(jsonStr,
				new TypeToken<Map<String, String>>() {
				}.getType());
	}

	public static WxUserInfo gsonStrToWxUserInfo(String jsonStr) {
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(jsonStr, WxUserInfo.class);
	}

	public static String objToGsonstr(Object obj) {
		Gson gson = new GsonBuilder()
				.setPrettyPrinting()
				.disableHtmlEscaping()
				.registerTypeAdapter(Timestamp.class,
						new TimestampTypeAdapter())
				.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(obj, obj.getClass());
	}

	@SuppressWarnings("unchecked")
	public static Map<String, String> jsonElemenToMap(JsonObject json) {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping()
				.create();
		return (Map<String, String>) gson.fromJson(json,
				new TypeToken<Map<String, String>>() {
				}.getType());
	}

	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, String> jsonElementToLinkedHashMap(
			JsonObject json) {
		Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping()
				.create();
		return (LinkedHashMap<String, String>) gson.fromJson(json,
				new TypeToken<LinkedHashMap<String, String>>() {
				}.getType());
	}

	/**
	 * 将一个输入流转化为字符串
	 */
	public static String getStreamString(InputStream tInputStream) {
		if (tInputStream != null) {
			try {
				BufferedReader tBufferedReader = new BufferedReader(
						new InputStreamReader(tInputStream));
				StringBuffer tStringBuffer = new StringBuffer();
				String sTempOneLine = new String("");
				while ((sTempOneLine = tBufferedReader.readLine()) != null) {
					tStringBuffer.append(sTempOneLine);
				}
				tInputStream.close();
				return tStringBuffer.toString();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 将日期型转换成字符串 参数：time，Date pattern, String, 转换的目标格式<一句话功能简述> <功能详细描述>
	 * 
	 * @param time
	 *            time
	 * @param pattern
	 *            pattern
	 * @return String
	 * @see [类、类#方法、类#成员]
	 */
	public static String date2TimeStr(Date time, String pattern) {
		if (null == pattern) {
			throw new IllegalArgumentException(
					"pattern parameter can not be null");
		}
		if (null == time) {
			throw new IllegalArgumentException("time parameter can not be null");
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(time);
	}

	/**
	 * 对有序map获得字符串后 key1=value1&key2=value2... 做sha1签名
	 * 
	 * @param paramMap
	 *            有序map
	 * @return
	 */
	public static String shaHex(Map<String, String> paramMap) {
		String signStr = "";
		Set<String> sets = paramMap.keySet();
		for (String key : sets) {
			if (StringUtils.isNotBlank(signStr)) {
				signStr += "&";
			}
			signStr += key + "=" + paramMap.get(key);
		}
		System.out.println(signStr);
		String signedStr = DigestUtils.shaHex(signStr);
		return signedStr;
	}
}
