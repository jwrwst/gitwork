/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.weixin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinQrcode;
import com.thinkgem.jeesite.modules.weixin.dao.WeixinQrcodeDao;

/**
 * 微信二维码Service
 * @author WHW
 * @version 2016-08-18
 */
@Service
@Transactional(readOnly = true)
public class WeixinQrcodeService extends CrudService<WeixinQrcodeDao, WeixinQrcode> {

	public WeixinQrcode get(String id) {
		return super.get(id);
	}
	
	public List<WeixinQrcode> findList(WeixinQrcode weixinQrcode) {
		return super.findList(weixinQrcode);
	}
	
	public Page<WeixinQrcode> findPage(Page<WeixinQrcode> page, WeixinQrcode weixinQrcode) {
		return super.findPage(page, weixinQrcode);
	}
	
	@Transactional(readOnly = false)
	public void save(WeixinQrcode weixinQrcode) {
		super.save(weixinQrcode);
	}
	
	@Transactional(readOnly = false)
	public void delete(WeixinQrcode weixinQrcode) {
		super.delete(weixinQrcode);
	}
	
}