package com.yangyang.pipeline;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

public class MyDecoder extends FrameDecoder {

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {

        if(buffer.readableBytes() > 4){

            //avoid socket attack
            if(buffer.readableBytes() > 2048){
                // clear buffer then can't recognition the package head
                //so data structure must be | head | length | data
                buffer.skipBytes(buffer.readableBytes());
            }

            //mark
            buffer.markReaderIndex();

            //read length
            int length = buffer.readInt();
            if(buffer.readableBytes() < length){
                //data incomplete
                buffer.resetReaderIndex();
                return null;
            }

            //read data from buffer
            byte[] bytes = new byte[length];
            buffer.readBytes(bytes);

            //distribute down --> handler1 to handler2
            return new String(bytes);
        }
        //waiting data
        return null;
    }
}
