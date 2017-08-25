package com.platform.rp.services.sys.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.sys.core.dao.entity.SysUserRoleDetailEntity;
import com.platform.rp.services.sys.inner.vo.SysUserRoleDetailVo;

/**
 * 
 * @author TangJun
 *
 */
public interface SysUserRoleDetailDAO<T> extends BaseDAO<T> {

	public List<SysUserRoleDetailEntity> getUserRoleDetailEntityList(int sysRoleId);
	
	public SysUserRoleDetailVo getUserRoleDetailVo(@Param("sysUserId")int sysUserId);
	
	public void deleteByRoleId(int roleId);
}
