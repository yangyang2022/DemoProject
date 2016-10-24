package com.yangyang.soap;

import com.yangyang.service.User;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IMyService {
    @WebResult(name = "addResult")
    int add(@WebParam(name = "a") int a,@WebParam(name = "b") int b);

    @WebResult(name = "user")
    User adduser(@WebParam(name = "user") User user);

    @WebResult(name = "user")
    User login(@WebParam(name = "username") String username,@WebParam(name = "password") String password);

    @WebResult(name = "user")
    List<User> list();
}
