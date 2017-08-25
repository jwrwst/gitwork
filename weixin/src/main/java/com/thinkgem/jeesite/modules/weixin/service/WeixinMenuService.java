/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.TreeService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMenu;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinMenuDao;

/**
 * 微信菜单管理Service
 * @author WHW
 * @version 2016-08-17
 */
@Service
@Transactional(readOnly = true)
public class WeixinMenuService extends TreeService<WeixinMenuDao, WeixinMenu> {

	public WeixinMenu get(String id) {
		return super.get(id);
	}
	
	public List<WeixinMenu> findList(WeixinMenu weixinMenu) {
		if (StringUtils.isNotBlank(weixinMenu.getParentIds())){
			weixinMenu.setParentIds(","+weixinMenu.getParentIds()+",");
		}
		return super.findList(weixinMenu);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinMenu weixinMenu) {
		super.save(weixinMenu);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinMenu weixinMenu) {
		super.delete(weixinMenu);
	}
	
}