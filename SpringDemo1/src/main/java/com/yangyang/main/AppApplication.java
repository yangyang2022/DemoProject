package com.yangyang.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AppApplication {

    @RequestMapping("/")
    public String greeting(){
        return "hello world";
    }
    @RequestMapping("/helloworld")
    public String hello(){
        return "hello yangyang";
    }
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class,args);
    }
}
