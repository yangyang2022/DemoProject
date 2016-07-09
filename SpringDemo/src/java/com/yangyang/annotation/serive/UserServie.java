package com.yangyang.annotation.serive;

import com.yangyang.annotation.repository.IUserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServie implements IUserService {
    private IUserRepository userRepository;

    @Resource
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(String name) {
        if(name.equals("admin")){
            System.out.println("这是一个 管理员  ... ");
        }
        userRepository.save(name);
    }
}
