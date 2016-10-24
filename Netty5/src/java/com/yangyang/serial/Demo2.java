package com.yangyang.serial;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import java.util.Arrays;

public class Demo2 {
    public static void main(String[] args) {

        ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer();

        channelBuffer.writeInt(1010101);
        channelBuffer.writeDouble(3.1415926);

        byte[] bytes = new byte[channelBuffer.writerIndex()];
        channelBuffer.readBytes(bytes);

        System.out.println(Arrays.toString(bytes));

    }
}

