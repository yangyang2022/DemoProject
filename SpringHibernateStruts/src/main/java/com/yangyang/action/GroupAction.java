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
        group = groupService.load(group.getId());
        return SUCCESS;
    }
    public String delete(){

        groupService.delete(group.getId());

        ActionContext.getContext().put("url",GROUP_LIST);
        return REDIRECT;
    }

    public String updateInput(){
        Group tg = groupService.load(group.getId());
        group.setGroupName(tg.getGroupName());
        return SUCCESS;
    }
    public String update(){

        groupService.update(group);

        ActionContext.getContext().put("url",GROUP_LIST);
        return REDIRECT;
    }
    public String addInput(){
        return SUCCESS;
    }
    public String add(){

        groupService.add(group);

        ActionContext.getContext().put("url",GROUP_LIST);
        return REDIRECT;
    }

    /**
     * 服务器端验证
     */
    public void validateAdd(){
        if(group.getGroupName() == null || "".equals(group.getGroupName().trim())){
            this.addFieldError("groupName","组名称不能为空!");
        }
    }
    @Override
    public Group getModel() {
        if(group == null) group = new Group();
        return group;
    }
}
