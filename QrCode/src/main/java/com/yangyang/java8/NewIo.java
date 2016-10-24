package com.yangyang.java8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NewIo {
    private static final int BUFFER_SIZE = 1024;// buffer is 1M

    private static void nioCopyFile(String resource,String desstination) throws IOException {
        FileInputStream fin = new FileInputStream(resource);
        FileOutputStream fout = new FileOutputStream(desstination);

        FileChannel readChannel = fin.getChannel();
        FileChannel writeChannel = fout.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        while (true){
            buffer.clear();
            int len = readChannel.read(buffer);
            if(len == -1) break;
            buffer.flip();
            writeChannel.write(buffer);
        }
        readChannel.close();
        writeChannel.close();
    }
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\code\\test\\";
        String sourceFilePath = "C:\\code\\test\\demo.txt";

        //nioCopyFile(sourceFilePath,basePath+"new.txt");

        RandomAccessFile raf = new RandomAccessFile(sourceFilePath,"rw");
        FileChannel fc = raf.getChannel();
        MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE,0,raf.length());

        while (buffer.hasRemaining()){
            System.out.print((char) buffer.get());
        }
        buffer.put(0, (byte) 98);
        buffer.put(1, (byte) 98);
        buffer.put(2, (byte) 98);

        fc.close();
    }
}
