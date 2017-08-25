package com.platform.rp.services.store.core.dao;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;
import com.platform.rp.services.employee.external.vo.EmployeeRankVo;
import com.platform.rp.services.store.external.vo.StoreEvaluateStatisticVo;
import com.platform.rp.services.store.external.vo.StorePeriodEvaluateScoreVo;

public interface ItEvaluateInfoDAO<T> extends BaseDAO<T> {
	
	//根据过滤条件查询店铺评价统计
	public List<StoreEvaluateStatisticVo> getEvaluateStatisticByStoreID(Map<String, Object> params);
	
	//根据过滤条件查询店铺评价统计
	public StorePeriodEvaluateScoreVo getPeriodEvaluateScoreByStoreID(Map<String, Object> params);

	//根据店铺ID和店员ID分页获取店员评价信息
	public List<EmployeeRankVo>getPage(Map<String, Object> params);
	
	//根据店铺ID和店员ID获取店员评价排名
	public EmployeeRankDetailVo getEmployeeEvaluateRankDetail(Map<String, Object> params);
}
