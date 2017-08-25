package com.platform.rp.framework.rest.jersey.client.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import javax.ws.rs.core.MediaType;

import org.apache.commons.collections4.list.TreeList;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.platform.rp.framework.rest.jersey.client.RestClient;
import com.platform.rp.framework.rest.jersey.client.component.BlockMark;
import com.platform.rp.framework.rest.jersey.client.component.ResponseProcess;
import com.platform.rp.framework.rest.jersey.client.component.StatusCode;
import com.platform.rp.framework.rest.jersey.client.util.CodecUtil;
import com.platform.rp.framework.threadPool.ThreadPoolProvider;
import com.platform.rp.util.PropertyUtil;
import com.sun.jersey.api.client.AsyncWebResource;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.async.TypeListener;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;

/**
 * rest方式客户端公共类(jersey依赖版)
 * 
 */
public class JerseyRestClient<T> extends RestClient<T> {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FastRestClient.class);
    private final Object sn = MDC.get("sn");

    private StatusCode statusCode = StatusCode.SUCCESS;
    private BlockMark blockMark = BlockMark.NON_BLOCK;
    private Class<T> resultClass = null;
    private String encoding = "UTF-8";
    private String method = null;
    private String url = "";
    private String bodyContent = "";
    private ResponseProcess<T> responseProcess = null;

    String threadInfo = " ， 当前线程ID:" + Thread.currentThread().getId();

    public T postByJson(Object requestEntity) {
        long sendTime = System.currentTimeMillis();
        Client jerseyClient = null;
        method = "POST";

        // 构造jerseyClient
        try {
            jerseyClient = CustomerJerseyClientBuilder.getInstance()
                    .buildClient();
        } catch (Exception e) {
            logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);

            if (getResultClass() == null)
                return null;
            try {
                return getResultClass().newInstance();
            } catch (Exception e1) {
                return null;
            }
        }

        // 无阻塞方式请求
        if (BlockMark.NON_BLOCK.equals(getBlockMark())) {
            try {
                AsyncWebResource asyncWebResource = jerseyClient
                        .asyncResource(getUrl());
                bodyContent = RestClient.serializerByJson(requestEntity,
                        "UTF-8");

                AsyncWebResource.Builder builder = asyncWebResource.entity(
                        requestEntity, MediaType.APPLICATION_JSON_TYPE);
                addSign(builder).post(typeListener, bodyContent);
            } catch (Exception e) {
                setStatusCode(StatusCode.ERROR);
                return null;
            }
            return null;
        }

        // 阻塞方式请求
        try {
            WebResource webResource = jerseyClient.resource(getUrl());
            bodyContent = RestClient.serializerByJson(requestEntity, "UTF-8");

            Builder builder = webResource.entity(requestEntity,
                    MediaType.APPLICATION_JSON_TYPE);
            T resultObject = addSign(builder).post(getResultClass(),
                    bodyContent);

            if (getResponseProcess() == null || resultObject == null)
                return resultObject;

            try {
                getResponseProcess().process(getStatusCode(), resultObject);
                return null;
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + "回调对象: "
                        + getResponseProcess().getClass().getCanonicalName()
                        + "处理异常", e);
            }

        } catch (Exception e) {
            logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);
        } finally {
            long mistiming = System.currentTimeMillis() - sendTime;
            System.out.println("请求其他系统执行时间差（毫秒数）：" + mistiming);
        }

        if (getResultClass() == null)
            return null;
        try {
            return getResultClass().newInstance();
        } catch (Exception e1) {
            return null;
        }
    }

    public T postTextByJson(String requestStr) {
        long sendTime = System.currentTimeMillis();
        Client jerseyClient = null;
        method = "POST";

        // 构造jerseyClient
        try {
            jerseyClient = CustomerJerseyClientBuilder.getInstance()
                    .buildClient();
        } catch (Exception e) {
            logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);

            if (getResultClass() == null)
                return null;
            try {
                return getResultClass().newInstance();
            } catch (Exception e1) {
                return null;
            }
        }

        // 无阻塞方式请求
        if (BlockMark.NON_BLOCK.equals(getBlockMark())) {
            try {
                AsyncWebResource asyncWebResource = jerseyClient
                        .asyncResource(getUrl());
                bodyContent = requestStr;

                AsyncWebResource.Builder builder = asyncWebResource
                        .type(MediaType.TEXT_PLAIN_TYPE);
                addSign(builder).post(typeListener, bodyContent);
            } catch (Exception e) {
                setStatusCode(StatusCode.ERROR);
                return null;
            }
            return null;
        }

        // 阻塞方式请求
        try {
            WebResource webResource = jerseyClient.resource(getUrl());
            bodyContent = requestStr;

            Builder builder = webResource.type(MediaType.TEXT_PLAIN_TYPE);
            T resultObject = addSign(builder).post(getResultClass(),
                    bodyContent);

            if (getResponseProcess() == null || resultObject == null)
                return resultObject;

            try {
                getResponseProcess().process(getStatusCode(), resultObject);
                return null;
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + "回调对象: "
                        + getResponseProcess().getClass().getCanonicalName()
                        + "处理异常", e);
            }

        } catch (Exception e) {
            logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);
        } finally {
            long mistiming = System.currentTimeMillis() - sendTime;
            System.out.println("请求其他系统执行时间差（毫秒数）：" + mistiming);
        }

        if (getResultClass() == null)
            return null;
        try {
            return getResultClass().newInstance();
        } catch (Exception e1) {
            return null;
        }
    }

    public T getByJson() {
        long sendTime = System.currentTimeMillis();
        Client jerseyClient = null;
        method = "GET";

        // 构造jerseyClient
        try {
            jerseyClient = CustomerJerseyClientBuilder.getInstance()
                    .buildClient();
        } catch (Exception e) {
            logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);

            if (getResultClass() == null)
                return null;
            try {
                return getResultClass().newInstance();
            } catch (Exception e1) {
                return null;
            }
        }

        // 无阻塞方式请求
        if (BlockMark.NON_BLOCK.equals(getBlockMark())) {
            try {
                AsyncWebResource asyncWebResource = jerseyClient
                        .asyncResource(getUrl());

                AsyncWebResource.Builder builder = asyncWebResource
                        .getRequestBuilder();
                addSign(builder).get(typeListener);
            } catch (Exception e) {
                setStatusCode(StatusCode.ERROR);
                return null;
            }
            return null;
        }

        // 阻塞方式请求
        try {
            WebResource webResource = jerseyClient.resource(getUrl());

            Builder builder = webResource.getRequestBuilder();
            T resultObject = addSign(builder).get(getResultClass());

            if (getResponseProcess() == null || resultObject == null)
                return resultObject;

            try {
                getResponseProcess().process(getStatusCode(), resultObject);
                return null;
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + "回调对象: "
                        + getResponseProcess().getClass().getCanonicalName()
                        + "处理异常", e);
            }

        } catch (Exception e) {
            logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);
        } finally {
            long mistiming = System.currentTimeMillis() - sendTime;
            System.out.println("请求其他系统执行时间差（毫秒数）：" + mistiming);
        }

        if (getResultClass() == null)
            return null;
        try {
            return getResultClass().newInstance();
        } catch (Exception e1) {
            return null;
        }
    }

    private AsyncWebResource.Builder addSign(AsyncWebResource.Builder builder)
            throws Exception {
        if (sn != null && ((String) sn).contains(":"))
            builder.header("sn", "" + ((String) sn).split(":")[1].trim());

        if ("POST".equals(method)) {
            String sourceSignStr = CodecUtil.digestByMD5(bodyContent
                    + PropertyUtil.DIGEST_SP);
            return builder.header("sign", sourceSignStr);
        }

        String sourceSignStr = null;
        String queryStr = new URL(getUrl()).getQuery();
        if (queryStr == null || queryStr.trim().length() < 1)
            return builder;

        String[] queryParameters = queryStr.trim().split("&");
        if (queryParameters == null || queryParameters.length < 1)
            return builder;

        StringBuffer stringBuffer = new StringBuffer();
        TreeList<String> keyList = new TreeList<String>();
        Map<String, String> parameterMap = new HashMap<String, String>();
        for (String queryParameter : queryParameters) {
            String[] parameter = queryParameter.split("=");
            keyList.add(parameter[0].trim());
            parameterMap.put(parameter[0].trim(), parameter[1].trim());
        }

        for (String key : keyList) {
            stringBuffer.append("&" + key + "=" + parameterMap.get(key));
        }

        sourceSignStr = stringBuffer.substring(1);
        sourceSignStr = CodecUtil.digestByMD5(sourceSignStr
                + PropertyUtil.DIGEST_SP);

        return builder.header("sign", sourceSignStr);
    }

    private Builder addSign(Builder builder) throws Exception {
        if (sn != null && ((String) sn).contains(":"))
            builder.header("sn", "" + ((String) sn).split(":")[1].trim());

        if ("POST".equals(method)) {
            String sourceSignStr = CodecUtil.digestByMD5(bodyContent
                    + PropertyUtil.DIGEST_SP);
            return builder.header("sign", sourceSignStr);
        }

        String sourceSignStr = null;
        String queryStr = new URL(getUrl()).getQuery();
        if (queryStr == null || queryStr.trim().length() < 1)
            return builder;

        String[] queryParameters = queryStr.trim().split("&");
        if (queryParameters == null || queryParameters.length < 1)
            return builder;

        StringBuffer stringBuffer = new StringBuffer();
        TreeList<String> keyList = new TreeList<String>();
        Map<String, String> parameterMap = new HashMap<String, String>();
        for (String queryParameter : queryParameters) {
            String[] parameter = queryParameter.split("=");
            keyList.add(parameter[0].trim());
            parameterMap.put(parameter[0].trim(), parameter[1].trim());
        }

        for (String key : keyList) {
            stringBuffer.append("&" + key + "=" + parameterMap.get(key));
        }

        sourceSignStr = stringBuffer.substring(1);
        sourceSignStr = CodecUtil.digestByMD5(sourceSignStr
                + PropertyUtil.DIGEST_SP);

        return builder.header("sign", sourceSignStr);
    }

    /**
     * 构造函数
     * 
     * <b>注意：确定本次接口调用，服务器端无任何返回信息</b>
     * 
     * @param url
     *            请求url
     */
    public JerseyRestClient(String url) {
        setUrl(url);
    }

    /**
     * 构造函数
     * 
     * @param url
     *            请求url
     * @param responseProcess
     *            返回结果后续处理对象，由调用者创建
     * @param resultClass
     *            返回实体类型
     */
    public JerseyRestClient(String url, ResponseProcess<T> responseProcess,
            Class<T> resultClass) {
        this(url);
        setResponseProcessAndResultClass(responseProcess, resultClass);
    }

    /**
     * @return encoding
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * @param encoding
     *            要设置的 encoding
     */
    public JerseyRestClient<T> setEncoding(String encoding) {
        this.encoding = encoding;
        return this;
    }

    /**
     * 设置返回结果后续处理对象与返回对象类
     * 
     * @param resultClass
     * @param responseProcess
     * @return
     */
    public JerseyRestClient<T> setResponseProcessAndResultClass(
            ResponseProcess<T> responseProcess, Class<T> resultClass) {
        setResultClass(resultClass);
        setResponseProcess(responseProcess);
        return this;
    }

    /**
     * @return resultClass
     */
    private Class<T> getResultClass() {
        return resultClass;
    }

    /**
     * @param resultClass
     *            要设置的 resultClass
     */
    private JerseyRestClient<T> setResultClass(Class<T> resultClass) {
        this.resultClass = resultClass;
        return this;
    }

    /**
     * @return responseProcess
     */
    private ResponseProcess<T> getResponseProcess() {
        return responseProcess;
    }

    /**
     * 设置后续处理类
     * 
     * @param responseProcess
     *            要设置的 responseProcess
     */
    private void setResponseProcess(ResponseProcess<T> responseProcess) {
        this.responseProcess = responseProcess;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            要设置的 url
     */
    public JerseyRestClient<T> setUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * @return statusCode
     */
    private StatusCode getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     *            要设置的 statusCode
     */
    private JerseyRestClient<T> setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * @return blockMark
     */
    public BlockMark getBlockMark() {
        return blockMark;
    }

    public JerseyRestClient<T> setBlockMark(BlockMark blockMark) {
        this.blockMark = blockMark;
        return this;
    }

    private static class CustomerJerseyClientBuilder {
        private static CustomerJerseyClientBuilder customerJerseyClientBuilder = null;
        private Client jerseyClient = null;

        public synchronized static CustomerJerseyClientBuilder getInstance() {
            if (customerJerseyClientBuilder != null)
                return customerJerseyClientBuilder;

            customerJerseyClientBuilder = new CustomerJerseyClientBuilder();
            return customerJerseyClientBuilder;
        }

        public Client buildClient() {
            return jerseyClient;
        }

        private CustomerJerseyClientBuilder() {
            ClientConfig clientConfig = new DefaultClientConfig();
            clientConfig.getClasses().add(JacksonJsonProvider.class);
            jerseyClient = Client.create(clientConfig);
            jerseyClient.setExecutorService(ThreadPoolProvider.getInstance()
                    .getThreadPoolExecutor());
            jerseyClient.addFilter(new LoggingFilter());
        }

        protected void finalize() throws Throwable {
            if (jerseyClient != null)
                jerseyClient.destroy();
        }
    }

    private TypeListener<T> typeListener = new TypeListener<T>(getResultClass()) {
        public void onComplete(Future<T> future) throws InterruptedException {
            T resultObject = null;

            try {
                resultObject = future.get();
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
                setStatusCode(StatusCode.ERROR);

                if (getResultClass() == null)
                    resultObject = null;
                try {
                    resultObject = getResultClass().newInstance();
                } catch (Exception e1) {
                    resultObject = null;
                }
            }

            if (getResponseProcess() == null || resultObject == null)
                return;

            try {
                getResponseProcess().process(getStatusCode(), resultObject);
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + "回调对象: "
                        + getResponseProcess().getClass().getCanonicalName()
                        + "处理异常", e);
            }
        }
    };
}