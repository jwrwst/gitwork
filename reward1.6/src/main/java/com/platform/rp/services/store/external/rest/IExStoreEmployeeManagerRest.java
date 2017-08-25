package com.platform.rp.services.store.external.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;

/**
 * 店铺人员管理
 *
 */
@Path("/external/storeEmpManager")
@Produces(MediaType.APPLICATION_JSON)
public interface IExStoreEmployeeManagerRest {

	//根据店铺ID查询店铺店长和店员数
	@GET
    @Path("/getStoreEmpCountByStoreID")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getStoreEmpCountByStoreID(@QueryParam("storeId") long storeID);
	
	//根据店铺ID查询店铺店长信息
	@GET
    @Path("/getStoreKeeperByStoreID")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getStoreKeeperByStoreID(@QueryParam("storeId") long storeID);
	
	//根据店铺ID查询店员信息
	@GET
    @Path("/getStoreAssistantByStoreID")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getStoreAssistantByStoreID(@QueryParam("storeId") long storeID);
}
