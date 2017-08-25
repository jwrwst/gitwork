package com.platform.rp.services.employee.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtEvaluateEntity;
import com.platform.rp.services.employee.external.vo.EmployeeIncomeBaseInfoVo;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;
import com.platform.rp.services.store.external.vo.StoreDynamicInfoVo;
import com.platform.rp.services.store.external.vo.StoreRankVo;

public interface BsEmployeeExtEvaluateDAO<T> extends BaseDAO<T>  {
	
	//根据店铺ID分页获取店员评价信息
	public List<StoreRankVo>getPage(Map<String, Object> params);
	
	//根据店铺ID获取店铺今日评价数据
	public StoreDynamicInfoVo getStoreDayDynamicByStoreId(Map<String, Object> params);
		
	//根据店铺ID获取店铺昨日评价数据
	public StoreDynamicInfoVo getStoreYesterdayDynamicByStoreId(Map<String, Object> params);
	
	//根据店铺ID和店员ID获取店员评价排名
	public EmployeeRankDetailVo getEmployeeEvaluateRankDetail(Map<String, Object> params);
	
	//根据店铺ID和店员ID获取某店员评价信息详情
	public List<BsEmployeeExtEvaluateEntity> getEmployeeEvaluate(@Param("storeId") long storeID, @Param("empId") long empID);

	//根据店员ID获取我的收入页面店员评星均值和评价总次数
	public EmployeeIncomeBaseInfoVo getEmployeeIncomeBaseInfo(long empId);
}
