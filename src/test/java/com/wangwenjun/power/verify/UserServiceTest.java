package com.wangwenjun.power.verify;

import com.wangwenjun.power.common.User;
import com.wangwenjun.power.mockfinal.dao.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * Created by wangwenjun on 2016/10/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserServiceTest {

    @Test
    public void testSaveOrUpdateWillUseNewJoiner()
            throws Exception {

        User user = PowerMockito.mock(User.class);

        UserDao userDao = PowerMockito.mock(UserDao.class);

        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.getCount(user)).thenReturn(0);

        UserService userService = new UserService();
        userService.saveOrUpdate(user);

        Mockito.verify(userDao).insertUser(user);
        Mockito.verify(userDao, Mockito.never()).updateUser(user);
    }


    @Test
    public void testSaveOrUpdateWillUseUpdateOriginal()
            throws Exception {

        User user = PowerMockito.mock(User.class);

        UserDao userDao = PowerMockito.mock(UserDao.class);

        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.getCount(user)).thenReturn(1);

        UserService userService = new UserService();
        userService.saveOrUpdate(user);

        Mockito.verify(userDao, Mockito.never()).insertUser(user);
        Mockito.verify(userDao).updateUser(user);

    }
}