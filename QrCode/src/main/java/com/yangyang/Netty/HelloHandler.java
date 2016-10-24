package com.yangyang.Netty;

import org.jboss.netty.channel.*;

public class HelloHandler extends SimpleChannelHandler{
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {

        //System.out.println(e.getMessage());
        //ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        //String s = new String(buffer.array());
        //System.out.println("get: "+s);

        System.out.println((String) e.getMessage());

        //send mgs to client,msg must intanceof ChannelBuffer or File...
        //ChannelBuffer copiedBuffer = ChannelBuffers.copiedBuffer("get it!\r\n".getBytes());
        //ctx.getChannel().write(copiedBuffer);
        ctx.getChannel().write("get it!\r\n");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        System.out.println("exceptionCaught");
        super.exceptionCaught(ctx, e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelConnected");
        super.channelConnected(ctx, e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelDisconnected");
        super.channelDisconnected(ctx, e);
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("channelClosed");
        super.channelClosed(ctx, e);
    }
}
