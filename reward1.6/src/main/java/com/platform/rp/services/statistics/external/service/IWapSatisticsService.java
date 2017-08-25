package com.platform.rp.services.statistics.external.service;

import java.util.Map;

import com.platform.rp.util.response.result.RestfulResult;

@SuppressWarnings({ "rawtypes" })
public interface IWapSatisticsService {
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public RestfulResult findEmpScoreRanking(Map params);
	/**
	 * 店员打赏排行
	 * @param params
	 * @return
	 */
	public  RestfulResult findEmpRanking(Map params);
	
	/**
	 * 店长分成排行
	 * @param params
	 * @return
	 */
	public  RestfulResult findStorManagerRanking(Map params);
	
	/**
	 * 区域经理排行
	 * @param entity
	 * @return
	 */
	public RestfulResult findMerManagerRanking(Map entity);
}
