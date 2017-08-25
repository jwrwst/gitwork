package com.platform.rp.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;










import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

public class PrepaidcardTest {
	//String host = "http://localhost";
	static	String host = "http://172.16.254.195";
	
	public static void main(String[] args) {
	//public @Test void  queryBuyList() {
		String reqSn =Tools.initTranantSn();
		String json="{\"reqSsn\":\""+reqSn+"\",\"reqFrom\":\"WECHAT_MP\",\"customerKey\":\"1002239203076126\"}";
		try {
				String digest = Signature.hmacSHA1Encrypt(json, "C58D336792015B71A8468E9B01175F3B62044483CF4ACF1193F303B1F4282AF56A89D058F9DC69233FD6E185334CBD9586258834E76DD34DB3A1E00E356733EB");
				System.out.println(digest);
				String url = "http://172.16.254.195/prepaidCard/queryBuyList?reqSsn="+reqSn+"&digest="+digest+"&reqFrom=WECHAT_MP";
				System.out.println(url);
	    		HttpClient client = HttpClientBuilder.create().build();
	    		HttpPost post = new HttpPost(url);
	        	post.setHeader("Content-type", "application/json");
	        	JSONObject jo = JSONObject.fromObject(json);
	        	System.out.println(jo.toString());
	        	StringEntity params =new StringEntity(jo.toString());
	        	post.setEntity(params);
	        	HttpResponse response = client.execute(post);
	        	sprint(response.getEntity());
		} catch (Exception e) {
		   e.printStackTrace();
	  }
	}
    public static void sprint(HttpEntity entity) throws Exception, IOException {
    	BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    	result.append(line);
    	}
    	System.out.println("result:"+result);
	}
	@Test
	public  void  buynotify() {
		String reqSn =Tools.initTranantSn();
		String json="{\"reqSsn\":\""+reqSn+"\",\"reqFrom\":\"WECHAT_MP\",\"customerKey\":\"1002239203076126\",\"mobile\":\"15801284070\",\"cards\":[{\"cardNo\":\"M20150609193050083\",\"count\":\"1\"}],\"remark\":\"\"}";
		try {
				String digest = Signature.hmacSHA1Encrypt(json, "C58D336792015B71A8468E9B01175F3B62044483CF4ACF1193F303B1F4282AF56A89D058F9DC69233FD6E185334CBD9586258834E76DD34DB3A1E00E356733EB");
				String url =host+"/prepaidCard/buy?reqSsn="+reqSn+"&digest="+digest+"&reqFrom=WECHAT_MP";
	    		HttpClient client = HttpClientBuilder.create().build();
	    		HttpPost post = new HttpPost(url);
	        	post.setHeader("Content-type", "application/json");
	        	JSONObject jo = JSONObject.fromObject(json);
	        	System.out.println(jo.toString());
	        	StringEntity params =new StringEntity(jo.toString());
	        	post.setEntity(params);
	        	HttpResponse response = client.execute(post);
	        	sprint(response.getEntity());
		
		} catch (Exception e) {
		   e.printStackTrace();
	  }
	}

	
	@Test
	public  void  bugList() {
		String reqSn =Tools.initTranantSn();
		String json="{\"reqSsn\":\""+reqSn+"\",\"reqFrom\":\"WECHAT_MP\",\"customerKey\":\"1002239203076126\"}";
		try {
				String digest = Signature.hmacSHA1Encrypt(json, "C58D336792015B71A8468E9B01175F3B62044483CF4ACF1193F303B1F4282AF56A89D058F9DC69233FD6E185334CBD9586258834E76DD34DB3A1E00E356733EB");
				String url = host + "/prepaidCard/queryBuyList?reqSsn="+reqSn+"&digest="+digest+"&reqFrom=WECHAT_MP";
	    		HttpClient client = HttpClientBuilder.create().build();
	    		HttpPost post = new HttpPost(url);
	        	post.setHeader("Content-type", "application/json");
	        	JSONObject jo = JSONObject.fromObject(json);
	        	System.out.println(jo.toString());
	        	StringEntity params =new StringEntity(jo.toString());
	        	post.setEntity(params);
	        	HttpResponse response = client.execute(post);
	        	sprint(response.getEntity());
		} catch (Exception e) {
		   e.printStackTrace();
	  }
	}
	
	public @Test void  pay() {
	//public static void main(String[] args) {
		String reqSn =Tools.initTranantSn();
		String customerKey = "1002239203076125"; //tan
		String orderId = "2314l14j13048asdf8a0f";//dummy order id
		String identifiCode = "134270";
		String payPwd = "111111";
		String orderAmount = "290.54";
		//String host = "http://localhost:8080";
//		String host = "http://172.16.254.195";
		String json="{\"reqSsn\":\""+reqSn+"\",\"reqFrom\":\"WECHAT_MP\",\"customerKey\":\""+customerKey+"\",\"orderAmount\":\""+orderAmount+"\",\"orderId\":\""+orderId+"\",\"payPwd\":\""+payPwd+"\",\"identifiCode\":\""+identifiCode+"\",\"cards\":[]}";
		try {
			String digest = Signature.hmacSHA1Encrypt(json, "C58D336792015B71A8468E9B01175F3B62044483CF4ACF1193F303B1F4282AF56A89D058F9DC69233FD6E185334CBD9586258834E76DD34DB3A1E00E356733EB");
			String url = host + "/prepaidCard/pay?reqSsn="+reqSn+"&digest="+digest+"&reqFrom=WECHAT_MP";
	    		HttpClient client = HttpClientBuilder.create().build();
	    		HttpPost post = new HttpPost(url);
	        	post.setHeader("Content-type", "application/json");
	        	JSONObject jo = JSONObject.fromObject(json);
	        	System.out.println(jo.toString());
	        	StringEntity params =new StringEntity(jo.toString());
	        	post.setEntity(params);
	        	HttpResponse response = client.execute(post);
	        	sprint(response.getEntity());
		} catch (Exception e) {
		   e.printStackTrace();
	  }
	}
	
	
	@Test
	public  void transfer() {
       // {"customerKey":"1002239203076123","mobile":"11","recipientMobile":"1580128","identifyCode":"111","payPwd":"111","cards":["20150608100526248050900","20150608100526248169233"]}
		String customerKey = "1002239203076125";
		String reqSn =Tools.initTranantSn();
		String recipientMobile = "15210141773";
		String identifyCode = "128252";
		String payPwd = "111111";
//		String account2 = "20150610190045908852597";
		String account1 = "20150610210622369064520";

		String json="{\"reqSsn\":\""+reqSn+"\",\"reqFrom\":\"WECHAT_MP\",\"customerKey\":\""+customerKey+"\",\"recipientMobile\":\""+recipientMobile+"\",\"identifyCode\":\""+identifyCode+"\",\"payPwd\":\""+payPwd+"\",\"cards\":[\""+account1+"\"]}";
		try {
				String digest = Signature.hmacSHA1Encrypt(json, "C58D336792015B71A8468E9B01175F3B62044483CF4ACF1193F303B1F4282AF56A89D058F9DC69233FD6E185334CBD9586258834E76DD34DB3A1E00E356733EB");
				String url = host + "/prepaidCard/transferCards?reqSsn="+reqSn+"&digest="+digest+"&reqFrom=WECHAT_MP";
	    		HttpClient client = HttpClientBuilder.create().build();
	    		HttpPost post = new HttpPost(url);
	        	post.setHeader("Content-type", "application/json");
	        	JSONObject jo = JSONObject.fromObject(json);
	        	System.out.println(jo.toString());
	        	StringEntity params =new StringEntity(jo.toString());
	        	post.setEntity(params);
	        	HttpResponse response = client.execute(post);
	        	sprint(response.getEntity());
		} catch (Exception e) {
		   e.printStackTrace();
	  }		
		
	}
	@Test
	public  void buyOrderList() {
		String reqSn =Tools.initTranantSn();
		String json="{\"reqSsn\":\""+reqSn+"\",\"reqFrom\":\"WECHAT_MP\",\"customerKey\":\"1002239203076123\",\"orderStatus\":\"1\",\"startTime\":null,\"endTime\":null}";
		try {
			String digest = Signature.hmacSHA1Encrypt(json, "C58D336792015B71A8468E9B01175F3B62044483CF4ACF1193F303B1F4282AF56A89D058F9DC69233FD6E185334CBD9586258834E76DD34DB3A1E00E356733EB");
			String url = host + "/prepaidCard/buyCardOrder?reqSsn="+reqSn+"&digest="+digest+"&reqFrom=WECHAT_MP";
    		HttpClient client = HttpClientBuilder.create().build();
    		HttpPost post = new HttpPost(url);
        	post.setHeader("Content-type", "application/json");
        	JSONObject jo = JSONObject.fromObject(json);
        	System.out.println(jo.toString());
        	StringEntity params =new StringEntity(jo.toString());
        	post.setEntity(params);
        	HttpResponse response = client.execute(post);
        	sprint(response.getEntity());
	} catch (Exception e) {
	   e.printStackTrace();
  }	        
	}
}
