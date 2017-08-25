package com.platform.rp.services.organize.external.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.organize.core.dao.entity.BsMerchantsEmployeeEntity;
import com.platform.rp.util.response.result.RestfulResult;

/**
 * 店铺统计信息
 *
 */
@Path("/external/merchantsEmployee")
@Produces(MediaType.APPLICATION_JSON)
public interface IExBsMerchantsEmployeeRest {


	//保存组织信息
	@POST
    @Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult save(BsMerchantsEmployeeEntity entity);
	
	//根据过滤条件查询店铺评价统计
	@GET
    @Path("/getList")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getList(@QueryParam("orgCode")String orgCode);
	
	
	//根据过滤条件查询店铺评价统计
	@POST
    @Path("/getPage")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getPage(Map map);
	
	@GET
    @Path("/deleteMerchantsEmployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult deleteMerchantsEmployee(@QueryParam("orgCode")String orgCode,@QueryParam("empId")long empId);
	
}
