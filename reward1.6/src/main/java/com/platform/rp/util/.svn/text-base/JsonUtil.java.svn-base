package com.platform.rp.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;

/**
 * @author tangjun
 * 创建日期：2014年11月25日
 */
public class JsonUtil {

	/**
	 * 将JAVA对象转换成JSON字符串
	 * 去除空字符、空对象
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String ObjectToJsonStr(Object obj)throws Exception{
		try{
			if(obj==null){
				return "null";
			}
			StringBuilder jsonStr = new StringBuilder("{");
			Class<?> cla = obj.getClass();
			Annotation annotation= cla.getAnnotation(XmlType.class);
			if(null!=annotation){
			   String [] arr=(String[]) annotation.annotationType().getMethod("propOrder").invoke(annotation);
			   for (String str : arr) {
				   Field f= cla.getDeclaredField(str);
				   f.setAccessible(true);
				   if(f.getType() == long.class){
						jsonStr.append( "\""+f.getName()+"\":"+f.getLong(obj)+"," );
					}else if(f.getType() == double.class){
						jsonStr.append( "\""+f.getName()+"\":"+f.getDouble(obj)+",");
					}else if(f.getType() == float.class){
						jsonStr.append( "\""+f.getName()+"\":"+f.getFloat(obj)+",");
					}else if(f.getType() == int.class){
						jsonStr.append( "\""+f.getName()+"\":"+f.getInt(obj)+",");
					}else if(f.getType() == boolean.class){
						jsonStr.append( "\""+f.getName()+"\":"+f.getBoolean(obj)+",");
					}else if(f.getType() == Integer.class||f.getType() == Boolean.class
							||f.getType() == Double.class||f.getType() == Float.class					
							||f.getType() == Long.class){	
						if(null!=f.get(obj)){
							jsonStr.append( "\""+f.getName()+"\":"+f.get(obj)+"," );
						}
					}else if(f.getType() == String.class){
						if(StringUtils.isNotEmpty((String)f.get(obj))){
							jsonStr.append( "\""+f.getName()+"\":\""+f.get(obj)+"\"," );
						}
					}else if(f.getType() == List.class){
						if(null!=f.get(obj)){
							String value = ListToJson((List<?>)f.get(obj));
							jsonStr.append( "\""+f.getName()+"\":"+value+"," );	
						}
					}else if(f.getType() == Set.class){
						if(null!=f.get(obj)){
							String value = SetToJson((Set<?>)f.get(obj));
							jsonStr.append( "\""+f.getName()+"\":"+value+"," );		
						}
					}else if(f.getType() == Map.class){
						if(null!=f.get(obj)){
							String value = MapToJsonStr((Map<?,?>)f.get(obj));
							jsonStr.append( "\""+f.getName()+"\":"+value+"," );	
						}
					}else if(f.getType().getName().indexOf("Decimal")>-1){		
						jsonStr.append( "\""+f.getName()+"\":"+f.get(obj)+"," );
					}else if(f.getType().getName().indexOf("Date")>-1){
						if(null!=f.get(obj)){
							jsonStr.append( "\""+f.getName()+"\":\""+DateUtil.format((Date)f.get(obj),"yyyy-MM-dd'T'HH:mm:ss.SSSZ")+"\"," );	
						}
					}else{
						if(null!=f.get(obj)){
							jsonStr.append("\""+f.getName()+"\":"+ObjectToJsonStr(f.get(obj))+"," );
						}
					}
			   }
			   if(jsonStr.lastIndexOf(",")==jsonStr.length()-1){
				   jsonStr.delete(jsonStr.length()-1,jsonStr.length());
			   }
			}
			
			jsonStr.append( "}" );
			return jsonStr.toString();	
		}catch(Exception e){
			throw e;
		}
	}
	
	/**
	 * 将JAVA的LIST转换成JSON字符串
	 * @param list
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String ListToJson(List<?> list) throws Exception{
		if(list==null||list.size()==0){
			return "[]";
		}
		StringBuilder jsonStr = new StringBuilder("[");
		for (Object object : list) {
			jsonStr.append(ObjectToJsonStr(object)+",");
		}
		if(jsonStr.lastIndexOf(",")==jsonStr.length()-1){
			   jsonStr.delete(jsonStr.length()-1,jsonStr.length());
		}
		jsonStr.append( "]" );
		return jsonStr.toString();	
	}
	
	/**
	 * 将JAVA的Set转换成JSON字符串
	 * @param list
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String SetToJson(Set<?> set) throws Exception{
		if(set==null||set.size()==0){
			return "[]";
		}
		StringBuilder jsonStr = new StringBuilder("[");
		for (Object object : set) {
			jsonStr.append(ObjectToJsonStr(object)+",");
		}
		if(jsonStr.lastIndexOf(",")==jsonStr.length()-1){
			   jsonStr.delete(jsonStr.length()-1,jsonStr.length());
		}
		jsonStr.append( "]" );
		return jsonStr.toString();
	}	
	
	/**
	 * 将JAVA的MAP转换成JSON字符串，
	 * 只转换第一层数据
	 * @param map
	 * @return
	 */
	public static String MapToJsonStr(Map<?,?> map) throws Exception{
		if(map==null||map.isEmpty()){
			return "null";
		}
		StringBuilder jsonStr = new StringBuilder("{");
		Set<?> keySet = map.keySet();
		for (Object key : keySet) {
			Object f=map.get(key);
			if(f.getClass() == long.class||f.getClass()==double.class||f.getClass()==float.class||
					f.getClass() == int.class||f.getClass()==boolean.class||f.getClass()==Integer.class||
					f.getClass() == Boolean.class||f.getClass()==Double.class||f.getClass()==Float.class||
					f.getClass() == Long.class||f.getClass()==String.class||f.getClass()==char.class){
				jsonStr.append( "\""+key+"\":\""+map.get(key)+"\"," );
			}else{
				jsonStr.append("\""+key+"\":\""+ObjectToJsonStr(key)+"\"," );
			}
		}
		if(jsonStr.lastIndexOf(",")==jsonStr.length()-1){
			   jsonStr.delete(jsonStr.length()-1,jsonStr.length());
		}
		jsonStr.append( "}" );
		return jsonStr.toString();
	}
	
}
