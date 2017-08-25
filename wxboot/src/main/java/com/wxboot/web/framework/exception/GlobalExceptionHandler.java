package com.wxboot.web.framework.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public void defaultErrorHandler(HttpServletRequest req, Exception e) {
		
		// ModelAndView mav = new ModelAndView();
		// mav.addObject("exception", e);
		// mav.addObject("url", req.getRequestURL());
		// mav.setViewName(DEFAULT_ERROR_VIEW);
		// return mav;
		// 打印异常信息：
		System.out.println("GlobalExceptionHandler.defaultErrorHandler()"+e.getMessage());
		e.printStackTrace();

		/*
		 * 1、返回json数据或者String数据： 那么需要在方法上加上注解：@ResponseBody 添加return即可。 2、返回视图：
		 * 定义一个ModelAndView即可， 然后return;
		 * 定义视图文件(比如：error.html,error.ftl,error.jsp);
		 */
	}
	
	

}
