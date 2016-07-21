package com.yangyang.model;

import com.yangyang.model.Address;

import java.util.List;

public interface IAddressDao {
    void add(Address address,int user_id);
    void update(Address address);
    void delete(int id);
    Address load(int id);
    List<Address> list(int user_id);
}
