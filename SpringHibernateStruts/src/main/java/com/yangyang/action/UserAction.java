package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yangyang.model.Group;
import com.yangyang.model.User;
import com.yangyang.service.iservice.IGroupService;
import com.yangyang.service.iservice.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

import static com.yangyang.utils.StaticUtils.*;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{

    @Resource
    private IGroupService groupService;
    @Resource
    private IUserService userService;

    private User user;
    private int gid;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String list(){
        setContextData("us",userService.findUser());
        return SUCCESS;
    }

    public String addInput(){
        List<Group> gs = groupService.listAll();
        ActionContext.getContext().put("gs",gs);
        return SUCCESS;
    }
    public String add(){

        user.setGroup(groupService.load(gid));
        userService.add(user);

        ActionContext.getContext().put("url", USER_LIST);
        return REDIRECT;
    }
    public void validateAdd(){
        if(checkEmpty(user.getUsername())){
            setError("username","用户名不能为空!");
        }
        if(checkEmpty(user.getPassword())){
            setError("password","用户密码不能为空!");
        }
        //如果有错误,需要设置组的信息
        if(this.hasErrors()){
            addInput();
        }
    }

    public String delete(){
        userService.delete(user.getId());
        setContextData("url",USER_LIST);
        return REDIRECT;
    }

    public String updateInput(){

        setContextData("gs",groupService.listAll());

        User tu = userService.load(user.getId());
        BeanUtils.copyProperties(tu,user);

        this.setGid(tu.getGroup().getId());

        return SUCCESS;
    }
    public String update(){

        User tu = userService.load(user.getId());
        BeanUtils.copyProperties(user,tu);

        tu.setGroup(groupService.load(gid));

        userService.update(tu,gid);
        setContextData("url",USER_LIST);
        return REDIRECT;
    }

    @Override
    public User getModel() {
        if(user == null) user = new User();
        return user;
    }
    public  void setError(String name,String value){
        this.addFieldError(name,value);
    }
}
