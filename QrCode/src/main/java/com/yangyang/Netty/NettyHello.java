package com.yangyang.Netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NettyHello {
    public static final int PORT = 10101;

    public static void main(String[] args) {

        //server
        ServerBootstrap bootstrap = new ServerBootstrap();

        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //set nioserversocketchannelFactory
        bootstrap.setFactory(new NioServerSocketChannelFactory(boss,worker));

        //set pipeline factory
        bootstrap.setPipelineFactory(()->{
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("encoder",new StringEncoder());//write string direct
            pipeline.addLast("decoder",new StringDecoder()); //get msg string
            pipeline.addLast("hellohandler",new HelloHandler());
            return pipeline;
        });

        bootstrap.bind(new InetSocketAddress(PORT));
        System.out.println("start ...");

    }
}
