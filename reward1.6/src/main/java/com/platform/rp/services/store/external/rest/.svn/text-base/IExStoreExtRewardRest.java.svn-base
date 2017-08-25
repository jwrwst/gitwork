package com.platform.rp.services.store.external.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.store.core.dao.entity.BsStoreExtRewardEntity;
import com.platform.rp.util.response.result.RestfulResult;


/**
 * 员工打赏日志
 *
 */
@Path("/external/storeExtReward")
@Produces(MediaType.APPLICATION_JSON)
public interface IExStoreExtRewardRest {

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult save(BsStoreExtRewardEntity vo);
	
	@POST
	@Path("/edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult edit(BsStoreExtRewardEntity vo);
	
	@GET
	@Path("/getStoreExtReward")
	public abstract RestfulResult getStoreExtReward(@QueryParam("storeId") long storeId);
}
