package com.platform.rp.services.statistics.external.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.statistics.core.dao.entity.ComprehensiveSatisicsEntity;
import com.platform.rp.util.response.result.RestfulResult;

@SuppressWarnings("rawtypes")
@Path("/external/statistics/comprehensive")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface IComprehensiveSatisicsRest {

	/**
	 * 门店员工排行
	 * @param entity
	 * @return
	 */
	@POST
    @Path("/findStorManagerRanking")
	public RestfulResult findStorManagerRanking(Map<String,String> entity);
	/**
	 * 区域经理（总监）排行
	 * @param entity
	 * @return
	 */
	@POST
    @Path("/findMerManagerRanking")
	public RestfulResult findMerManagerRanking(Map<String,String> entity);
	
	/**
	 * 实时动态统计
	 * @param entity
	 * @return
	 */
	@POST
    @Path("/findRealTimeDynamicAll")
	public Object findRealTimeDynamicAll();
	
	/**
	 * 实时动态
	 * @return
	 */
	@POST
    @Path("/findRealTimeDynamic")
	public RestfulResult findRealTimeDynamic(Map<String,String> entity);
	
	/**
	 * 打赏金额趋势
	 * @return
	 */
	@POST
    @Path("/findCharStatistcs")
	public  List<ComprehensiveSatisicsEntity> findCharStatistcs(Map<String,String> entity);
	

	/**
	 * 报表页面统计所有的数据
	 * @param entity
	 * @return
	 */
	@POST
    @Path("/findCharStatistcsAll")
	@Consumes(MediaType.APPLICATION_JSON)
	public  ComprehensiveSatisicsEntity findCharStatistcsAll(Map<String,String> entity);
	
	/**
	 * 店铺排行
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findStoreRankingPage")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult findStoreRankingPage(Map params);
	
	/**
	 * 员工排行
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findStaffRankingPage")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult findStaffRankingPage(Map params);
	
	/**
	 * 按日期查询日志
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findLogsByData")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult findLogsByData(Map params);

	/**
	 * 按日期查询明细
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findLogsByDataDetail")
	@Consumes(MediaType.APPLICATION_JSON)
	public  List<ComprehensiveSatisicsEntity> findLogsByDataDetail(Map params);
	
	/**
	 * 门店汇总查询
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findStoreSummaryPage")
	@Consumes(MediaType.APPLICATION_JSON)
	public  RestfulResult findStoreSummaryPage(Map<String, Object> params);	
	/**
	 * 按日期查询明细
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findStoreSummaryDetail")
	@Consumes(MediaType.APPLICATION_JSON)
	public  List<ComprehensiveSatisicsEntity> findStoreSummaryDetail(Map<String, Object> params);
}
