package com.platform.rp.services.organize.inner.rest;

import java.io.Reader;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/inner/organize")
@Produces(MediaType.APPLICATION_JSON)
public interface IBsOrganizeRest {


	/**
	 * @see 列表分页
	 * @param page
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("/toPagePost")
	public void getOrganizeInfoPage(Reader reader) throws Exception;

	@GET
	@Path("/toListPage")
	public abstract void toListPage(Reader reader);
	/**
	 * 修改信息
	 * @param bsOrganizeEntity
	 * @throws Exception
	 */
	@POST
	@Path("/update")
	public String update(Reader reader)
			throws Exception;

	/**
	 * 查询详细信息
	 * @param orgCode
	 * @return
	 */
	@GET
	@Path("/getMerchantsInfo")
	public void getMerchantsInfo(@QueryParam("orgCode")String orgCode);
	
	/**
	 * 批量修改信息
	 * @param bsOrganizeEntity
	 * @throws Exception
	 */
	@POST
	@Path("/updateList")
	public String updateList(@QueryParam("orgCodes")String orgCodes,@QueryParam("status")int status,@QueryParam("remark") String remark);
	
	
	
}