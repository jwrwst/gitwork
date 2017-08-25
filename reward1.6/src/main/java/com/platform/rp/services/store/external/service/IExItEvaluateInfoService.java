package com.platform.rp.services.store.external.service;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;
import com.platform.rp.services.store.core.dao.entity.ItEvaluateInfoEntity;
import com.platform.rp.services.store.external.vo.StoreEvaluateStatisticVo;
import com.platform.rp.services.store.external.vo.StorePeriodEvaluateScoreVo;

public interface IExItEvaluateInfoService {

	public void add(ItEvaluateInfoEntity vo);

	//根据过滤条件查询店铺评价统计
	public List<StoreEvaluateStatisticVo> getEvaluateStatisticByStoreID(String storeID, String filter,String startTime,String endTime);
	
	//根据过滤条件查询店铺评价统计
	public StorePeriodEvaluateScoreVo getPeriodEvaluateScoreByStoreID(String storeID, String filter,String startTime,String endTime);
	
	@SuppressWarnings({ "rawtypes" })
	//根据店铺ID分页获取店员评价信息
	public Page getPage(Page page, Map params) throws Exception;
	
	//根据店铺ID和店员ID获取店员本日/本周/本月评价信息详情
	public EmployeeRankDetailVo getEmployeeEvaluateRankDetail(String storeID, long empID);
}
