package com.wxboot.wx.framework.config;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.wxboot.wx.framework.constants.CommonConstant;
import com.wxboot.wx.framework.resp.AccessToken;
import com.wxboot.wx.framework.utils.HttpUtils;
import com.wxboot.wx.media.resp.Media;

import net.sf.json.JSONObject;

/**
 * 家家帮
 * @author wang
 * 2017年4月8日 上午11:12:41
 * 类描述：
 */
public class WeiXinUtils {
	
	/**获取access_token对象*/
	public static AccessToken getAccessToken(){
		AccessToken accessToken = new AccessToken();
		String url = BaseURL.getAccessTokenUrl();
		JSONObject jsonObject = HttpUtils.sendGet(url);
		if (jsonObject != null) {
			String token = jsonObject.getString("access_token");
			Long expires = jsonObject.getLong("expires_in");
			accessToken.setAccessToken(token);
			accessToken.setExpiresIn(expires);
		}
		return accessToken;
	}
	
/*	public static void main(String[] args) {
		try {
			String msgType = CommonConstant.MEDIA_TYPE.IMAGE;
			AccessToken accessToken = getAccessToken();
			String token = accessToken.getAccessToken();
			System.out.println(accessToken.getAccessToken());
			System.out.println(accessToken.getExpiresIn());
			String result = upload("F:/image/1.png", token, msgType);
			Media media = getMediaInfo(result, msgType);
			System.out.println(JSONObject.fromObject(media));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/**根据上传返回的result结果集，转换为对象*/
	public static Media getMediaInfo(String result, String msgType){
		JSONObject jsonObj = JSONObject.fromObject(result);
		String typeName = "media_id";
		if(!CommonConstant.MEDIA_TYPE.IMAGE.equals(msgType)){
			typeName = msgType + "_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		String createdAt = jsonObj.getString("created_at");
		return Media.dao.initMedia(mediaId, msgType, createdAt);
	}
	/**上传返回结果集*/
	public static String upload(String filePath, String accessToken,String msgType) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}

//		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);

		String url = BaseURL.getImageMediaInfo(msgType, accessToken);
		
		URL urlObj = new URL(url);
		//连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 

		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);

		//文件正文部分
		//把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return result;
	}
	
	/** xml转map */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			SAXReader reader = new SAXReader();
			InputStream ins = request.getInputStream();
			Document doc = reader.read(ins);
			Element root = doc.getRootElement();
			List<Element> elements = root.elements();
			for (Element el : elements) {
				map.put(el.getName(), el.getText());
			}
			ins.close();
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}

 