package com.yangyang.service.serviceImpl;

import com.yangyang.dao.idao.IGroupDao;
import com.yangyang.dao.idao.IUserDao;
import com.yangyang.exception.UserException;
import com.yangyang.model.Group;
import com.yangyang.service.iservice.IGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupService implements IGroupService{

    @Resource
    private IGroupDao groupDao;

    @Resource
    private IUserDao userDao;

    @Override
    public void add(Group group) {
        groupDao.add(group);
    }

    @Override
    public void update(Group group) {
        groupDao.update(group);
    }

    @Override
    public void delete(int id) {
        long count = userDao.countUserByGroupId(id);
        if(count > 0){
            throw new UserException("改组还有用户,不能删除!");
        }
        //userDao.deleteByGroupId(id);

        groupDao.delete(id);
    }

    @Override
    public Group load(int id) {
        return groupDao.load(id);
    }

    @Override
    public List<Group> listAll() {
        return groupDao.list("from Group");
    }
}
