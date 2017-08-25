package com.platform.rp.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtil {

	public static <T> T mapConvertBean(Map<String, Object> mapObj, Class<T> clazz) throws Exception {

		Object objValue = null;
		T instance = clazz.newInstance();

		Field[] arrField = clazz.getDeclaredFields();

		for (Field field : arrField) {

			objValue = mapObj.get(field.getName());

			if (objValue == null) {

				continue;
			}
			if(field.getType().getName().toLowerCase().contains("int")){
				objValue=Integer.parseInt(objValue+"");
			}
			field.setAccessible(true);
			field.set(instance, objValue);
		}

		return instance;
	}
	
	public static <T> T copy(T t, Object src){
		try {
			BeanUtils.copyProperties(t, src);
			return t;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
