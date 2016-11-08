package com.yangyang.Client;

import com.yangyang.model.Response;
import com.yangyang.module.counterpart.response.FightResponse;
import org.jboss.netty.channel.*;

public class HelloHandlerClient extends SimpleChannelHandler{
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Response responseMsg = (Response)e.getMessage();
        if(responseMsg.getModule() == 1){
            if(responseMsg.getCmd() == 1){
                FightResponse fightResponse = new FightResponse();
                fightResponse.readFromBytes(responseMsg.getDatas());

                System.out.println("get Gold: "+fightResponse.getGold());
            }
        }
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
