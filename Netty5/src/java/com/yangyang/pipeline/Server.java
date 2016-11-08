package com.yangyang.pipeline;

import com.yangyang.constant.HttpCode;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

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
            pipeline.addLast("decoder",new MyDecoder());
            pipeline.addLast("handler1",new SimpleHandler());
            return pipeline;
        });

        bootstrap.bind(new InetSocketAddress(HttpCode.NET_ADDREE,HttpCode.PORT));
        System.out.println("start ...");

    }
}
