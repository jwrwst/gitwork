/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ituber.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ituber.entity.ReqRequirementResource;
import com.thinkgem.jeesite.modules.ituber.dao.ReqRequirementResourceDao;

/**
 * 需求资源Service
 * @author 王宏伟
 * @version 2016-08-30
 */
@Service
@Transactional(readOnly = true)
public class ReqRequirementResourceService extends CrudService<ReqRequirementResourceDao, ReqRequirementResource> {

	public ReqRequirementResource get(String id) {
		return super.get(id);
	}
	
	public List<ReqRequirementResource> findList(ReqRequirementResource reqRequirementResource) {
		return super.findList(reqRequirementResource);
	}
	
	public Page<ReqRequirementResource> findPage(Page<ReqRequirementResource> page, ReqRequirementResource reqRequirementResource) {
		return super.findPage(page, reqRequirementResource);
	}
	
	@Transactional(readOnly = false)
	public void save(ReqRequirementResource reqRequirementResource) {
		super.save(reqRequirementResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(ReqRequirementResource reqRequirementResource) {
		super.delete(reqRequirementResource);
	}
	
}