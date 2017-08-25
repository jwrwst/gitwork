/**
 * 
 */
package com.platform.rp.services.sys.inner.service;

import java.util.List;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.sys.core.dao.entity.SysCodeInfoEntity;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;

/**
 * 
 * <pre>
 * 
 * Created Date： 2015年7月2日 下午8:45:17
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
public interface ISysCodeInfoService {

	public abstract void modify(SysCodeInfoEntity entity);

	public abstract void delete(int id);

	public abstract SysCodeInfoEntity get(int id);

	public abstract Page getListPage(Page page, int classifyid, String name);

	public abstract List<SysCodeInfoVo> getByClassvalue(String classvalue);
	
	public abstract List<SysCodeInfoVo> getByInfovalue(String name,String classvalue);
	
}
