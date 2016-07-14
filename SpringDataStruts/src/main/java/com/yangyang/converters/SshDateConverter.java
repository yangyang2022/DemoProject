package com.yangyang.converters;

import org.apache.struts2.util.StrutsTypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SshDateConverter extends StrutsTypeConverter{

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        if(toClass == LocalDate.class){
            return LocalDate.parse(values[0],formatter);
        }
        return null;
    }

    @Override
    public String convertToString(Map context, Object o) {
        if(o instanceof LocalDate){
            return formatter.format((LocalDate)o);
        }
        return null;
    }
}
