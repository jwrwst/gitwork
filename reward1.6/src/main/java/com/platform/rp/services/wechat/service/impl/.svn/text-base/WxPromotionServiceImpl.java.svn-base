package com.platform.rp.services.wechat.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.wechat.external.common.wxPay.CommonUtil;
import com.platform.rp.services.wechat.external.common.wxv3Pay.utils.RequestHandler;
import com.platform.rp.services.wechat.service.IWxPromotionService;
import com.platform.rp.services.wechat.service.IWxSendMessageService;
import com.platform.rp.util.DateUtil;
import com.platform.rp.util.Properties;


@Service
public class WxPromotionServiceImpl implements IWxPromotionService{

	Logger log = Logger.getLogger(WxPromotionServiceImpl.class);
	
	@Autowired
	private IWxSendMessageService sendMessageService;
	
	@Resource(name="systemProperties")
	private Properties properties;
	

	@Override
	public boolean promationPay(String orderId,String openId,String amount,String desc){
		String appid = properties.appId;
		String appsecret = properties.secret;
		String partnerkey = properties.wxpayPartnerKey;
		String mchid = properties.wxpayPartner;
		String tradeNo = orderId;
		String nonceStr = CommonUtil.CreateNoncestr();//Wx3Pay.getNonceStr()
		String spbill_create_ip = "127.0.0.1";
        String check_name = "NO_CHECK"; 
		
		CloseableHttpClient httpclient = null;
		try {
			KeyStore keyStore  = KeyStore.getInstance("PKCS12");
	        FileInputStream instream = new FileInputStream(new File(this.getClass().getClassLoader().getResource("key/apiclient_cert.p12").getPath()));
	        try {
	            keyStore.load(instream, mchid.toCharArray());
	        } finally {
	            instream.close();
	        }

	        SSLContext sslcontext = SSLContexts.custom()
	                .loadKeyMaterial(keyStore, mchid.toCharArray())
	                .build();
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	        httpclient =HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();
			
			//请求企业付款地址
			String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";	

			//按acsii值排序请求参数执行签名
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("mch_appid", appid);
			packageParams.put("mchid", mchid);
			packageParams.put("nonce_str", nonceStr);
			packageParams.put("partner_trade_no", tradeNo);
			packageParams.put("openid", openId);
			packageParams.put("check_name", check_name);			
			packageParams.put("amount", amount);
			packageParams.put("desc", desc);			
			packageParams.put("spbill_create_ip", spbill_create_ip);			

			//生成签名
			RequestHandler reqHandler = new RequestHandler(null, null);
			reqHandler.init(appid, appsecret, partnerkey);			
			String sign = reqHandler.createSign(packageParams);
			
			//生成请求参数
			StringBuffer transdata = new StringBuffer();
			transdata.append("<xml>");
			transdata.append("<mch_appid>"+appid+"</mch_appid>");
			transdata.append("<mchid>"+mchid+"</mchid>");
			transdata.append("<nonce_str>"+nonceStr+"</nonce_str>");
			transdata.append("<partner_trade_no>"+tradeNo+"</partner_trade_no>");
			transdata.append("<openid>"+openId+"</openid>");
			transdata.append("<check_name>"+check_name+"</check_name>");
			//transdata.append("<re_user_name>张三</re_user_name>");
			transdata.append("<amount>"+amount+"</amount>");
			transdata.append("<desc>"+desc+"</desc>");
			transdata.append("<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>");
			transdata.append("<sign>"+sign+"</sign>");
			transdata.append("</xml>");
			
			log.info("企业付款请求参数："+transdata.toString());
			
			HttpPost post = new HttpPost(url);
	    	StringEntity params =new StringEntity(transdata.toString(),"utf-8");
	    	post.setEntity(params);
	    	
	    	CloseableHttpResponse response = httpclient.execute(post);			
	    	try {
                HttpEntity entity = response.getEntity();
                
                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    StringBuffer strBuff= new StringBuffer();
                    while ((text = bufferedReader.readLine()) != null) {
                        strBuff.append(text);
                    }
                    log.info("企业付款返回："+strBuff.toString());
                    try{
	                    if(strBuff.toString().indexOf("NOTENOUGH")>-1){
	                    	JSONObject jsonObject = new JSONObject();
	                        jsonObject.element("first", new JSONObject().element("value", "您好，企业账户余额不足，请尽快充值").element("color", "#173177"));
	                        jsonObject.element("keyword1", "哈哈");
	            		    jsonObject.element("keyword2", "哈哈");	                        
	                        jsonObject.element("keyword3", "哈哈");
	            		    jsonObject.element("remark", new JSONObject().element("value", "\n众赏打赏平台，众赏之下，人人勇夫").element("color", "#173177"));
	            		    
	                    	sendMessageService.sendIncomeTemplate(jsonObject, "o21XSvxwpan2qqdG-Fdwkmlzpql8");
	                    }
                    }catch(Exception e){log.error("通知异常",e);}
                    
                    if(strBuff.toString().indexOf("SUCCESS")>-1 && strBuff.toString().indexOf("[CDATA[]]")>-1){
                    	return true;
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
			
		} catch (Exception e) {
			log.error("企业付款",e);
			return false;
		}finally{
			if(httpclient!=null){
				try {
					httpclient.close();
				} catch (IOException e) {
					log.error("关闭连接",e);
				}
			}
		}
		return false;
	}
	
		
	public static void main(String[] args)throws Exception {
		KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        //FileInputStream instream = new FileInputStream(new File(Thread.currentThread().getContextClassLoader().getResource("key/apiclient_cert.p12").getPath()));
		FileInputStream instream = new FileInputStream(new File("G:\\打赏工作\\project\\reward\\target\\classes\\key\\apiclient_cert.p12"));
        try {
            keyStore.load(instream, "1324943801".toCharArray());
        } finally {
            instream.close();
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, "1324943801".toCharArray())
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[] { "TLSv1" },
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {
            
            try {
    			String url = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
    			
    			String appid = "wxd8fcc1484e28b278";
    			String appsecret = "bb0532bf00dc81148c82881166ee4a1b";
    			String partnerkey = "dashangtech321tipsdashangtech321";
    			String mchid = "1324943801";
    			String tradeNo = "13423nngg83";
    			String nonceStr = "4444bbbbhhuu7";//Wx3Pay.getNonceStr()
    			String spbill_create_ip = "127.0.0.1";
                String check_name = "NO_CHECK"; 
                
                String openId = "o21XSvw683Hm8Nb3MvrSnknKA5A4";
                String amount = "1940";
                String desc = "付款";
    			
    			SortedMap<String, String> packageParams = new TreeMap<String, String>();
    			packageParams.put("mch_appid", appid);
    			packageParams.put("mchid", mchid);
    			packageParams.put("nonce_str", nonceStr);
    			packageParams.put("partner_trade_no", tradeNo);
    			packageParams.put("openid", openId);
    			packageParams.put("check_name", check_name);			
    			packageParams.put("amount", amount);
    			packageParams.put("desc", desc);			
    			packageParams.put("spbill_create_ip", spbill_create_ip);			

    			RequestHandler reqHandler = new RequestHandler(null, null);
    			reqHandler.init(appid, appsecret, partnerkey);

    			String sign = reqHandler.createSign(packageParams);
    			System.out.println(sign);
    			StringBuffer transdata = new StringBuffer();
    			transdata.append("<xml>");
    			transdata.append("<mch_appid>"+appid+"</mch_appid>");
    			transdata.append("<mchid>"+mchid+"</mchid>");
    			transdata.append("<nonce_str>"+nonceStr+"</nonce_str>");
    			transdata.append("<partner_trade_no>"+tradeNo+"</partner_trade_no>");
    			transdata.append("<openid>"+openId+"</openid>");
    			transdata.append("<check_name>"+check_name+"</check_name>");
    			//transdata.append("<re_user_name>张三</re_user_name>");
    			transdata.append("<amount>"+amount+"</amount>");
    			transdata.append("<desc>"+desc+"</desc>");
    			transdata.append("<spbill_create_ip>"+spbill_create_ip+"</spbill_create_ip>");
    			transdata.append("<sign>"+sign+"</sign>");
    			transdata.append("</xml>");
    			//httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
    			    			
    			HttpPost post = new HttpPost(url);
    	    	//post.setHeader("Content-type", "application/json");
    	    	//JSONObject jo = JSONObject.fromObject(json);
    	    	
    	    	StringEntity params =new StringEntity(transdata.toString(),"utf-8");
    	    	post.setEntity(params);
    	    	CloseableHttpResponse response = httpclient.execute(post);
    			
    	    	try {
                    HttpEntity entity = response.getEntity();

                    System.out.println("----------------------------------------");
                    System.out.println(response.getStatusLine());
                    if (entity != null) {
                        System.out.println("Response content length: " + entity.getContentLength());
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                        String text;
                        while ((text = bufferedReader.readLine()) != null) {
                            System.out.println(text);
                        }
                       
                    }
                    EntityUtils.consume(entity);
                } finally {
                    response.close();
                }
    			
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        } finally {
            httpclient.close();
        }
		
		
	}

}
