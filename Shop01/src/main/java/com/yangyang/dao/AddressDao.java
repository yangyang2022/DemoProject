package com.yangyang.dao;

import com.yangyang.model.Address;
import com.yangyang.model.ShopException;
import com.yangyang.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressDao extends BaseDao<Address> implements IAddressDao{
    private IUserDao userDao;
    private IAddressDao addressDao;

    @ShopDI
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(Address address, int user_id) {
        User u = userDao.load(user_id);
        if(u == null ) throw new ShopException("添加地址的用户不存在!");
        address.setUser(u);
        super.add(address);
    }

    @Override
    public void update(Address address) {
        //TODO 删除之前 需要删除关联对象
        super.update(address);
    }

    @Override
    public void delete(int id) {
        super.delete(Address.class,id);
    }

    @Override
    public Address load(int id) {
        return super.load(Address.class,id);
    }

    @Override
    public List<Address> list(int user_id) {
        Map<String,Object> params = new HashMap<>();
        params.put("user_id",user_id);
        return super.list(Address.class,params);
    }
}
