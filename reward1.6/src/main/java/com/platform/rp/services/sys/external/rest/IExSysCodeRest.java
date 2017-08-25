package com.platform.rp.services.sys.external.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/syscode")
@Produces(MediaType.APPLICATION_JSON)
public interface IExSysCodeRest {

	@GET
	@Path("findByParam")
	public RestfulResult findByParam(@QueryParam("jsonStr") String jsonStr);
	
	@GET
	@Path("findByCodeClass")
	public RestfulResult findByCodeClass(@QueryParam("codeClass") String codeClass);
	@GET
	@Path("getCodeInfoLists")
	public RestfulResult getCodeInfoList(@QueryParam("pCode")String parCode);

	/**
	 * 根据父ID获取所有的
	 * @param parCode
	 * @return
	 */
	@GET
	@Path("getAllList")
	public RestfulResult getAllList(@QueryParam("pCode")String parCode);
	/**
	 * 根据父ID获取所有的
	 * @param parCode
	 * @return
	 */
	@GET
	@Path("wap/getAllList")
	public RestfulResult getWapAllList(@QueryParam("pCode")String parCode);

	/**
	 * 清空缓存
	 * @return
	 */
	@GET
	@Path("cleanCache")
	public RestfulResult cleanCache();
}
