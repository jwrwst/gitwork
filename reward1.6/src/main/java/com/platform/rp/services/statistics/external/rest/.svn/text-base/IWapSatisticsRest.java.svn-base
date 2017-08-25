package com.platform.rp.services.statistics.external.rest;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;
/**
 * 客户端统计
 * @author dd
 *
 */
@Path("/external/statistics/wap")
@Produces(MediaType.APPLICATION_JSON)
public interface IWapSatisticsRest {

	/**
	 * @param entity
	  	searchType	查询类型	0:服务员打赏；	1：服务员评分	2：店长分成
		storeType	类型		0:本人所在门店；1 商家
		storeId		门店ID
		dateType	时间范围	0:今天，1昨天，2：本周，3：上周，4：本月，5上月
		empId		员 工ID   如果有存在员工ID  storeType、storeId无效
	 * @return
	 */
	@POST
    @Path("/findStorManagerRanking")
	public RestfulResult findStorManagerRanking(Map<String,Object> entity);
	
}
