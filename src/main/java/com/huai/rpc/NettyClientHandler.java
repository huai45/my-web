package com.huai.rpc;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;

/**
 * Created by zhonghuai.zhang on 2017/5/11.
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<byte[]> {

    /**
     *  其中需要注意的是 channelRead0()方法，此方法接收到的可能是一些数据片段，比如服务器发送了5个字节数据，Client端不能保证一次全部收到，比如第一次收到3个字节，第二次收到2个字节。
     *  我们可能还会关心收到这些片段的顺序是否可发送顺序一致，这要看具体是什么协议，比如基于TCP协议的字节流是能保证顺序的。
     *  还有一点，在Client端我们的业务Handler继承的是SimpleChannelInboundHandler，而在服务器端继承的是ChannelInboundHandlerAdapter，那么这两个有什么区别呢？
     *  最主要的区别就是SimpleChannelInboundHandler在接收到数据后会自动release掉数据占用的Bytebuffer资源(自动调用Bytebuffer.release())。
     *  而为何服务器端不能用呢，因为我们想让服务器把客户端请求的数据发送回去，而服务器端有可能在channelRead方法返回前还没有写完数据，因此不能让它自动release。
    */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
        System.out.println(" channelRead0    msg : " + msg);
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

    /**
     *捕捉到异常
     * */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}