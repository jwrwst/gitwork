package com.platform.rp.services.store.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;
import com.platform.rp.services.employee.external.vo.EmployeeRankVo;
import com.platform.rp.services.store.core.dao.entity.ItRewardInfoEntity;
import com.platform.rp.services.store.external.vo.StoreRewardStatisticVo;

public interface ItRewardInfoDAO<T> extends BaseDAO<T> {
	
	//根据过滤条件查询店铺打赏统计
	public List<StoreRewardStatisticVo> getStoreRewardStatisticByStoreID(Map<String, Object> params);

	//根据店铺ID和店员ID分页获取店员打赏信息
	public List<EmployeeRankVo>getPage(Map<String, Object> params);
	
	/**
	 * 根据订单编号删除纪录
	 * @param orderNum
	 */
	public void deleteByOrderNum(@Param("orderNum") String orderNum);
	
	/**
	 * 根据订单编号查询纪录
	 * @param orderNum
	 */
	public ItRewardInfoEntity getRewardInfoByOrderNum(@Param("orderNum") String orderNum);
	
	//根据店铺ID和店员ID获取店员打赏排名信息
	public EmployeeRankDetailVo getEmployeeRewardRankDetail(Map<String, Object> params);
	
}
