package com.huai.rpc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by zhonghuai.zhang on 2017/5/10.
 */
public class HelloClientIntHandler extends ChannelInboundHandlerAdapter {

    public HelloClientIntHandler(Map param){
        this.param = param;
    }

    private Map param;
    public String msg = "Are you ok?";
    public String data = "";

    private static Logger logger = LoggerFactory.getLogger(HelloClientIntHandler.class);

    // 接收server端的消息，并打印出来
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("HelloClientIntHandler.channelRead");
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);

        Map data = MyUtil.getObject(result1);
        System.out.println("data:" + data);

        param.put("data",data);
        System.out.println("Server said:" + data);
        result.release();
    }

    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("HelloClientIntHandler.channelActive");
        byte[] b = MyUtil.getByte(param);
        ByteBuf encoded = ctx.alloc().buffer(4 * b.length);
        encoded.writeBytes(b);
        ctx.write(encoded);
        ctx.flush();
    }

}
