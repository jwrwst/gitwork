package com.platform.rp.services.qrcodeinfo.external.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.qrcodeinfo.core.dao.entity.ItFileInfoEntity;
import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/fileInfo")
@Produces(MediaType.APPLICATION_JSON)
public interface IExItFileInfoRest {

	@POST
	@Path("save")
	public RestfulResult save(ItFileInfoEntity entity) ;
	
	@POST
	@Path("getByOrderNum")
	public RestfulResult getByOrderNum(@QueryParam("orderNum") String orderNum) ;
	
}
