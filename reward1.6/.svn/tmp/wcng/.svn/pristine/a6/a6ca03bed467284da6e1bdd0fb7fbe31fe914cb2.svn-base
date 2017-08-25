package com.platform.rp.services.store.external.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;

/**
 * 店铺统计信息
 *
 */
@Path("/external/storeStatistic")
@Produces(MediaType.APPLICATION_JSON)
public interface IExStoreStatisticInfoRest {


	//根据过滤条件查询店铺评价统计
	@GET
    @Path("/getStoreStatisticInfoByStoreID")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getStoreStatisticInfoByStoreID(@QueryParam("storeId") long storeID, @QueryParam("filter") String filter);
	//根据过滤条件查询店铺评价统计
	@GET
	@Path("/getStoreStatisticInfoByOrgCode")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getStoreStatisticInfoByOrgCode(@QueryParam("orgCode")String orgCode,
			@QueryParam("startTime")String startTime,@QueryParam("endTime")String endTime);
}
