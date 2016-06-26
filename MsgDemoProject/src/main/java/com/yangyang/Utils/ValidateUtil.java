package com.yangyang.Utils;

import com.yangyang.mode.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public static boolean checkAdminOrSelf(HttpSession session,int user_id){
        User u = (User) session.getAttribute("loginUser");
        if(u == null) return false;
        if(u.getType() == 1) return true;
        else if(u.getId() == user_id) return true;
        else return false;
    }
}
