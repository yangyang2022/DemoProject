package com.yangyang.Netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer {

    public static final int PORT = 10101;
    public static final int BUFFER_SIZE = 1024;

    //管道管理器
    private Selector selector;

    private void initServer(int port) throws Exception{

        //创建一个服务
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //配置为非阻塞
        serverSocketChannel.configureBlocking(false);

        //绑定端口号
        serverSocketChannel.bind(new InetSocketAddress(port));

        //获得一个通道管理器
        this.selector = Selector.open();

        //注册accept事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

    }


    private void listen() throws IOException{
        //轮训访问seletor
        while (true){

            //block here
            this.selector.select();//底层是用c实现的 --> 这里的iterator貌似只有一个选中
            Iterator<?> ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()){
                SelectionKey key = (SelectionKey) ite.next();
                //删除已有的key,防止重复操作
                ite.remove();
                handleSelectKey(key);
            }
        }
    }
    private void handleSelectKey(SelectionKey key) throws IOException {

        //client accept
        if(key.isAcceptable()){
            handleAccept(key);
        }else if (key.isReadable()){
            handRead(key);
        }
    }

    private void handRead(SelectionKey key) throws IOException {
        //get socket channel
        SocketChannel socketChannel = (SocketChannel) key.channel();

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        int read = socketChannel.read(buffer);
        if(read > 0){
            byte[] data = buffer.array();
            String msg = new String(data).trim();
            System.out.println("Server getMsg: "+msg);

            //send msg to client
            ByteBuffer outBuffer = ByteBuffer.wrap("OK-> ".getBytes());
            socketChannel.write(outBuffer);

        }else {
            System.out.println("Client close");
            //break the channel ,can't use return otherwise error
            key.cancel();
        }
    }
    private void handleAccept(SelectionKey key) throws IOException {

        //get serverl channel from key
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

        SocketChannel socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);

        System.out.println("new client");
        //register
        socketChannel.register(this.selector,SelectionKey.OP_READ);

    }
    public static void main(String[] args) throws Exception {
        NioServer server = new NioServer();
        server.initServer(PORT);
        server.listen();
    }
}
