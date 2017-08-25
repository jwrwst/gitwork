package com.wxboot.wx.menu.req;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable{
	private static final long serialVersionUID = 1L;
	//一级菜单
	private List<Button> button;
	public List<Button> getButton() {
		return button;
	}
	public void setButton(List<Button> button) {
		this.button = button;
	}
	
}
