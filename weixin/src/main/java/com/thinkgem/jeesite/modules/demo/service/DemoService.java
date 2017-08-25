/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.demo.entity.Demo;
import com.thinkgem.jeesite.modules.demo.dao.DemoDao;

/**
 * 样本Service
 * @author WHW
 * @version 2016-12-09
 */
@Service
@Transactional(readOnly = true)
public class DemoService extends CrudService<DemoDao, Demo> {

	public Demo get(String id) {
		return super.get(id);
	}
	
	public List<Demo> findList(Demo demo) {
		return super.findList(demo);
	}
	
	public Page<Demo> findPage(Page<Demo> page, Demo demo) {
		return super.findPage(page, demo);
	}
	
	@Transactional(readOnly = false)
	public void save(Demo demo) {
		super.save(demo);
	}
	
	@Transactional(readOnly = false)
	public void delete(Demo demo) {
		super.delete(demo);
	}
	
}