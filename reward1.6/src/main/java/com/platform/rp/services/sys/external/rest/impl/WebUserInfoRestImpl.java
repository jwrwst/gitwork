/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.platform.rp.services.sys.external.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.platform.rp.framework.mvcface.rest.BaseController;
import com.platform.rp.services.sys.external.rest.IWebUserInfoRest;
import com.platform.rp.services.sys.inner.service.ISysUserInfoService;
import com.platform.rp.util.spec.Base64;

/**
 * <pre>
 * Created Date： Jul 22, 2015 4:06:10 PM
 * 
 * Updator：
 * 
 * Updated Date：
 * 
 * Decription：
 * 
 * Version： 1.0.1
 * </pre>
 */
@Controller
public class WebUserInfoRestImpl extends BaseController implements IWebUserInfoRest {

	@Autowired
	ISysUserInfoService sysUserInfoService;
	
	@Override
	public String checkLogin(String ssoid) {
		String plaintext = new String(Base64.decode(ssoid));
		String[] pairs = plaintext.split(",");
		boolean abort = false;
		if(pairs.length == 2){
			
		}
		
		return String.valueOf(abort);
	}

}
