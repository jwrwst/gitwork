/*
 * @(#)CftsUtil.java
 *
 * Copyright (c) 2013-2014 ZhongShiAn
 */
package com.platform.rp.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * 公共类
 * 
 */
public class CftsUtil {

	/**
	 * 数据库存储数据加密<BR>
	 * 
	 * @param password 明码password
	 * @return 加密后password
	 */
	public static String makeDBPassword(String password) {
		return digestByMD5(PropertyUtil.DB_PASSWORD_EN + password);
	}
	
	/**
	 * MD5加密函数
	 * 
	 * @param key 要加密的字符串
	 * @return 加密后所得到的字符串
	 */
	public static String digestByMD5(String key) {
		return DigestUtils.md5Hex(key);
	}
	
	/**
	 * 取得文件的MD5码
	 * 
	 * @param fis 文件流
	 * @return MD5码
	 * @throws Exception 异常
	 */
	public static String digestByMD5(InputStream fis) throws Exception {
		return DigestUtils.md5Hex(fis);
	}
    /**
     * 从指定路径的XML取数据
     * 
     * @param Document xml的Document
     * @param xpathStr 检索路径
     * @return 结果list
     * @throws Exception
     */
	public static List<String> getNodeContentList(Document doc, String xpathStr) throws Exception{

		List<String> resultList = null;
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile(xpathStr);

		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		resultList = new ArrayList<String>();
		for (int i = 0; i < nodes.getLength(); i++) {
			resultList.add(nodes.item(i).getTextContent());
		}
		
		return resultList;
	}
	
	 /**
     * 从指定路径的XML取数据
     * 
     * @param Document xml的Document
     * @param xpathStr 检索路径
     * @return 结果list
     * @throws Exception
     */
	public static String getNodeContent(Document doc, String xpathStr) throws Exception{

		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		XPathExpression expr = xpath.compile(xpathStr);

		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;

		if (nodes.getLength() > 0) {
			return nodes.item(0).getTextContent();
		}
		return "";
	}
	
	/**
	 * 对象序列化<BR>
	 * 把对象序列化到输出流里面。
	 * 
	 * @param serializable 序列化对象
	 * @param os 输出流
	 * @throws IOException 异常
	 */
	public static void writeObject(Object serializable, OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(serializable);
		oos.flush();
		oos.close();
	}
	
	/**
	 * 从序列化字节流里面生成关联对象<BR>
	 * 
	 * @param is 序列化字节流
	 * @return 序列化字节流对象
	 * @throws Exception 异常
	 */
	public static Object readObject(InputStream is) throws Exception {
		 ObjectInputStream oin = new ObjectInputStream(is);
		 Object obj = oin.readObject();
		 oin.close();
		 return obj;
	}

	/**
	 * 从序列化字节流里面生成关联对象<BR>
	 * 
	 * @param url url
	 * @param param 参数
	 * @return 序列化字节流对象
	 * @throws Exception 异常
	 */
	public static Object readObject(String url, Object param) throws Exception {
		
//		if (param != null && !param.isEmpty()) {
//			StringBuilder sb = new StringBuilder(url);
//			sb.append('?');
//			Set<Entry<String, String>> entrys = param.entrySet();
//			for (Entry<String, String> entry : entrys) {
//				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
//				sb.append('=');
//				sb.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
//				sb.append('&');
//			}
//			sb.setLength(sb.length() - 1);
//			url = sb.toString();
//		}
		URL url0 = new URL(url);
		HttpURLConnection rulConnection = (HttpURLConnection) url0.openConnection();
		rulConnection.setDoOutput(true);
		rulConnection.setDoInput(true);
		rulConnection.setUseCaches(false);
		rulConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
		rulConnection.setRequestMethod("POST"); 
		
		OutputStream outStrm = rulConnection.getOutputStream();
		ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);
		objOutputStrm.writeObject(param); 
		objOutputStrm.flush();
		objOutputStrm.close(); 
		
		ObjectInputStream oin = new ObjectInputStream(
				rulConnection.getInputStream());
		Object obj = oin.readObject();
		oin.close();
		return obj;
	}
	/**
	 * 从序列化字节流里面生成关联对象<BR>
	 * 
	 * @param url url
	 * @return 序列化字节流对象
	 * @throws Exception 异常
	 */
	public static Object readObject(String url) throws Exception {
		 return readObject(url, null);
	}
	/**
	 * 是否为空
	 * 
	 * @param value 参数值
	 * @return 如果是null或""的情况为ture，反之为false
	 */
	public static boolean isEmpty(String value) {
		if (value == null || "".equals(value)) {
			return true;
		}
		return false;
	}
	
	/**
	 * spring容器bean实例取得方法。
	 * 
	 * @param context 容器上下文
	 * @param beanName 容器中bean名称
	 * @return bean实例
	 */
	public static Object getSpringBean(ServletContext context, String beanName) {
		org.springframework.web.context.WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(context);
		return springContext.getBean(beanName);
	}
	
	/**
	 * html特殊字符转换。
	 * 
	 * @param source html特殊字符
	 * @return 转换后结果
	 */
	public static String htmlEncode(String source) {
        if (source == null) {
            return "";
        }
        String html = "";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
            case '<':
                buffer.append("&lt;");
                break;
            case '>':
                buffer.append("&gt;");
                break;
            case '&':
                buffer.append("&amp;");
                break;
            case '"':
                buffer.append("&quot;");
                break;
            case ' ':
                buffer.append("&nbsp;");
                break;
            case '\\':
                buffer.append("&#165;");
                break;
            case 10:
            case 13:
                break;
            default:
                buffer.append(c);
            }
        }
        html = buffer.toString();
        return html;
    }
	
	/**
	 * 从SOAPMessage里面取出 soap 电文。
	 *  
	 * @param soapMessage SOAPMessage
	 * @return soap 电文
	 * @throws Exception
	 */
	public static String getSopaXml(SOAPMessage soapMessage) throws Exception {
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		Source sc = soapMessage.getSOAPPart().getContent();
		// Set output transformation
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		tf.transform(sc, result);
		String ret = sw.getBuffer().toString();
		//System.out.println(sw.getBuffer().toString());
		sw.close();
		
		return ret;
	}
	
	/**
	 * HTTPS通信时，不需要证书时SSL认证使用
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static void doTrustToCertificates() throws NoSuchAlgorithmException, KeyManagementException  {
//        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
 
                    public void checkServerTrusted(X509Certificate[] certs, String authType){
                        return;
                    }
 
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        return;
                    }
                }
        };
 
//        SSLContext sc = SSLContext.getInstance("SSL");
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
                    System.out.println("Warning: URL host '" + urlHostName + "' is different to SSLSession host '" + session.getPeerHost() + "'.");
                }
                return true;
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }
	/**
	 * 生成随机字符串<BR>
	 * 随机字符串包含英（大小写均有）数字。
	 * @param length 生成字符串的长度
	 * @return 随机字符串
	 */
	public static String genRandomStr(int length) {
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 
				'u', 'v', 'w', 'x', 'y', 'z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
				'U', 'V', 'W', 'X', 'Y', 'Z',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		int maxNum = 62;
		int count = 0;
		
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		
		while (count < length) {
			int random = r.nextInt(maxNum);
			sb.append(str[random]);
			count ++;
		}
		return sb.toString();
	}
	
	   /**
     * Returns a set from comma delimted Strings.
     * @param s The String to parse.
     * @return A set from comma delimted Strings.
     */
    public static Set<String> commaDelimitedStringToSet(String s) {
        Set<String> set = new HashSet<String>();
        String[] split = s.split(",");
        for (String aSplit : split) {
            String trimmed = aSplit.trim();
            if (trimmed.length() > 0)
                set.add(trimmed);
        }
        return set;
    }
    
	/**
	 * 测试用
	 * 
	 * @param args 参数
	 */
	public static void main(String[]args) {
		System.out.println(digestByMD5("xiace"));
		System.out.println(DigestUtils.md5Hex("xiace"));
		System.out.println(digestByMD5("201311221234567890cfts-mobile"));
		System.out.println(digestByMD5("201311221234567890cfts-pc"));
		
		System.out.println(genRandomStr(10));
		System.out.println(genRandomStr(20));
		System.out.println(genRandomStr(9));
	}

}
