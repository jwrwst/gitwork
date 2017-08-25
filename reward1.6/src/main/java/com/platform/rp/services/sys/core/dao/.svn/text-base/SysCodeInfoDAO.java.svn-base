package com.platform.rp.services.sys.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.sys.core.dao.entity.SysCodeInfoEntity;
import com.platform.rp.services.sys.inner.vo.SysCodeInfoVo;

/**
 * 
 * <pre>
 * 
 * Created Date： 2015年7月2日 下午8:40:40
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
public interface SysCodeInfoDAO<T> extends BaseDAO<T> {

	public List<SysCodeInfoVo> getListPage(@Param("start") int start, @Param("end") int end, @Param("classifyid") int classifyid,
			@Param("name") String name);

	public List<SysCodeInfoVo> getByClassvalue(String classvalue);

	public List<SysCodeInfoVo> getByInfovalue(@Param("name") String name, @Param("classvalue") String classvalue);

	public SysCodeInfoVo findSysCodeByClassAndCodeValue(@Param("codeClass") String codeClass, @Param("codeValue") String codeValue);

	public List<SysCodeInfoEntity> getListByParentCode(@Param("parCode")String parCode);
}
