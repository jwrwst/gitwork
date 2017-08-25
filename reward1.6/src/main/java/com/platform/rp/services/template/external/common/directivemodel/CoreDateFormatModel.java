package com.platform.rp.services.template.external.common.directivemodel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 日期格式转换函数
 * 
 *  @author tangjun
 *  create date : 2015年1月28日
 */
public class CoreDateFormatModel  implements TemplateMethodModelEx{
	
	private Logger log=Logger.getLogger(CoreDateFormatModel.class);

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		   String datelong = arguments.get(0).toString();  
	        String formatstr = arguments.get(1).toString();  
	        try{
	        	Date dt=new Date(Long.valueOf(datelong));
	        	SimpleDateFormat smf  = new SimpleDateFormat(formatstr);
	        	return smf.format(dt);
	        }catch(Exception e){
	        	log.error("日期转换异常", e);
	        	return "";
	        }
		
	}  
}
