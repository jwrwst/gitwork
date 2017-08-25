/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinTemplate;

/**
 * 微信消息模板DAO接口
 * @author WHW
 * @version 2016-08-18
 */
@MyBatisDao
public interface WeixinTemplateDao extends CrudDao<WeixinTemplate> {
	
}