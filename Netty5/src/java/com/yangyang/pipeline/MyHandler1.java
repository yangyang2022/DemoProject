package com.yangyang.pipeline;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

public class MyHandler1  extends SimpleChannelHandler{
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ChannelBuffer buffer = (ChannelBuffer)e.getMessage();
        byte[] array = buffer.array();
        String msg = new String(array);
        System.out.println("handler1 msg: "+msg);

        //distribute handler2
        ctx.sendUpstream(new UpstreamMessageEvent(ctx.getChannel(),msg,e.getRemoteAddress()));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelConnected(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
    }
}
