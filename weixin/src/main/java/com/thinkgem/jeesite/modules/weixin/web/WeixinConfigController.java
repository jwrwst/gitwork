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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinConfig;
import com.thinkgem.jeesite.modules.weixin.service.WeixinConfigService;

/**
 * 微信配置Controller
 * @author WHW
 * @version 2016-08-18
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/weixinConfig")
public class WeixinConfigController extends BaseController {

	@Autowired
	private WeixinConfigService weixinConfigService;
	
	@ModelAttribute
	public WeixinConfig get(@RequestParam(required=false) String id) {
		WeixinConfig entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = weixinConfigService.get(id);
		}
		if (entity == null){
			entity = new WeixinConfig();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:weixinConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(WeixinConfig weixinConfig, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<WeixinConfig> page = weixinConfigService.findPage(new Page<WeixinConfig>(request, response), weixinConfig); 
		model.addAttribute("page", page);
		return "modules/weixin/weixinConfigList";
	}

	@RequiresPermissions("weixin:weixinConfig:view")
	@RequestMapping(value = "form")
	public String form(WeixinConfig weixinConfig, Model model) {
		model.addAttribute("weixinConfig", weixinConfig);
		return "modules/weixin/weixinConfigForm";
	}

	@RequiresPermissions("weixin:weixinConfig:edit")
	@RequestMapping(value = "save")
	public String save(WeixinConfig weixinConfig, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, weixinConfig)){
			return form(weixinConfig, model);
		}
		weixinConfigService.save(weixinConfig);
		addMessage(redirectAttributes, "保存微信配置成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinConfig/?repage";
	}
	
	@RequiresPermissions("weixin:weixinConfig:edit")
	@RequestMapping(value = "delete")
	public String delete(WeixinConfig weixinConfig, RedirectAttributes redirectAttributes) {
		weixinConfigService.delete(weixinConfig);
		addMessage(redirectAttributes, "删除微信配置成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/weixinConfig/?repage";
	}

}