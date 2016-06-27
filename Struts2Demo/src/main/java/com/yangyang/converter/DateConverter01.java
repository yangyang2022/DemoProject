package com.yangyang.converter;

import org.apache.struts2.util.StrutsTypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class DateConverter01 extends StrutsTypeConverter {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // string -> object
    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        try {
            if(values.length <= 1){
                String d = values[0];
                return sdf.parse(d);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //object -> string
    @Override
    public String convertToString(Map context, Object o) {
        return sdf.format((Date)o);
    }
}
