package com.platform.rp.util.cache;

public interface ICache {
	/**
	 * 保存默认一天
	 * @param key		一级类别
	 * @param key2		二级类别
	 * @param value		内容
	 */
	public void save(String key,String key2,Object value);
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param validity		有效期
	 */
	public void save(String key,String key2,Object value,int validity);
	/**
	 * 获取信息
	 * @param key
	 * @return
	 */
	public Object get(String key,String key2);
	/**
	 * 获取信息
	 * @param key
	 * @return
	 */
	public void remove(String key,String key2);
	/**
	 * 获取信息
	 * @param key
	 * @return
	 */
	public void remove(String key);
}
