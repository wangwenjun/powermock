package com.wangwenjun.power.mockfinal.service;

import com.wangwenjun.power.mockfinal.dao.UserDao;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;

/**
 * Created by wangwenjun on 2016/10/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UserService.class,UserDao.class})
public class UserServiceTest {
/*
    @Mock
    private UserDao userDao;

    @Ignore
    @Test
    public void testQueryUserCountWithMockito() throws Exception {
        MockitoAnnotations.initMocks(this);
        UserService userService = new UserService(userDao);
        Mockito.when(userDao.getCount()).thenReturn(10);
        int result = userService.queryUserCount();
        assertEquals(10, result);
    }*/


    @Test
    public void testQueryUserCountWithPowerMock() throws Exception {
        UserDao uDao = PowerMockito.mock(UserDao.class);
        System.out.println(uDao.getClass());
        PowerMockito.when(uDao.getCount()).thenReturn(10);
        UserService userService = new UserService(uDao);
        int result = userService.queryUserCount();
        assertEquals(10, result);
    }

}