package com.platform.rp.subsystem.dwz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class DwzClient {
	static Logger log = Logger.getLogger(DwzClient.class);
	
	private static DwzClient dwzClient=null;
	
	public static DwzClient getInstance(){
		if(null==dwzClient){
			dwzClient=new DwzClient();
		}
		return dwzClient;
	}
	
	public String generate(String serverUrl,String generateUrl) {               
		  /** 网络的url地址 */        
	    URL url = null;        
	        /**//** 输入流 */   
	    BufferedReader in = null;   
	    StringBuffer sb = new StringBuffer();   
	    String newUrl = serverUrl+"/?g="+generateUrl;
	    try{     
	     url = new URL(newUrl);
	     in = new BufferedReader( new InputStreamReader(url.openStream(),"UTF-8") );   
	     String str = null;    
	     while((str = in.readLine()) != null) {    
	      sb.append( str );     
	            }     
	        } catch (Exception ex) {   
	            
	        } finally{    
	         try{             
	          if(in!=null) {  
	           in.close();     
	                }     
	            }catch(IOException ex) {      
	            }     
	        }     
	        String result =sb.toString();     
	        try {
				JSONObject json =JSONObject.fromObject(result);
				int flag = (Integer) json.get("flag");
				if( flag != 1){
					JSONObject data =(JSONObject) json.get("data");
					return (String) data.get("code");
				}
			} catch (JSONException e) {
				log.error(e);
			}    	        
	        return null;    
	 }   
	
}
