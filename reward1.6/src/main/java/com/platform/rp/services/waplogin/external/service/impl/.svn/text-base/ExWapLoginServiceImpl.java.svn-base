package com.platform.rp.services.waplogin.external.service.impl;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItFileInfoEntity;
import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItQrcodeInfoEntity;
import com.platform.rp.services.qrcodeinfo.external.service.IExItFileInfoService;
import com.platform.rp.services.qrcodeinfo.external.service.IExItQrcodeInfoService;
import com.platform.rp.services.waplogin.external.service.IExWapLoginService;
import com.platform.rp.util.Constant;
import com.platform.rp.util.TinyUrlGenerater;


@Service
public class ExWapLoginServiceImpl  implements IExWapLoginService {

	Logger log = Logger.getLogger(ExWapLoginServiceImpl.class);
	
	@Autowired
	IExItFileInfoService exItFileInfoService;
	
	@Autowired
	IExItQrcodeInfoService exItQrcodeInfoService;

	
	public synchronized ItFileInfoEntity uploadQrcode(String orderNum)throws Exception{
		try {
			ItFileInfoEntity fileEntity=exItFileInfoService.getByOrderNum(orderNum);
			if(null == fileEntity){
				throw new Exception("文件不存在");
			}
			
			String[] qrcodeArr;
			//判断当前订单是否已经生成二维码
			List<ItQrcodeInfoEntity> list=exItQrcodeInfoService.getListByOrderNum(orderNum);		
			if(list.size()>0){
				qrcodeArr=new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					qrcodeArr[i] =list.get(i).getQrcode();
				}
				
				fileEntity.setQrcodeArr(qrcodeArr);
				
				return fileEntity;
			}
			
			//没有生成二维
			int gnum=fileEntity.getGnum();
			qrcodeArr=TinyUrlGenerater.generatorByUUID("321tips",gnum);
			ItQrcodeInfoEntity entity;
			Date dt=new Date();
			for (int i = 0; i < qrcodeArr.length; i++) {
				qrcodeArr[i] = fileEntity.getStoreId()+qrcodeArr[i];
				entity=new ItQrcodeInfoEntity();
				entity.setOrderNum(orderNum);
				entity.setQrcode(qrcodeArr[i]);
				entity.setState(Constant.NOTUSER);
				entity.setStoreId(fileEntity.getStoreId());
				entity.setUpdateTime(dt);
				exItQrcodeInfoService.save(entity);
			}
			fileEntity.setQrcodeArr(qrcodeArr);
			
			return fileEntity;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public void updateItFileInfo(String orderNum)throws Exception{
		try {
			ItFileInfoEntity fileEntity=exItFileInfoService.getByOrderNum(orderNum);
			if(null == fileEntity){
				throw new Exception("文件不存在");
			}
			//如果重复下载不修改
			if(fileEntity.getState()==Constant.USER){
				return;
			}
			fileEntity.setState(Constant.USER);
			fileEntity.setUpdateTime(new Date());
			exItFileInfoService.update(fileEntity);
		} catch (Exception e) {
			throw e;
		}
	}
}
