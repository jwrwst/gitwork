package com.platform.rp.services.organize.external.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.organize.external.vo.BsMerchantsVo;
import com.platform.rp.util.response.result.RestfulResult;

/**
 * 店铺统计信息
 *
 */
@Path("/external/merchantsReward")
@Produces(MediaType.APPLICATION_JSON)
public interface IExBsMerchantsRewardRest {


	//根据过滤条件查询店铺评价统计
	@POST
    @Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult save(BsMerchantsVo vo);
	
	//根据过滤条件查询店铺评价统计
	@GET
    @Path("/getList")
	@Consumes(MediaType.APPLICATION_JSON)
	public abstract RestfulResult getList(@QueryParam("orgCode")String orgCode);
	
	//根据组织编号获取赏金分配店铺列表
	@GET
    @Path("/getRewardStoreList")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult getRewardStoreList(@QueryParam("orgCode")String orgCode);
	
}
