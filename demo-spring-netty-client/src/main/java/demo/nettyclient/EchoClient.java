package demo.nettyclient;

import demo.nettyclient.handler.EchoClientChannelHandler;
import demo.nettyclient.handler.EchoClientSecondChannelHandler;
import demo.nettyclient.handler.EchoClientThirdChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;

    private EchoClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    private void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
       try {
           Bootstrap bootstrap = new Bootstrap();
           bootstrap.group(group)
                   .channel(NioSocketChannel.class)
                   .remoteAddress(new InetSocketAddress(host, port))
                   .handler(new ChannelInitializer<SocketChannel>() {
                       @Override
                       protected void initChannel(SocketChannel ch) {
                           ch.pipeline().addLast(new EchoClientChannelHandler());
                           ch.pipeline().addLast(new EchoClientSecondChannelHandler());
                           ch.pipeline().addLast(new EchoClientThirdChannelHandler());
                       }
                   });
           ChannelFuture channelFuture = bootstrap.connect().sync();
           System.out.println("client connected!!!!");
           channelFuture.channel().closeFuture().sync();
           System.out.println("client channel closed!!!!!");
       }finally {
           group.shutdownGracefully().sync();
       }
    }

    public static void main(String[] args) throws Exception {
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        System.out.println("client main start, host: "+host+", port:"+port);
        if (args.length!=2){
            System.err.println("Usage:"+EchoClient.class.getSimpleName()+"<host><port>");
            return;
        }
        EchoClient echoClient=new EchoClient(host,port);
        echoClient.start();
    }


}
