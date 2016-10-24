package com.yangyang.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class HelloServer {

    public static void main(String[] args) throws InterruptedException {

        //server
        ServerBootstrap bootstrap = new ServerBootstrap();

        //boss and worker
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        //set boss and worker
        bootstrap.group(boss,worker);

        //set socket factory
        bootstrap.channel(NioServerSocketChannel.class);

        //set pipeline factory
        bootstrap.childHandler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel channel) throws Exception {
                //time out
                channel.pipeline().addLast(new IdleStateHandler(5,5,10));
                channel.pipeline().addLast(new StringDecoder());
                channel.pipeline().addLast(new StringEncoder());
                channel.pipeline().addLast(new HelloHandler());
            }
        });

        //set option,not only netty, here is TCP option

        //set serverSoketChannel connetion poolsize,max 2048
        bootstrap.option(ChannelOption.SO_BACKLOG,2048);
        //set soketchannel connetion alive
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
        //set soketchannel,close lazy send
        bootstrap.childOption(ChannelOption.TCP_NODELAY,true);


        //bind inet port
        ChannelFuture future = bootstrap.bind(10101);

        System.out.println("start ...");

        //wait server close then go down
        future.channel().closeFuture().sync();

        //close
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
