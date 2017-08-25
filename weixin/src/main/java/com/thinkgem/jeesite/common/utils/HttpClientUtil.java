package com.thinkgem.jeesite.common.utils;


import java.io.IOException;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;
    private final static Logger LOG = LoggerFactory.getLogger(HttpClientUtil.class);

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);

        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());



        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        // configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     * 
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<String, Object>());
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     * 
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, Object> params) {
        return doGet(url, params, "UTF-8");
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     * 
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, Object> params, String charset) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0)
                param.append("?");
            else
                param.append("&");
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();

            System.out.println("执行状态码 : " + statusCode);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送 POST 请求（HTTP），不带输入数据
     * 
     * @param apiUrl
     * @return
     */
    public static String doPost(String apiUrl) {
        return doPost(apiUrl, new HashMap<String, Object>());
    }

    public static String doPost(String apiUrl, Map<String, Object> params) {
        return doPost(apiUrl, params, "UTF-8");
    }

    /**
     * 发送 POST 请求（HTTP），K-V形式
     * 
     * @param apiUrl
     *            API接口URL
     * @param params
     *            参数map
     * @return
     */
    public static String doPost(String apiUrl, Map<String, Object> params, String charset) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(charset)));
            response = httpClient.execute(httpPost);
            System.out.println(response.toString());
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, charset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     * 
     * @param apiUrl
     * @param json
     *            json对象
     * @return
     */
    public static String doPost(String apiUrl, Object json, String charset) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), charset);// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(entity, charset);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），K-V形式
     * 
     * @param apiUrl
     *            API接口URL
     * @param params
     *            参数map
     * @return
     */
    public static String doPostSSL(String apiUrl, Map<String, Object> params, String charset) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(charset)));
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, charset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     * 
     * @param apiUrl
     *            API接口URL
     * @param json
     *            JSON对象
     * @return
     */
    public static String doPostSSL(String apiUrl, Object json, String charset) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), charset);// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, charset);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接 过期证书也可以访问
     * 
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {

            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            // SSLContext sslContext = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(final X509Certificate[] chain, final String authType) throws CertificateException {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    // final Object[] obj = null;
                    // return (X509Certificate[]) obj;
                    return null;
                }


            };
            sslContext.init(null, new TrustManager[] { tm }, null);
            sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

    public static JSONObject getJsonByUrl(final String url) {
        JSONObject json = null;
        if (StringUtils.startsWith(url, "https://")) {
            final String httpOrgCreateTestRtn = doGetSSL(url, new HashMap<String, Object>(), "utf-8");
            if(httpOrgCreateTestRtn == null) return null;
            json = JSONObject.parseObject(httpOrgCreateTestRtn);

        } else if (StringUtils.startsWith(url, "http://")) {
            final String httpOrgCreateTestRtn = doGet(url, new HashMap<String, Object>(), "utf-8");
            if(httpOrgCreateTestRtn == null) return null;
            json = JSONObject.parseObject(httpOrgCreateTestRtn);
        }
        return json;
    }

    public static JSONObject getJsonByUrl(final String url, HashMap<String, Object> map) {
        JSONObject json = null;
        if (StringUtils.startsWith(url, "https://")) {
            final String httpOrgCreateTestRtn = doPostSSL(url, "", "utf-8");
            json = JSONObject.parseObject(httpOrgCreateTestRtn);

        } else if (StringUtils.startsWith(url, "http://")) {
            final String httpOrgCreateTestRtn = doPost(url, map, "utf-8");
            json = JSONObject.parseObject(httpOrgCreateTestRtn);
        }
        return json;
    }

    public static JSONObject getJsonByUrlByPost(final String url, HashMap<String, Object> map) {
        JSONObject json = null;
        if (StringUtils.startsWith(url, "https://")) {
            final String httpOrgCreateTestRtn = doPostSSL(url, "", "utf-8");
            json = JSONObject.parseObject(httpOrgCreateTestRtn);

        } else if (StringUtils.startsWith(url, "http://")) {
            final String httpOrgCreateTestRtn = doPost(url, map, "utf-8");
            json = JSONObject.parseObject(httpOrgCreateTestRtn);
        }
        return json;
    }
    
  public static int doGetRequestStatus(String url) {
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            LOG.info("status code : {}", statusCode);
            return statusCode;
        } catch (IOException e) {
            LOG.error("Error happend when in doGetRequestStatus: {}", url);
        }
        return -1;
    }
  
  /**
   * 发送 SSL GET 请求（HTTPS），K-V形式
   * 
   * @param apiUrl
   *            API接口URL
   * @param params
   *            参数map
   * @return
   */
  public static String doGetSSL(String apiUrl, Map<String, Object> params, String charset) {
      CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setDefaultRequestConfig(requestConfig).build();
      StringBuffer param = new StringBuffer();
      int i = 0;
      for (String key : params.keySet()) {
          if (i == 0)
              param.append("?");
          else
              param.append("&");
          param.append(key).append("=").append(params.get(key));
          i++;
      }
      apiUrl += param;
      HttpGet httpGet = new HttpGet(apiUrl);
      CloseableHttpResponse response = null;
      String httpStr = null;

      try {
          httpGet.setConfig(requestConfig);           
          response = httpClient.execute(httpGet);
          int statusCode = response.getStatusLine().getStatusCode();
          LOG.info("status code : {}",statusCode);
          if (statusCode != HttpStatus.SC_OK) {
              return null;
          }
          HttpEntity entity = response.getEntity();
          if (entity == null) {
              return null;
          }
          httpStr = EntityUtils.toString(entity, charset);
      } catch (Exception e) {
          LOG.error("Error happend when in doGetSSL: {}", apiUrl);
      } 
      return httpStr;
  }
  
  public static void main(String[] args) {
	  JSONObject json = new JSONObject();
	  json.put("authkey", "9836");
	  json.put("phone", "13846890270");
	  json.put("password", "123qwe");
	  System.out.println(json.toJSONString());
	  String doPost = doPost("http://localhost:8080/weixin-web/common/register", json.toJSONString(), "UTF-8");
	  System.out.println(doPost);
  }
}