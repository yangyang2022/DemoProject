package com.yangyang.web.Test;

import com.yangyang.web.model.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBeanUtils {
    @Test
    public void testUser01() throws InvocationTargetException, IllegalAccessException {
        Person p = new Person();
        String key = "username";
        String value = "张三";
        BeanUtils.copyProperty(p,key,value);
        BeanUtils.copyProperty(p,"age",12);
        System.out.println(p);
        Person p2 = new Person();
        p2.setId(1);p2.setAge(33);p2.setBorn(new Date());
        p2.setPassword("123123");p2.setUsername("yangyang");

        BeanUtils.copyProperties(p,p2);
        System.out.println(p);

    }

    @Test
    public void testDemo2() throws InvocationTargetException, IllegalAccessException {
        //copy date can't be convert
        // ==> 1 创建一个类实现Convert 接口
        // ==> 2 实现接口中的方法
        // ==> 3 在拷贝之前注册转换器
        //Converter
        Person p = new Person();
        ConvertUtils.register(new ConverterDate(),Date.class);
        ConvertUtils.register(new ConvertPoint(),Point.class);

        BeanUtils.copyProperty(p,"born","1992-09-09");
        BeanUtils.copyProperty(p,"p","11,22");
        System.out.println(p.getP());
        System.out.println(p.getBorn());

    }
}

class ConverterDate implements Converter{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public <T> T convert(Class<T> type, Object value) {
        try {
            return (T) sdf.parse((String)value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class ConvertPoint implements Converter{
    @Override
    public <T> T convert(Class<T> type, Object value) {
        if(type != Point.class) return null;
        String[] xy = ((String)value).split(",");
        int x = Integer.parseInt(xy[0]);
        int y = Integer.parseInt(xy[1]);
        return (T) new Point(x,y);
    }
}