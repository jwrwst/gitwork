package com.platform.rp.services.employee.external.rest;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;


/**
 * 员工打赏日志
 *
 */
@Path("/external/employeeIncome")
@Produces(MediaType.APPLICATION_JSON)
public interface IExEmployeeIncomeRest {
	@POST
    @Path("/toListPage")
	public RestfulResult toListPage(Map<String, String> params);
	
	@POST
    @Path("/page")
	public RestfulResult getAll(Map<String, String> params);
	
	@GET
    @Path("/getEmployeeIncomeBaseInfo")
	//根据店员empid获取店员收入页面基础信息
	public RestfulResult getEmployeeIncomeBaseInfo(@QueryParam("empId") long empID);
}
