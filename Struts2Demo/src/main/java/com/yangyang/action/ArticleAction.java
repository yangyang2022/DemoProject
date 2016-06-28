package com.yangyang.action;

import com.yangyang.model.StrutsException;

public class ArticleAction {
    public String add(){
        int i = 0;
        if( i == 0){
            throw new StrutsException("这是模拟的Struts异常 ... ");
        }
        return "success";
    }
}
