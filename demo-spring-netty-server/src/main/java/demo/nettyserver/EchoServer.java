package demo.nettyserver;

import demo.nettyserver.handler.EchoSecondServerHandler;
import demo.nettyserver.handler.EchoServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    private final int port;

    private EchoServer(int port) {
        this.port = port;
    }

    private void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        final EchoSecondServerHandler secondServerHandler = new EchoSecondServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            ServerBootstrap serverBootstrap=new ServerBootstrap();
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                            ch.pipeline().addLast(secondServerHandler);
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("server start!!!! port:"+args[0]);
        if (args.length != 1){
            System.err.println("Usage:"+ EchoServer.class.getSimpleName()+"<port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();

    }

}
