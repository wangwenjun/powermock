package com.wangwenjun.power.matcher;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserService {

    public String find(String name) {
        UserDao userDao = new UserDao();
        return userDao.queryByName(name);
    }
}
