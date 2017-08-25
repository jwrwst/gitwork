package com.platform.rp.services.customer.external.service;

import com.platform.rp.services.customer.core.dao.entity.BsCustomerInfoEntity;
import com.platform.rp.services.wechat.external.vo.LoginVo;


public interface IExBsCustomerInfoService {

	public BsCustomerInfoEntity save(LoginVo vo)throws Exception;
	
}
