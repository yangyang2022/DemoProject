package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yangyang.Iservice.IDepService;
import com.yangyang.Iservice.IEmployeeService;
import com.yangyang.model.Department;
import com.yangyang.model.Employee;
import com.yangyang.systemContext.SystemContext;
import com.yangyang.utils.StaticUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

public class EmployeeAction extends ActionSupport implements ModelDriven<Employee>{

    @Resource
    private IEmployeeService employeeService;
    @Resource
    private IDepService depService;

    private Employee employee;

    private Integer id;
    private InputStream inputStream;

    public InputStream getInputStream() {
        return inputStream;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String list(){

        int pageNum = SystemContext.getPageOffset();
        int pageSize = SystemContext.getPageSize();

        PageRequest page = new PageRequest(pageNum/pageSize,pageSize);
        Page<Employee> emps = employeeService.findAll(page);
        ActionContext.getContext().put("emps",emps);

        return "list";
    }


    public String delete(){

        try {
            employeeService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ActionContext.getContext().put("url","/Employee_list");
        return StaticUtils.REDIRECT;
    }

    public String addInput(){

        Iterable<Department> deps = depService.findAll();
        ActionContext.getContext().put("deps",deps);
        return "addInput";
    }
    public String add(){

        Department dep = depService.findOne(employee.getDepartment().getId());
        employee.setDepartment(dep);
        //注意 createTime 不能改变
        if(employee.getCreateTime() ==null){
            employee.setCreateTime(LocalDateTime.now());
        }
        employeeService.save(employee);

        ActionContext.getContext().put("url","/Employee_list");
        return StaticUtils.REDIRECT;
    }

    public String validateLastName() throws UnsupportedEncodingException {
        //这里属性注入貌似不行 ...
        HttpServletRequest request = ServletActionContext.getRequest();
        String name = request.getParameter("lastname");
        Employee emp = employeeService.findByLastname(name);

        if(emp == null){
            inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
        }else {
            inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
        }
        return "ajax-success";
    }
    @Override
    public Employee getModel() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String _id = request.getParameter("id");

        if( _id== null){
            employee = new Employee();
        }else{
            Integer id = Integer.parseInt(_id);
            employee = employeeService.findOne(id);
        }
        return employee;
    }
}
