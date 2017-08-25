package com.platform.rp.services.sys.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.sys.inner.vo.SysCodeClassVo;

/**
 * 
 * <pre>
 * 
 * Created Date： 2015年7月2日 上午10:10:10
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
public interface SysCodeClassDAO<T> extends BaseDAO<T> {

	public List<SysCodeClassVo> getListPage(@Param("start") int start, @Param("end") int end,
			@Param("name") String name);

}
