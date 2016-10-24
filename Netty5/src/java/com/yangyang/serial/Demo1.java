package com.yangyang.serial;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Demo1 {
    /**
     * 必须指定 buffer 的大小, netty里面的bufer 比较好
     * @param args
     */
    public static void main(String[] args) {

        int id =101;
        int age = 123456;

        ByteBuffer buffer = ByteBuffer.allocate(18);
        buffer.putInt(id);
        buffer.putInt(age);

        byte[] array = buffer.array();

        System.out.println(Arrays.toString(array));

        ByteBuffer byteBuffer = ByteBuffer.wrap(array);
        System.out.println("id: "+byteBuffer.getInt()+" age: "+byteBuffer.getInt());



    }
    /***
     * 大端序列 (先高位 在低位)
     */
    private static byte[] int2Bytes(int i){
        byte[] bytes = new byte[4];
        bytes[0] = (byte)(i >> 3*8);
        bytes[1] = (byte)(i >> 2*8);
        bytes[2] = (byte)(i >> 1*8);
        bytes[3] = (byte)(i >> 0*8);
        return bytes;
    }
}
