/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.thinkgem.jeesite.modules.weixin.entity.Test1;
import com.thinkgem.jeesite.modules.weixin.service.Test1Service;

/**
 * 测试Controller
 * @author WHW
 * @version 2016-09-12
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/test1")
public class Test1Controller extends BaseController {

	@Autowired
	private Test1Service test1Service;
	
	@ModelAttribute
	public Test1 get(@RequestParam(required=false) String id) {
		Test1 entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = test1Service.get(id);
		}
		if (entity == null){
			entity = new Test1();
		}
		return entity;
	}
	
	@RequiresPermissions("weixin:test1:view")
	@RequestMapping(value = {"list", ""})
	public String list(Test1 test1, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Test1> page = test1Service.findPage(new Page<Test1>(request, response), test1); 
		model.addAttribute("page", page);
		return "modules/weixin/test1List";
	}

	@RequiresPermissions("weixin:test1:view")
	@RequestMapping(value = "form")
	public String form(Test1 test1, Model model) {
		model.addAttribute("test1", test1);
		return "modules/weixin/test1Form";
	}

	@RequiresPermissions("weixin:test1:edit")
	@RequestMapping(value = "save")
	public String save(Test1 test1, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, test1)){
			return form(test1, model);
		}
		test1Service.save(test1);
		addMessage(redirectAttributes, "保存测试成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/test1/?repage";
	}
	
	@RequiresPermissions("weixin:test1:edit")
	@RequestMapping(value = "nicai")
	public String save1(Test1 test1, Model model, RedirectAttributes redirectAttributes) {
		return "redirect:http://www.baidu.com";
	}
	
	@RequiresPermissions("weixin:test1:edit")
	@RequestMapping(value = "delete")
	public String delete(Test1 test1, RedirectAttributes redirectAttributes) {
		test1Service.delete(test1);
		addMessage(redirectAttributes, "删除测试成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/test1/?repage";
	}

	
	public static void main(String[] args) {
		 Pattern pattern = Pattern.compile("^[1][1-9]\\d{9}$");
		 Matcher matcher = pattern.matcher("135657654321");
		 System.out.println(matcher.matches()?"手机号输入合法":"手机号输入不合法");
	}
}