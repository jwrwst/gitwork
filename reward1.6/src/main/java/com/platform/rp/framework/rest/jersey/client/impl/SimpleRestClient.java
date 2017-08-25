package com.platform.rp.framework.rest.jersey.client.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections4.list.TreeList;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.platform.rp.framework.rest.jersey.client.RestClient;
import com.platform.rp.framework.rest.jersey.client.component.BlockMark;
import com.platform.rp.framework.rest.jersey.client.component.ResponseProcess;
import com.platform.rp.framework.rest.jersey.client.component.StatusCode;
import com.platform.rp.framework.rest.jersey.client.util.CodecUtil;
import com.platform.rp.util.PropertyUtil;

/**
 * rest方式客户端公共类（无第三方公共包依赖版）
 * 
 */
public class SimpleRestClient<T> extends RestClient<T> {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger
            .getLogger(SimpleRestClient.class);

    public static final ExecutorService executorService = Executors
            .newCachedThreadPool();
    private final Object sn = MDC.get("sn");

    private StatusCode statusCode = StatusCode.SUCCESS;
    private BlockMark blockMark = BlockMark.NON_BLOCK;
    private Class<T> resultClass = null;
    private String encoding = "UTF-8";
    private ResponseProcess<T> responseProcess = null;
    private RequestBuilder requestBuilder = null;

    String threadInfo = " ， 当前线程ID:" + Thread.currentThread().getId();

    /**
     * post方式，json数据格式通讯
     * 
     * @param requestEntity
     *            请求数据实体
     */
    public T postByJson(Object requestEntity) {
        try {
            requestBuilder.setMethod("POST");
            requestBuilder.addHeader("Content-Type",
                    "application/json; charset=" + getEncoding());
            requestBuilder.setBodyContentByJson(requestEntity);
        } catch (Exception e) {
            logger.error("客户端打印" + threadInfo + "，请求实体转换为json串出错！", e);
            setStatusCode(StatusCode.ERROR);

            if (getResultClass() == null)
                return null;
            try {
                return getResultClass().newInstance();
            } catch (Exception e1) {
                return null;
            }
        }

        return passImplByJson(requestBuilder);
    }

    /**
     * post方式，参数请求，返回json格式的数据
     */
    public T postTextByJson(String requestStr) {
        try {
            requestBuilder.setMethod("POST");
            requestBuilder.addHeader("Content-Type", "text/plain; charset="
                    + getEncoding());
            requestBuilder.setBodyContentByText(requestStr);
        } catch (Exception e) {
            logger.error("客户端打印" + threadInfo + "，请求实体转换为json串出错！", e);
            setStatusCode(StatusCode.ERROR);

            if (getResultClass() == null)
                return null;
            try {
                return getResultClass().newInstance();
            } catch (Exception e1) {
                return null;
            }
        }

        return passImplByJson(requestBuilder);
    }

    /**
     * get方式，json数据格式通讯
     */
    public T getByJson() {
        try {
            requestBuilder.setMethod("GET");
            requestBuilder.addHeader("Content-Type",
                    "application/json; charset=" + getEncoding());
        } catch (Exception e) {
            logger.error("客户端打印" + threadInfo + "，请求实体转换为json串出错！", e);
            setStatusCode(StatusCode.ERROR);

            if (getResultClass() == null)
                return null;
            try {
                return getResultClass().newInstance();
            } catch (Exception e1) {
                return null;
            }
        }

        return passImplByJson(requestBuilder);
    }

    private T passImplByJson(RequestBuilder requestBuilder) {
        // 无阻塞方式请求
        if (BlockMark.NON_BLOCK.equals(getBlockMark())) {
            executorService.execute(new HttpResponseHandle(requestBuilder));
            return null;
        }

        // 阻塞方式请求
        try {
            Response<T> response = requestBuilder.execute();
            T resultObject = response.getEntity();
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
            logger.error(e.getMessage(), e);
            setStatusCode(StatusCode.ERROR);
        }

        if (getResultClass() == null)
            return null;
        try {
            return getResultClass().newInstance();
        } catch (Exception e1) {
            return null;
        }

    }

    public String sendPost(RequestBuilder requestBuilder) throws Exception {
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String result = "";
        try {
            URL realUrl = new URL(requestBuilder.getUrl());
            URLConnection uRLConnection = realUrl.openConnection();
            requestBuilder.injectHeads(uRLConnection);
            uRLConnection.setDoOutput(true);
            uRLConnection.setDoInput(true);

            printWriter = new PrintWriter(uRLConnection.getOutputStream());
            printWriter.print(requestBuilder.getBodyContent());
            printWriter.flush();

            bufferedReader = new BufferedReader(new InputStreamReader(
                    uRLConnection.getInputStream(), getEncoding()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("客户端打印" + threadInfo + "，发送 POST 请求出现异常！", e);
            throw e;
        } finally {
            try {
                if (printWriter != null) {
                    printWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
            }
        }
        return result;
    }

    public String sendGet(RequestBuilder requestBuilder) throws Exception {
        BufferedReader bufferedReader = null;
        String result = "";
        try {
            URL realUrl = new URL(requestBuilder.getUrl());
            URLConnection uRLConnection = realUrl.openConnection();
            requestBuilder.injectHeads(uRLConnection);
            uRLConnection.connect();

            bufferedReader = new BufferedReader(new InputStreamReader(
                    uRLConnection.getInputStream(), getEncoding()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            logger.error("客户端打印" + threadInfo + "，发送 POST 请求出现异常！", e);
            throw e;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException ex) {
            }
        }
        return result;
    }

    class Response<R> {
        String resultStr = null;

        public Response(RequestBuilder requestBuilder) throws Exception {
            execute(requestBuilder);
        }

        private void execute(RequestBuilder requestBuilder) throws Exception {
            if ("POST".equals(requestBuilder.getMethod())) {
                setResultStr(sendPost(requestBuilder));
                return;
            }

            setResultStr(sendGet(requestBuilder));
        }

        public T getEntity() throws Exception {
            logger.info("客戶端打印" + threadInfo + "，返回json串 : " + resultStr);

            if (resultStr == null || resultStr.trim().length() < 1
                    || "\"{}\"".equals(resultStr)) {
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
         * @return resultStr
         */
        public String getResultStr() {
            return resultStr;
        }

        /**
         * @param resultStr
         *            要设置的 resultStr
         */
        public void setResultStr(String resultStr) {
            this.resultStr = resultStr;
        }
    }

    class RequestBuilder {
        private String url = null;
        private String method = null;
        private String bodyContent = null;
        private Set<Header> headerSet = new HashSet<Header>();

        @SuppressWarnings({ "unchecked", "rawtypes" })
        public Response<T> execute() throws Exception {
            logger.info("客户端打印" + threadInfo + "，请求url : \"" + getUrl() + "\"");
            logger.info("客户端打印" + threadInfo + "，请求方式 ：\"" + getMethod() + "\"");
            logger.info("客户端打印" + threadInfo + "，请求头信息 ：\"" + headersToString()
                    + "\"");
            logger.info("客户端打印" + threadInfo + "，请求json串 : " + bodyContent);

            return new Response(this);
        }

        public void injectHeads(URLConnection uRLConnection) throws Exception {
            addHeader("sn", "" + sn);
            addSign(uRLConnection);

            if (getHeaderSet() == null || getHeaderSet().size() < 1)
                return;

            for (Header header : getHeaderSet()) {
                uRLConnection.addRequestProperty(header.getName(),
                        header.getValue());
            }
        }

        private void addSign(URLConnection uRLConnection) throws Exception {

            if ("POST".equals(getMethod())) {
                String sourceSignStr = CodecUtil.digestByMD5(getBodyContent()
                        + PropertyUtil.DIGEST_SP);
                addHeader("sign", sourceSignStr);
                return;
            }

            String sourceSignStr = null;
            String queryStr = new URL(getUrl()).getQuery();
            if (queryStr == null || queryStr.trim().length() < 1)
                return;

            String[] queryParameters = queryStr.trim().split("&");
            if (queryParameters == null || queryParameters.length < 1)
                return;

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

            addHeader("sign", sourceSignStr);
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
        public void setUrl(String url) {
            this.url = url;
        }

        /**
         * @return method
         */
        public String getMethod() {
            return method;
        }

        /**
         * @param method
         *            要设置的 method
         */
        public void setMethod(String method) {
            this.method = method;
        }

        /**
         * @return bodyContent
         */
        public String getBodyContent() {
            return bodyContent;
        }

        /**
         * @param requestEntity
         *            要设置的 requestEntity
         * @throws Exception
         */
        public void setBodyContentByJson(Object requestEntity) throws Exception {
            this.bodyContent = serializerByJson(requestEntity, getEncoding());
        }

        /**
         * @param bodyContent
         *            要设置的 bodyContent
         * @throws Exception
         */
        public void setBodyContentByText(String bodyContent) throws Exception {
            this.bodyContent = bodyContent;
        }

        public void addHeader(String name, String value) {
            new Header().addHeader(name, value);
        }

        /**
         * @return headerSet
         */
        public Set<Header> getHeaderSet() {
            return headerSet;
        }

        /**
         * @return headerSet
         */
        public String headersToString() {
            StringBuffer stringBuffer = new StringBuffer("[");
            if (getHeaderSet().size() < 1) {
                return "[]";
            }
            for (Header header : getHeaderSet()) {
                stringBuffer.append(header.getName() + "=" + header.getValue()
                        + ",");
            }

            return stringBuffer.substring(0, stringBuffer.length() - 1) + "]";

        }

        /**
         * @param headerSet
         *            要设置的 headerSet
         */
        public void setHeaderSet(Set<Header> headerSet) {
            this.headerSet = headerSet;
        }

        public class Header {
            private String name = null;
            private String value = null;

            public Header() {
            }

            public Header(String name, String value) {
                setName(name);
                setValue(value);
                headerSet.add(this);
            }

            private Header addHeader(String name, String value) {
                if (name == null || name.trim().length() < 1)
                    return null;

                if (value == null || value.trim().length() < 1)
                    return null;

                setName(name);
                setValue(value);
                headerSet.add(this);
                return this;
            }

            public Header getHeader(String name) {
                if (name == null || name.trim().length() < 1)
                    return null;

                Iterator<Header> iterator = headerSet.iterator();
                while (iterator.hasNext()) {
                    Header headerTemp = iterator.next();
                    if (headerTemp.getName().equals(name.trim()))
                        return headerTemp;
                }

                return null;
            }

            public Set<Header> getHeaders() {
                if (headerSet.isEmpty())
                    return Collections.emptySet();

                return Collections.unmodifiableSet(headerSet);
            }

            /**
             * @return name
             */
            public String getName() {
                return name;
            }

            /**
             * @param name
             *            要设置的 name
             */
            public void setName(String name) {
                this.name = name;
            }

            /**
             * @return value
             */
            public String getValue() {
                return value;
            }

            /**
             * @param value
             *            要设置的 value
             */
            public void setValue(String value) {
                this.value = value;
            }
        }

    }

    /**
     * 构造函数
     * 
     * <b>注意：确定本次接口调用，服务器端无任何返回信息</b>
     * 
     * @param url
     *            请求url
     */
    public SimpleRestClient(String url) {
        requestBuilder = new RequestBuilder();
        requestBuilder.setUrl(url);
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
    public SimpleRestClient(String url, ResponseProcess<T> responseProcess,
            Class<T> resultClass) {
        requestBuilder = new RequestBuilder();
        requestBuilder.setUrl(url);
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
    public SimpleRestClient<T> setEncoding(String encoding) {
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
    public SimpleRestClient<T> setResponseProcessAndResultClass(
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
    private SimpleRestClient<T> setResultClass(Class<T> resultClass) {
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
     * @param url
     *            要设置的 url
     */
    public SimpleRestClient<T> setUrl(String url) {
        requestBuilder.setUrl(url);
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
    private SimpleRestClient<T> setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * @return blockMark
     */
    public BlockMark getBlockMark() {
        return blockMark;
    }

    /**
     * 设置是否阻塞标志
     * 
     * @param blockMark
     *            要设置的 blockMark
     */
    public SimpleRestClient<T> setBlockMark(BlockMark blockMark) {
        this.blockMark = blockMark;
        return this;
    }

    private class HttpResponseHandle implements Runnable {
        private RequestBuilder requestBuilder = null;

        public HttpResponseHandle(RequestBuilder requestBuilder) {
            setRequestBuilder(requestBuilder);
        }

        public void run() {
            T resultObject = null;

            try {
                Response<T> response = getRequestBuilder().execute();
                resultObject = response.getEntity();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                setStatusCode(StatusCode.ERROR);
            }

            if (getResponseProcess() == null || resultObject == null)
                return;

            try {
                getResponseProcess().process(getStatusCode(), resultObject);
            } catch (Exception e) {
                logger.error("客户端打印，" + threadInfo + "回调对象： "
                        + getResponseProcess().getClass().getCanonicalName()
                        + "处理异常", e);
            }
        }

        /**
         * @return requestBuilder
         */
        public RequestBuilder getRequestBuilder() {
            return requestBuilder;
        }

        /**
         * @param requestBuilder
         *            要设置的 requestBuilder
         */
        public void setRequestBuilder(RequestBuilder requestBuilder) {
            this.requestBuilder = requestBuilder;
        }
    }
}
