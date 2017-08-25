package com.platform.rp.services.statistics.external.service;

import java.util.List;
import java.util.Map;

import com.platform.rp.framework.mvcface.vo.Page;
import com.platform.rp.services.statistics.core.dao.entity.ComprehensiveSatisicsEntity;
import com.platform.rp.util.response.result.RestfulResult;
/**
 * 综合统计
 * @author Administrator
 *
 */
@SuppressWarnings("rawtypes")
public interface IComprehensiveSatisicsService {
	public ComprehensiveSatisicsEntity findRealTimeDynamicAll(Map<String,String> entity);
	
	/**
	 * 实时动态
	 * @param entity
	 * @return
	 */
	public RestfulResult findRealTimeDynamic(Map<String,String> entity);
	
	/**
	 * 打赏金额趋势
	 * @return
	 */
	public  List<ComprehensiveSatisicsEntity> findCharStatistcs(Map<String,String> entity);
	

	/**
	 * 报表页面统计所有的数据
	 * @param entity
	 * @return
	 */
	public  ComprehensiveSatisicsEntity findCharStatistcsAll(Map<String,String> entity);
	
	/**
	 * 店铺排行
	 * @param params
	 * @return
	 */
	public Page findStoreRankingPage(Map params);
	
	/**
	 * 员工排行
	 * @param params
	 * @return
	 */
	public Page findStaffRankingPage(Map params);
	
	/**
	 * 按日期查询
	 * @param params
	 * @return
	 */
	public Page findLogsByData(Map params);
	
	/**
	 * 按日期查询明细
	 * @param params
	 * @return
	 */
	public  List<ComprehensiveSatisicsEntity> findLogsByDataDetail(Map params);
	
	/**
	 * 门店汇总查询
	 * @param params
	 * @return
	 */
	public  Page findStoreSummaryPage(Map<String, Object> params);	
	
	public  List<ComprehensiveSatisicsEntity> findStoreSummaryDetail(Map<String, Object> params);
}
