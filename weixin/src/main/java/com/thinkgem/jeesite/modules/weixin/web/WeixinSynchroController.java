package com.thinkgem.jeesite.modules.weixin.web;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.management.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.thinkgem.jeesite.common.pojo.Button;
import com.thinkgem.jeesite.common.pojo.CommonButton;
import com.thinkgem.jeesite.common.pojo.ComplexButton;
import com.thinkgem.jeesite.common.pojo.Menu;
import com.thinkgem.jeesite.common.pojo.ViewButton;
import com.thinkgem.jeesite.common.utils.JedisUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinConfig;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMenu;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinQrcode;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTemplate;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinUser;
import com.thinkgem.jeesite.modules.weixin.entity.constant.WXConstant;
import com.thinkgem.jeesite.modules.weixin.service.WeixinConfigService;
import com.thinkgem.jeesite.modules.weixin.service.WeixinMenuService;
import com.thinkgem.jeesite.modules.weixin.service.WeixinTemplateService;
import com.thinkgem.jeesite.modules.weixin.service.WeixinUserService;
import com.thinkgem.jeesite.modules.weixin.util.WeixinUtil;

/**
 * 微信基础类
 * @author whw
 *
 */
@Controller
@RequestMapping("${adminPath}/weixin/synchro")
public class WeixinSynchroController extends BaseController {
	
	@Autowired
	private WeixinMenuService weixinMenuService;
	
	@Autowired
	private WeixinConfigService weixinConfigService;
	
	@Autowired
	private WeixinUserService weixinUserService;

	@Autowired
	private WeixinTemplateService weixinTemplateService;
	/**
	 * 初始化微信配置
	 */
	@PostConstruct
	public void initialize(){
		List<WeixinConfig> configs = weixinConfigService.findList(new WeixinConfig());
		for(WeixinConfig config:configs){
			JedisUtils.set(config.getConfigKey(), config.getConfigValue(), 0);
			System.out.println(JedisUtils.get(config.getConfigKey()));
		}
	}
	
	/**
	 * 获取微信配置 【默认先访问reids中抓取配置，如果redis没有该配置信息则会查询数据库，然后将查询结果放在缓存服务器redis中】
	 * @param key
	 * @return
	 */
	private String getWeixinConfig(String key){
		String value = null;
		try {
			value = JedisUtils.get(key);
		} catch (Exception e) {
			logger.debug("Reids报错："+e.getMessage());
		}
		if(StringUtils.isEmpty(value)){
			WeixinConfig config = new WeixinConfig();
			config.setConfigKey(key);
			config = weixinConfigService.get(config);
			if(config!=null&&StringUtils.isNotEmpty(config.getConfigValue())){
				value = config.getConfigValue();
				JedisUtils.set(key, value, 0);
			}
		}
		return value;
	}
	
	/**
	 * 获取ACCESS_TOKEN
	 * @return
	 */
	public String getAccessToken(){
		return WeixinUtil.getAccessToken(getWeixinConfig(WXConstant.WXAPPID),getWeixinConfig(WXConstant.WXAPPSECRET));
	}
	
	
	/**
	 * 创建微信菜单
	 * @return
	 */
	@ResponseBody
	@RequestMapping("synchroMenu")
	public String synchroMenu(){
		JSONObject json = new JSONObject();
		//获取配置的菜单信息
		List<WeixinMenu> menus = weixinMenuService.findList(new WeixinMenu());
		List<Button> buttons = new ArrayList<>();
		for(WeixinMenu firstMenu:menus){
			//判断是否是一级菜单
			if(firstMenu.getParentIds().equals("0,")){
				//判断一级菜单的类型
				if(StringUtils.isEmpty(firstMenu.getType())){
					//创建一级菜单
					ComplexButton mainBtn = new ComplexButton();
					mainBtn.setName(firstMenu.getName());
					//创建二级菜单集合
					List<Button> buts = new ArrayList<>();
					for(WeixinMenu menu:menus){
						if(menu.getParentId().equals(firstMenu.getId())){
							//将一级菜单所对应的二级菜单添加到集合中
							buts.add(createButton(menu));
						}
					}
					if(buts.size()>5){
						json.put("msg", "二级菜单数量不能大于5");
						return json.toJSONString();
					}
					mainBtn.setSub_button(buts.toArray(new Button[buts.size()]));
					//加入一级菜单
					buttons.add(mainBtn);
				}else{
					//加入一级菜单
					buttons.add(createButton(firstMenu));
				}
			}
		}
		if(buttons.size()>3){
			json.put("msg", "一级菜单数量不能大于3");
			return json.toJSONString();
		}
		//将获取到的菜单组转化为Menu对象
		Menu menu = new Menu();
        menu.setButton(buttons.toArray(new Button[buttons.size()]));
        //获取ACCESS_TOKEN
        String at = getAccessToken();
        if (StringUtils.isNotEmpty(at)) {
            // 调用接口创建菜单
            int result = WeixinUtil.createMenu(menu, at);
            // 判断菜单创建结果
            if (0 == result)
                json.put("msg", "创建菜单成功");
            else
            	json.put("msg", "菜单创建失败，错误码：" + result);
        }else{
        	json.put("msg", "获取ACCESS_TOKEN失败");
        }
		return json.toJSONString();
	}
	
	/**
	 * 根据菜单类型来创建菜单
	 * @param weixinMenu
	 * @return
	 */
	private Button createButton(WeixinMenu weixinMenu){
		//判断类型创建菜单
		if("click".equals(weixinMenu.getType())){
			CommonButton btn = new CommonButton();
			btn.setName(weixinMenu.getName());
			btn.setType("click");
			btn.setKey(weixinMenu.getVal());
			return btn;
		}else{
			ViewButton btn = new ViewButton();
	        btn.setName(weixinMenu.getName());
	        btn.setType("view");
	        btn.setUrl(getUrl(weixinMenu.getVal()));
	        return btn;
		}
	}
	
	private String getUrl(String baseUrl){
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+JedisUtils.get("WXAPPID")+"&redirect_uri="+baseUrl+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
//		return baseUrl ;
	}
	
	/**
	 * 抓取微信用户信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("synchroUser")
	public String synchroUser(){
		JSONObject jsonObject = new JSONObject();
		//获取ACCESS_TOKEN
		String accessToken = getAccessToken();
		if(StringUtils.isNotEmpty(accessToken)){
			//定义通过接口每次获取的微信用户数量（最大值为10000）
			int count = 0;
			//获取微信用户总数
			int num = 0;
			JSONObject json = new JSONObject();
			do {
				String append = "";
				//判断json是否包含next_openid（当获取微信用户大于10000时，会出现该值）
				if(json.get("next_openid")!=null){
					append = "&next_openid="+json.getString("next_openid");
				}
				//发送获取微信用户请求
				json = WeixinUtil.getAllForker(accessToken,append);
				count = json.getIntValue("count");
				//解析JSON获取用户OPENIDS
				JSONArray array = json.getJSONObject("data").getJSONArray("openid");
				for(int i = 0;i<array.size();i++){
					//通过OPENID获取用户信息
					WeixinUser weixinUser = WeixinUtil.getWerxinUser(accessToken, array.getString(i));
					if(weixinUser!=null){
						//保存微信用户
						try {
							weixinUserService.save(weixinUser);
							num++;
						} catch (Exception e) {
							logger.error("保存微信用户失败 "+e.getMessage());
						}
					}
				}
			} while (count==10000);
			jsonObject.put("msg", "成功获取 "+ num +" 名微信用户");
		}else{
			jsonObject.put("msg", "获取ACCESS_TOKEN失败");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 提取微信消息模板
	 * @return
	 */
	@ResponseBody
	@RequestMapping("synchroTemplate")
	public String synchroTemplate(){
		JSONObject jsonObject = new JSONObject();
		//获取ACCESS_TOKEN
		String accessToken = getAccessToken();
		if(StringUtils.isNotEmpty(accessToken)){
			//获取消息模板集合
			List<WeixinTemplate> list = WeixinUtil.geTemplates(accessToken);
			int num = 0;
			//添加消息模板
			for(WeixinTemplate template:list){
				//保存消息模板
				try {
					weixinTemplateService.save(template);
					num++;
				} catch (Exception e) {
					logger.error("保存微信消息模板失败 "+e.getMessage());
				}
			}
			jsonObject.put("msg", "成功获取 "+ num +" 条微信消息模板");
		}else{
			jsonObject.put("msg", "获取ACCESS_TOKEN失败");
		}
		return jsonObject.toJSONString();
	}
	
	/**
	 * 获取微信二维码
	 * @param weixinQrcode 二维码实体类
	 * @return
	 */
	@ResponseBody
	@RequestMapping("synchroQrCode")
	public String synchroQrCode(WeixinQrcode weixinQrcode){
		JSONObject jsonObject = new JSONObject();
		String accessToken = getAccessToken();
		
		
		if(WXConstant.WXQRTYPEP.equals(weixinQrcode.getType())){
			
		}else{
			
		}
		
		return jsonObject.toJSONString();
	}
}
