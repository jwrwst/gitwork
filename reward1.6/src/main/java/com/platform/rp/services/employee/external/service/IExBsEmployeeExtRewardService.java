package com.platform.rp.services.employee.external.service;

import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtRewardEntity;
import com.platform.rp.services.employee.external.vo.EmployeeIncomeBaseInfoVo;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;

public interface IExBsEmployeeExtRewardService {

	@SuppressWarnings({ "rawtypes" })
	//根据店铺ID分页获取店员打赏信息
	public Page getPage(Page page, Map params) throws Exception;

	public void add(BsEmployeeExtRewardEntity vo);
	
	public void update(BsEmployeeExtRewardEntity vo);
		
	//根据店铺ID和店员ID获取店员打赏信息详情
	public EmployeeRankDetailVo getEmployeeRewardRankDetail(long storeID, long empID);
	
	//根据店员empid获取店员收入页面基础信息
	public EmployeeIncomeBaseInfoVo getEmployeeIncomeBaseInfo(long empID);
}
