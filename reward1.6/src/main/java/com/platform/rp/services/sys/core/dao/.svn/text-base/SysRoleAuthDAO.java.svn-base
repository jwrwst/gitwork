package com.platform.rp.services.sys.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.sys.core.dao.entity.SysRoleAuthEntity;
import com.platform.rp.services.sys.inner.vo.SysRoleAuthVo;

/**
 * 
 * @author TangJun
 *
 */
public interface SysRoleAuthDAO<T> extends BaseDAO<T> {

	
	public List<SysRoleAuthVo> getRoleAuthList(@Param("sysRoleId")int sysRoleId);
	
	public List<SysRoleAuthEntity> getRoleAuthEntityList(@Param("sysRoleId")int sysRoleId);
	
	public void deleteByRoleId(@Param("roleId")int roleId);
}
