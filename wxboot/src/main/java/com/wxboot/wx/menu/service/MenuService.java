package com.wxboot.wx.menu.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.wxboot.wx.framework.config.BaseURL;
import com.wxboot.wx.framework.config.WeiXinUtils;
import com.wxboot.wx.framework.resp.AccessToken;
import com.wxboot.wx.framework.utils.HttpUtils;
import com.wxboot.wx.menu.constants.MenuConstant;
import com.wxboot.wx.menu.req.Button;
import com.wxboot.wx.menu.req.ClickButton;
import com.wxboot.wx.menu.req.Menu;
import com.wxboot.wx.menu.req.ViewButton;

import net.sf.json.JSONObject;

/**
 * 家家帮
 * @author wang
 * 2017年4月12日 下午4:35:52
 * 类描述：
 */
public class MenuService {
	public final static Logger log = LoggerFactory.getLogger(MenuService.class);
	
	private static List<Button> initSecondMenu(){
		List<Button> subButtons = new ArrayList<Button>();
		//1、扫码clickButton
		ClickButton clickButton = new ClickButton();
		clickButton.setName("扫码事件");
		clickButton.setType(MenuConstant.SCANCODE_PUSH);
		clickButton.setKey("31");
		//2、地理位置clickButton
		ClickButton clickBtn = new ClickButton();
		clickBtn.setName("地理位置");
		clickBtn.setType(MenuConstant.LOCATION_SELECT);
		clickBtn.setKey("32");
		//3、添加到集合中
		subButtons.add(clickButton);
		subButtons.add(clickBtn);
		return subButtons;
	}
	
	private static List<Button> initFirstMenu(){
		List<Button> buttons = new ArrayList<Button>();
		//1、点击Buttion
		ClickButton clickButton = new ClickButton();
		clickButton.setName("你懂的1");
		clickButton.setType(MenuConstant.CLICK);
		clickButton.setKey("1001");
		buttons.add(clickButton);
		
		//2、视图Button
		ViewButton viewButton = new ViewButton();
		viewButton.setName("view菜单");
		viewButton.setType(MenuConstant.VIEW);
		viewButton.setUrl("http://211.103.227.133:80/wxboot/userinfo/index");
		buttons.add(viewButton);
		
		//3、
		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(initSecondMenu());
		buttons.add(button);
		return buttons;
	}
	/**
	 * 组装菜单
	 * @return
	 */
	public static String initMenu(){
		List<Button> buttons = initFirstMenu();
		Menu menu = new Menu();
		menu.setButton(buttons);
		return JSON.toJSONString(menu);
	}
	/**创建菜单*/
	public static JSONObject createMenu() throws Exception{
		//success: {"errcode":0,"errmsg":"ok"}
		//error  : {"errcode":40018,"errmsg":"invalid button name size"}
		String params = initMenu();
		log.info(params);
		AccessToken token =WeiXinUtils.getAccessToken();
		String url = BaseURL.getCreateMenuUrl(token.getAccessToken());
		JSONObject jsonObject = HttpUtils.doPost(url, params);
		return jsonObject;
	}
	
	public static void main(String[] args) {
		try {
			JSONObject jsonObject = createMenu();
			if(jsonObject != null){
//				int result = jsonObject.getInt("errcode");
//				int errmsg = jsonObject.getInt("errmsg");
				log.info(JSON.toJSONString(jsonObject));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

 