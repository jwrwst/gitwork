package com.platform.rp.framework.mvcface.rest;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.platform.rp.services.wechat.external.vo.LoginVo;
import com.platform.rp.util.Constant;
import com.platform.rp.util.Properties;

public class  BaseController {

	protected static String SUCCESS_CODE = "SYS010";

	/** Logger available to subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Context
	protected HttpServletRequest request;

	@Context
	protected HttpServletResponse response;

	@Context
	protected ServletContext servletContext;

	@Autowired
	protected Properties properties;


	protected String getOpenId (){
		LoginVo vo = (LoginVo) getSessionVal("loginVo");
		if(vo==null || "".equals(vo.getOpenId())){
			logger.error("LoginVo 是空");
			
			//return "o3TzSv4jkZduiWYTOME_mUujr1Ys";
			return Constant.DUF_OPENID;
			//return "o21XSvzuoDw5fQ09QdMnMlY0hByU";
		}
		return  vo.getOpenId();
	}

	/**
	 * @see 请求转向
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void forward(String url) throws Exception {
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @see 请求重定向
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void sendRedirect(String url) throws Exception {
		try {
			//ServletActionContext.getResponse().sendRedirect(properties.host+"/views/template/web/tips_tenant_registerMerchants.xhtml");
			response.setCharacterEncoding("utf-8");
			response.sendRedirect(url);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @see 发送响应结果 为实体对象
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String sendResponseResult(Object obj) {
		return JSONObject.fromObject(obj).toString();
	}

	/**
	 * @see 发送响应结果 为实体数组
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public String sendResponseArrayResult(Object obj) {
		return JSONArray.fromObject(obj).toString();
	}

	public String getPar(String key){
		return request.getParameter(key);
	}
	
	public Object getAttr(String key){
		return request.getAttribute(key);
	}
	
	public void setAttr(String key,Object value){
		request.setAttribute(key, value);
	}
	/**
	 *	启动公共缓存
	 * @param key
	 * @return
	 */
	public Object getSessionCahceVal(String key){
		return request.getSession().getAttribute(key);
	}
	/**
	 * 启动公共缓存
	 * @param key
	 * @param value
	 */
	public void setSessionCahceVal(String key,Object value){
		request.getSession().setAttribute(key, value);
	}
	public Object getSessionVal(String key){
		return request.getSession().getAttribute(key);
	}
	public void setSessionVal(String key,Object value){
		request.getSession().setAttribute(key, value);
	}
	

	protected Map<String,Object> initExcleParams(){
		Map<String,Object> params = new HashMap<String,Object>();
		//
		params.put("iDisplayStart", 0);
		params.put("iDisplayLength", 100000);
		params.put("startTime", getPar("startTime"));
		params.put("endTime", getPar("endTime"));
		params.put("storeIds", getPar("storeIds"));
		return params;
	}
	protected void dowloadExcle(String fileName,XSSFWorkbook xssfwk) throws IOException {

		OutputStream outputStream = response.getOutputStream();
		response.reset();			
		if(fileName == null){
			response.addHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis()+".xlsx");
		}else{
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"),"iso-8859-1")+".xlsx");		
		}
		response.setContentType("application/octet-stream");
		xssfwk.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
}
