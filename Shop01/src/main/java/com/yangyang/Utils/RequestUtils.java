package com.yangyang.Utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class RequestUtils {
    public static boolean validate(Class clz,HttpServletRequest request){
        Field[] fs = clz.getDeclaredFields();
        boolean isValidate = true;
        Map<String,String> errors = (Map<String, String>) request.getAttribute("errors");
        //request.setAttribute("errors",errors);

        for(Field f:fs){
            if(f.isAnnotationPresent(ValidateForm.class)){
                ValidateForm vf = f.getAnnotation(ValidateForm.class);
                ValidateType vt = vf.type();
                boolean b = true;
                if(vt == ValidateType.NotNull){
                     b = validateNotNull(f.getName(),request);
                    if(!b){
                        isValidate = false;
                        errors.put(f.getName(),vf.errorMsg());
                    }
                }else if(vt == ValidateType.Length){
                    b = validateLength(f.getName(),request,vf.value());
                    if(!b){
                        isValidate = false;
                        errors.put(f.getName(),vf.errorMsg());
                    }
                }else if (vt == ValidateType.Number){
                    b = validateNumber(f.getName(),request);
                    if(!b){
                        isValidate = false;
                        errors.put(f.getName(),vf.errorMsg());
                    }
                }
            }
        }
        return isValidate;
    }
    private static boolean validateNumber(String name,HttpServletRequest requset){
        try {
            Integer.parseInt(requset.getParameter(name));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    private static boolean validateLength(String name,HttpServletRequest request,String defaultLength){
        String v = request.getParameter(name);
        int length = Integer.parseInt(defaultLength);
        if( v.length() < length || v == null || "".equals(v.trim())){
            return false;
        }
        return true;
    }
    private static boolean validateNotNull(String name,HttpServletRequest request){
        //没有这个表单就不用验证
        if(!request.getParameterMap().containsKey(name)) return true;
        String v = request.getParameter(name);
        if(v == null || "".equals(v.trim())){
            return false;
        }
        return true;
    }
    public static Object setParams(Class clz,HttpServletRequest request){
        Map<String,String[]> params = request.getParameterMap();
        Set<String> keys = params.keySet();
        Object o = null;
        try {
            o = clz.newInstance();
            for(String key:keys){
                if(key.equals("method"))continue;
                String[] vv = params.get(key);
                if(vv.length > 1){
                    BeanUtils.copyProperty(o,key,vv);
                }else{
                    BeanUtils.copyProperty(o,key,vv[0]);
                }
            }
        } catch (Exception e) {
        }
        return  o;
    }
}
