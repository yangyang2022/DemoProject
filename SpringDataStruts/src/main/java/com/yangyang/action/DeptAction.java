package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yangyang.Iservice.IDepService;
import com.yangyang.model.Department;
import com.yangyang.systemContext.SystemContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

public class DeptAction extends ActionSupport{
    @Resource
    private IDepService depService;

    public String list(){

        int pageNum = SystemContext.getPageOffset();
        int pageSize = SystemContext.getPageSize();

        PageRequest page = new PageRequest(pageNum/pageSize,pageSize);
        Page<Department> emps = depService.findAll(page);

        ActionContext.getContext().put("deps",emps);
        return "list";
    }
}
