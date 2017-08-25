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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinUser;
import com.thinkgem.jeesite.modules.weixin.service.WeixinUserService;

/**
 * 微信用户Controller
 * @author WHW
 * @version 2016-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinUser")
public class WeixinUserController extends BaseController {

	@Autowired
	private WeixinUserService weixinUserService;
	
	@ModelAttribute
	public WeixinUser get(@RequestParam(required=false) String id) {
		WeixinUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinUserService.get(id);
		}
		if (entity == null){
			entity = new WeixinUser();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinUser weixinUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinUser> page = weixinUserService.findPage(new Page<WeixinUser>(request, response), weixinUser); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinUserList";
	}
}