package com.wxboot.web.userinfo.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxboot.web.userinfo.model.UserInfo;
import com.wxboot.web.userinfo.service.UserInfoService;
import com.wxboot.wx.framework.controller.BaseController;

/**
 * 家家帮
 * @author wang
 * 2017年4月7日 上午9:21:05
 * 类描述：
 */
@RestController
@RequestMapping(value = "userinfo", produces = "application/json; charset=UTF-8")
public class UserInfoController extends BaseController{
	@Resource
	private UserInfoService userInfoService;
	
	@RequestMapping(value = "index")
	public UserInfo index() {
		UserInfo userInfo = userInfoService.selectById("S7dU8PNB");
		if (userInfo != null) {
			//throw new SysException(CodeEnum.PERMISSION_EX);
		}
		return userInfo;
	}
	
	
	@RequestMapping(value = "insert")
	public UserInfo insert(@RequestBody UserInfo userInfo) {
		UserInfo user = userInfoService.insert(userInfo);
		return user;
	}
	
	
}

 