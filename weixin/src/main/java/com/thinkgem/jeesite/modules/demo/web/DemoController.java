/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.demo.web;

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
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.demo.entity.Demo;
import com.thinkgem.jeesite.modules.demo.service.DemoService;

/**
 * 样本Controller
 * @author WHW
 * @version 2016-12-09
 */
@Controller
@RequestMapping(value = "${adminPath}/demo/demo")
public class DemoController extends BaseController {

	@Autowired
	private DemoService demoService;
	
	@ModelAttribute
	public Demo get(@RequestParam(required=false) String id) {
		Demo entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = demoService.get(id);
		}
		if (entity == null){
			entity = new Demo();
		}
		return entity;
	}
	
	@RequiresPermissions("demo:demo:view")
	@RequestMapping(value = {"list", ""})
	public String list(Demo demo, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Demo> page = demoService.findPage(new Page<Demo>(request, response), demo); 
		model.addAttribute("page", page);
		return "modules/demo/demoList";
	}

	@RequiresPermissions("demo:demo:view")
	@RequestMapping(value = "form")
	public String form(Demo demo, Model model) {
		model.addAttribute("demo", demo);
		return "modules/demo/demoForm";
	}

	@RequiresPermissions("demo:demo:edit")
	@RequestMapping(value = "save")
	public String save(Demo demo, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, demo)){
			return form(demo, model);
		}
		demoService.save(demo);
		addMessage(redirectAttributes, "保存样本成功");
		return "redirect:"+Global.getAdminPath()+"/demo/demo/?repage";
	}
	
	@RequiresPermissions("demo:demo:edit")
	@RequestMapping(value = "delete")
	public String delete(Demo demo, RedirectAttributes redirectAttributes) {
		demoService.delete(demo);
		addMessage(redirectAttributes, "删除样本成功");
		return "redirect:"+Global.getAdminPath()+"/demo/demo/?repage";
	}

}