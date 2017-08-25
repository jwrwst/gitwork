package com.platform.rp.services.organize.external.service;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.services.organize.core.dao.entity.BsMerchantsInfoEntity;


public interface IExBsMerchantsEmployeeService {

    public Page getPage(Map params) throws Exception;
	
	public void save(BsMerchantsEmployeeEntity entity) throws Exception;
	
	public List<BsMerchantsEmployeeEntity> getList(String orgCode)throws Exception;
	
	public void deleteMerchantsEmployee(String orgCode,long empId)throws Exception;

	public void save(BsEmployeeInfoEntity eliv,
			BsMerchantsInfoEntity merchantsInfoEntity);

	/**
	 * 根据openId,组织CODE获取数量，验证是否存在
	 */
	public int getCountByOpenIdAndOrgCode(String openId, String orgCode);
}
