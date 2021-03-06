/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.demo.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.demo.entity.Demo;

/**
 * 样本DAO接口
 * @author WHW
 * @version 2016-12-09
 */
@MyBatisDao
public interface DemoDao extends CrudDao<Demo> {
	
}