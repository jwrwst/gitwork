package com.thinkgem.jeesite.common.pojo;

/** 
 * view类型的菜单 
 *  
 * @author WHW 
 * @date 2014年12月4日12:11:10 
 */ 
public class ViewButton extends Button {
	private String type;  
    private String url;  
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getUrl() {  
        return url;  
    }  
  
    public void setUrl(String url) {  
        this.url = url;  
    }  
}
