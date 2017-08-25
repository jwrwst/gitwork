package com.wxboot.web.framework.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 家家帮
 * 
 * @author wang 2017年4月11日 上午10:03:27 类描述：
 */
@Component
@Aspect
public class AopAspect {
	private int count;
	
	@Pointcut("execution(* com.wxboot..*.*(..))")
	public void handle() {
	}

	//环切
	@Around("handle()")
	public Object aroundLog(ProceedingJoinPoint joinpoint) throws Throwable {
		count++;
		System.out.println("-------"+count+"------");
		return joinpoint.proceed();
	}

	////////点切
	@Pointcut("execution(* com.wxboot.web.userinfo.controller.UserInfoController.index())")
	public void pointCut() {
	}
	@Before("pointCut()")
	public void beforeHandle() throws Throwable {
		System.out.println("beforeHandle()");
	}
	
	@After("pointCut()")
	public void afterHandle() {
		System.out.println("afterHandle()");
	}
	
	
	
}
