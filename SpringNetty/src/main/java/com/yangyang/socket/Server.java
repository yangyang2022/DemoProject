package com.yangyang.socket;

import com.yangyang.Util.HttpUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {

        new Server().start();

    }
    private void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(HttpUtil.NET_PORT);
        new Thread(new Acctor(serverSocket)).start();
    }
    private class Acctor implements Runnable{

        private ServerSocket serverSocket;

        public Acctor(ServerSocket serverSocket) {
            this.serverSocket = serverSocket;
        }

        @Override
        public void run() {
            while (true){
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    if(socket != null){
                        System.out.println("connect: "+socket.getRemoteSocketAddress());
                        new Thread(new Processor(socket)).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class Processor implements Runnable{

        private Socket socket;

        public Processor(Socket socket) {
            this.socket = socket;
        }

        private void writeMsg2Client(Socket socket,String msg) throws IOException {
            PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            out.write(msg);
        }
        private void writeOK2Client(Socket socket) throws IOException {
            writeMsg2Client(socket,"OK\r\n");
        }
        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String readLine;
                while (true){
                    readLine = br.readLine();
                    System.out.println("getMsg : "+readLine);
                    if("end".equals(readLine)) {
                        socket.sendUrgentData(0xff);
                        break;
                    }
                    PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
                    out.write("OK\r\n");
                    out.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
