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
    public volatile boolean running = true;
    public Map param;

    public NettyClient(Map param){
        this.param = param;
    }

    public void connect() throws Exception {
        /**
         * 客户端的Bootstrap一般用一个EventLoopGroup，而服务器端的ServerBootstrap会用到两个（这两个也可以是同一个实例）。
         * 为何服务器端要用到两个EventLoopGroup呢？
         * 这么设计有明显的好处，如果一个ServerBootstrap有两个EventLoopGroup，
         * 那么就可以把第一个EventLoopGroup用来专门负责绑定到端口监听连接事件，
         * 而把第二个EventLoopGroup用来处理每个接收到的连接
         */
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyClientInitializer(this));

            // 连接服务端
            Channel ch = b.connect(host, port).sync().channel();
//            ch.writeAndFlush("hello world!" + "\r\n");

            param.put("hello","world");
            byte[] bytes = MyUtil.getByte(param);
            System.out.println(" 1111111111111111111111  ");
            ch.writeAndFlush(bytes);
            System.out.println(" 2222222222222222222222  ");
            while (running) {
//                Thread.sleep(1);
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

    public Object getResult() throws Exception {
        return param.get("data");
    }

    public static void main(String[] args) throws InterruptedException, IOException {


    }
}
