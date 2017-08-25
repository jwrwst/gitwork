package com.platform.rp.services.sys.external.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * <pre>：
 * 
 * Created Date： Jul 22, 2015 4:00:18 PM
 * 
 * Updator：
 * 
 * Updated Date：
 * 
 * Decription：
 * 
 * Version： 1.0.1
 * </pre>
 */
@Path("/external/webUserInfo")
@Produces(MediaType.APPLICATION_JSON)
public interface IWebUserInfoRest {
	
	/**
	 * 检查用户是否登录
	 * 
	 * @param reader
	 */
	@GET
    @Path("/checkLogin")
	public abstract String checkLogin(@QueryParam("ssoid")String ssoid);

}