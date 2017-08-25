package com.platform.rp.services.sms.external.rest;


import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.platform.rp.util.response.result.RestfulResult;

@Path("/external/sms")
@Produces(MediaType.APPLICATION_JSON)
public interface ISmsRest {
	/**
	 * 发送短信
	 * @param pthone
	 * @param value
	 * @return
	 */

	@GET
	@Path("send")
	public RestfulResult send(@QueryParam("phone") String phone);
	/**
	 * 发送短信
	 * @param pthone
	 * @param value
	 * @return
	 */

	@GET
	@Path("/wap/send")
	public RestfulResult wapSend(@QueryParam("phone") String phone);
	
	/**
	 * 描述：验证手机验证码的正确性
	 * Administrator 2016年6月2日 下午9:38:22
	 * @param phone
	 * @param verificationCode
	 * @return
	 */
	@GET
	@Path("/wap/validateInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	public RestfulResult validateInfo(@QueryParam("inputCode")String inputCode,@QueryParam("randomCodeInput")String randomCodeInput);

	/**
	 * 描述：验证手机验证码的正确性
	 * Administrator 2016年6月2日 下午9:38:22
	 * @param phone
	 * @param verificationCode
	 * @return
	 */
	@POST
	@Path("/wap/validateCode")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	//@QueryParam("inputCode")String inputCode,@QueryParam("phone")String phone
	public RestfulResult validateCode(Map<String,String> entity);
}
