package com.yangyang.annotation.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {
    @Override
    public void save(String name) {
        System.out.println("userRepository save : "+name);
    }
}
