package com.yangyang.web.model;

public class SystemContext {

    static ThreadLocal<Integer> pageSize = new ThreadLocal<>();
    static ThreadLocal<Integer> pageIndex = new ThreadLocal<>();
    static ThreadLocal<Integer> pageOffset = new ThreadLocal<>();

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

    static public  void setPageIndex(int _pageIndex){
        pageIndex.set(_pageIndex);
    }
    static public  int getPageIndex(){
        return pageIndex.get();
    }
    static public  void removePageIndex(){
        pageIndex.remove();
    }
}
