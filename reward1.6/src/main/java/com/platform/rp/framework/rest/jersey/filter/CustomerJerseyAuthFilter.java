package com.platform.rp.framework.rest.jersey.filter;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections4.list.TreeList;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.platform.rp.util.CftsUtil;
import com.platform.rp.util.PropertyUtil;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

/**
 * jersey服务器端，自定义认证检查过滤器
 * 
 * 
 */
public class CustomerJerseyAuthFilter implements ContainerRequestFilter,
        ContainerResponseFilter {
    private static final Logger logger = Logger
            .getLogger(CustomerJerseyAuthFilter.class);

    private static Set<String> nonCheckPaths = new HashSet<String>();

    String threadInfo = " ， 当前线程ID:" + Thread.currentThread().getId();

    /**
     * 请求过滤
     * 
     * @param containerRequest
     *            request包装
     * @return request包装
     */
    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) {
        String sn = containerRequest.getHeaderValue("sn");
        MDC.put("sn", "唯一标识:" + sn + " ");

        // if (checkNonPath(containerRequest))
        // return containerRequest;

        if (logger.getEffectiveLevel().toInt() > Level.INFO_INT) {
            return containerRequest;
        }
        if (MediaType.APPLICATION_OCTET_STREAM.equals(containerRequest
                .getHeaderValue("content-type"))) {
            return containerRequest;
        }

        if ("GET".equals(containerRequest.getMethod())) {
            checkGetRequest(containerRequest);
        } else {
            checkPostRequest(containerRequest);
        }

        return containerRequest;
    }

    /**
     * check GET方式请求
     * 
     * @param containerRequest
     */
    private void checkGetRequest(ContainerRequest containerRequest) {
        String sourceStr = null;
        try {
            StringBuffer stringBuffer = new StringBuffer();

            MultivaluedMap<String, String> parameterMap = containerRequest
                    .getQueryParameters();
            if (parameterMap == null || parameterMap.size() < 1) {
                logger.info("服务器端打印" + threadInfo + " , get方式，无任何请求参数!");
                return;
                // throw new Exception("服务器端打印" + threadInfo +
                // " , get方式，无任何请求参数!");
            }

            TreeList<String> keyList = new TreeList<String>(
                    parameterMap.keySet());
            for (String key : keyList) {
                stringBuffer.append("&" + key.trim() + "="
                        + parameterMap.get(key).get(0).trim());
            }

            sourceStr = stringBuffer.substring(1);
            //logger.info("服务器端打印" + threadInfo + " , 签名验证源串：" + sourceStr);
        } catch (Exception e) {
            logger.error("服务器端打印" + threadInfo + " , 解析请求数据异常！", e);
            throw new IllegalArgumentException("解析请求数据异常！");
        }

        // validateSign(containerRequest, sourceStr);

    }

    /**
     * 过滤掉不需要验证的path
     * 
     * @param containerRequest
     * @return
     */
    @SuppressWarnings("unused")
	private boolean checkNonPath(ContainerRequest containerRequest) {
        if (CollectionUtils.isEmpty(getNonCheckPaths()))
            return false;

        for (String path : getNonCheckPaths()) {
            if (!StringUtils.startsWith(containerRequest.getPath(), path))
                continue;

            return true;
        }

        return false;
    }

    /**
     * check POST方式请求
     * 
     * @param containerRequest
     */
    private void checkPostRequest(ContainerRequest containerRequest) {
        String sourceStr = null;
        try {
            InputStream inputStream = containerRequest.getEntityInputStream();
            int size = 0;
            try {
                size = Integer.valueOf(containerRequest
                        .getHeaderValue("content-length"));
            } catch (Exception e) {
                logger.info("服务器端打印" + threadInfo + " , post方式，无任何输入流信息!");
                return;
                // throw new Exception("服务器端打印" + threadInfo
                // + " , post方式，无任何输入流信息!");
            }

            logger.info("服务器端打印" + threadInfo + "，content-type："
                    + containerRequest.getHeaderValue("content-type"));

//            PushbackInputStream pushbackInputStream = new PushbackInputStream(
//                    inputStream, size);
//            byte[] bytes = IOUtils.toByteArray(pushbackInputStream);
//            pushbackInputStream.unread(bytes);
//
//            containerRequest.setEntityInputStream(pushbackInputStream);
//
//            sourceStr = new String(bytes, "UTF-8");
//            logger.info("服务器端打印" + threadInfo + "，签名验证源串：" + sourceStr);
        } catch (Exception e) {
            logger.error("服务器端打印" + threadInfo + " , 读取输入流异常！", e);
            throw new IllegalArgumentException("读取输入流异常！");
        }

        // validateSign(containerRequest, sourceStr);
    }

    /**
     * 签名验证
     * 
     * @param containerRequest
     * @param sourceStr
     */
    private void validateSign(ContainerRequest containerRequest,
            String sourceStr) {
        String sign = containerRequest.getHeaderValue("sign");
        logger.info("服务器端打印" + threadInfo + "，客户端请求签名串：" + sign);

        if (sign == null || sign.trim().length() < 1) {
            logger.error("服务器端打印" + threadInfo + " , 请求头中，未传递客户端签名串！",
                    new Throwable());
            throw new IllegalArgumentException("请求头中，未传递客户端签名串！");
        }

        String sourceSignStr = CftsUtil.digestByMD5(sourceStr
                + PropertyUtil.DIGEST_SP);

        logger.info("服务器端打印" + threadInfo + "，源串转换后签名串：" + sourceSignStr);
        if (!sign.equals(sourceSignStr)) {
            logger.error("服务器端打印" + threadInfo + " , 签名未通过！", new Throwable());
            throw new IllegalArgumentException("签名未通过！");
        }
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

    /**
     * @return nonCheckPaths
     */
    public static Set<String> getNonCheckPaths() {
        return nonCheckPaths;
    }

    /**
     * @param nonCheckPaths
     *            要设置的 nonCheckPaths
     */
    public static void setNonCheckPaths(Set<String> nonCheckPaths) {
        CustomerJerseyAuthFilter.nonCheckPaths = nonCheckPaths;
    }
}
