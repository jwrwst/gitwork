package com.platform.rp.services.employee.external.service;

import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtEvaluateEntity;
import com.platform.rp.services.employee.external.vo.EmployeeIncomeBaseInfoVo;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;

public interface IExBsEmployeeExtEvaluateService {

	@SuppressWarnings({ "rawtypes" })
	public Page getPage(Page page, Map params) throws Exception;

	public void add(BsEmployeeExtEvaluateEntity vo);
	
	public void update(BsEmployeeExtEvaluateEntity vo);
	
	//根据店铺ID和店员ID获取店员评价信息详情
	public EmployeeRankDetailVo getEmployeeEvaluateRankDetail(long storeID, long empid);
	
	//根据店员ID获取我的收入页面店员评星均值和评价总次数
	public EmployeeIncomeBaseInfoVo getEmployeeIncomeBaseInfo(long empId);
}
