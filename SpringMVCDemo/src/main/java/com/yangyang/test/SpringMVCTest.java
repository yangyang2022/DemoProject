package com.yangyang.test;

import com.yangyang.dao.EmployeeDao;
import com.yangyang.handler.EmployeeHandler;
import com.yangyang.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;

@Controller
public class SpringMVCTest {
    @Resource
    private EmployeeDao employeeDao;

    @ResponseBody
    @RequestMapping("/msgConverter")
    public String testMessgaeConverter(@RequestBody String body){
        System.out.println("bddy: "+body);
        return "hello world "+new Date();
    }

    @ResponseBody
    @RequestMapping("/json")
    public Collection<Employee> testJson(){
        return employeeDao.getAll();
    }

    @RequestMapping("/testConverse")
    public String testConverter(@RequestParam("employee")Employee employee){

        System.out.println("add Employee: "+employee.getLastName());
        employeeDao.save(employee);

        return EmployeeHandler.REDIRECT_LISt;
    }
}
