package com.yangyang.dao.idao;

import com.yangyang.model.User;

public interface IUserDao extends IBaseDao<User>{
    /**
     * 统计一个组中的人数
     */
    long countUserByGroupId(int gid);


    /**
     * 删除一个组的用户
     * @param gid
     */
    void deleteByGroupId(int gid);

}
