package com.yangyang.systemContext;

import com.yangyang.model.User;

public class SystemContext {

    static ThreadLocal<Integer> pageSize = new ThreadLocal<>();
    static ThreadLocal<User> loginUser = new ThreadLocal<>();
    static ThreadLocal<Integer> pageOffset = new ThreadLocal<>();
    static ThreadLocal<String> realPath = new ThreadLocal<>();

    static public void setPageOffset(int _pageOffset){pageOffset.set(_pageOffset);}
    static public int getPageOffset(){return pageOffset.get();}
    static public void removePageOffset(){pageOffset.remove();}

    static public void setPageSize(int _pageSize){
        pageSize.set(_pageSize);
    }
    static public int getPageSize(){
        return pageSize.get();
    }
    static public void removePageSize(){
        pageSize.remove();
    }

    static public  void setLoginUser(User _pageIndex){
        loginUser.set(_pageIndex);
    }
    static public  User getLoginUser(){
        return loginUser.get();
    }
    static public  void removeLoginUser(){
        loginUser.remove();
    }

    static public void setRealPath(String _realPath){realPath.set(_realPath);}
    static public String getRealPath(){return realPath.get();}
    static public void removeRealPath(){realPath.remove();}
}
