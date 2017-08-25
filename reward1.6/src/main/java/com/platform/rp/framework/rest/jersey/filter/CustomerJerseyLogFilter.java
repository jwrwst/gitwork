package com.platform.rp.framework.rest.jersey.filter;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * jersey服务器端，自定义日志过滤器
 * 
 * 
 */
public class CustomerJerseyLogFilter implements ContainerRequestFilter,
        ContainerResponseFilter {
    private static final Logger logger = Logger
            .getLogger(CustomerJerseyLogFilter.class);

    /**
     * 请求过滤
     * 
     * @param containerRequest
     *            request包装
     * @return request包装
     */
    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) {
        String threadInfo = " ， 当前线程ID:" + Thread.currentThread().getId();

        logger.info("服务器端打印" + threadInfo + " ，请求url ：\""
                + containerRequest.getRequestUri().toString() + "\"");
        logger.info("服务器端打印" + threadInfo + " ，请求方式 ：\""
                + containerRequest.getMethod() + "\"");

        if (MapUtils.isNotEmpty(containerRequest.getRequestHeaders()))
            logger.info("服务器端打印" + threadInfo + " ，请求头信息 ：\""
                    + containerRequest.getRequestHeaders().toString() + "\"");

        return containerRequest;
    }

    /**
     * 返回过滤
     * 
     * @param containerRequest
     *            request包装
     * @param containerResponse
     *            response包装
     * @return response包装
     */
    @Override
    public ContainerResponse filter(ContainerRequest containerRequest,
            ContainerResponse containerResponse) {
        return containerResponse;
    }

}
