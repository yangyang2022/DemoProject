package com.yangyang.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.List;
import java.util.function.Consumer;

public class TestXml {

    //xpath 在线文档 http://www.w3school.com.cn/xpath/


    public static void main(String[] args) {
        Consumer printObject = System.out::println;

        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(TestXml.class.getResource("/test/books.xml"));

            Element root = doc.getRootElement();
            System.out.println("rootName: "+root.getName());

            //使用相对路径查找 从当前节点出发 寻找 book 子节点
            List<Element> eles = root.selectNodes("book");
            System.out.println("size: "+eles.size());

            //使用绝对路径查找
            eles = root.selectNodes("/books/book");
            System.out.println("size: "+eles.size());

            //以上查找方式不能查找 子节点

            eles = root.selectNodes("//book");
            System.out.println("size: "+eles.size());

            eles = root.selectNodes("/books/book[author='yangyang']");
            System.out.println("size: "+eles.get(0).elementTextTrim("title"));

            //查找属性 id
            eles = root.selectNodes("/books/book[@id<2]");
            System.out.println("size: "+eles.get(0).elementText("title"));

            //查找 名称中 包含有java 的 price节点
            eles = root.selectNodes("/books/book[contains(title,'think')]/price");
            System.out.println("yangyang");
            eles.stream().forEach(e-> System.out.println(e.getTextTrim()));

            //查找 名称中 包含有java 的 并且 price < 50 节点
            System.out.println("contains java and price < 50: ");
            eles = root.selectNodes("/books/book[contains(title,'java') and price < 50]");
            eles.stream().forEach(e-> System.out.println(e.elementText("title") + " : "+e.elementText("price")));

            //List<Element> eles = root.elements();
            //get attributeValue -> id
            //eles.stream().map(e->e.attributeValue("id")).forEach(printObject);
            //eles.stream().map(e->e.elementText("title")+" : "+e.elementText("price")).forEach(printObject);

        } catch (DocumentException e) {
            System.out.println("file not exsist ... ");
            //e.printStackTrace();
        }
    }
}
