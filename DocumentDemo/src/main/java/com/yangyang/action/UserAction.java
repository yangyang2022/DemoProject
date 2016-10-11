package com.yangyang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yangyang.model.User;
import com.yangyang.service.iservice.IDepartmentService;
import com.yangyang.service.iservice.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import static com.yangyang.utils.ActionUtil.*;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User> {
    @Resource
    private IUserService userService;
    @Resource
    private IDepartmentService departmentService;

    private User user;
    private Integer depId;

    public Integer getDepId() {
        return depId;
    }
    public void setDepId(Integer depId) {
        this.depId = depId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String list(){
        setContextData("ds",departmentService.listAllDeps());
        setContextData("pages",userService.findUserByDep(depId));
        return SUCCESS;
    }
    public String addInput(){
        setContextData("ds",departmentService.listAllDeps());
        return SUCCESS;
    }

    public void validateAdd(){
        if(StringUtils.isEmpty(user.getUsername())){
            this.addFieldError("username","用户名称不能为空!");
        }
        if(this.hasErrors()) addInput();
    }
    public String add(){
        userService.add(user,depId);
        setContextUrl(REDIRECT_USER_LIST);
        return REDIRECT;
    }
    public String updateInput(){

        User tu = userService.load(user.getId());
        BeanUtils.copyProperties(tu,user);

        setContextData("ds",departmentService.listAllDeps());
        return SUCCESS;
    }
    public String update(){

        User tu = userService.load(user.getId());
        //修改的部分
        tu.setNickname(user.getNickname());
        tu.setEmail(user.getEmail());
        tu.setType(user.getType());

        userService.update(tu,depId);
        setContextUrl(REDIRECT_USER_LIST);
        return REDIRECT;
    }

    public String show(){
        User tu = userService.load(user.getId());
        BeanUtils.copyProperties(tu,user);

        return SUCCESS;
    }
    public String delete(){
        userService.delete(user.getId());
        setContextUrl(REDIRECT_USER_LIST);
        return REDIRECT;
    }

    public String updateSelfInput(){
        User tu = (User) getSessionDate("loginUser");
        BeanUtils.copyProperties(tu,user);
        setContextData("ds",departmentService.listAllDeps());
        return SUCCESS;
    }
    public String updateSelf(){

        User tu = (User) getSessionDate("loginUser");
        //修改的部分
        tu.setNickname(user.getNickname());
        tu.setEmail(user.getEmail());
        //修改密码放在其他地方修改
        setContextUrl("user_showSelf");
        return REDIRECT;
    }
    public String showSelf(){
        User tu = (User) getSessionDate("loginUser");
        BeanUtils.copyProperties(tu,user);
        return SUCCESS;
    }
    @Override
    public User getModel() {
        if(user == null) user = new User();
        return user;
    }
}
