/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinConfig;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinConfigDao;

/**
 * 微信配置Service
 * @author WHW
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WeixinConfigService extends CrudService<WeixinConfigDao, WeixinConfig> {

	public WeixinConfig get(String id) {
		return super.get(id);
	}
	
	public List<WeixinConfig> findList(WeixinConfig weixinConfig) {
		return super.findList(weixinConfig);
	}
	
	public Page<WeixinConfig> findPage(Page<WeixinConfig> page, WeixinConfig weixinConfig) {
		return super.findPage(page, weixinConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinConfig weixinConfig) {
		super.save(weixinConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinConfig weixinConfig) {
		super.delete(weixinConfig);
	}
	
}