package com.thinkgem.jeesite.common.pojo;

/** 
 * 复杂按钮（父按钮） 
 *  
 * @author WHW 
 * @date 2014年12月3日11:42:30 
 */ 
public class ComplexButton extends Button {
	private Button[] sub_button;  
	  
    public Button[] getSub_button() {  
        return sub_button;  
    }  
  
    public void setSub_button(Button[] sub_button) {  
        this.sub_button = sub_button;  
    }  
}
