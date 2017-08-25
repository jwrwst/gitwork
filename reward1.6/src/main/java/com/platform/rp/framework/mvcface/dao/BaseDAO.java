package com.platform.rp.framework.mvcface.dao;

import java.util.Map;

/**
 * 
 * @author TangJun
 *
 */
public interface BaseDAO<T>{

	public void save(T t);
	
	public void delete(long id);
	
	public T get(long id);
	
	public void update(T t);
	
	public int count(Map<String, Object> params);
	
}
