package com.platform.rp.common.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author tangjun
 *
 */
public class DefaultAuthenticationController implements Filter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) arg0;
	    HttpServletResponse response=(HttpServletResponse)arg1;
	    String contentPath=request.getContextPath();
	    String url=request.getRequestURI();
	    String ip = request.getRemoteAddr();
		try {			
			//判断是否直接请求的网页，如果是，给出错误提示
//			if(url.indexOf("/views/template")>-1&&url.indexOf(".html")>-1){
//				throw new Exception();
//			}
			
			//进入后台管理系统			
			if(url.equals(contentPath+"/admin")){
				//request.getRequestDispatcher("/views/template/admin/login/login.html").forward(request, response);
				response.sendRedirect(contentPath+"/views/template/admin/login/login.html");
				return;
			}
			
			//进入商户后台管理系统			
			if(url.equals(contentPath+"/merchants")||url.equals(contentPath+"/")){
				//request.getRequestDispatcher("/views/template/cusAdmin/login.html").forward(request, response);
				response.sendRedirect(contentPath+"/views/template/cusAdmin/login.html");
				return;
			}
			//进入前端管理
			/*if(url.equals(contentPath+"/web")){
				//request.getRequestDispatcher("/views/template/web/index.xhtml").forward(request, response);
				response.sendRedirect(contentPath+"/views/template/web/index.xhtml");
				return;
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(contentPath+"/views/error/error.html");
			return;
		}	
		arg2.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
