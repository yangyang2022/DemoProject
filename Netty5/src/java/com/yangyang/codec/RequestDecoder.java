package com.yangyang.codec;

import com.yangyang.constant.ConstantValue;
import com.yangyang.model.Request;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

/**
 *
 * data structure | package head | module number | module cmd | length |  data
 *
 * FrameDecoder can help us solve divide-package and distribute
 *
 */
public class RequestDecoder extends FrameDecoder{

    //a package at last has 12 bytes,exclude datas
    public static int BASE_LENGTH = 4+2+2+4;
    @Override
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {

        //check the base length
        if(channelBuffer.readableBytes() >= BASE_LENGTH){

            //aviod socket attack
            if(channelBuffer.readableBytes() > 2048){
                channelBuffer.skipBytes(channelBuffer.readableBytes());
            }

            //record read point ,write-point is always greater read-pointer
            int beginRead = channelBuffer.readerIndex();

            //read package head
            while (channelBuffer.readInt() != ConstantValue.FLAG);

            //read module
            short module = channelBuffer.readShort();
            //read cmd
            short cmd = channelBuffer.readShort();
            //read length
            int length = channelBuffer.readInt();

            if(channelBuffer.readableBytes() < length){
                //datas incomplete ,reset the read-point ,
                channelBuffer.readerIndex(beginRead);
                return null;
            }

            //here read datas
            byte[] data = new byte[length];
            channelBuffer.readBytes(data);

            Request request = new Request(module,cmd,data);

            //invoke down, sendUpstream
            return request;
        }

        //package data Incomplete,waiting ... just return null
        return null;
    }

}
