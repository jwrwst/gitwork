package com.platform.rp.services.employee.external.rest;

import java.util.Map;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.platform.rp.services.employee.core.dao.entity.ItRecordCashLogEntity;
import com.platform.rp.util.response.result.RestfulResult;


/**
 * 员工打赏日志
 *
 */
@Path("/external/itRecordCashLog")
@Produces(MediaType.APPLICATION_JSON)
public interface IExItRecordCashLogRest {
	@POST
	@Path("/save")
	public abstract RestfulResult save(ItRecordCashLogEntity vo);
	
	@POST
	@Path("/edit")
	public abstract RestfulResult edit(ItRecordCashLogEntity vo);
	
	@POST
    @Path("/toListPage")
	public RestfulResult toListPage(Map<String, String> params);
	
	@POST
    @Path("/page")
	public RestfulResult getAll(Map<String, String> params);
}
