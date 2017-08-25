/**
 * 
 */
package com.platform.rp.services.sys.inner.service;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.sys.core.dao.entity.SysCodeClassEntity;

/**
 * <pre>
 * Created Date： 2015年7月2日 上午10:44:35
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
public interface ISysCodeClassService {

	public abstract void modify(SysCodeClassEntity entity);

	public abstract void delete(int id);

	public abstract SysCodeClassEntity get(int id);

	public abstract Page getListPage(Page page, String name);
}
