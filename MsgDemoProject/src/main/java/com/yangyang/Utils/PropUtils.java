package com.yangyang.Utils;

import java.io.IOException;
import java.util.Properties;

public class PropUtils {
    private static Properties jdbcProp;
    private static final String jdbcPropFile = "jdbc.properties";
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
