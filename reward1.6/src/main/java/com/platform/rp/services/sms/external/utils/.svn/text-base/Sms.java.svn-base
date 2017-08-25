package com.platform.rp.services.sms.external.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONObject;

import com.google.gson.JsonObject;
import com.platform.rp.services.sms.external.entity.SmsResult;
import com.platform.rp.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.platform.rp.common.application.ApplicationContextUtil;

import sun.misc.BASE64Encoder;

public class Sms {

	static Logger logger = Logger.getLogger(Sms.class);
	/**
	 * String code = "测试";
		long seqId = System.currentTimeMillis();
		String param = "cdkey=" + properties.smsSn + "&password=" + properties.smsPassword
				+ "&phone=" + phone + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
	
		String ret = SDKHttpClient.sendSMS(properties.smsUrl, param);
		logger.debug("发送结果:" + ret);
	 */
	//userid:49475  企业号： AC00203 初始密码：  
	//private static String USER_NAME = "tanggong";
	//private static String PASSWORD = "000000";
	//private static String USER_ID = "49429";
	//private static String URL = "http://114.113.154.5/sms.aspx?action=send";
	private static String USER_NAME = "AC00203";
	private static String PASSWORD = "Gu#gong9msg";
	private static String USER_ID = "49475";
	private static String URL = "http://114.113.154.5/sms.aspx?action=send";
	/*private static String USER_NAME;
	private static String PASSWORD ;
	private static String USER_ID;
	private static String URL;
	static{
		Properties properties = (Properties) ApplicationContextUtil.getBean("systemProperties");
		URL = properties.smsUrl;
		USER_ID = properties.smsSn;
		USER_NAME = properties.smsKey;
		PASSWORD = properties.smsPassword;
	}*/
	
	public static void send(String text,String mobileNum) throws HttpException, IOException{
		//String Text=URLEncoder.encode("�����֤�룺8859�����š�","utf-8");
		//String Text="�����֤�룺8859�����š�";
		
		HttpClient client=new HttpClient();
				
		PostMethod post=new PostMethod(URL);
		post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		NameValuePair userid=new NameValuePair("userid","");
		NameValuePair account=new NameValuePair("account",USER_NAME);
		NameValuePair password=new NameValuePair("password",PASSWORD);
		NameValuePair mobile=new NameValuePair("mobile",mobileNum);
		NameValuePair content=new NameValuePair("content",text);
		NameValuePair sendTime=new NameValuePair("sendTime","");
		NameValuePair extno=new NameValuePair("extno","");
		post.setRequestBody(new NameValuePair[]{userid,account,password,mobile,content,sendTime,extno});
		int statu=client.executeMethod(post);
		logger.debug("statu="+statu);
		String str=post.getResponseBodyAsString();
		
		logger.debug(str);
		
//		HttpMethod method=new PostMethod(Url);//������Url
//		method.setRequestHeader("Content-type", "text/xml; charset=utf-8");
//		client.executeMethod(method);
//		String str = method.getResponseBodyAsString();
//		System.out.println("result="+str);
		
		try {
			//���ַ�ת��ΪXML
			Document doc=DocumentHelper.parseText(str);
			//��ȡ��ڵ�
			Element rootElt=doc.getRootElement();
			//��ȡ��ڵ��µ��ӽڵ��ֵ
			String returnstatus=rootElt.elementText("returnstatus").trim();
			String message=rootElt.elementText("message").trim();
			String remainpoint=rootElt.elementText("remainpoint").trim();
			String taskID=rootElt.elementText("taskID").trim();
			String successCounts=rootElt.elementText("successCounts").trim();

			
			System.out.println("返回状态为："+returnstatus);
			System.out.println("返回信息提示："+message);
			System.out.println("返回余额："+remainpoint);
			System.out.println("返回任务批次："+taskID);
			System.out.println("返回成功条数："+successCounts);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public static SmsResult secretSend(String text,String mobileNum) throws HttpException, IOException{

		SimpleDateFormat df=new SimpleDateFormat("MMddHHmmss");		
		String Stamp = df.format(new Date());
					//UUID.randomUUID().toString();
					//"0525104204";
		//String password="123456";
		String Secret=MD5.GetMD5Code(PASSWORD+Stamp).toUpperCase();
		
		try {
			JSONObject j=new JSONObject();
			j.put("UserName", USER_NAME);
			j.put("Stamp", Stamp);
			j.put("Secret", Secret);
			j.put("Moblie", mobileNum);
			j.put("Text", text);
			j.put("Ext", "");
			j.put("SendTime", "");
			//获取json字符串
			String json=j.toString();
			byte[] data=json.getBytes("utf-8");
			byte[] key=PASSWORD.getBytes();
			//获取加密的key
			byte[] nkey=new byte[8];
			System.arraycopy(key, 0, nkey, 0, key.length > 8 ? 8 : key.length);
			//Des加密，base64转码
			String str = //"2OpRqDznqXj8gIouMlRO44IGUtNR+pkV/fiWGgdpEvmGSlrNbXP31dZjVwjswyebLC39BCCKNEKYQG6CTkGePAMJpPO+tNqx4WRjPx06t8PWGuAa4lLRDb0DKv5TsMKs6Bn9giHCgc8Y1LFSmyGDKW97DyQ6TGr6w8Y+06HUHj2eNeH/E+xz0J1or8OHBOBdSXr0U3QzlTjY4fs6/THLy9lmH8mvfKIjLSKImny8+DrJEU/FuOcl2+hf0/ECjTGy4J8cj/G+7AiNNpwYncuMaax67tgi3kvwiIUU2Q6spH9YEHz7PGqiNkEoYwCKisS5LAhpvA7GTQS1iRJPVQ14sQ==";
					new BASE64Encoder().encode(DesHelper.encrypt(data, nkey)); 
			
			logger.debug(str);
			//url编码
			//str=URLEncoder.encode(str, "utf-8");
			
			//发送http请求
			String Url="http://dx.ipyy.net/ensms.ashx";
			HttpClient client=new HttpClient();
			PostMethod post=new PostMethod(Url);
			post.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			NameValuePair UserId=new NameValuePair("UserId",USER_ID);
			NameValuePair Text64=new NameValuePair("Text64",str);
			post.setRequestBody(new NameValuePair[]{UserId,Text64});
			int statu=client.executeMethod(post);
			System.out.println("statu="+statu);
			//返回结果
			String result=post.getResponseBodyAsString();
			result = result.replaceAll("null","\"1\"");
			logger.info("result="+result);
			//JSONObject.fromObject(json), HOProductPrice.class, classMap);
			JSONObject jobj = JSONObject.fromObject(result);
			SmsResult res = new SmsResult();//(SmsResult) JSONObject.toBean(jobj, SmsResult.class);
			res.setDescription(jobj.get("Description")+"");
			res.setStatusCode(jobj.get("StatusCode")+"");
			res.setErrors(jobj.get("Errors")+"");
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
