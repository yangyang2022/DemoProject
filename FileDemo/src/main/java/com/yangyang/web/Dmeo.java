package com.yangyang.web;

public class Dmeo {
    public static void main(String[] args) {
        //System.out.println((char)6533);
        int i = 8;
        System.out.println(i>6 ? 1:2.0);
        System.out.println(i>6 ? 'b':97);
        System.out.println(i>6 ? 97:'b');
        System.out.println(i>6 ? 'b':65535);
        System.out.println(i>6 ? 'b':65633);
        System.out.println(i>6 ? 65633:'b');
        System.out.println('a'+0);
        System.out.println((char) 65633%128);
        System.out.println((char)97);
    }
}
