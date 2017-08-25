package com.platform.rp.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

/**
 * 与会员系统的接口请求
 * 
 * @author 戴
 * 
 */

public class HttpsClient {

    private static Logger logger = Logger.getLogger(HttpsClient.class);

    private static X509TrustManager xtm = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };
    private static X509TrustManager[] xtmArray = new X509TrustManager[] { xtm };

    public static String sendHttpsPost(String path, String param)
            throws Exception {

        logger.info("会员请求地址：" + path);
        logger.info("会员请求参数：" + param);
        // 发送请求时间
        Date sendTime = new Date();

        HttpsURLConnection conn = null;
        StringBuffer stingbuff = new StringBuffer();
        BufferedReader buffRed = null;
        OutputStream outStream = null;
        try {
            URL url = new URL(path);
            conn = (HttpsURLConnection) url.openConnection();
            if (conn instanceof HttpsURLConnection) {
                SSLContext context = SSLContext.getInstance("TLS");
                context.init(new KeyManager[0], xtmArray, new SecureRandom());
                SSLSocketFactory socketFactory = context.getSocketFactory();
                ((HttpsURLConnection) conn).setSSLSocketFactory(socketFactory);
                HostnameVerifier hgg = new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                };
                ((HttpsURLConnection) conn).setHostnameVerifier(hgg);
            }
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(30000);
            conn.setDoInput(true);
            conn.setDoOutput(true);// 允许输出数据
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(param.getBytes("UTF-8"));
            outStream.flush();

            buffRed = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));

            String line = "";
            while ((line = buffRed.readLine()) != null) {
                stingbuff.append(line);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        } finally {

            // 接收到应答时间
            Date acceptTime = new Date();

            Long missingTime = acceptTime.getTime() - sendTime.getTime();
            logger.info("后台调用会员接口发送请求至收到应答时间差（毫秒数）：" + missingTime);

            IOUtils.closeQuietly(outStream);
            IOUtils.closeQuietly(buffRed);
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                }
            }
        }
        return stingbuff.toString();
    }
    
    
    public static String doPost(String url,String json){
    	HttpResponse response;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
	    	post.setHeader("Content-type", "application/json");
	    	JSONObject jo = JSONObject.fromObject(json);
	    	System.out.println(jo.toString());
	    	StringEntity params =new StringEntity(jo.toString());
	    	post.setEntity(params);
			response = client.execute(post);
			return sprint(response.getEntity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    	
    }
    
    
    public static String sprint(HttpEntity entity) throws Exception, IOException {
    	BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent()));
    	StringBuffer result = new StringBuffer();
    	String line = "";
    	while ((line = rd.readLine()) != null) {
    	result.append(line);
    	}
    	System.out.println("result:"+result);
		return result.toString();
	}
}