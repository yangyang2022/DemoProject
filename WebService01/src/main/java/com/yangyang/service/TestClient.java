package com.yangyang.service;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class TestClient {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://localhost:8888/ns?wsdl");
        QName qName = new QName("http://service.yangyang.com/","MyServiceService");
        Service service = Service.create(url,qName);

        //使用了自己的接口...不太好
        IMyService ms = service.getPort(IMyService.class);

        System.out.println(ms.add(99, 11));
    }
}
