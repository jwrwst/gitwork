package com.platform.rp.services.store.external.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/storeEmpDiv")
@Produces(MediaType.APPLICATION_JSON)
public interface IExBsStoreEmployeeDividedRest {

	@GET
	@Path("getDivEmpListByStoreId")
	public RestfulResult getDivEmpListByStoreId(@QueryParam("storeId")long storeId) ;
	
	@GET
	@Path("deleteDivManager")
	public RestfulResult deleteManager(@QueryParam("storeId")long storeId,@QueryParam("empId")long empId);

}
