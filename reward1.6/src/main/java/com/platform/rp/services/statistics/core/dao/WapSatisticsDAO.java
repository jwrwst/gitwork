package com.platform.rp.services.statistics.core.dao;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.dao.BaseDAO;
import com.platform.rp.services.statistics.core.dao.entity.WapSatisticsEntity;

public interface WapSatisticsDAO  <T> extends BaseDAO<T>{
	/**
	 * 查询店员打赏排行
	 * @param params
	 *  #{storeId}
	 *  #{orgCode}
	 *  #{startTime} 
	 *  #{endTime}  
	 * @return
	 */
	public List<WapSatisticsEntity> findEmpRawardRanking(Map<String, Object> params);
	public int findEmpRawardRankingCount(Map<String, Object> params);
	/**
	 * 查询店员评分排行
	 * @param params
	 *  #{storeId}
	 *  #{orgCode}
	 *  #{startTime} 
	 *  #{endTime}  
	 * @return
	 */
	public List<WapSatisticsEntity> findEmpScoreRanking(Map<String, Object> params);
	public int findEmpScoreRankingCount(Map<String, Object> params);
	/**
	 * 查询店长提成排行
	 * @param params
	 * @return
	 */
	public List<WapSatisticsEntity> findStorManagerRanking(Map<String, Object> params);
	public int findStorManagerRankingCount(Map<String, Object> params);
	
	/**
	 * 区域经理（总监）排行
	 * @param params
	 * @return
	 */
	public List<WapSatisticsEntity> findMerManagerRanking(
			Map<String, String> params);
	public int findMerManagerRankingCount(Map<String, String> params);
}
