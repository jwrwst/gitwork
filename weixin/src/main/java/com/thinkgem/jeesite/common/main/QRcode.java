package com.thinkgem.jeesite.common.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.modules.weixin.util.ConfigUtils;
import com.thinkgem.jeesite.modules.weixin.util.WeixinUtil;


/**
 * 二维码创建类
 * 
 * @author WHW
 * @date 2014年12月3日
 */
public class QRcode {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	
	public static void main(String[] args) {
        String scene_id = "170";
        // 调用接口获取access_token  
//        String token = WeixinUtil.getAccessToken(ConfigUtils.APPID,ConfigUtils.APPSECRET);  
//        if(token!=null){
//        	//获取token
//        	String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+token;
//        	JSONObject jsonObject = WeixinUtil.httpRequest(url,"POST","{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\":"+scene_id+"}}}");
//        	System.out.println(jsonObject.toString());
//        	String kicket =jsonObject.getString("ticket");
//        	String res_url = jsonObject.getString("url");
//        	String qr_url ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+kicket;
//        	System.out.println("kicket:\t\t"+kicket);
//        	System.out.println("url:\t\t"+res_url);
//        	System.out.println("show_url:\t"+qr_url);
//        	System.out.println(scene_id);
//        }else{
//        	log.error("获取token失败");
//        }
        String accessToken = "XDJFFYpY5sIBAhIggALdi5laf_woMlLCdAzCrM94D0R3FWzfXK_p7iKeQWyHrJhhuNYKWjUFsHN1AwX02Rq_TE2CLc4r3Yvc-lcOaBJjBCaX3sJ0zrGIscweQqvohJ2zFLAjAFAZFU";
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
		JSONObject json = new JSONObject();
		json.put("expire_seconds", 120);
		json.put("action_name", "QR_SCENE");
		JSONObject sceneJson = new JSONObject();
		Long id= IdGen.randomLong();
		sceneJson.put("scene_id", id);
		JSONObject actionJson = new JSONObject();
		actionJson.put("scene", sceneJson);
		json.put("action_info", actionJson);
		JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", json.toJSONString());
		System.out.println(id);
		System.out.println(jsonObject.toJSONString());
		System.out.println("https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+jsonObject.getString("ticket"));
	}
	
}
