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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.yangyang.netty.StaticUtil.NET_ADDRESS;
import static com.yangyang.netty.StaticUtil.NET_PORT;

/**
 * 多连接客户端
 */
public class MultiClient {


    //service class
    private Bootstrap bootstrap = new Bootstrap();

    //session
    private List<Channel> channels = new ArrayList<>();

    //reference count
    private final AtomicInteger index = new AtomicInteger();

    public void init(int count){
        EventLoopGroup worker = new NioEventLoopGroup();
        bootstrap.group(worker);
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

        for (int i = 1; i <= count; ++i) {
            //connected server
            ChannelFuture connet = bootstrap.connect(NET_ADDRESS, NET_PORT);
            channels.add(connet.channel());
        }
    }

    //get session/channel
    public Channel getChannel(){
        return getFirstActiveChannel(0);
    }
    private Channel getFirstActiveChannel(int count){
        Channel channel = channels.get(Math.abs(index.getAndIncrement() % channels.size()));
        if(!channel.isActive()){
            //重连
            reconnect(channel);

            if(count >= channels.size())
                throw new RuntimeException("没有连接可用!");
            return getFirstActiveChannel(count+1);
        }
        return channel;
    }
    private void reconnect(Channel channel){
        synchronized (channel){
            if(channels.indexOf(channel) == -1){
                return;
            }
            Channel newChannel = bootstrap.connect(NET_ADDRESS, NET_PORT).channel();
            channels.set(channels.indexOf(channel),newChannel);
        }
    }
}
