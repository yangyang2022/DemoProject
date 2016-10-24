package com.yangyang.protobuf;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class paly2Bytes {

    private static byte[] bytes(Player player) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(player);
        byte[] bytes = byteArrayOutputStream.toByteArray();

        System.out.println("length: "+bytes.length);
        System.out.println(Arrays.toString(bytes));

        return bytes;
    }
    private static Player rebytes(byte[] bs) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bs));
        Player player = (Player)objectInputStream.readObject();
        System.out.println(player);

        return player;
    }
    public static void main(String[] args) throws Exception {
        List<Integer> skills = Arrays.asList(1,2,3,4,5);

        Player player = new Player(1, "hello", 12, skills);

        byte[] bytes = bytes(player);

        rebytes(bytes);

    }
}
