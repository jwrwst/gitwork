package com.platform.rp.framework.socket.client.component;

/**
 * 业务调用方根据返回信息，后续处理接口
 * 
 */
public interface ResponseProcess {

    /**
     * 服务器返回，调用方后续处理方法
     * 
     * @param statusCode
     *            本次链接处理状态
     * @param resultStr
     *            返回字符串
     */
    public void process(StatusCode statusCode, String resultStr);
}
