package com.platform.rp.services.statistics.external.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.statistics.core.dao.entity.ComprehensiveSatisicsEntity;
import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/statistics/export")
public interface IExportStatisticsRest {
	/**
	 * 店铺排行
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findStoreRankingPageEx")
	public void findStoreRankingPage();
	
	/**
	 * 员工排行
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findStaffRankingPageEx")
	public void findStaffRankingPage();
	/**
	 * 按日期查询日志
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findLogsByDataEx")
	public void findLogsByData();
	
	/**
	 * 门店汇总查询
	 * @param params
	 * @return
	 */
	@POST
    @Path("/findStoreSummaryPageEx")
	public  void findStoreSummaryPage();	
}
