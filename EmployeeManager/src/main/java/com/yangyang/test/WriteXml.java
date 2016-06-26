package com.yangyang.test;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteXml {

    public static Element writeList2XML(Element root,List<Person> persons){
        for(Person person :persons){
            Element ep = root.addElement("person");
            ep.addAttribute("age",String.valueOf(person.getAge()));
            ep.addElement("name").setText(person.getName());
            ep.addElement("sex").setText(person.getSex());
            ep.addElement("idcard").setText(person.getIdcard());
        }
        return root;
    }

    public static void main(String[] args) {
        Document d = DocumentHelper.createDocument();
        d.addElement("persons");
        Element root = d.getRootElement();
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(11,"zhangsan","man","2011080329"));
        persons.add(new Person(11,"zhangsan","man","2011080329"));
        persons.add(new Person(12,"lisi","feman","2011080330"));
        persons.add(new Person(13,"wamgwu","feman","2011080331"));
        persons.add(new Person(14,"zhaoliu","man","2011080332"));
        root = writeList2XML(root,persons);
        XMLWriter out = null;
        String path = WriteXml.class.getResource("/test/persons.xml").getPath();
        //path = path.replace("bin","src");//eclipse replace path
        // idea path replace
        path = path.replace("target/classes","src/main/resources");
        try {
            out = new XMLWriter(new FileWriter(path), OutputFormat.createPrettyPrint());
            out.write(d);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null)out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main1(String[] args) {
        //create a document
        Document doc = DocumentHelper.createDocument();
        doc.addElement("users");
        XMLWriter out = null;

        try {

            Element root = doc.getRootElement();
            Element eu = root.addElement("user");
            eu.addAttribute("id","1");

            eu.addElement("username").addText("zs");
            eu.addElement("password").addText("123123");

            eu = root.addElement("user2");
            eu.addAttribute("id","2");
            eu.addElement("username").addText("li");
            eu.addElement("password").addText("123123");

            String path = WriteXml.class.getResource("/test/users.xml").getPath();
            //path = path.replace("bin","src");//eclipse replace path
            // idea path replace
            path = path.replace("target/classes","src/main/resources");
            out = new XMLWriter(new FileWriter(path), OutputFormat.createPrettyPrint());
            //write element
            out.write(doc);
            System.out.println("ok ... ");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null){
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
