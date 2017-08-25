package com.platform.rp.services.waplogin.external.service;

import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItFileInfoEntity;




/**
 * 
 * @author Owner
 *
 */
public interface IExWapLoginService  {
	
	/**
	 * 生成二维码
	 * @param orderNum
	 * @return
	 * @throws Exception
	 */
	public ItFileInfoEntity uploadQrcode(String orderNum)throws Exception;
	
	/**
	 * 修改文件表
	 * @param orderNum
	 * @throws Exception
	 */
	public void updateItFileInfo(String orderNum)throws Exception;
	
}
