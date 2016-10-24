package com.yangyang.service.serviceImpl;

import com.yangyang.dao.idao.IDepartmentDao;
import com.yangyang.dao.idao.IUserDao;
import com.yangyang.exception.UserException;
import com.yangyang.model.Pager;
import com.yangyang.model.User;
import com.yangyang.service.iservice.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Resource
    private IUserDao userDao;
    @Resource
    private IDepartmentDao departmentDao;

    private User loadByUsername(String username){
        String hql = "select u from User u where u.username=?";
        return (User) userDao.queryForHql(hql,username);
    }
    @Override
    public void add(User user, int depId) {
        if(loadByUsername(user.getUsername()) != null)
            throw new UserException("用户已经存在!");
        user.setDepartment(departmentDao.load(depId));
        userDao.add(user);
    }

    @Override
    public void delete(int id) {
        //删除用户之前 删除他发的消息删除(删除关联文档/消息)
        userDao.delete(id);
    }

    @Override
    public void update(User user,int depId) {
        user.setDepartment(departmentDao.load(depId));
        userDao.update(user);
    }

    @Override
    public User load(int id) {
        return userDao.load(id);
    }

    @Override
    public Pager<User> findUserByDep(Integer depId) {
        Pager<User> users = null;
        if(depId == null || depId < 0 ){
            users = userDao.find("from User u left join fetch u.department ");
        }else {
            users = userDao.find("from User u left join fetch u.department dep where dep.id=? " ,depId);
        }
        return users;
    }

    @Override
    public User login(String username, String password) {
        User user = this.loadByUsername(username);
        if(user == null )throw new UserException("用户不存在!");
        if(!user.getPassword().equals(password)) throw new UserException("用户密码不正确!");
        return user;
    }

    @Override
    public List<User> listAllSendUser(int userId) {
        return userDao.listAllSendUser(userId);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
