package com.platform.rp.services.sys.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.sys.inner.vo.SysUserInfoVo;

/**
 * 
 * @author TangJun
 * 
 */
public interface SysUserInfoDAO<T> extends BaseDAO<T> {

	public List<SysUserInfoVo> getSysUserInfoPage(@Param("start") int start, @Param("end") int end,
			@Param("userName")String userName, @Param("roleName")String roleName);

	public List<SysUserInfoVo> getSysUserInfoList();

	public SysUserInfoVo getSysUserInfo(@Param("name") String name, @Param("password") String password);

	public boolean updatePwd(String name, String password);
	
	public abstract SysUserInfoVo authenticationExtrinsicUser(@Param("name") String name, @Param("password") String password);
}
