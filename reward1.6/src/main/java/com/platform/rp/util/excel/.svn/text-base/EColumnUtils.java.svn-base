package com.platform.rp.util.excel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public abstract class EColumnUtils {

	public static List<EColumnVo> parse(Class<?> clazz){
		List<EColumnVo> list = null;
		List<Field> fields = parseField(clazz);
		if(!fields.isEmpty()){
			list = new ArrayList<EColumnVo>();
			EColumnVo vo = null;
			for(Field f : fields){
				vo = new EColumnVo();
				EColumn eColumn = f.getAnnotation(EColumn.class);
				if(eColumn != null){
					vo.setName(f.getName());
					vo.setIndex(eColumn.index());
					vo.setTitle(eColumn.title());
					vo.setType(eColumn.type());
					vo.setWidth(eColumn.width());
					list.add(vo);
				}
			}
		}
		
		return list;
	}
	
	public static List<Field> parseField(Class<?> clazz){
		return Arrays.asList(clazz.getDeclaredFields());
	}
	
	public static Object getValue(Object o, String field){
		try {
			return BeanUtils.getProperty(o, field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
