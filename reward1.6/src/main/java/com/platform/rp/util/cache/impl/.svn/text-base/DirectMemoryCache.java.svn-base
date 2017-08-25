package com.platform.rp.util.cache.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.platform.rp.util.cache.ICache;

/**
 * 本机内存缓存
 * TODO:临时方法
 * @author zhangsheng
 *
 */
@Component
public class DirectMemoryCache implements ICache{
	private Map<String,Object> DATAS = new HashMap<String,Object>();
	private Map<String,List<String>> KEYS = new HashMap<String,List<String>>();
	@Override
	public void save(String key,String key2, Object value) {
		List<String> keyLists = KEYS.get(key);
		if(keyLists == null){
			keyLists = new ArrayList<String>();
			KEYS.put(key, keyLists);
		}
		keyLists.add(key2);
		save(key,key2, value, 1);
	}
	public void save(String key,String key2,Object value,int validity){
		DATAS.put(key+key2, value);
	}
	@Override
	public Object get(String key,String key2) {
		return DATAS.get(key+key2);
	}
	public void remove(String key,String key2){
		DATAS.remove(key+key2);
	}
	@Override
	public void remove(String key) {

		List<String> keyLists = KEYS.get(key);
		if(keyLists != null){
			for (String key2 : keyLists) {
				remove(key,key2);
			}
		}
	}
}
