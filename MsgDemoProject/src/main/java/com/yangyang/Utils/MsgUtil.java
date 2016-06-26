package com.yangyang.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgUtil {
    private static final String dateFormate = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(dateFormate);

    public static String formateDate(Date date){
        return sdf.format(date);
    }
}
