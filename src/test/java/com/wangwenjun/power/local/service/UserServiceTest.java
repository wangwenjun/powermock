package com.wangwenjun.power.local.service;

import com.wangwenjun.power.common.User;
import com.wangwenjun.power.local.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Created by wangwenjun on 2016/10/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class})
public class UserServiceTest {

    @Test
    public void testQueryUserCount() {

        try {
            UserService userService = new UserService();
            UserDao userDao = mock(UserDao.class);
            System.out.println(userDao.getClass());
            whenNew(UserDao.class).withNoArguments().thenReturn(userDao);
            doReturn(10).when(userDao).getCount();
            int result = userService.queryUserCount();
            assertEquals(10, result);
        } catch (Throwable e) {
            fail();
        }
    }

    @Test
    public void testSaveUser() {

        try {
            User user = new User();
            UserService userService = new UserService();
            UserDao userDao = mock(UserDao.class);
            whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);
            doNothing().when(userDao).insertUser(user);

            userService.saveUser(user);
            Mockito.verify(userDao, Mockito.times(1)).insertUser(user);
        } catch (Throwable e) {
            fail();
        }

    }
}