package com.platform.rp.services.sys.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.sys.core.dao.entity.SysRoleInfoEntity;
import com.platform.rp.services.sys.inner.vo.SysRoleInfoVo;

/**
 * 
 * @author TangJun
 * 
 */
public interface SysRoleInfoDAO<T> extends BaseDAO<T> {

	public List<SysRoleInfoVo> getSysRoleInfoPage(@Param("start") int start, @Param("end") int end,
			@Param("roleName") String roleName);

	public List<SysRoleInfoVo> getSysRoleInfoList();

	public SysRoleInfoEntity getSysUserInfo(int id);

}
