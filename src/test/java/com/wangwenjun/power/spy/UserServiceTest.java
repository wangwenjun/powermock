package com.wangwenjun.power.spy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import static org.junit.Assert.assertTrue;

/**
 * Created by wangwenjun on 2016/10/8.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserServiceTest {

    @Test
    public void testFoo() throws Exception {

        UserService userService = PowerMockito.spy(new UserService());
        System.out.println(userService);
        String arg = "hello";
        PowerMockito.doNothing().when(userService).foo(arg);

        userService.foo("world");
    }

    @Test
    public void testCheck() throws Exception {

        UserService userService = PowerMockito.spy(new UserService());

        PowerMockito.doReturn(true).when(userService, "checkExist", "alex");

        assertTrue(userService.exist("alex"));

        userService.exist("other");
    }
}