package com.yangyang.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HelloHandler extends SimpleChannelInboundHandler<String>{

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("get: " + s);
        channelHandlerContext.channel().writeAndFlush("OK!\r\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exception ...");

        super.exceptionCaught(ctx, cause);
    }

    //connected
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connected ...");

        super.channelActive(ctx);
    }

    //disconnect
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("dicconnected ...");

        super.channelInactive(ctx);
    }

    //like netty3 idelStateAware...
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if(event.state() == IdleState.ALL_IDLE){
                ChannelFuture future = ctx.writeAndFlush("time out,you will close");
                future.addListener((future1)->{
                    ctx.channel().close();
                });
            }
        }else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
