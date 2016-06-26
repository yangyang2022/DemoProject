package com.yangyang.model;

public class SystemContext {

    static ThreadLocal<Integer> pageSize = new ThreadLocal<>();
    static ThreadLocal<Integer> pageIndex = new ThreadLocal<>();
    static ThreadLocal<Integer> pageOffset = new ThreadLocal<>();
    // asc or desc
    static ThreadLocal<String> order = new ThreadLocal<>();
    //order by xxx
    static ThreadLocal<String> sort = new ThreadLocal<>();

    static public void setOrder(String _order){order.set(_order);}
    static public String getOrder(){return order.get();}
    static public void removeOrder(){order.remove();}

    static public void setSort(String _sort){sort.set(_sort);}
    static public String getSort(){return sort.get();}
    static public void removeSort(){sort.remove();}

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
