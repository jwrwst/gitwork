/**
 * 
 */
package com.platform.rp.services.qrcodeinfo.external.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.qrcodeinfo.core.dao.ItFileInfoDAO;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItFileInfoEntity;
import com.platform.rp.services.qrcodeinfo.external.service.IExItFileInfoService;
import com.platform.rp.util.Constant;
import com.platform.rp.util.Properties;
import com.platform.rp.util.TinyUrlGenerater;


@Service
public class ExItFileInfoServiceImpl implements IExItFileInfoService {

	
	
	@Autowired
	ItFileInfoDAO<ItFileInfoEntity> itFileInfoDAO;
	
	@Autowired
	Properties properties;
	

	public String save(ItFileInfoEntity entity)throws Exception{
		try {
			
			Date dt=new Date();
			entity.setCreatedTime(dt);
			entity.setState(Constant.NOTUSER);
			List<ItFileInfoEntity> list=itFileInfoDAO.getList(entity);
			if(list.size()>0){
				return list.get(0).getFileUrl();
			}			
			entity.setUpdateTime(dt);			
			String orderNum = TinyUrlGenerater.generatorByUUID();
			entity.setOrderNum(orderNum);
			String fileUrl=properties.host+"/rs/wap/uploadfile/"+orderNum+".zip";
			entity.setFileUrl(fileUrl);
			itFileInfoDAO.save(entity);
			return fileUrl;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public String update(ItFileInfoEntity entity)throws Exception{
		try {
			itFileInfoDAO.update(entity);
			return "";
		} catch (Exception e) {
			throw e;
		}
	}
	
	public ItFileInfoEntity getByOrderNum(String orderNum)throws Exception{
		try {			
			return itFileInfoDAO.getByOrderNum(orderNum);
		} catch (Exception e) {
			throw e;
		}
	}
	


	
}
