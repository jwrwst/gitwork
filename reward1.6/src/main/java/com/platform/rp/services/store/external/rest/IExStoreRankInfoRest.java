package com.platform.rp.services.store.external.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;


/**
 * 店铺统计信息
 *
 */
@Path("/external/storeRank")
@Produces(MediaType.APPLICATION_JSON)
public interface IExStoreRankInfoRest {

	@POST
    @Path("/toListPage")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult toListPage(Map<String, String> params);
	
	@POST
    @Path("/page")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getAll(Map<String, String> params);
	
	@GET
    @Path("/getEmployeeRewardRankDetail")
	//根据店铺ID和店员ID获取店员打赏信息详情
	public RestfulResult getEmployeeRewardRankDetail(@QueryParam("storeId") long storeID, @QueryParam("empId") long empID);
	
	@GET
    @Path("/getEmployeeEvaluateRankDetail")
	//根据店铺ID和店员ID获取店员评价信息详情
	public RestfulResult getEmployeeEvaluateRankDetail(@QueryParam("storeId") long storeID, @QueryParam("empId") long empID);
}
