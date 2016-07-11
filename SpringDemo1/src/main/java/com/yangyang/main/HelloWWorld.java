package com.yangyang.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWWorld {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello world --> yangyang";
    }
}
