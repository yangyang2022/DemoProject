package com.yangyang.annotation.gernericdi;

import javax.annotation.Resource;

public class BaseService <T>{
    /**
     * 注意 : BaseService 和 BaseRepository 上面都没有加入注解
     */
    @Resource
    protected BaseRepository<T> baseRepository;

    public void add(){

        System.out.println("add ... ");
        System.out.println(baseRepository);
    }
}
