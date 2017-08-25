package com.platform.rp.framework.socket.client.impl;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * netty异步通讯（待完善）
 * 
 */
public class MessageClient {
    public static void main(String args[]) {
        // Client服务启动器
        ClientBootstrap bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        // 设置一个处理服务端消息和各种消息事件的类(Handler)
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new MessageClientHandler());
            }
        });
        // 连接到本地的8000端口的服务端
        bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
    }

    private static class MessageClientHandler extends SimpleChannelHandler {

        /**
         * 当绑定到服务端的时候触发，给服务端发消息。
         * 
         * @author yangt
         */
        @Override
        public void channelConnected(
                ChannelHandlerContext channelHandlerContext,
                ChannelStateEvent channelStateEvent) {
            String msg = "Hello, I'm client.";

            ChannelBuffer channelBuffer = ChannelBuffers.buffer(msg.length());
            channelBuffer.writeBytes(msg.getBytes());
            channelStateEvent.getChannel().write(channelBuffer);
        }

        /**
         * 当有服务器端消息到达时触发，接受服务器端传来的消息
         * 
         * @author yangt
         */
        @Override
        public void messageReceived(
                ChannelHandlerContext channelHandlerContext,
                MessageEvent messageEvent) {
            ChannelBuffer channelBuffer = (ChannelBuffer) messageEvent
                    .getMessage();
            System.out
                    .println(channelBuffer.toString(Charset.forName("UTF-8")));
        }
    }
}
