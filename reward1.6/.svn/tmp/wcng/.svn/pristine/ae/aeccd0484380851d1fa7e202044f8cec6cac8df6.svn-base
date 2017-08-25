package com.platform.rp.services.store.external.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/storeEmp")
@Produces(MediaType.APPLICATION_JSON)
public interface IExBsStoreEmpRest {

	@GET
	@Path("getEmpListByStoreId")
	public RestfulResult getEmpListByStoreId(@QueryParam("storeId")long storeId,@QueryParam("filter")String filter) ;
	
	@GET
	@Path("deleteManager")
	public RestfulResult deleteManager(@QueryParam("storeId")long storeId,@QueryParam("empId")long empId);

	//获取区域管理人员
	@GET
    @Path("/getAreaManagerByStoreID")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getAreaManagerByStoreID(@QueryParam("storeId") long storeID);
}
