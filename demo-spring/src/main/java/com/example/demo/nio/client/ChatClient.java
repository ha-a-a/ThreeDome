package com.example.demo.nio.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @FUNC
 * @Author mengyuetang
 * @createTime 2020/8/6
 * @Desc
 */
public class ChatClient {
    private String hostname;
    private int port;
    private Selector selector;
    ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    ByteBuffer wBuffer = ByteBuffer.allocate(1024);
    public static final Logger log = LoggerFactory.getLogger(ChatClient.class);

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public ChatClient(int port) {
        this("127.0.0.1", port);
    }

    private void start() {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(hostname, port));
            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    // 监听客户端channel连接就绪，不代表连接成功
                    if (selectionKey.isConnectable()) {
                        SocketChannel clientChannel = (SocketChannel) selectionKey.channel();
                        // 有可能处于正在连接中
                        if (clientChannel.isConnectionPending()) {
                            //等待连接成功
                            clientChannel.finishConnect();
                            // 监听用户控制台输入
                            inputListening(clientChannel);
                            // 监听客户端是否读就绪
                            clientChannel.register(selector, SelectionKey.OP_READ);
                            log.info("my dear {}, you are the one of us", clientChannel.socket().getPort());
                        }
                    }
                    // 监听客户端channel是否可读
                    if (selectionKey.isReadable()) {
                        // 处理服务端发来的消息
                        handleServerMsg(selectionKey);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 监听用户在控制台的输入，并向服务端发送消息
     *
     * @param clientChannel
     */
    private void inputListening(SocketChannel clientChannel) {
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setName("inputListening");
            return thread;
        };
        Runnable runnable = () -> {
            Scanner scanner = new Scanner(System.in);
            try {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if ("exit".equals(line)) {
                        selector.close();
                        return;
                    }
                    wBuffer.put(line.getBytes());
                    wBuffer.flip();
                    while (wBuffer.hasRemaining()) {
                        clientChannel.write(wBuffer);
                    }
                    wBuffer.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        ExecutorService executorService = new ThreadPoolExecutor(1, 1, 3L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1), threadFactory);
        executorService.submit(runnable);
        executorService.shutdown();
    }


    /**
     * 处理服务端发来的消息
     *
     * @param selectionKey
     */
    private void handleServerMsg(SelectionKey selectionKey) {
        try {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            StringBuilder stringBuffer = new StringBuilder();
            while (socketChannel.read(rBuffer) > 0) {
                rBuffer.flip();
                stringBuffer.append(StandardCharsets.UTF_8.decode(rBuffer));
                rBuffer.clear();
            }
            log.info("{}", stringBuffer.toString());
        } catch (IOException e) {
            // clientChannel.read()方法抛出异常时，说明clientChannel已经关闭，
            // clientChannel需要取消selector上的监听，否则会循环监听到该clientChannel注册的OP_READ事件，不断执行clientChannel.read()方法报错
            selectionKey.cancel();
            // 如果多线程环境下，其他的线程在selector.select()阻塞时，此时的selectionKey集合已经发生了变化，需要立即使select方法返回结果，
            selector.wakeup();
            log.info("chat room has close");
        }
    }

    public static void main(String[] args) {
        new ChatClient(9999).start();
    }
}
