package com.wxboot.wx.framework.utils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class HttpUtils {
	
	/**
	 * httpclient POST请求
	 * @param url 请求地址
	 * @return
	 */
	public static String sendHttpsPost(String url){
		HttpResponse response = null;
		HttpPost request = new HttpPost(url);
		try {
			response = HttpClients.createDefault().execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				return result;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
	}
	
	/**
	 * httpclient GET请求
	 * @param url 请求地址
	 * @return
	 */
	public static String sendHttpsGet(String url){
		HttpResponse response = null;
		HttpGet request = new HttpGet(url);
		try {
			response = HttpClients.createDefault().execute(request);
			if(response.getStatusLine().getStatusCode()==200){
				String result = EntityUtils.toString(response.getEntity(), "UTF-8");
				return result;
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
	}
	/**发送http post请求，返回JSONObject对象*/
	public static JSONObject sendPost(String url){
		JSONObject fromObject = null;
		String result = sendHttpsPost(url);
		if (result != null) {
			fromObject = JSONObject.fromObject(result);
		}
		return fromObject;
	}
	
	/**发送http get请求，返回JSONObject对象*/
	public static JSONObject sendGet(String url){
		JSONObject fromObject = null;
		String result = sendHttpsGet(url);
		if (result != null) {
			fromObject = JSONObject.fromObject(result);
		}
		return fromObject;
	}
	
	public static JSONObject doPost(String url,String params) throws Exception{
		HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(params,"UTF-8"));
		HttpResponse response = HttpClients.createDefault().execute(post);
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}
	
	
}
