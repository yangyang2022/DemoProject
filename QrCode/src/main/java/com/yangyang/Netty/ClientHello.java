package com.yangyang.Netty;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientHello {
    private static void close(Channel channel,ExecutorService boss,ExecutorService worker){
        channel.close();
        boss.shutdown();
        worker.shutdown();
        System.exit(0);
    }
    public static void main(String[] args) {

        //client server
        ClientBootstrap bootstrap = new ClientBootstrap();

        //threads worker
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //socket channel
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));

        //channel factory
        bootstrap.setPipelineFactory(()->{
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("encoder",new StringEncoder());//write string direct
            pipeline.addLast("decoder",new StringDecoder()); //get msg string
            pipeline.addLast("handler",new HelloHandlerClient()); //get msg string
            return pipeline;
        });

        //connect to server
        ChannelFuture connet = bootstrap.connect(new InetSocketAddress("localhost",NettyHello.PORT));
        Channel channel =connet.getChannel();

        //read string from console
        System.out.println("start client ...");
        Scanner scanner = new Scanner(System.in);

        while (true){
            String readString = scanner.next();
            if(readString.equals("quit")) break;
            channel.write(readString);
        }

        //close system
        close(channel,boss,worker);
    }
}
