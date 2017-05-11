package com.huai.rpc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

/**
 * Created by zhonghuai.zhang on 2017/5/11.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<byte[]> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        System.out.println(" channelRead0    msg : " + msg);
//        byte[] bytes = new byte[msg.readableBytes()];
//        msg.readBytes(bytes);
        Map param = (Map)MyUtil.getObject(msg);
        // 收到消息直接打印输出
        System.out.println(" Server return  param : " + param);

        if(param.containsKey("result_code")){
            NettyClient.running = false;
            System.out.println(" NettyClient.running : " + NettyClient.running);
        }

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client active ");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client close ");
        super.channelInactive(ctx);
    }
}