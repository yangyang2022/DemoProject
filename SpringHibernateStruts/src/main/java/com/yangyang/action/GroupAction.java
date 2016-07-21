package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yangyang.model.Group;
import com.yangyang.service.iservice.IGroupService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import static com.yangyang.utils.StaticUtils.GROUP_LIST;
import static com.yangyang.utils.StaticUtils.REDIRECT;

@Controller
@Scope("prototype")
public class GroupAction extends ActionSupport implements ModelDriven<Group> {

    @Resource
    private IGroupService groupService;

    private Group group;
    private int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String list(){

        ActionContext.getContext().put("gs",groupService.listAll());

        return SUCCESS;
    }

    public String show(){
        group = groupService.load(cid);
        return SUCCESS;
    }
    public String delete(){
        groupService.delete(cid);
        ActionContext.getContext().put("url",GROUP_LIST);

        return REDIRECT;
    }
    @Override
    public Group getModel() {
        if(group == null) group = new Group();
        return group;
    }
}
