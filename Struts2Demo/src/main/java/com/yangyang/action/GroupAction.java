package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yangyang.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupAction extends ActionSupport implements ModelDriven<Group>{

    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String addInput(){

        List<String> interets = new ArrayList<>();
        interets.add("foot");
        interets.add("pingpang");
        interets.add("basket");

        List<Group> groups = new ArrayList<>();
        groups.add(new Group(1,"财务部"));
        groups.add(new Group(2,"销售部"));
        groups.add(new Group(3,"开发部"));
        groups.add(new Group(4,"审核部"));

        ActionContext.getContext().put("ints",interets);
        ActionContext.getContext().put("gs",groups);
        ActionContext.getContext().put("username","张三");

        return this.SUCCESS;
    }

    @Override
    public Group getModel() {
        if(group == null) group = new Group(1,"财务部");
        return group;
    }
}
