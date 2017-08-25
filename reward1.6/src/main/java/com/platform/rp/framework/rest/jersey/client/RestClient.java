package com.platform.rp.framework.rest.jersey.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.platform.rp.framework.rest.jersey.client.component.BlockMark;

/**
 * rest方式客户端公共类
 * 
 */
@SuppressWarnings("serial")
public abstract class RestClient<T> implements Serializable {

    /**
     * rest 使用post方式json格式 传递信息
     * 
     * @param requestEntity
     *            请求数据实体
     * @throws Exception
     */
    public abstract T postByJson(Object requestEntity);

    /**
     * 传递字符串，返回json串
     * 
     * @param requestStr
     * @return
     */
    public abstract T postTextByJson(String requestStr);

    /**
     * rest 使用get方式json格式 传递信息
     * 
     * @return
     */
    public abstract T getByJson();

    /**
     * 设置是否阻塞标志
     * 
     * @param blockMark
     *            要设置的 blockMark
     */
    public abstract RestClient<T> setBlockMark(BlockMark blockMark);

    /**
     * json序列化
     * 
     * @param inputEntity
     * @return
     * @throws Exception
     */
    public static String serializerByJson(Object inputEntity, String encoding)
            throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return new String(mapper.writeValueAsBytes(inputEntity), encoding);
    }

    /**
     * json反序列化
     * 
     * @param jsonStr
     * @return
     */
    public static <T> T deserializeByJson(String jsonStr, Class<T> resultClass)
            throws Exception {
        Gson gson = new Gson();
        return gson.fromJson(jsonStr, resultClass);
    }
    
    /**
     * 普通post请求
     * @param params
     * @return
     */
    public static String sendPost(String url,Map<String, String> params)throws Exception{
    	URL u = null;
		HttpURLConnection con = null;

		// 构建请求参数
		StringBuffer sb = new StringBuffer();
		if (params != null && params.size()!=0) {
			for (Entry<String, String> e : params.entrySet()) {
				sb.append(e.getKey());
				sb.append("=");
				sb.append(e.getValue());
				sb.append("&");
			}
			sb.substring(0, sb.length() - 1);
		}

		// 尝试发送请求
		try {
			u = new URL(url);

			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			osw.write(sb.toString());
			osw.flush();
			osw.close();
		} catch (Exception e) {
			throw e;
		} finally {
			//关闭连接
			if (con != null) {
				con.disconnect();
			}
		}

		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String temp = null;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
		} catch (Exception e) {
			throw e;
		}

		return buffer.toString();
    }
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param)throws Exception {		
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
        	throw e;
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
            	throw e2;
            }
        }
        return result;
    }
}
