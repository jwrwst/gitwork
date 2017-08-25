/**
 * 
 */
package com.platform.rp.services.sys.inner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.sys.core.dao.SysCodeClassDAO;
import com.platform.rp.services.sys.core.dao.entity.SysCodeClassEntity;
import com.platform.rp.services.sys.inner.service.ISysCodeClassService;
import com.platform.rp.services.sys.inner.vo.SysCodeClassVo;

/**
 * <pre>
 * 
 * Created Date： 2015年7月2日 上午10:47:14
 * 
 * Updator：
 * 
 * Updated Date：
 * 
 * Decription：
 * 
 * Version： 1.0.1
 * </pre>
 */
@Service
public class SysCodeClassServiceImpl implements ISysCodeClassService {

	@Autowired
	SysCodeClassDAO<SysCodeClassEntity> sysCodeClassDAO;
	
	@Override
	public void modify(SysCodeClassEntity entity) {
		if(entity.getId() > 0)
			sysCodeClassDAO.update(entity);
		else
			sysCodeClassDAO.save(entity);
	}

	@Override
	public void delete(int id) {
		sysCodeClassDAO.delete(id);
	}
	
	@Override
	public SysCodeClassEntity get(int id) {
		return sysCodeClassDAO.get(id);
	}

	@Override
	public Page getListPage(Page page, String name) {
		int start = page.getPageNo() > 0 ? (page.getPageNo() - 1) * page.getPageSize(): 0;
		int end = page.getPageNo() * page.getPageSize();
		List<SysCodeClassVo> list = sysCodeClassDAO.getListPage(start, end, name);
		page.setResult(list);
		
		return page;
	}

}
