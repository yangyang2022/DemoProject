package com.yangyang.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class ValidateUtil {
    public static boolean validateNull(HttpServletRequest request,String[] fields){
        boolean validate = true;
        Map<String,String> errorMap = new HashMap<>();

        for(String field:fields){
            String value = request.getParameter(field);
            if(value == null || "".equals(value.trim())){
                validate = false;
                errorMap.put(field,field+" 不能为空!");
            }
        }
        if(!validate) request.setAttribute("error",errorMap);
        return validate;
    }
    public static String showErrorMsg(Map<String,String> errorMap,String field){
        //Map<String,String> errorMap = (Map<String, String>) request.getAttribute("error");
        if(errorMap == null ) return "";
        String msg = errorMap.get(field);
        if(msg == null ) return "";
        return msg;
    }
}
