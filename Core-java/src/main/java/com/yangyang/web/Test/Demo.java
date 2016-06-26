package com.yangyang.web.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        System.out.println("输入表达式: 格式 1+1 or 1+1=");
        String calc = new Scanner(System.in).next();
        int lastIdnex = calc.indexOf("=");
        calc = calc.substring(0,lastIdnex<0?calc.length():lastIdnex);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engin = manager.getEngineByName("javascript");
        while (true){
            try {
                    System.out.println(calc+"="+engin.eval(calc));
                    calc = new Scanner(System.in).next();
            } catch (Exception e) {
                System.out.println("输入不合法!请重新输入!");
                calc = new Scanner(System.in).next();
            }finally {
                calc = calc.substring(0,lastIdnex<0?calc.length():lastIdnex);
            }
        }
    }
}
