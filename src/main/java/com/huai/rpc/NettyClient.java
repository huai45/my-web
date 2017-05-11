package com.huai.rpc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhonghuai.zhang on 2017/5/11.
 */
public class NettyClient {

    public static String host = "127.0.0.1";
    public static int port = 8000;
    public static boolean running = true;

    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyClientInitializer());

            // 连接服务端
            Channel ch = b.connect(host, port).sync().channel();
//            ch.writeAndFlush("hello world!" + "\r\n");

            Map param = new HashMap();
            param.put("hello","world");
            byte[] bytes = MyUtil.getByte(param);
            System.out.println(" 1111111111111111111111  ");
            ch.writeAndFlush(bytes);
            System.out.println(" 2222222222222222222222  ");
            while (running) {
                Thread.sleep(1);
            }
            System.out.println(" Client is closing    NettyClient.running : " + running);
            // 控制台输入
//            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//            for (;;) {
//                String line = in.readLine();
//                if (line == null) {
//                    continue;
//                }
//                /*
//                 * 向服务端发送在控制台输入的文本 并用"\r\n"结尾
//                 * 之所以用\r\n结尾 是因为我们在handler中添加了 DelimiterBasedFrameDecoder 帧解码。
//                 * 这个解码器是一个根据\n符号位分隔符的解码器。所以每条消息的最后必须加上\n否则无法识别和解码
//                 * */
//                ch.writeAndFlush(line + "\r\n");
//            }
        } finally {
            // The connection is closed automatically on shutdown.
            group.shutdownGracefully();
        }
    }
}
