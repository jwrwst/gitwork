package com.platform.rp.services.sys.external.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/sysArea")
@Produces(MediaType.APPLICATION_JSON)
public interface iExSysAreaInfoRest {


	@GET
	@Path("wap/getAreaInfoList")
	public RestfulResult getAreaInfoList(@QueryParam("pCode")String parCode);
	

	@GET
	@Path("getAllList")
	public RestfulResult getAllList();
}
