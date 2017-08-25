package com.platform.rp.services.sys.inner.rest;

import java.io.Reader;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * <pre>
 * 
 * Created Date： 2015年7月2日 上午10:54:26
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
@Path("/inner/sysCodeInfo")
// @Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ISysCodeInfoRest {

	/**
	 * 跳转[代码字典]列表
	 * 
	 * @param reader
	 */
	@GET
	@Path("/toCodeListPage")
	public void toCodeListPage(Reader reader);

	/**
	 * 跳转[代码分类]列表
	 * 
	 * @param reader
	 */
	@POST
	@Path("/toClassifyListPage")
	public void toClassifyListPage(Reader reader);

	/**
	 * 跳转[代码字典管理]列表
	 * 
	 * @param reader
	 */
	@GET
	@Path("/toListPage")
	public void toListPage(Reader reader);

	/**
	 * 跳转[代码分类]维护页面
	 * 
	 * @return
	 */
	@GET
	@Path("/toClassify")
	public void toClassify(@QueryParam("id") int id);

	/**
	 * 维护[代码分类]信息
	 * 
	 * @return
	 */
	@POST
	@Path("/modifyClassify")
	public String modifyClassify(Reader reader);

	/**
	 * 删除[代码分类]信息
	 * 
	 * @return
	 */
	@POST
	@Path("/deleteClassify")
	public String deleteClassify(@QueryParam("id") int id);

	/**
	 * 跳转[代码]维护页面
	 * 
	 * @return
	 */
	@GET
	@Path("/toCode")
	public void toCode(@QueryParam("id") int id, @QueryParam("classifyid") int classifyid);

	/**
	 * 维护[代码]信息
	 * 
	 * @return
	 */
	@POST
	@Path("/modifyCode")
	public String modifyCode(Reader reader);

	/**
	 * 删除[代码]信息
	 * 
	 * @return
	 */
	@POST
	@Path("/deleteCode")
	public String deleteCode(@QueryParam("id") int id);
}