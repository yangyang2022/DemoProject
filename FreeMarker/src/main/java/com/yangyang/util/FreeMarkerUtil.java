package com.yangyang.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class FreeMarkerUtil {

    private static FreeMarkerUtil util;
    private static Configuration configuration;

    private FreeMarkerUtil() {}

    public static FreeMarkerUtil getInstance(String pname) throws TemplateModelException {
        if(util == null){
            util = new FreeMarkerUtil();
            configuration = new Configuration(Configuration.VERSION_2_3_23);
            configuration.setSharedVariable("company","yangyanghud.com");
            configuration.setClassForTemplateLoading(FreeMarkerUtil.class,pname);
        }
        return util;
    }

    private Template getTemplate(String fname){
        try {
            return configuration.getTemplate(fname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过标准输出流输出
    public void sprint(Map<String,Object> map, String fname){
        try {
            getTemplate(fname).process(map,new PrintWriter(System.out));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void fprint(Map<String,Object> map,String fname,String outFilePath){
        try {
            getTemplate(fname).process(map,new FileWriter(outFilePath));
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
