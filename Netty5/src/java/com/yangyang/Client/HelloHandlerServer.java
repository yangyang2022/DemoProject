package com.yangyang.Client;

import com.yangyang.constant.StateCode;
import com.yangyang.model.Request;
import com.yangyang.model.Response;
import com.yangyang.module.counterpart.request.FightRequest;
import com.yangyang.module.counterpart.response.FightResponse;
import org.jboss.netty.channel.*;

public class HelloHandlerServer extends SimpleChannelHandler{
    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        Request request = (Request)e.getMessage();
        int moduleNumber = request.getModule();
        if( moduleNumber == 1){
            if(request.getCmd() == 1){
                FightRequest fightRequest = new FightRequest();
                fightRequest.readFromBytes(request.getDatas());
                System.out.println("counterPartId: "+fightRequest.getCounterpartId()
                        +" ,count: "+fightRequest.getCount());

                //write data to client
                FightResponse fightResponse = new FightResponse();
                fightResponse.setGold(999);
                Response resp = new Response((short)1,(short)1, StateCode.SUCCESS,fightResponse.getBytes());

                ctx.getChannel().write(resp);
            }
        }else if(moduleNumber == 2){
            System.out.println("module 2 ...");

        }
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
