/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinUser;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinUserDao;

/**
 * 微信用户Service
 * @author WHW
 * @version 2016-08-17
 */
@Service
@Transactional(readOnly = true)
public class WeixinUserService extends CrudService<WeixinUserDao, WeixinUser> {

	public WeixinUser get(String id) {
		return super.get(id);
	}
	
	public List<WeixinUser> findList(WeixinUser weixinUser) {
		return super.findList(weixinUser);
	}
	
	public Page<WeixinUser> findPage(Page<WeixinUser> page, WeixinUser weixinUser) {
		return super.findPage(page, weixinUser);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinUser weixinUser) {
		super.save(weixinUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinUser weixinUser) {
		super.delete(weixinUser);
	}
	
}