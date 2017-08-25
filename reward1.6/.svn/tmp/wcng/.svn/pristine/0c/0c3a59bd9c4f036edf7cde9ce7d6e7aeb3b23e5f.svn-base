package com.platform.rp.services.store.external.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.store.core.dao.entity.BsStoreAuthDetailEntity;
import com.platform.rp.util.response.result.RestfulResult;


/**
 * 员工打赏日志
 *
 */
@Path("/external/storeAuthDetail")
@Produces(MediaType.APPLICATION_JSON)
public interface IExStoreAuthDetailRest {

	@POST
	@Path("/save")
	public abstract RestfulResult save(BsStoreAuthDetailEntity vo);
	
	@POST
	@Path("/edit")
	public abstract RestfulResult edit(BsStoreAuthDetailEntity vo);
	
	@GET
	@Path("/getStoreAuth")
	public RestfulResult getStoreAuthDetailByStoreId(@QueryParam("storeId")long storeId);
	
}
