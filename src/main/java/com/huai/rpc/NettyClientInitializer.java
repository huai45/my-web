package com.huai.rpc;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Map;

/**
 * Created by zhonghuai.zhang on 2017/5/11.
 */
public class NettyClientInitializer extends ChannelInitializer<SocketChannel> {

    private NettyClient nettyClient;

    public NettyClientInitializer(NettyClient nettyClient){
        this.nettyClient = nettyClient;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        /*
         * 这个地方的 必须和服务端对应上。否则无法正常解码和编码
         *
         * 解码和编码 我将会在下一张为大家详细的讲解。再次暂时不做详细的描述
         *
         * */
//        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
//        pipeline.addLast("decoder", new StringDecoder());
//        pipeline.addLast("encoder", new StringEncoder());

        // 客户端的逻辑
        pipeline.addLast("encoder", new ObjectEncoder());
        pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));
        pipeline.addLast("handler", new NettyClientHandler(nettyClient));
    }
}
