package com.yangyang.beans;

public abstract class GetBeanTest {

    public abstract User getBean();

    public void showMe(){
        this.getBean().showMe();
    }
}
