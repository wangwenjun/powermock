package com.wangwenjun.power.mockstatic.service;

import com.wangwenjun.power.common.User;
import com.wangwenjun.power.mockstatic.dao.UserDao;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserService {

    public int queryUserCount() {
        return UserDao.getCount();
    }

    public void saveUser(User user) {
        UserDao.insertUser(user);
    }
}
