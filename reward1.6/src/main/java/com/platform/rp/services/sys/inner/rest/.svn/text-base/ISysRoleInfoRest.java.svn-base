package com.platform.rp.services.sys.inner.rest;

import java.io.Reader;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/inner/sysRoleInfo")
// @Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ISysRoleInfoRest {

	@POST
	@Path("/page")
	public void getAll(Reader reader);

	/**
	 * 列表界面跳转
	 */
	@GET
	@Path("/toListPage")
	public void toListPage(Reader reader);

	/**
	 * 获取角色列表
	 * 
	 * @return
	 */
	@POST
	@Path("/list")
	public String getList();

	/**
	 * 根据编号获取对象
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@GET
	@Path("/get")
	public String get(@QueryParam("id") int id);

	/**
	 * 获取当前用户所拥有的权限
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/currentAuth")
	public String getCurrentAuth(@QueryParam("id") int id);

	/**
	 * @see 保存
	 * @return
	 */
	@POST
	@Path("/save")
	public String save(Reader reader);

	/**
	 * @see 删除
	 * @param id
	 * @param getid
	 * @return
	 */
	@POST
	@Path("/delete")
	public String delete(@FormParam("id") String id, @QueryParam("getid") String getid);

	/**
	 * @see 转向添加页面
	 */
	@GET
	@Path("/roleadd")
	public String roleadd();

}