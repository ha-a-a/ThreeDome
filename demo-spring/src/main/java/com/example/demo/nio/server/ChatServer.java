package com.example.demo.nio.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/8/6
 * @Desc
 */
public class ChatServer {
    private String hostname;
    private int port;
    private Selector selector;
    ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    ByteBuffer wBuffer = ByteBuffer.allocate(1024);
    public static final Logger log = LoggerFactory.getLogger(ChatServer.class);

    public ChatServer(int port) {
        this("127.0.0.1", port);
    }

    public ChatServer(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    private void start() {
        try {
            // 生成fd
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // port绑定fd
            serverSocketChannel.bind(new InetSocketAddress(hostname, port));
            serverSocketChannel.configureBlocking(false);
            // 选择网络实现select，poll，epoll
            selector = Selector.open();
            // 注册自己的监听accept事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                log.info("selector begin!!!!!");
                // 底层调用poll，检测一组fd的可读可写和出错事件
                selector.select();
                // 从poll检测出的一组fd事件中，选择自己的注册监听的时间
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
                        SocketChannel clientChannel = serverChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        log.info("{} join the chat room", clientChannel.socket().getPort());
                        log.warn("selectionKey.isAcceptable(), serverChannel:{}，clientChannel:{}", serverChannel, clientChannel);
                    }
                    if (selectionKey.isConnectable()) {
                        log.warn("selectionKey.isConnectable(), selectionKey:{}", selectionKey);
                    }
                    if (selectionKey.isReadable()) {
                        // 处理客户端发过来的消息
                        handleMessage(selectionKey);
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        log.warn("selectionKey.isReadable(), clientChannel:{}", channel.socket().getPort());
                    }
                    // 没有注册 OP_WRITE 事件，这里会抛错
//                    if (selectionKey.isWritable()) {
//                        log.warn("selectionKey.isWritable()， selectionKey:{}", selectionKey);
//                    }
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理客户端发来的消息
     */
    private void handleMessage(SelectionKey selectionKey) {
        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
        try {
            StringBuilder stringBuffer = new StringBuilder();
            while (clientChannel.read(rBuffer) > 0) {
                rBuffer.flip();
                CharBuffer decode = StandardCharsets.UTF_8.decode(rBuffer);
                stringBuffer.append(decode);
                rBuffer.clear();
            }
            String msg = stringBuffer.toString();
            broadcastMessage(clientChannel, msg);
        } catch (IOException e) {
            // clientChannel.read()方法抛出异常时，说明clientChannel已经关闭，
            // clientChannel需要取消selector上的监听，否则会循环监听到该clientChannel注册的OP_READ事件，不断执行clientChannel.read()方法报错
            selectionKey.cancel();
            // 如果多线程环境下，其他的线程在selector.select()阻塞时，此时的selectionKey集合已经发生了变化，需要立即使select方法返回结果，
            // 否则 报错CancelledKeyException,导致server端异常
            selector.wakeup();
            log.info("consumer {} leave the chat room", clientChannel.socket().getPort());
        }
    }

    /**
     * 将客户端的消息分发给其他客户端
     *
     * @param channel
     * @param msg
     */
    private void broadcastMessage(SocketChannel channel, String msg) {
        // 遍历selector中注册的所有channel
        Set<SelectionKey> keys = selector.keys();
        try {
            for (SelectionKey selectionKey : keys) {
                SelectableChannel otherClientChannel = selectionKey.channel();
                // 消息不发给ServerSocketChannel和发消息的clientChannel
                if (selectionKey.isValid()
                        && otherClientChannel instanceof SocketChannel
                        && !otherClientChannel.equals(channel)) {
                    wBuffer.put(("consumer" + channel.socket().getPort() + ":" + msg).getBytes());
                    while (wBuffer.hasRemaining()) {
                        wBuffer.flip();
                        ((SocketChannel) otherClientChannel).write(wBuffer);
                    }
                    wBuffer.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ChatServer(9999).start();
    }
}
