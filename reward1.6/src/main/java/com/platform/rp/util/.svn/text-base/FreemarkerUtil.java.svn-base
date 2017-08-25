package com.platform.rp.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	
	private static  final String TEMPLATE_NAME="/templates/property/common/content/content.html";
	
   /** 
     * @param templatePath 模板文件存放目录  
     * @param templateName 模板文件名称  
     * @param savepath 保存路径
     * @param savefile   保存文件名
     * @param root 数据模型根对象 
     */  
    public static void processFileTemplate(String templatePath, String templateName,String savePath,String savefile, Map<?,?> root)throws IOException,TemplateException{  
    	Writer out=null;
    	FileOutputStream fileout=null;
    	try {  
            Configuration config=new Configuration();  
            File file=new File(templatePath);  
            //设置要解析的模板所在的目录，并加载模板文件  
            config.setDirectoryForTemplateLoading(file);  
            //设置包装器，并将对象包装为数据模型  
            config.setObjectWrapper(new DefaultObjectWrapper());  
            config.setNumberFormat("#0.#####");
            config.setDateFormat("yyyy-MM-dd");
            config.setTimeFormat("HH:mm:ss");
            config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
              
            //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致  
            templateName=(templateName==null?TEMPLATE_NAME:templateName);
            Template template=config.getTemplate(templateName,"UTF-8");  
            //数据输出地址
            File savepath=new File(savePath);
            if(!savepath.exists()){
            	savepath.mkdirs();
            }
            fileout=new FileOutputStream(new File(savePath+"/"+savefile));
            out= new OutputStreamWriter(fileout);
          //合并数据模型与模板  
            template.process(root, out);  
        }finally{
        	if(out!=null){
	        	out.flush();  
	            out.close();  
        	}
        	if(fileout!=null){
        		fileout.close();  
        	}
        }  
          
    }   
    
   /** 
     * @param servletContext  web上下文对象
     * @param templateName 模板文件名称  
     * @param savepath 保存路径
     * @param savefile   保存文件名
     * @param root 数据模型根对象 
     */  
    public static void processServletContextTemplate(Object servletContext, String templateName,String savePath,String savefile, Map<?,?> root)throws IOException,TemplateException{  
      	Writer out=null;
    	FileOutputStream fileout=null;
    	try {  
            Configuration config=new Configuration();  
            //设置要解析的模板所在的目录，并加载模板文件  
            config.setServletContextForTemplateLoading(servletContext, "/");
            //设置包装器，并将对象包装为数据模型  
            config.setObjectWrapper(new DefaultObjectWrapper());  
            config.setNumberFormat("#0.#####");
            config.setDateFormat("yyyy-MM-dd");
            config.setTimeFormat("HH:mm:ss");
            config.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
              
            //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致  
            templateName=(templateName==null?TEMPLATE_NAME:templateName);
            Template template=config.getTemplate(templateName,"UTF-8");  
            //数据输出地址
            File savepath=new File(savePath);
            if(!savepath.exists()){
            	savepath.mkdirs();
            }
            fileout=new FileOutputStream(new File(savePath+"/"+savefile));
            out= new OutputStreamWriter(fileout);
            //合并数据模型与模板  
            template.process(root, out);  
        }finally{
        	if(out!=null){
	        	out.flush();  
	            out.close();  
        	}
        	if(fileout!=null){
        		fileout.close();  
        	}
        }  
    }  
    
    /** 
     * @param url 模板文件路径  
     * @param tagId 获取内容块
     * @throws IOException 
     */  
    public static String parseUrlTemplate(String url,String tagId) throws IOException{  
    	try {  
    		//请求网址
    		Document doc = Jsoup.connect(url).timeout(0).get(); 
    		String str=null;
    		if(StringUtils.isEmpty(tagId)){
    			str=doc.html();
    		}else{
    			str= doc.getElementById(tagId).html();
    		}
            return str;
        }catch(NullPointerException ex){
        	return "";
        }
    }  
    
  
}

