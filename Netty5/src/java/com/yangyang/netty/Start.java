package com.yangyang.netty;

import java.io.IOException;

public class Start {
    public static void main(String[] args) throws IOException {

        MultiClient client = new MultiClient();
        client.init(5);

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //
        //while (true){
        //    String msg = br.readLine();
        //    client.getChannel().writeAndFlush(msg);
        //}



    }
}
