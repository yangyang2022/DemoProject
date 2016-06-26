package com.yangyang.Utils;

import java.io.IOException;
import java.util.Properties;

public class PropUtils {
    private static Properties jdbcProp;
    private static Properties factoryProp;
    private static final String jdbcPropFile = "jdbc.properties";

    public static Properties getDaoFacoryProperties(){
        if(factoryProp == null){
            factoryProp = new Properties();
            try {
                factoryProp.load(PropUtils.class.getClassLoader().getResourceAsStream("dao.properties"));
            } catch (IOException e) {
                System.out.println("资源文件读取失败!");
            }
        }
        return factoryProp;
    }

    public static Properties getProperties(){
        if(jdbcProp == null){
            jdbcProp = new Properties();
            try {
                jdbcProp.load(PropUtils.class.getClassLoader().getResourceAsStream(jdbcPropFile));
            } catch (IOException e) {
                System.out.println("资源文件读取失败!");
            }
        }
        return jdbcProp;
    }
}
