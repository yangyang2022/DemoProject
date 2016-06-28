package com.yangyang.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Testi18n {
    public static void main(String[] args) {

        ResourceBundle rb = ResourceBundle.getBundle("Message", Locale.ENGLISH);
        System.out.println(rb.getObject("username") + " : "+rb.getObject("password"));
    }
}
