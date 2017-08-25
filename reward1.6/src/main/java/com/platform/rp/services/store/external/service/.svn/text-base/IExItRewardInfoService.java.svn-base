package com.platform.rp.services.store.external.service;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.employee.external.vo.EmployeeRankDetailVo;
import com.platform.rp.services.store.core.dao.entity.ItRewardInfoEntity;
import com.platform.rp.services.store.external.vo.StoreRewardStatisticVo;

public interface IExItRewardInfoService {

	public void add(ItRewardInfoEntity vo);

	//根据过滤条件查询店铺打赏统计
	public List<StoreRewardStatisticVo> getStoreRewardStatisticByStoreID(String storeID, String filter,String startTime,String endTime);
	
	@SuppressWarnings({ "rawtypes" })
	//根据店铺ID分页获取店员打赏信息
	public Page getPage(Page page, Map params) throws Exception;
	
	/**
	 * 根据订单编号删除打赏纪录
	 * @param orderNum
	 * @throws Exception
	 */
	public void deleteByOrderNum(String orderNum)throws Exception;
	
	/**
	 * 根据订单号获取打赏纪录
	 * @param orderNum
	 * @return
	 * @throws Exception
	 */
	public ItRewardInfoEntity getRewardInfoByOrderNum(String orderNum)throws Exception;
	
	//根据店铺ID和店员ID获取店员本日/本周/本月打赏信息详情
	public EmployeeRankDetailVo getEmployeeRewardRankDetail(long storeID, long empID);
}
