package com.yangyang.beanScanner;

import java.util.HashMap;
import java.util.Map;

public class InvokeHolder {

    public static Map<Short,Map<Short,Invoker>> invokers = new HashMap<>();

    public static void addInvoker(short module,short cmd,Invoker invoker){
        Map<Short,Invoker> map = invokers.get(module);
        if(map == null) {
            map = new HashMap<>();
        }
        map.put(cmd,invoker);
        invokers.put(module,map);
    }

    public static Invoker getInvoker(short module,short cmd){
        Map<Short,Invoker> map = invokers.get(module);
        if(map != null) return map.get(cmd);
        return null;
    }

}
