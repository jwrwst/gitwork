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
@Path("/external/storeDynamic")
@Produces(MediaType.APPLICATION_JSON)
public interface IExStoreDynamicInfoRest {

	@POST
    @Path("/toListPage")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult toListPage(Map<String, String> params);
	
	@POST
    @Path("/page")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getAll(Map<String, String> params);
	
	@GET
    @Path("/getStoreListByEmpId")
	public abstract RestfulResult getStoreListByEmpId(@QueryParam("empId") long empID,@QueryParam("orgCode") String orgCode);
	
	@GET
    @Path("/getStoreDynamicByStoreId")
	public abstract RestfulResult getStoreDynamicByStoreId(@QueryParam("storeId") long storeID);
}
