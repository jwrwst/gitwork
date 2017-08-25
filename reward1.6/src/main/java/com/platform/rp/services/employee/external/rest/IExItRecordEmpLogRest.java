package com.platform.rp.services.employee.external.rest;

import java.util.Map;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.employee.core.dao.entity.ItRecordEmpLogEntity;
import com.platform.rp.util.response.result.RestfulResult;

/**
 * 员工打赏日志
 *
 */
@Path("/external/itRecordEmpLog")
@Produces(MediaType.APPLICATION_JSON)
public interface IExItRecordEmpLogRest {
	@POST
	@Path("/save")
	public abstract RestfulResult save(ItRecordEmpLogEntity vo);
	
	@POST
	@Path("/edit")
	public abstract RestfulResult edit(ItRecordEmpLogEntity vo);
	
	@POST
    @Path("/toListPage")
	public RestfulResult toListPage(Map<String, String> params);
	
	@POST
    @Path("/page")
	public RestfulResult getAll(Map<String, String> params);
	
	@POST
    @Path("/getRecordLogListByStoreId")
	public abstract Object getRecordLogListByStoreId(@QueryParam("storeId") long storeId);
}
