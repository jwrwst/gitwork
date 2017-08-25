/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.dao;

import com.thinkgem.jeesite.common.persistence.TreeDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinMenu;

/**
 * 微信菜单管理DAO接口
 * @author WHW
 * @version 2016-08-17
 */
@MyBatisDao
public interface WeixinMenuDao extends TreeDao<WeixinMenu> {
	
}