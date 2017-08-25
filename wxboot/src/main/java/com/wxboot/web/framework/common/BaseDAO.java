package com.wxboot.web.framework.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface BaseDAO<T> {
	
	List<T> selectListByPage(@Param("entity") T t,@Param("pager") Pager<T> pager);
	
	Long selectCount(@Param("entity") T t);

	T selectOne(@Param("entity") T t);

	T selectById(@Param("id")String id);
	
	void insert(T t);
	
	void update(T t);
	
	void deleteByIds(@Param("codes") String[] ids);
	
	void delete(@Param("code")String id);
}
