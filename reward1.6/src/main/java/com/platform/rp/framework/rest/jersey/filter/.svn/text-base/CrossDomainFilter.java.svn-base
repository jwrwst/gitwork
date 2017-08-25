package com.platform.rp.framework.rest.jersey.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.platform.rp.util.StringUtils;

/**
 * 
 * ClassName: 跨域封装
 *
 */
public class CrossDomainFilter implements Filter {
    private static final Logger logger = Logger.getLogger(CrossDomainFilter.class);
    
    private static final String[] noCheckPath={"/wechat/","/wap/","/qrcode"};

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        long stateTime = System.currentTimeMillis();
        String sn = UUID.randomUUID().toString();
        MDC.put("sn", "唯一标识:" + sn + " ");
        
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        
        //验证合法性
        boolean isValid=true;
        for (String path : noCheckPath) {
        	if(request.getRequestURI().indexOf(path)>-1){
        		isValid = false;
        	}
		}
        if (logger.getEffectiveLevel().toInt() > Level.DEBUG_INT) {
	        if(isValid && null==request.getSession().getAttribute("loginVo")){
	        	response.setCharacterEncoding("utf-8");
	        	OutputStream outputStream = response.getOutputStream();
	        	outputStream.write(new String("{\"resultMessage\":{\"status\":1,\"messageCode\":\"AUTH001\",\"messageText\":\"网络出错,请重新进入,可能原因：网络不稳定\"},\"resultData\":null}").getBytes("utf-8"));
	        	return;
	
	        }
        }
        
                
        String threadInfo = "";
        String requestMethod = ((HttpServletRequest) servletRequest).getMethod();
        String nocrossHeader= ((HttpServletRequest) servletRequest).getHeader("nocross");
        if(requestMethod.equals("POST")&&"nocross".equals(nocrossHeader)){        	
        	chain.doFilter(servletRequest, servletResponse);
        	return;
        }
       
        String remoteAddr = servletRequest.getParameter("addr");
        if (!StringUtils.isBlank(remoteAddr)) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
            response.addHeader("Access-Control-Allow-Headers",
                    "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
            response.addHeader("Access-Control-Max-Age", "1728000");

            logger.info("服务器端打印" + threadInfo + " ，本地请求方式为H5方式跨域访问");
        }

        String jsoncallback = ((HttpServletRequest) servletRequest).getParameter("callback");
        if (jsoncallback != null) {
            logger.info("服务器端打印" + threadInfo + " ，本地请求方式为jsonp方式跨域访问");

            OutputStream outputStream = response.getOutputStream();
            outputStream.write((jsoncallback + "(").getBytes());
            chain.doFilter(servletRequest, servletResponse);
            outputStream.write(")".getBytes());
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }

        long mistiming = System.currentTimeMillis() - stateTime;
        //logger.info("本地服务系统总执行时间差（毫秒数）：" + mistiming);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void destroy() {
    }
}
