package com.platform.rp.services.customer.external.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platform.rp.services.customer.core.dao.BsCustomerInfoDAO;
import com.platform.rp.services.customer.core.dao.entity.BsCustomerInfoEntity;
import com.platform.rp.services.customer.external.service.IExBsCustomerInfoService;
import com.platform.rp.services.wechat.external.vo.LoginVo;

@Service
public class ExBsCustomerInfoServiceImpl implements IExBsCustomerInfoService {

	@Autowired
	BsCustomerInfoDAO<BsCustomerInfoEntity> bsCustomerInfoDAO;
	
	public BsCustomerInfoEntity save(LoginVo vo)throws Exception{
		try {
			BsCustomerInfoEntity entity=bsCustomerInfoDAO.getInfoByOpenId(vo.getOpenId());
			if(null==entity){
				entity = new BsCustomerInfoEntity();
				entity.setCreatedtime(new Date());
				entity.setOpenId(vo.getOpenId());
				entity.setGender(vo.getSex());
				entity.setHeadUrl(vo.getHeadimgurl());
				entity.setName(vo.getNickname());
				entity.setSource("weixin");
				bsCustomerInfoDAO.save(entity);
			}
			return entity;
		} catch (Exception e) {
			throw e;
		}
	}
}
