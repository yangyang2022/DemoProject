package com.yangyang.service;


import com.yangyang.annotation.SocketCmd;
import com.yangyang.annotation.SocketModule;

@SocketModule(module = (short) 1)
public interface IUserService {

    @SocketCmd(cmd = (short)1)
    void login();

    @SocketCmd(cmd = (short)2)
    void getInfo();
}
