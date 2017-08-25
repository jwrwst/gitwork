package com.platform.rp.util.qrcode.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;




@Path("/qrcode")
public interface IQrCodeRest{


	@GET
	@Path("/")
	public void qrcode(@QueryParam("text") String txt);
	
}