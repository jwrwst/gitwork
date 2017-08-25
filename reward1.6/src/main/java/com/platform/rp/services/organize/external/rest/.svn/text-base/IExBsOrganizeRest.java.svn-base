package com.platform.rp.services.organize.external.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.organize.core.dao.entity.BsOrganizeEntity;
import com.platform.rp.util.response.result.RestfulResult;

/**
 * 店铺统计信息
 *
 */
@Path("/external/organize")
@Produces(MediaType.APPLICATION_JSON)
public interface IExBsOrganizeRest {


	//保存组织信息
	@POST
    @Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult save(BsOrganizeEntity entity);
	
	//保存组织信息
	@GET
    @Path("/remove")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult remove(@QueryParam("orgCode")String orgCode);
	
	//根据过滤条件查询店铺评价统计
	@GET
    @Path("/getList")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getList(@QueryParam("orgCode")String orgCode,@QueryParam("status") int status,@QueryParam("storeName") String storeName);
	

	/**
	 * 根据父ID查询   
	 * @param parentCode
	 * @return
	 */
	@POST
    @Path("/getListByParentCode")
	@Consumes(MediaType.APPLICATION_JSON)
	//public RestfulResult getListByParentCode(@QueryParam("parentCode")String parentCode,@QueryParam("schema")String schema);
	public RestfulResult getListByParentCode(Map<String,String> entity);
}
