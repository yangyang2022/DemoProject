package com.yangyang.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HelloClient {

    public static void main(String[] args) {

        //client service --> not ClientBootstrap
        Bootstrap bootstrap = new Bootstrap();

        //boss listen port and work send/recevie msg
        EventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(worker);

        try {
            //set socket factory
            bootstrap.channel(NioSocketChannel.class);

            //set channel
            bootstrap.handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new StringDecoder());
                    channel.pipeline().addLast(new StringEncoder());
                    channel.pipeline().addLast(new HelloClientHandler());
                }
            });

            //connected server
            ChannelFuture connet = bootstrap.connect("localhost", 10101);

            //read data from console
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String s = br.readLine();
                if(s.equals("quit")) break;
                connet.channel().writeAndFlush(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }

}
