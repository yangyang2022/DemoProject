package com.yangyang.service;

import javax.jws.WebService;

@WebService
public interface IMyService {
    int add(int a,int b);
    int minus(int a,int b);
}
