package com.platform.rp.services.employee.external.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.store.core.dao.entity.ItEvaluateInfoEntity;
import com.platform.rp.services.store.core.dao.entity.ItRewardInfoEntity;
import com.platform.rp.util.response.result.RestfulResult;


/**
 * 员工打赏日志
 *
 */
@Path("/external/employeeHomepage")
@Produces(MediaType.APPLICATION_JSON)
public interface IExEmployeeHomepageRest {
	
	@GET
    @Path("/getEmpHomepageBaseInfoByEmpId")
	public RestfulResult getEmpHomepageBaseInfoByEmpId(@QueryParam("empId") long empID);
	
	@POST
    @Path("/addEvaluate")
	//顾客评价店员
	public RestfulResult addEvaluate(ItEvaluateInfoEntity itEvaluateInfo);
		
	@POST
    @Path("/addReward")
	//顾客打赏店员
	public RestfulResult addReward(ItRewardInfoEntity itRewardInfo);
}
