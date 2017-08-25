package com.platform.rp.framework.rest.jersey.client.impl;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.list.TreeList;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Request;
import com.ning.http.client.Response;
import com.platform.rp.framework.rest.jersey.client.RestClient;
import com.platform.rp.framework.rest.jersey.client.component.BlockMark;
import com.platform.rp.framework.rest.jersey.client.component.ResponseProcess;
import com.platform.rp.framework.rest.jersey.client.component.StatusCode;
import com.platform.rp.framework.rest.jersey.client.util.CodecUtil;
import com.platform.rp.framework.threadPool.ThreadPoolProvider;
import com.platform.rp.util.PropertyUtil;
import com.platform.rp.util.annotations.ErrorCode;

/**
 * rest方式客户端公共类（性能优化版）
 * 
 */
public class FastRestClient<T> extends RestClient<T> {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FastRestClient.class);
    private final Object sn = MDC.get("sn");

    private StatusCode statusCode = StatusCode.SUCCESS;
    private BlockMark blockMark = BlockMark.NON_BLOCK;
    private Class<T> resultClass = null;
    private String encoding = "UTF-8";
    private String method = null;
    private String url = "";
    private String bodyContent = null;
    private String contentType = null;
    private ResponseProcess<T> responseProcess = null;

    String threadInfo = " ， 当前线程ID:" + Thread.currentThread().getId();

    public T postByJson(Object requestEntity) {
        Long sendTime = System.currentTimeMillis();
        AsyncHttpClient asyncHttpClient = null;
        BoundRequestBuilder boundRequestBuilder = null;
        if (this.method == null)
            this.method = "POST";

        // 构造request
        try {
            this.bodyContent = serializerByJson(requestEntity, getEncoding());
            this.contentType = ContentType.APPLICATION_JSON.toString();
            asyncHttpClient = CustomerAsyncHttpClientBuilder.getInstance()
                    .buildClient();
            boundRequestBuilder = requestBuilder(asyncHttpClient);
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
                boundRequestBuilder.execute(asyncHandler);
            } catch (Exception e) {
                setStatusCode(StatusCode.ERROR);
                return null;
            }
            return null;
        }

        // 阻塞方式请求
        try {
            Future<Response> future = boundRequestBuilder.execute();
            T resultObject = resultEntityByResponse(future.get());
            if (getResponseProcess() == null || resultObject == null)
                return resultObject;

            try {
                getResponseProcess().process(getStatusCode(), resultObject);
                return null;
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + "回调对象： "
                        + getResponseProcess().getClass().getCanonicalName()
                        + "处理异常", e);
            }

        } catch (Exception e) {
            logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);
        } finally {
            Long mistiming = System.currentTimeMillis() - sendTime;
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

    /**
     * 传递字符串，返回json串
     * 
     * @param requestStr
     * @return
     */
    public T postTextByJson(String requestStr) {
        Long sendTime = System.currentTimeMillis();
        AsyncHttpClient asyncHttpClient = null;
        BoundRequestBuilder boundRequestBuilder = null;
        if (this.method == null)
            this.method = "POST";

        // 构造request
        try {
            this.bodyContent = requestStr;
            this.contentType = ContentType.create("text/plain", Consts.UTF_8)
                    .toString();
            asyncHttpClient = CustomerAsyncHttpClientBuilder.getInstance()
                    .buildClient();
            boundRequestBuilder = requestBuilder(asyncHttpClient);
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
                boundRequestBuilder.execute(asyncHandler);
            } catch (Exception e) {
                setStatusCode(StatusCode.ERROR);
                return null;
            }
            return null;
        }

        // 阻塞方式请求
        try {
            Future<Response> future = boundRequestBuilder.execute();
            T resultObject = resultEntityByResponse(future.get());
            if (getResponseProcess() == null || resultObject == null)
                return resultObject;

            try {
                getResponseProcess().process(getStatusCode(), resultObject);
                return null;
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + "回调对象： "
                        + getResponseProcess().getClass().getCanonicalName()
                        + "处理异常", e);
            }

        } catch (Exception e) {
            logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);
        } finally {
            Long mistiming = System.currentTimeMillis() - sendTime;
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
        this.method = "GET";
        return postByJson(null);
    }

    /**
     * 根据request实体构造request请求
     * 
     * @param requestEntity
     * @param asyncHttpClient
     * @param contentType
     * @return
     * @throws Exception
     */
    private BoundRequestBuilder requestBuilder(AsyncHttpClient asyncHttpClient)
            throws Exception {
        BoundRequestBuilder requestBuilder = null;

        if ("POST".equals(this.method)) {
            requestBuilder = asyncHttpClient.preparePost(getUrl());
            requestBuilder.addHeader("Content-Type", this.contentType);
            requestBuilder.setBodyEncoding(getEncoding());
            requestBuilder.setBody(this.bodyContent);
        } else {
            requestBuilder = asyncHttpClient.prepareGet(getUrl());
        }

        addSign(requestBuilder);

        Request request = requestBuilder.build();
        logger.info("客户端打印" + threadInfo + "，请求url : \"" + request.getUrl()
                + "\"");
        logger.info("客户端打印" + threadInfo + "，请求方式 ：\"" + request.getMethod()
                + "\"");
        logger.info("客户端打印" + threadInfo + "，请求头信息 ：\"" + request.getHeaders()
                + "\"");
        logger.info("客户端打印" + threadInfo + "，请求json串 : " + this.bodyContent);

        return requestBuilder;
    }

    private BoundRequestBuilder addSign(BoundRequestBuilder requestBuilder)
            throws Exception {
        if (sn != null && ((String) sn).contains(":"))
            requestBuilder.addHeader("sn",
                    "" + ((String) sn).split(":")[1].trim());

        if ("POST".equals(this.method)) {
            String sourceSignStr = CodecUtil.digestByMD5(this.bodyContent
                    + PropertyUtil.DIGEST_SP);
            return requestBuilder.addHeader("sign", sourceSignStr);
        }

        String sourceSignStr = null;
        String queryStr = new URL(getUrl()).getQuery();
        if (queryStr == null || queryStr.trim().length() < 1)
            return requestBuilder;

        String[] queryParameters = queryStr.trim().split("&");
        if (queryParameters == null || queryParameters.length < 1)
            return requestBuilder;

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

        return requestBuilder.addHeader("sign", sourceSignStr);
    }

    /**
     * 构造response的返回实体
     * 
     * @param future
     * @return
     * @throws Exception
     */
    private T resultEntityByResponse(Response response) throws Exception {
        if (getResultClass() == null) {
            logger.info("客戶端打印" + threadInfo + "，RestClient构造函数时，未注入返回类型！ ");
            setStatusCode(StatusCode.WARN);

            return null;
        }

        if (!response.hasResponseBody()) {
            logger.warn("客戶端打印" + threadInfo + "，无任何返回信息！ ");
            setStatusCode(StatusCode.WARN);

            try {
                return getResultClass().newInstance();
            } catch (Exception e1) {
                return null;
            }
        }

        String resultStr = response.getResponseBody(getEncoding());
        logger.info("客戶端打印" + threadInfo + "，返回json串 : " + resultStr);

        if (StringUtils.isBlank(resultStr) || "\"{}\"".equals(resultStr)) {
            logger.warn("客戶端打印" + threadInfo + "，返回空串或返回对象内容为空！ ");
            setStatusCode(StatusCode.WARN);

            try {
                return getResultClass().newInstance();
            } catch (Exception e) {
                return null;
            }
        }

        return deserializeByJson(resultStr, getResultClass());
    }

    /**
     * 构造函数
     * 
     * <b>注意：确定本次接口调用，服务器端无任何返回信息</b>
     * 
     * @param url
     *            请求url
     */
    public FastRestClient(String url) {
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
    public FastRestClient(String url, ResponseProcess<T> responseProcess,
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
    public FastRestClient<T> setEncoding(String encoding) {
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
    public FastRestClient<T> setResponseProcessAndResultClass(
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
    private FastRestClient<T> setResultClass(Class<T> resultClass) {
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
    public FastRestClient<T> setUrl(String url) {
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
    private FastRestClient<T> setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * @return blockMark
     */
    public BlockMark getBlockMark() {
        return blockMark;
    }

    public FastRestClient<T> setBlockMark(BlockMark blockMark) {
        this.blockMark = blockMark;
        return this;
    }

    static class CustomerAsyncHttpClientBuilder {
        private static CustomerAsyncHttpClientBuilder customerAsyncHttpClientBuilder = null;
        private AsyncHttpClient asyncHttpClient = null;

        public synchronized static CustomerAsyncHttpClientBuilder getInstance() {
            if (customerAsyncHttpClientBuilder != null)
                return customerAsyncHttpClientBuilder;

            customerAsyncHttpClientBuilder = new CustomerAsyncHttpClientBuilder();
            return customerAsyncHttpClientBuilder;
        }

        public AsyncHttpClient buildClient() {
            return asyncHttpClient;
        }

        private CustomerAsyncHttpClientBuilder() {
            AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
            builder.setExecutorService(ThreadPoolProvider.getInstance()
                    .getThreadPoolExecutor());
            asyncHttpClient = new AsyncHttpClient(builder.build());
        }

        protected void finalize() throws Throwable {
            if (asyncHttpClient != null)
                asyncHttpClient.close();
        }
    }

    private AsyncHandler<T> asyncHandler = new AsyncCompletionHandler<T>() {
        @Override
        public T onCompleted(Response response) throws Exception {
            T resultObject = null;

            try {
                resultObject = resultEntityByResponse(response);
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
                return resultObject;

            try {
                getResponseProcess().process(getStatusCode(), resultObject);
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + "回调对象： "
                        + getResponseProcess().getClass().getCanonicalName()
                        + "处理异常", e);
            }

            return resultObject;
        }

        public void onThrowable(Throwable e) {
            logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);

            // 循环调用
            ThreadPoolProvider.getInstance().execute(new MoreInform(null));
        }
    };

    /**
     * 循环通知，每分钟通知一次，直至通知成功为止
     */
    private class MoreInform implements Runnable {
        T resultObject = null;

        @Override
        public void run() {
            try {
                moreInform(resultObject);
            } catch (Exception e) {
            }
        }

        private T moreInform(T resultObject) {
            int size = 20;
            while (true) {
                resultObject = moreInformImpl(resultObject, size);
                if (resultObject != null)
                    break;

                size = 20;
            }

            try {
                if (getResponseProcess() == null || resultObject == null)
                    return resultObject;

                try {
                    getResponseProcess().process(getStatusCode(), resultObject);
                } catch (Exception e) {
                    logger.error("客户端打印，"
                            + threadInfo
                            + "回调对象： "
                            + getResponseProcess().getClass()
                                    .getCanonicalName() + "处理异常", e);
                }
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
                setStatusCode(StatusCode.ERROR);
            }
            return resultObject;
        }

        private T moreInformImpl(T resultObject, int size) {
            if (size < 1)
                return null;

            try {
                String errorCode = "#errorCode#";
                Field[] Fields = resultClass.getDeclaredFields();
                for (Field field : Fields) {
                    if (resultObject == null)
                        break;
                    if (!field.isAnnotationPresent(ErrorCode.class))
                        continue;
                    errorCode = BeanUtils.getSimpleProperty(resultObject,
                            field.getName());
                    if (StringUtils.isEmpty(errorCode))
                        return resultObject;
                    break;
                }
                if (resultObject != null && "#errorCode#".equals(errorCode))
                    return resultObject;
            } catch (Exception e) {
            }

            try {
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            AsyncHttpClient asyncHttpClient = null;
            BoundRequestBuilder boundRequestBuilder = null;
            setStatusCode(StatusCode.SUCCESS);

            try {
                asyncHttpClient = CustomerAsyncHttpClientBuilder.getInstance()
                        .buildClient();
                boundRequestBuilder = requestBuilder(asyncHttpClient);
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + e.getMessage(), e);
                setStatusCode(StatusCode.ERROR);
            }

            try {
                Future<Response> future = boundRequestBuilder.execute();
                resultObject = resultEntityByResponse(future.get());
                return moreInformImpl(resultObject, --size);
            } catch (Exception e) {
                return moreInformImpl(resultObject, --size);
            }
        }

        public MoreInform(T resultObject) {
            this.resultObject = resultObject;
        }
    }
}
