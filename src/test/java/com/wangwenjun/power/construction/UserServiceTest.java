package com.wangwenjun.power.construction;

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
    public void testSave() throws Exception {
        UserDao userDao = PowerMockito.mock(UserDao.class);
        String username = "wangwenjun";
        String password = "alex";
        PowerMockito.whenNew(UserDao.class).withArguments(username, password).thenReturn(userDao);

        PowerMockito.doNothing().when(userDao).insert();

        UserService userService = new UserService();
        userService.save(username, password);

        Mockito.verify(userDao).insert();
    }
}