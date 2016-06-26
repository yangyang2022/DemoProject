package com.yangyang.web.Util;

import java.io.IOException;
import java.util.Properties;

public class PropUtils {
    private static Properties jdbcProp;
    private static Properties daoProp;
    private static final String jdbcPropFile = "jdbc.properties";
    private static final String daoPropFile = "dao.properties";

    public static Properties getDaoProperties(){
        if(daoProp == null){
            daoProp = new Properties();
            try {
                daoProp.load(PropUtils.class.getClassLoader().getResourceAsStream(daoPropFile));
            } catch (IOException e) {
                System.out.println("资源文件读取失败!");
            }
        }
        return daoProp;
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
