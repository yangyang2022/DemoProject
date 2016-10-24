package com.yangyang.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HelloClientHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("client get: "+s);

    }

}
