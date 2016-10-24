package com.yangyang.Netty;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OldServer {

    public static final int PORT = 8888;

    public static void main(String[] args) throws Exception{

        ExecutorService service = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server start ... ");
        while (true){
            final Socket socket = serverSocket.accept();
            System.out.println("a new client");

            service.submit(()->handleSocket(socket));

        }
    }

    private static void handleSocket(Socket socket){
        try {
            byte[] buffer = new byte[1024];
            InputStream is = socket.getInputStream();
            while(true){
                int read = is.read(buffer);
                if(read != -1) System.out.print(new String(buffer,0,read));
                else break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("socket close ...");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
