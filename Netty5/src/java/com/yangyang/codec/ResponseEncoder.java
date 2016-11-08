package com.yangyang.codec;

import com.yangyang.constant.ConstantValue;
import com.yangyang.model.Response;
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
public class ResponseEncoder extends OneToOneEncoder{

    // request --> channelbuffer
    private void writeDataToChannel(ChannelBuffer buffer,Response response){

        //write package head
        buffer.writeInt(ConstantValue.FLAG);

        //write module number
        buffer.writeShort(response.getModule());

        //write cmd
        buffer.writeShort(response.getCmd());

        //write statecode
        buffer.writeInt(response.getStateCode());

        //write length
        buffer.writeInt(response.getDataLength());

        //write data
        if(response.getDatas() != null){
            buffer.writeBytes(response.getDatas());
        }

    }
    @Override
    protected Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object req) throws Exception {
        Response response = (Response)req;

        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();

        //write data to channelbuffer
        writeDataToChannel(buffer,response);

        return buffer;
    }
}
