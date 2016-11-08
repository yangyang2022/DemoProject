package com.yangyang.codec;

import com.yangyang.constant.ConstantValue;
import com.yangyang.model.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * request codec object
 *
 *  data structure | package head | module number | module cmd | length | data
 *
 */
public class RequestEncoder extends OneToOneEncoder{

    // request --> channelbuffer
    private void writeDataToChannel(ChannelBuffer buffer,Request request){

        //write package head
        buffer.writeInt(ConstantValue.FLAG);

        //write module number
        buffer.writeShort(request.getModule());

        //write cmd
        buffer.writeShort(request.getCmd());

        //write length
        buffer.writeInt(request.getDataLength());

        //write data
        if(request.getDatas() != null){
            buffer.writeBytes(request.getDatas());
        }

    }
    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object req) throws Exception {
        Request request = (Request)req;

        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

        //write data to channelbuffer
        writeDataToChannel(buffer,request);

        return buffer;
    }
}
