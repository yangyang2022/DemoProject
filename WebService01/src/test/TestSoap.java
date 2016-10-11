import com.yangyang.service.User;
import org.junit.Test;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.transform.Source;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;

public class TestSoap {

    @Test
    public void testDemo1() throws SOAPException, IOException {
        //1 : 创建消息工厂
        MessageFactory factory = MessageFactory.newInstance();
        //2: 通过工厂创建soap message
        SOAPMessage message = factory.createMessage();
        //3: 创建soappart
        SOAPPart part = message.getSOAPPart();
        //4: 获取soapEnvelope
        SOAPEnvelope envelope = part.getEnvelope();
        //5: 可以通过envelope 获取body head 等消息
        SOAPBody body = envelope.getBody();
        //6: 根据QName创建相应的节点(QName就是带有命名空间的节点)
        QName qName = new QName("www.baidu.com","add","ns");
        //body.addBodyElement(qName).setTextContent("123123");
        //不能这样使用
        body.addBodyElement(qName).setTextContent("<a>123</a>");
        //打印消息信息
        message.writeTo(System.out);
    }

    private String ns = "http://soap.yangyang.com/";
    private String wsdUrl = "http://localhost:8989/ms?wsdl";
    //1:提交给服务器
    @Test
    public void testDemo2() throws IOException, SOAPException {

        //1: 创建服务
        URL url = new URL(wsdUrl);
        QName sname = new QName(ns,"MyServiceService");
        Service service = Service.create(url,sname);

        //2: 创建dispatcher
        Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns,"MyServicePort"),SOAPMessage.class,Service.Mode.MESSAGE);

        //3: 创建soapmessage
        SOAPMessage message = MessageFactory.newInstance().createMessage();
        SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
        SOAPBody body = envelope.getBody();

        //4: 创建qname来制定消息传递数据
        QName qName = new QName(ns,"add","nn");
        SOAPBodyElement ele = body.addBodyElement(qName);
        ele.addChildElement("a").setValue("22");
        ele.addChildElement("b").setValue("33");

        //输出消息
        message.writeTo(System.out);

        System.out.println("\n invoking ... ");
        //传递消息 返回响应消息
        SOAPMessage respone = dispatch.invoke(message);
        respone.writeTo(System.out);

        //将相应的消息转换为doc文档
        Document doc = respone.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();

        String res = doc.getElementsByTagName("addResult").item(0).getTextContent();
        System.out.println("\nget : " + res);

    }

    @Test
    public void testDemo3() throws IOException, SOAPException, JAXBException {

        //1: 创建服务
        URL url = new URL(wsdUrl);
        QName sname = new QName(ns,"MyServiceService");
        Service service = Service.create(url,sname);

        //2: 创建dispatcher (负载的方式创建 通过string传递)
        Dispatch<Source> dispatch = service.createDispatch(new QName(ns,"MyServicePort"),Source.class,Service.Mode.PAYLOAD);

        User user = new User(2,"zhangsan","123123","张三");
        //编排
        JAXBContext ctx = JAXBContext.newInstance(User.class);
        Marshaller marshaller = ctx.createMarshaller();

        //这里不显示头信息
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT,true);

        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(user,stringWriter);

        //封装响应的part addUser
        String payLoad = "<nn:addUser xmlns:nn=\""+ns+"\">"+stringWriter.toString()+"</nn:addUser>";
        System.out.println(payLoad);

        //通过服dispatcher传递负载
        StAXSource response = (StAXSource) dispatch.invoke(new StreamSource(new StringReader(payLoad)));

        //处理响应信息

    }
}
