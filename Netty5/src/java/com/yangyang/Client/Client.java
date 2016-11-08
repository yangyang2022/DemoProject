package com.yangyang.Client;

import com.yangyang.codec.RequestEncoder;
import com.yangyang.codec.ResponseDecoder;
import com.yangyang.constant.HttpCode;
import com.yangyang.model.Request;
import com.yangyang.module.counterpart.request.FightRequest;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    private static void close(Channel channel,ExecutorService boss,ExecutorService worker){
        channel.close();
        boss.shutdown();
        worker.shutdown();
        System.exit(0);
    }
    public static void main(String[] args) throws InterruptedException {

        //client server
        ClientBootstrap bootstrap = new ClientBootstrap();

        //threads worker
        ExecutorService boss = Executors.newCachedThreadPool();
        ExecutorService worker = Executors.newCachedThreadPool();

        //socket channel
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));

        //channel factory
        bootstrap.setPipelineFactory(()->{
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("encoder",new RequestEncoder());//write string direct
            pipeline.addLast("decoder",new ResponseDecoder()); //get msg Response
            pipeline.addLast("handler",new HelloHandlerClient()); //get msg string
            return pipeline;
        });

        StringDecoder stringDecoder = new StringDecoder();
        StringEncoder stringEncoder = new StringEncoder();

        //connect to server
        ChannelFuture connet = bootstrap.connect(new InetSocketAddress(HttpCode.NET_ADDREE, HttpCode.PORT));
        Channel channel =connet.sync().getChannel();

        //read string from console
        System.out.println("start client ...");
        Scanner scanner = new Scanner(System.in);

        while (true){
            //String readString = scanner.nextLine();
            //if(readString.equals("quit")) break;
            //channel.write(readString);

            System.out.println("please input ...");
            int counterpartId = scanner.nextInt();
            int count = scanner.nextInt();

            if(count == 0 ) break;

            FightRequest fightRequest = new FightRequest(counterpartId,count);

            Request request = new Request((short)1,(short)1,fightRequest.getBytes());

            channel.write(request);

        }

        //close system
        close(channel,boss,worker);
    }
}
