package com.platform.rp.services.statistics.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.statistics.core.dao.entity.ComprehensiveSatisicsEntity;
import com.platform.rp.util.response.result.RestfulResult;

public interface ComprehensiveSatisicsDAO <T> extends BaseDAO<T> {
//	public List<ComprehensiveSatisicsEntity> findRewardMemoyTrend(@Param("storeIds") long storeIds,
//			@Param("startTime") Date startTime,@Param("endTime") Date endTime);
	/**
	 * 实时动态
	 * @param entity
	 * @return
	 */
	public List<ComprehensiveSatisicsEntity> findRealTimeDynamicPage(Map<String,String> entity);
	/**
	 * 实时动态
	 * @param entity
	 * @return
	 */
	public int findRealTimeDynamicCount(Map<String,String> entity);
	/**
	 * 实时动态统计
	 * @param entity
	 * @return
	 */
	public ComprehensiveSatisicsEntity findRealTimeDynamicAll(Map<String,String> entity);
	/**
	 * 统计人数，次数，金额
	 * @param storeIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ComprehensiveSatisicsEntity> findCharStatistcs(@Param("storeIds") String[] storeIds,
			@Param("startTime") String startTime,@Param("endTime") String endTime);
	/**
	 * 统计所有门店的人数，次数，金额
	 * @param storeIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ComprehensiveSatisicsEntity> findCharStatistcsAllStore(@Param("storeIds") String[] storeIds,
			@Param("startTime") String startTime,@Param("endTime") String endTime);
	
	
	/**
	 * 统计所有的人数，次数，金额
	 * @param storeIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public ComprehensiveSatisicsEntity findAllCharStatistcs(@Param("storeIds") String[] storeIds,
			@Param("startTime") String startTime,@Param("endTime") String endTime);
	
	/**
	 * 所有的店长分成
	 * @param storeIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public ComprehensiveSatisicsEntity findAllShopownerDivided  (@Param("storeIds") String[] storeIds,
			@Param("startTime") String startTime,@Param("endTime") String endTime);
	/**
	 * 店铺排行
	 * @param storeIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ComprehensiveSatisicsEntity> findStoreRankingPage(Map<String, Object> params);

	/**
	 * 店铺排行数量
	 * @param params
	 * @return
	 */
	public int findStoreRankingCount(Map<String, Object> params);
	
	
	/**
	 * 员工排行
	 * @param storeIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<ComprehensiveSatisicsEntity> findStaffRankingPage(Map<String, Object> params);
	
	/**
	 * 员工排行数量
	 * @param storeIds
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int findStaffRankingCount(Map<String, Object> params);
	
	/**
	 * 按日期查询日志
	 * @param params
	 * @return
	 */
	public  List<ComprehensiveSatisicsEntity> findLogsByDataPage(Map<String, Object> params);	
	
	/**
	 * 按日期查询数量
	 * @param params
	 * @return
	 */
	public int findLogsByDataCount(Map<String, Object> params);
	
	/**
	 * 日志明细
	 * @param params
	 * @return
	 */
	public  List<ComprehensiveSatisicsEntity> findLogsByDataDetail(Map<String, Object> params);
	
	/**
	 * 门店汇总查询
	 * @param params
	 * @return
	 */
	public  List<ComprehensiveSatisicsEntity> findStoreSummaryPage(Map<String, Object> params);	
	
	/**
	 * 门店汇总查询数量
	 * @param params
	 * @return
	 */
	public int findStoreSummaryCount(Map<String, Object> params);
	/**
	 * 日志明细
	 * @param params
	 * @return
	 */
	public  List<ComprehensiveSatisicsEntity> findStoreSummaryDetail(Map<String, Object> params);
	
}
