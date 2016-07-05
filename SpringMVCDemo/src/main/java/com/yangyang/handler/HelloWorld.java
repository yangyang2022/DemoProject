package com.yangyang.handler;

import com.yangyang.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

@SessionAttributes(value = {"user"},types = {String.class})
@Controller
public class HelloWorld {
    private static final String SUCCESS = "success";

    /**
     *  value = {"user"} 将value 是user的放入session
     *  types = {String.class} 将String类型的放入session中
     *  这个注解只能放在类的上面 不是方法上面
     */
    @RequestMapping("/session")
    public String testSessionAttribute(Map<String,Object> map){

        User user = new User("Tom","123123","123@qq.com",22,null);
        map.put("user",user);
        map.put("shcool","school");
        return SUCCESS;
    }
    /**
     *  可以是 map 或者 Model 或 ModelMap
     *
     *  ModelAndView(name=success,modelMap = map)
     */
    @RequestMapping("/map")
    public String testMap(Map<String,Object> map){
        System.out.println(map.getClass().getName());
        map.put("name", Arrays.asList("yangyang","Tom","Jerry"));
        return SUCCESS;
    }
    /**
     *  返回值 ModelAndView 包含 试图和模型 信息
     *  springmvc 会把 ModelAndView 中的model 放入request 请求域里面
     */
    @RequestMapping("/mv")
    public ModelAndView testModelAndView(){
        String viewName = SUCCESS;
        ModelAndView mv = new ModelAndView(viewName);
        //添加模型数据到mv中
        mv.addObject("time",new Date());
        return mv;
    }


    /**
     *  可以使用 servlet 原生类型:
     *  HttpServletRequest HttpServletResponse HttpSession
     *  java.security.Principle Local InputStream OutputStream
     *  Writer Reader
     */
    @RequestMapping("/servlet")
    public void testServletApi(HttpServletRequest requset, HttpServletResponse response, Writer out) throws IOException {
        System.out.println(requset+" --> "+response);
        out.write("hello springmvc "); //调用的是HttpResponse.outwrite
        out.write(requset+" --> "+response);
        //return SUCCESS;
    }
    /**
     * springmvc 自动为属性设置值,支持级联属性 ,例如 address.city
     *
     */
    @RequestMapping("/user")
    public String testPOJO(User user){
        System.out.println(user);
        return SUCCESS;
    }
    @RequestMapping("/cookie")
    public String requestCookie(@CookieValue("JSESSIONID") String cookie){
        System.out.println("cookie: "+cookie);
        return SUCCESS;
    }
    @RequestMapping("/head")
    public String requestHeader(@RequestHeader("Accept-Language") String header){
        System.out.println(header);
        return SUCCESS;
    }
    @RequestMapping("/param")
    public String requestParam(@RequestParam(name = "username") String username,
                               @RequestParam(name = "age",required = false,defaultValue = "0") int age){
        System.out.println(username+" : "+age);
        return SUCCESS;
    }
    /**
     * 以CRUD为例
     *  新增: /order --> POST
     *  修改: /order/1 --> PUT
     *  获取: /order/1 --> GET
     *  删除: /order/1 --> DELETE
     *
     *  如何发送PUT 和 DELETE 请求?
     *  --> 配置: HiddenHttpMethodFilter
     *  发送 post 请求时:携带一个隐藏域, name="_method" value = PUT/DELETE
     */
    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.PUT)
    public String testRestPUT(@PathVariable("id") int id){
        System.out.println("PUT : " + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable("id") int id){
        System.out.println("delete : " + id);
        return SUCCESS;
    }
    @RequestMapping(value = "/testRest/{id}",method = RequestMethod.GET)
    public String testRest(@PathVariable("id") int id){
        System.out.println("get : " + id);
        return SUCCESS;
    }
    @RequestMapping(value = "/testRest",method = RequestMethod.POST)
    public String testPathVariable(){
        System.out.println("POST ");
        return SUCCESS;
    }
    @RequestMapping(value = "/param/*/demo",params = {"username","age!=10"})
    public String testParams(){
        return SUCCESS;
    }
    @RequestMapping(value = "/method",method = RequestMethod.GET)
    public String testMethod(){
        return SUCCESS;
    }

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello world");
        return SUCCESS;
    }
}
