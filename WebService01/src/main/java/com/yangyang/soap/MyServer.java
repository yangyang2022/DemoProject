package com.yangyang.soap;

import javax.xml.ws.Endpoint;

public class MyServer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8989/ms",new MyService());
    }
}
