/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTemplate;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinTemplateDao;

/**
 * 微信消息模板Service
 * @author WHW
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WeixinTemplateService extends CrudService<WeixinTemplateDao, WeixinTemplate> {

	public WeixinTemplate get(String id) {
		return super.get(id);
	}
	
	public List<WeixinTemplate> findList(WeixinTemplate weixinTemplate) {
		return super.findList(weixinTemplate);
	}
	
	public Page<WeixinTemplate> findPage(Page<WeixinTemplate> page, WeixinTemplate weixinTemplate) {
		return super.findPage(page, weixinTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinTemplate weixinTemplate) {
		super.save(weixinTemplate);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinTemplate weixinTemplate) {
		super.delete(weixinTemplate);
	}
	
}