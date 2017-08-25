package com.platform.rp.services.waplogin.external.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;


@Path("/wap")
@Produces(MediaType.APPLICATION_JSON)
public interface IExWapLoginRest{


	@GET
	@Path("/uploadfile/{orderNum}.zip")
	public RestfulResult uploadQrcode(@PathParam("orderNum") String orderNum);
	
	@GET
	@Path("/uploadlogofile/{orderNum}.zip")
	public RestfulResult uploadLogoQrcode(@PathParam("orderNum") String orderNum);
	
}