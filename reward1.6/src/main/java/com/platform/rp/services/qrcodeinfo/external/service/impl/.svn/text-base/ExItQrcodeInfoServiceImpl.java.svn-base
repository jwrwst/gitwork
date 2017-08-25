/**
 * 
 */
package com.platform.rp.services.qrcodeinfo.external.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.qrcodeinfo.core.dao.ItQrcodeInfoDAO;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItQrcodeInfoEntity;
import com.platform.rp.services.qrcodeinfo.external.service.IExItQrcodeInfoService;


@Service
public class ExItQrcodeInfoServiceImpl implements IExItQrcodeInfoService {

	
	
	@Autowired
	ItQrcodeInfoDAO<ItQrcodeInfoEntity> itQrcodeInfoDAO;
	

	public ItQrcodeInfoEntity getByQrcode(String qrcode,long storeId){
		try {
			return itQrcodeInfoDAO.getByQrcode(qrcode, storeId);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<ItQrcodeInfoEntity> getListByOrderNum(String orderNUm){
		try {
			return itQrcodeInfoDAO.getListByOrderNum(orderNUm);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void save(ItQrcodeInfoEntity entity)throws Exception{
		try {
			itQrcodeInfoDAO.save(entity);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void update(ItQrcodeInfoEntity entity)throws Exception{
		try {
			itQrcodeInfoDAO.update(entity);
		} catch (Exception e) {
			throw e;
		}
	}

	
}
