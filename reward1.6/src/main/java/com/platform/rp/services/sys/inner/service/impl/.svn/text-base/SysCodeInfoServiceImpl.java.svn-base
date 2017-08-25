/**
 * 
 */
package com.platform.rp.services.sys.inner.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.sys.core.dao.SysCodeInfoDAO;
import com.platform.rp.services.sys.core.dao.entity.SysCodeInfoEntity;
import com.platform.rp.services.sys.inner.service.ISysCodeInfoService;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;

/**
 * 
 * <pre>
 * 
 * Created Date： 2015年7月2日 下午8:45:27
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
public class SysCodeInfoServiceImpl implements ISysCodeInfoService {

	@Autowired
	SysCodeInfoDAO<SysCodeInfoEntity> sysCodeInfoDAO;
	
	@Override
	public void modify(SysCodeInfoEntity entity) {
		if(entity.getId() > 0)
			sysCodeInfoDAO.update(entity);
		else
			sysCodeInfoDAO.save(entity);
	}

	@Override
	public void delete(int id) {
		sysCodeInfoDAO.delete(id);
	}
	
	@Override
	public SysCodeInfoEntity get(int id) {
		return sysCodeInfoDAO.get(id);
	}

	@Override
	public Page getListPage(Page page, int classifyid, String name) {
		int start = page.getPageNo() > 0 ? (page.getPageNo() - 1) * page.getPageSize(): 0;
		int end = page.getPageNo() * page.getPageSize();
		List<SysCodeInfoVo> list = sysCodeInfoDAO.getListPage(start, end, classifyid, name);
		page.setResult(list);
		
		return page;
	}

	@Override
	public List<SysCodeInfoVo> getByClassvalue(String classvalue) {
		
		return sysCodeInfoDAO.getByClassvalue(classvalue);
	}

	@Override
	public List<SysCodeInfoVo> getByInfovalue(String name,String classvalue) {
		
		return sysCodeInfoDAO.getByInfovalue(name,classvalue);
	}
	
}
