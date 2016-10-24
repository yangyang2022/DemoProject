package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yangyang.model.Department;
import com.yangyang.service.iservice.IDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

import static com.yangyang.utils.ActionUtil.*;

@Controller
@Scope("prototype")
public class DepartmentAction extends ActionSupport implements ModelDriven<Department> {

    @Resource
    private IDepartmentService departmentService;
    private Department department;

    private int[] sdeps;

    public int[] getSdeps() {
        return sdeps;
    }

    public void setSdeps(int[] sdeps) {
        this.sdeps = sdeps;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String list(){

        ActionContext.getContext().put("ds",departmentService.listAllDeps());

        return SUCCESS;
    }
    public String addInput(){
        return SUCCESS;
    }

    public void validateAdd(){
        if(StringUtils.isEmpty(department.getName())){
            this.addFieldError("name","部门名称不能为空!");
        }
    }
    public String add(){

        departmentService.add(department);

        setContextData("url",REDIRECT_DEP_LIST);
        return REDIRECT;
    }
    public String delete(){
        departmentService.delete(department.getId());

        setContextData("url",REDIRECT_DEP_LIST);
        return REDIRECT;
    }

    public void validateUpdate(){
        if(StringUtils.isEmpty(department.getName())){
            this.addFieldError("name","部门名称不能为空!");
        }
    }
    public String updateInput(){

        Department dp = departmentService.load(department.getId());
        BeanUtils.copyProperties(dp,department);

        return SUCCESS;
    }
    public String update(){
        Department tp = departmentService.load(department.getId());
        tp.setName(department.getName());
        departmentService.update(tp);

        setContextData("url",REDIRECT_DEP_LIST);
        return REDIRECT;
    }

    public String show(){
        department = departmentService.load(department.getId());
        setContextData("ds",departmentService.listDepScopeDep(department.getId()));
        return SUCCESS;
    }

    public String setDepscopeInput(){
        department = departmentService.load(department.getId());
        //List<Integer> ads = departmentService.listDepScopeDep(department.getId()).stream()
        //        .map(Department::getId).collect(Collectors.toList());

        List<Integer> ads = departmentService.listDepSocpeDepId(department.getId());
        List<Department> deps = departmentService.listAllDeps();
        //deps.remove(department); //??? 为什么这里删不掉呢?
        deps.removeIf(e->e.getId()==department.getId());
        setContextData("ads",ads);
        setContextData("ds", deps);
        return SUCCESS;
    }
    //setDepscope
    public String setDepscope(){
        departmentService.addScopeDeps(department.getId(),sdeps);

        setContextUrl(REDIRECT_DEP_LIST);
        return REDIRECT;
    }


    @Override
    public Department getModel() {
        if(department == null) department = new Department();
        return department;
    }
}
