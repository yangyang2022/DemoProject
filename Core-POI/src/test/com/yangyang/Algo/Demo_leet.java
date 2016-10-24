package com.yangyang.Algo;

import org.junit.Test;

import java.util.Stack;

public class Demo_leet {

    //(){}[]
    public boolean isValid(String s) {
        String[] ss = s.split("");
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < ss.length; ++i) {
            if(ss[i].equals("(") || ss[i].equals("[") || ss.equals("{"))
                stack.push(ss[i]);
            else {
                if(ss[i-1].equals("(")) return stack.pop().equals("(");
                else if(ss[i-1].equals("[")) return stack.pop().equals("[");
                else if(ss[i-1].equals("{")) return stack.pop().equals("{");
            }
        }
        return false;
    }

    @Test
    public void testDemo1() {
        System.out.println(isValid("[]["));
    }
}
