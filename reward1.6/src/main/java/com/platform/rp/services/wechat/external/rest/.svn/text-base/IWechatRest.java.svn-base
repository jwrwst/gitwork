package com.platform.rp.services.wechat.external.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;




@Path("/wechat")
@Produces(MediaType.APPLICATION_JSON)
public interface IWechatRest{

	@GET
	@Path("/getWxParam")
	public String getWxParam(@QueryParam("url") String url);
	
	@POST
	@Path("/getWxPayInit")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getWxPayInit(Map<String, String> map);
	
	@GET
	@Path("/getAliPayInit")
	public String getAliPayInit(@QueryParam("empId") String empId,@QueryParam("storeCode") String storeCode,@QueryParam("customerKey") String customerKey,
			@QueryParam("jobNumber") String jobNumber,@QueryParam("userPhone") String userPhone);
	
	
	@GET
	@Path("/goBaseLogin")
	public void goBaseLogin();
	
	@GET
	@Path("/oauthuserInfo")
	public void oAuthUserInfo();
		
	@GET
	@Path("/uploadImg")
	public String uploadImg(@QueryParam("serverId") String serverId,@QueryParam("empId") long empId);
}