import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

public class TestStax {

    //基于光标的方式 --- demo1 and demo2
    @Test
    public void testDemo() {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        InputStream is = null;
        try {
            is = TestStax.class.getResourceAsStream("/books.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);

            while (reader.hasNext()){
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT){
                    System.out.println(reader.getName());
                }else if(type == XMLStreamConstants.CHARACTERS){
                    //文本节点
                    System.out.println(reader.getText());
                }else if(type == XMLStreamConstants.END_ELEMENT){
                    System.out.println("/"+reader.getName());
                }
            }


        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testDemo2() {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        InputStream is = null;
        try {
            is = TestStax.class.getResourceAsStream("/books.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(is);

            while (reader.hasNext()){
                int type = reader.next();
                if(type == XMLStreamConstants.START_ELEMENT){
                    String name = reader.getName().toString();
                    if(name.equals("book")){
                        System.out.println(reader.getAttributeName(0) + " : " + reader.getAttributeValue(0));
                    }
                }
            }


        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //基于迭代模型的方式
    @Test
    public void testDemo3() {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        InputStream is = null;
        try {
            is = TestStax.class.getResourceAsStream("/books.xml");
            XMLEventReader reader = factory.createXMLEventReader(is);
            while (reader.hasNext()){
                //通过XMLEvent来获取是否是某种类型
                XMLEvent event = reader.nextEvent();
                if(event.isStartElement()){
                    //通过asXXX来获取类型
                    String name = event.asStartElement().getName().toString();
                    if(name.equals("title")){
                        System.out.print(reader.getElementText()+" : ");
                    }
                    if(name.equals("price")){
                        System.out.println(reader.getElementText());
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }finally {
            try {
                if(is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //通过xpath查询 --> 先创建文本对象 --> 创建xpath对象
    @Test
    public void testDemo4() {
        InputStream is = null;
        try {
            is = TestStax.class.getResourceAsStream("/books.xml");
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(is);
            XPath xpath = XPathFactory.newInstance().newXPath();

            NodeList list = (NodeList) xpath.evaluate("//book[@category='WEB']",doc, XPathConstants.NODESET);

            for (int i = 0; i < list.getLength(); ++i) {
                Element element = (Element) list.item(i);

                System.out.println(element.getElementsByTagName("title").item(0).getTextContent());
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    //创建xmlwriter
    @Test
    public void testDemo7() throws XMLStreamException {
        XMLStreamWriter writer = XMLOutputFactory.newFactory().createXMLStreamWriter(System.out);
        writer.writeStartDocument("utf-8","1.0");
        writer.writeEndDocument();

        String ns = "http://localhost:3306";
        writer.writeStartElement("ns","person",ns);

        writer.writeStartElement(ns,"id");
            writer.writeCharacters("1");
        writer.writeEndElement();

        writer.writeEndElement();

        writer.flush();
        writer.close();
    }


    //使用transform来修改文档
    @Test
    public void testDemo8() {
        InputStream is = null;
        try {
            is = TestStax.class.getResourceAsStream("/books.xml");
            DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = db.parse(is);
            XPath xpath = XPathFactory.newInstance().newXPath();

            //修改文档
            Transformer trans = TransformerFactory.newInstance().newTransformer();
            trans.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            //第一行换行
            trans.setOutputProperty(OutputKeys.INDENT,"yes");
            //这里找到的是book节点
            NodeList list = (NodeList) xpath.evaluate("//book[title='Learning XML']",doc, XPathConstants.NODESET);
            //这里是price节点
            Element node = (Element) ((Element) list.item(0)).getElementsByTagName("price").item(0);
            //修改节点
            node.setTextContent("99.99");
            trans.transform(new DOMSource(doc),new StreamResult(System.out));

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
