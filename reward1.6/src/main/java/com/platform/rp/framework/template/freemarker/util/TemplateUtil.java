package com.platform.rp.framework.template.freemarker.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.platform.rp.framework.template.freemarker.cache.StreamTemplateLoader;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 模板工具类
 * 
 *  @author tangjun
 *  create date : 2015年3月11日
 */
public class TemplateUtil {
	
   /** 
     * @param reader 输入流
     * @param out   输入流
     * @param root 数据模型根对象 
     * @param encode 编码
     */  
    public static void processStreamTemplate(Map<?,?> root,Reader reader,Writer out,String encode)throws IOException,TemplateException{  
    	String templateName="FTL";
    	try {  
            Configuration config=new Configuration();  
            //设置要解析的模板所在的目录，并加载模板文件  
            config.setTemplateLoader(new StreamTemplateLoader(templateName, reader));
            //设置包装器，并将对象包装为数据模型  
            config.setObjectWrapper(new DefaultObjectWrapper());  
            config.setNumberFormat("#0.#####");
            config.setDateFormat("yyyy-MM-dd");
            config.setTimeFormat("HH:mm:ss");
            config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
              
            //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致  
            Template template=config.getTemplate(templateName,encode);  
            //合并数据模型与模板  
            template.process(root, out);  
        }finally{
        	if(reader!=null){
        		reader.close();  
        	}
        }  
          
    }   
    
    /** 
     * @param reader 输入流
     * @param out   输入流
     * @param root 数据模型根对象 
     * @param encode 编码
     */  
    public static void processStreamTemplate(Map<?,?> root,Reader reader,Writer out)throws IOException,TemplateException{ 
    	processStreamTemplate(root, reader, out,"UTF-8");
    }
    
    
    public static void main(String[] args) {
    	
		try {
	    	
			Reader reader=new StringReader("${abc}");
			Writer out=new StringWriter();
			Map<String, String> map=new HashMap<String, String>();
			map.put("abc", "ilove");
			
			processStreamTemplate(map, reader, out);
			
			System.out.println(out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
  
}

