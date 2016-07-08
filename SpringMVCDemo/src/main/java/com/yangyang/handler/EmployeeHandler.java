package com.yangyang.handler;

import com.yangyang.dao.DepartmentDao;
import com.yangyang.dao.EmployeeDao;
import com.yangyang.model.Employee;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Map;

@Controller
public class EmployeeHandler {
    public static final String SUCCESS = "success";
    public static String REDIRECT_LISt = "redirect:/emps";

    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private DepartmentDao departmentDao;

    @Resource
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/i18n")
    private String testI18n(Locale locale){
        String val = messageSource.getMessage("username",null,locale);
        System.out.println(val);
        return "i18n";
    }
    @RequestMapping(value = "/emp/{id}" ,method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") int id){
        System.out.println("delete: "+id);
        employeeDao.delete(id);
        return REDIRECT_LISt;
    }

    @ModelAttribute
    public void getEmployee(@RequestParam(value = "id",required = false) Integer id,Map<String ,Object> map){
        if(id != null){
            System.out.println(employeeDao.get(id));
            map.put("employee",employeeDao.get(id));
        }
    }
    @RequestMapping(value = "/emp",method = RequestMethod.PUT)
    public String update(Employee e){
        employeeDao.save(e);
        return REDIRECT_LISt;
    }

    @RequestMapping(value = "/emp/{id}" ,method = RequestMethod.GET)
    public String input(@PathVariable("id") int id,Map<String,Object> map){
        map.put("employee",employeeDao.get(id));
        map.put("deps",departmentDao.getDeps());
        return "add";
    }

    @RequestMapping(value = "/emp" ,method = RequestMethod.POST)
    public String save(@Valid Employee employee, BindingResult result,Map<String,Object> map){
        System.out.println("save: "+employee);
        if(result.getErrorCount() > 0){
            System.out.println("出错了 !!! ");
            result.getFieldErrors().forEach(e-> System.out.println(e.getField()+" --> "+e.getDefaultMessage()));
            //专项自己的定制页面
            map.put("deps",departmentDao.getDeps());
            return "add";
        }
        //employeeDao.save(employee);
        return REDIRECT_LISt;
    }

    @RequestMapping(value = "/emp" ,method = RequestMethod.GET)
    public String add(Map<String,Object> map){
        map.put("deps",departmentDao.getDeps());
        //这里不需要表单回显,所以new一个空对象即可
        map.put("employee",new Employee());
        return "add";
    }

    @RequestMapping("/emps")
    public String list(Map<String,Object> maps){

        maps.put("emps",employeeDao.getAll());

        return "list";
    }

    //@InitBinder
    //public void initBinder(WebDataBinder binder){
    //    binder.setDisallowedFields("lastName");
    //    binder.setDisallowedFields("email");
    //}
}
