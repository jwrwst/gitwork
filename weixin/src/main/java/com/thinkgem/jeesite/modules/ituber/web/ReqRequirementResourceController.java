/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ituber.web;

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
import com.thinkgem.jeesite.modules.ituber.entity.ReqRequirementResource;
import com.thinkgem.jeesite.modules.ituber.service.ReqRequirementResourceService;

/**
 * 需求资源Controller
 * @author 王宏伟
 * @version 2016-08-30
 */
@Controller
@RequestMapping(value = "${adminPath}/ituber/reqRequirementResource")
public class ReqRequirementResourceController extends BaseController {

	@Autowired
	private ReqRequirementResourceService reqRequirementResourceService;
	
	@ModelAttribute
	public ReqRequirementResource get(@RequestParam(required=false) String id) {
		ReqRequirementResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = reqRequirementResourceService.get(id);
		}
		if (entity == null){
			entity = new ReqRequirementResource();
		}
		return entity;
	}
	
	@RequiresPermissions("ituber:reqRequirementResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(ReqRequirementResource reqRequirementResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ReqRequirementResource> page = reqRequirementResourceService.findPage(new Page<ReqRequirementResource>(request, response), reqRequirementResource); 
		model.addAttribute("page", page);
		return "modules/ituber/reqRequirementResourceList";
	}

	@RequiresPermissions("ituber:reqRequirementResource:view")
	@RequestMapping(value = "form")
	public String form(ReqRequirementResource reqRequirementResource, Model model) {
		model.addAttribute("reqRequirementResource", reqRequirementResource);
		return "modules/ituber/reqRequirementResourceForm";
	}

	@RequiresPermissions("ituber:reqRequirementResource:edit")
	@RequestMapping(value = "save")
	public String save(ReqRequirementResource reqRequirementResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, reqRequirementResource)){
			return form(reqRequirementResource, model);
		}
		reqRequirementResourceService.save(reqRequirementResource);
		addMessage(redirectAttributes, "保存需求资源成功");
		return "redirect:"+Global.getAdminPath()+"/ituber/reqRequirementResource/?repage";
	}
	
	@RequiresPermissions("ituber:reqRequirementResource:edit")
	@RequestMapping(value = "delete")
	public String delete(ReqRequirementResource reqRequirementResource, RedirectAttributes redirectAttributes) {
		reqRequirementResourceService.delete(reqRequirementResource);
		addMessage(redirectAttributes, "删除需求资源成功");
		return "redirect:"+Global.getAdminPath()+"/ituber/reqRequirementResource/?repage";
	}

}