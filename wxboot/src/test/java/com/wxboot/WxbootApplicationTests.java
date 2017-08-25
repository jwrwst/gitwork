package com.wxboot;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wxboot.wx.framework.config.WeiXinUtils;
import com.wxboot.wx.framework.constants.CommonConstant;
import com.wxboot.wx.framework.resp.AccessToken;
import com.wxboot.wx.media.resp.Media;
import com.wxboot.wx.message.service.MessageService;

import net.sf.json.JSONObject;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxbootApplicationTests {
	@Resource
	private MessageService messageService;
	@Test
	public void contextLoads() {
		try {
			String msgType = CommonConstant.MEDIA_TYPE.IMAGE;
			//1、获取token对象
			AccessToken token = WeiXinUtils.getAccessToken();
			//2、上传
			String result = WeiXinUtils.upload("F:/image/yxd.png", token.getAccessToken(), msgType);
			//3、解析返回media对象
			Media media = WeiXinUtils.getMediaInfo(result, msgType);
			//4、输出，拿到 mediaId
			System.out.println("media:"+JSONObject.fromObject(media)+"\ntoken:"+JSONObject.fromObject(token));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
