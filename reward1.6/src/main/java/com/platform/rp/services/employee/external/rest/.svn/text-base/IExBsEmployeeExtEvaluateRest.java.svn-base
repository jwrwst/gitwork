package com.platform.rp.services.employee.external.rest;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.employee.core.dao.entity.BsEmployeeExtEvaluateEntity;
import com.platform.rp.util.response.result.RestfulResult;


/**
 * 员工打赏日志
 *
 */
@Path("/external/employeeExtEvaluate")
@Produces(MediaType.APPLICATION_JSON)
public interface IExBsEmployeeExtEvaluateRest {
	
	@POST
    @Path("/save")
	public RestfulResult save(BsEmployeeExtEvaluateEntity vo);
	
	@POST
    @Path("/edit")
	public RestfulResult edit(BsEmployeeExtEvaluateEntity vo);
	
//	@POST
//    @Path("/toListPage")
//	public RestfulResult toListPage(Map<String, String> params);
//	
//	@POST
//    @Path("/page")
//	public RestfulResult getAll(Map<String, String> params);
	
//	@POST
//    @Path("/getEmployeeEvaluateRankByStoreID")
//	//根据店铺ID获取店员评价排名
//	public RestfulResult getEmployeeEvaluateRankByStoreID(@QueryParam("storeId") long storeID, @QueryParam("filter") String filter);

//	@GET
//    @Path("/getEmployeeEvaluateRankDetail")
//	//根据店铺ID和店员ID获取店员评价信息详情
//	public RestfulResult getEmployeeEvaluateRankDetail(@QueryParam("storeId") long storeID, @QueryParam("empId") long empID);
}
