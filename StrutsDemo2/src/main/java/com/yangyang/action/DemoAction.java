package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;

import java.util.Arrays;
import java.util.List;

import static com.yangyang.utils.StaticUtils.SUCCESS;

public class DemoAction{

    public String list(){
        List<String> names= Arrays.asList("shen","yang","Demo","Hehe");

        ActionContext.getContext().put("us",names);

        return SUCCESS;
    }
}
