package com.yangyang.converter;

import com.yangyang.model.PointDemo;
import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;

/**
 * 创建类型转换器 extends StrutsTypeConverter
 * 完成 两个方法
 */
public class PointConverter extends StrutsTypeConverter {

    // string -> object
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        PointDemo p = null;
        if(values.length <= 1){
            p = new PointDemo();
            String temp = new String(values[0]);
            String[] xy = temp.split(",");
            p.setX(Integer.parseInt(xy[0]));
            p.setY(Integer.parseInt(xy[1]));
        }
        return p;
    }

    //object -> string
    @Override
    public String convertToString(Map context, Object o) {
        PointDemo point = (PointDemo)o;
        return "("+point.getX()+","+point.getY()+")";
    }
}
