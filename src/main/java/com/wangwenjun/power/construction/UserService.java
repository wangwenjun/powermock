package com.wangwenjun.power.construction;

import com.wangwenjun.power.local.dao.*;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserService
{
    public void save(String username,String password){
        UserDao userDao = new UserDao(username,password);
        userDao.insert();
    }
}
