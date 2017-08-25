package com.platform.rp.services.sys.inner.rest;

import java.io.Reader;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.sys.inner.vo.SysUserInfoVo;

@Path("/inner/sysUserInfo")
@Produces(MediaType.APPLICATION_JSON)
public interface ISysUserInfoRest {

	@POST
	@Path("/getSysUserInfoList")
	public abstract void getAll(Reader reader);

	@GET
	@Path("/toListPage")
	public abstract void toListPage(Reader reader);

	/**
	 * 用户登陆
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@POST
	@Path("/login")
	public abstract SysUserInfoVo login(@FormParam("name") String name, @FormParam("password") String password);

	/**
	 * 前端用户登入
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@POST
	@Path("/userLogin")
	public void userLogin(@FormParam("username") String username, @FormParam("password") String password);

	/**
	 * 判断是否用户名密码正确
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@POST
	@Path("/isExist")
	public abstract SysUserInfoVo getSysUserInfo(@FormParam("name") String name, @FormParam("password") String password);

	/**
	 * 跳转维护界面(包括：新增、修改)
	 * 
	 * @return
	 */
	@GET
	@Path("/toPage")
	public abstract String toPage(@QueryParam("id") int id);

	/**
	 * 重置密码
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@POST
	@Path("/resetPwd")
	public abstract String updatePwd(@QueryParam("id") int id);

	/**
	 * 保存
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@POST
	@Path("/save")
	public abstract String save(Reader reader);

	/**
	 * 删除用户名密码正确
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@POST
	@Path("/delete")
	public abstract String delete(@FormParam("id") String id, @QueryParam("getid") String getid);

	/**
	 * 注销当前用户
	 * 
	 * @return
	 */
	@POST
	@Path("/logout")
	public abstract void logout();

	/**
	 * 获取员工信息
	 * 
	 * @return
	 */
	@POST
	@Path("/toEmployeePagePost")
	public abstract void toEmployeePagePost(Reader reader);

	@GET
	@Path("/toEmployeePage")
	public abstract void toEmployeePage(Reader reader);
}