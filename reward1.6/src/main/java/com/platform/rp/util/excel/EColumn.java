package com.platform.rp.util.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EColumn {
	
	// 列索引
	int index() default 0;
	// 列名
	String title() default "";
	// 整列的格式
	EColumnType type() default EColumnType.TYPE_STRING;
	
	//宽度
	double width() default 0d;
	
	enum EColumnType{
		TYPE_STRING, TYPE_INT, TYPE_DOUBLE
	}
}
