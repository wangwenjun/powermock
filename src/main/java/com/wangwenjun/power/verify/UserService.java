package com.wangwenjun.power.verify;

import com.wangwenjun.power.common.User;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserService {

    public void saveOrUpdate(User user) {
        UserDao userDao = new UserDao();
        if (userDao.getCount(user) > 0) {
            userDao.updateUser(user);
        } else {
            userDao.insertUser(user);
        }
    }
}
