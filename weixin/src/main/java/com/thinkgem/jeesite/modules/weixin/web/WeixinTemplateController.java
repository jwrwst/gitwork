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
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTemplate;
import com.thinkgem.jeesite.modules.weixin.service.WeixinTemplateService;

/**
 * 微信消息模板Controller
 * @author WHW
 * @version 2016-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinTemplate")
public class WeixinTemplateController extends BaseController {

	@Autowired
	private WeixinTemplateService weixinTemplateService;
	
	@ModelAttribute
	public WeixinTemplate get(@RequestParam(required=false) String id) {
		WeixinTemplate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinTemplateService.get(id);
		}
		if (entity == null){
			entity = new WeixinTemplate();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinTemplate:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinTemplate weixinTemplate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinTemplate> page = weixinTemplateService.findPage(new Page<WeixinTemplate>(request, response), weixinTemplate); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinTemplateList";
	}

	@RequiresPermissions("weixin:weixinTemplate:view")
	@RequestMapping(value = "form")
	public String form(WeixinTemplate weixinTemplate, Model model) {
		model.addAttribute("weixinTemplate", weixinTemplate);
		return "modules/weixin/weixinTemplateForm";
	}
}