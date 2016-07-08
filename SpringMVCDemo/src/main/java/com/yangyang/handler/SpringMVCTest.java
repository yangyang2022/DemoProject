package com.yangyang.handler;

import com.yangyang.StaticUtils.StaticValues;
import com.yangyang.dao.EmployeeDao;
import com.yangyang.exception.UsernameNotMatchPassword;
import com.yangyang.model.Employee;
import com.yangyang.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;

@Controller
public class SpringMVCTest {
    @Resource
    private UserService userService;

    public SpringMVCTest(){
        System.out.println("springmvc constructor ... ");
    }
    @Resource
    private EmployeeDao employeeDao;

    @RequestMapping("/hellotest")
    public String hellotest(){
        userService.add();
        return StaticValues.SUCCESS;
    }
    /**
     * 1: 加入map带入exception异常信息,不可行,传异常信息到页面上需要 加入到modelAndView
     * 2: 异常优先级 ,就近最匹配的原则
     * 3: 如果在当前类中没有找到exceptionHnadler 则去 @ControllerAdvice 的
     * 标记的类中找
     *
     */
    //@ExceptionHandler({ArithmeticException.class})
    //public ModelAndView handerException(Exception e){
    //    System.out.println("发现异常: "+e.getMessage());
    //    ModelAndView mv = new ModelAndView("error");
    //    mv.addObject("ex",e.getMessage());
    //    return mv;
    //}

    @RequestMapping("/simpleex")
    public String testSinpleMappingExceptionResolver(@RequestParam("id") int id){
        String[] vals = new String[10];
        System.out.println("val: "+vals[id]);
        return StaticValues.SUCCESS;
    }
    @RequestMapping("userex")
    public String testMySelfException(@RequestParam("id") int id){
        if(id == 13){
            throw new UsernameNotMatchPassword();
        }
        System.out.println("go ... ");
        return StaticValues.SUCCESS;
    }

    @RequestMapping("/ex")
    public String testException(@RequestParam("i") int i){
        System.out.println("result: "+(10/i));
        return EmployeeHandler.SUCCESS;
    }
    @RequestMapping("/fileUpload")
    public String testFileUpload(@RequestParam("desc")String desc,@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("desc: "+desc);
        System.out.println("fileName: "+file.getOriginalFilename());
        System.out.println("fileInputStream: "+file.getInputStream());
        return EmployeeHandler.SUCCESS;
    }
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
