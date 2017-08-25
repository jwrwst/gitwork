package com.platform.rp.services.employee.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtRewardEntity;
import com.platform.rp.services.employee.external.vo.EmployeeIncomeBaseInfoVo;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;
import com.platform.rp.services.store.external.vo.StoreDynamicInfoVo;
import com.platform.rp.services.store.external.vo.StoreRankVo;

public interface BsEmployeeExtRewardDAO<T> extends BaseDAO<T>  {

	//根据店铺ID分页获取店员打赏信息
	public List<StoreRankVo>getPage(Map<String, Object> params);
		
	//根据店铺ID获取店铺今日打赏数据
	public StoreDynamicInfoVo getStoreDayDynamicByStoreId(Map<String, Object> params);
	
	//根据店铺ID获取店铺昨日打赏数据
	public StoreDynamicInfoVo getStoreYesterdayDynamicByStoreId(Map<String, Object> params);
	
	//根据店铺ID和店员ID获取店员打赏排名信息
	public EmployeeRankDetailVo getEmployeeRewardRankDetail(Map<String, Object> params);
	
	//根据店员empid获取店员收入页面基础信息
	public EmployeeIncomeBaseInfoVo getEmployeeIncomeBaseInfo(@Param("empId") long empID);
	
	//根据店铺ID和店员ID获取某店员打赏信息详情
	public List<BsEmployeeExtRewardEntity> getEmployeeReward(@Param("storeId") long storeID, @Param("empId") long empID, @Param("limit") int limit);
}
