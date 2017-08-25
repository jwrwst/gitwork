package com.platform.rp.services.employee.external.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.employee.core.dao.entity.BsEmployeeInfoEntity;
import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/empInfo")
@Produces(MediaType.APPLICATION_JSON)
public interface IExBsEmployeeInfoRest {

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult save(BsEmployeeInfoEntity entity);
	
	@POST
	@Path("/edit")
	public RestfulResult edit(BsEmployeeInfoEntity vo);
	
	@GET
    @Path("/getEmployeeInfo")
	public RestfulResult getEmployeeInfoByEmpId(@QueryParam("empId") long empID,@QueryParam("openId") String openId);
	
	@GET
    @Path("/wap/getEmployeeInfo")
	public RestfulResult getEmployeeInfo();
	
	@POST
    @Path("/toListPage")
	public RestfulResult toListPage(Map<String, String> params);
	
	@GET
    @Path("/removeEmployee")
	public RestfulResult removeEmployee(@QueryParam("empId")  long empID);
	
	@GET
    @Path("/getAllEmployee")
	public RestfulResult getAllEmployee(@QueryParam("orgCode")String orgCode,@QueryParam("storeId")String storeId);
}
