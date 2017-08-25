package com.platform.rp.framework.socket.client;

import java.io.Serializable;

import com.platform.rp.framework.socket.client.component.BlockMark;

/**
 * socket异步信息传递
 * 
 */
public interface SocketClient extends Serializable {

    /**
     * 写入信息，自动读取
     * 
     * @param requestStr
     * @return
     */
    public String execute(String requestStr);

    /**
     * 设置是否阻塞方式链接
     * 
     * @param blockMark
     */
    public void setBlockMark(BlockMark blockMark);

}
