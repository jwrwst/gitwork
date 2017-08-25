package com.platform.rp.services.sys.core.dao;

import java.util.List;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.sys.inner.vo.SysAuthInfoVo;

/**
 * 
 * @author TangJun
 *
 */
public interface SysAuthInfoDAO<T> extends BaseDAO<T>{

	public List<SysAuthInfoVo> getSysAuthInfoList();
	
	
}
