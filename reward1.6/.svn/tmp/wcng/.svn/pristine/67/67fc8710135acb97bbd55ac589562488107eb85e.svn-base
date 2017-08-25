package com.platform.rp.services.store.external.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/rewardInfo")
@Produces(MediaType.APPLICATION_JSON)
public interface IExItRewardInfoRest {

	@GET
	@Path("deleteByOrderNum")
	public RestfulResult deleteByOrderNum(@QueryParam("orderNum")String orderNum) ;
	
}
