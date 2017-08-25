package com.platform.rp.services.store.external.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;


/**
 * 店铺权限列表
 *
 */
@Path("/external/storeAuth")
@Produces(MediaType.APPLICATION_JSON)
public interface IExStoreAuthRest {

	@GET
	@Path("/getStoreAuthList")
	public RestfulResult getList(@QueryParam("groupCode")String groupCode);
	
}
