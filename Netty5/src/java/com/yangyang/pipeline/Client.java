package com.yangyang.pipeline;

import com.yangyang.constant.HttpCode;

import java.net.Socket;
import java.nio.ByteBuffer;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket(HttpCode.NET_ADDREE,HttpCode.PORT);

        String message = "hello";
        byte[] bytes = message.getBytes();

        ByteBuffer buffer = ByteBuffer.allocate(4+bytes.length);
        buffer.putInt(bytes.length);
        buffer.put(bytes);

        byte[] array = buffer.array();

        for (int i = 0; i < 1000; ++i) {
            socket.getOutputStream().write(array);
        }
        socket.close();
    }
}
