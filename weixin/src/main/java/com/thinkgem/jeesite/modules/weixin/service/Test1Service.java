/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.Test1;
import com.thinkgem.jeesite.modules.weixin.dao.Test1Dao;

/**
 * 测试Service
 * @author WHW
 * @version 2016-09-12
 */
@Service
@Transactional(readOnly = true)
public class Test1Service extends CrudService<Test1Dao, Test1> {

	public Test1 get(String id) {
		return super.get(id);
	}
	
	public List<Test1> findList(Test1 test1) {
		return super.findList(test1);
	}
	
	public Page<Test1> findPage(Page<Test1> page, Test1 test1) {
		return super.findPage(page, test1);
	}
	
	@Transactional(readOnly = false)
	public void save(Test1 test1) {
		super.save(test1);
	}
	
	@Transactional(readOnly = false)
	public void delete(Test1 test1) {
		super.delete(test1);
	}
	
}