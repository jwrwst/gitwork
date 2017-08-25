/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinQrcode;
import com.thinkgem.jeesite.modules.weixin.service.WeixinQrcodeService;

/**
 * 微信二维码Controller
 * @author WHW
 * @version 2016-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinQrcode")
public class WeixinQrcodeController extends BaseController {

	@Autowired
	private WeixinQrcodeService weixinQrcodeService;
	
	@ModelAttribute
	public WeixinQrcode get(@RequestParam(required=false) String id) {
		WeixinQrcode entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinQrcodeService.get(id);
		}
		if (entity == null){
			entity = new WeixinQrcode();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinQrcode:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinQrcode weixinQrcode, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinQrcode> page = weixinQrcodeService.findPage(new Page<WeixinQrcode>(request, response), weixinQrcode); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinQrcodeList";
	}
	
}