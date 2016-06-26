package com.yangyang.Utils;

import com.yangyang.test.WriteXml;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XMLUtil {
    private static Document userDocument;
    private static Document depDocuement ;
    private static Document empDocuemnet;

    public static Document getEmpDocuemnet(){
        if(empDocuemnet != null) return empDocuemnet;
        SAXReader reader = new SAXReader();
        try {
            empDocuemnet = reader.read(XMLUtil.class.getResource("/xml/emps.xml"));
            return empDocuemnet;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return empDocuemnet;
    }

    public static Document getDepDocuement(){
        if(depDocuement != null) return depDocuement;
        SAXReader reader = new SAXReader();
        try {
            depDocuement = reader.read(XMLUtil.class.getResource("/xml/deps.xml"));
            return depDocuement;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return depDocuement;
    }
    public static Document getUserDocument(){
        if(userDocument != null ) return userDocument;
        SAXReader reader = new SAXReader();
        try {
            userDocument = reader.read(XMLUtil.class.getResource("/xml/users.xml"));
            return userDocument;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getMaxId(Document dd,String xpath){
        //String xpath = "/deps/dep/id";
        List<Element> eles = dd.selectNodes(xpath);

        if(eles == null || eles.size() == 0) return 0;
        List<Integer> ids = new ArrayList<>();
        for(Element e:eles){
            if(e == null || "".equals(e.getTextTrim())) continue;
            ids.add(Integer.parseInt(e.getTextTrim()));
        }
        Collections.sort(ids);
        return ids.size() == 0? 0:ids.get(ids.size()-1);
    }

    public static void write2XML(Document d,String name){
        String path = WriteXml.class.getResource(name).getPath();
        //path = path.replace("bin","src");//eclipse replace path
        // idea path replace
        path = path.replace("target/classes","src/main/resources");

        XMLWriter out = null;
        try {
            out = new XMLWriter(new FileWriter(path), OutputFormat.createPrettyPrint());
            out.write(d);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
