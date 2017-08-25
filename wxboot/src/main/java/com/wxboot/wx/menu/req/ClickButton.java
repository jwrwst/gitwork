package com.wxboot.wx.menu.req;

public class ClickButton extends Button{
	private static final long serialVersionUID = 1L;
	//Click类型菜单key
	private String key;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	/**初始化方法*/
	public static ClickButton initClickButton(String name, String type, String key){
		ClickButton button = new ClickButton();
		button.setName(name);
		button.setType(type);
		button.setKey(key);
		return button;
	}
	
}
