package com.wangwenjun.power.matcher;

import com.wangwenjun.power.mockfinal.dao.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;
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
    public void testFind() throws Exception {

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.queryByName("alex")).thenReturn("wangwenjun");
        UserService service = new UserService();
        String result = service.find("alex");
        assertEquals("wangwenjun", result);

        PowerMockito.when(userDao.queryByName("Jacky")).thenReturn("wangwenjun");
        String jacky = service.find("Jacky");
        assertEquals("wangwenjun", jacky);

        PowerMockito.when(userDao.queryByName("Tommy")).thenReturn("wangwenjun");
        String tommy = service.find("Tommy");
        assertEquals("wangwenjun", tommy);

    }

    @Test
    public void testFindWithMatcher() throws Exception {

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.queryByName(Matchers.argThat(new MyArgumentMatcher()))).thenReturn("wangwenjun");
        UserService service = new UserService();

        assertEquals("wangwenjun", service.find("Alex"));
        assertEquals("wangwenjun", service.find("Jacky"));
        assertEquals("wangwenjun", service.find("Van"));
        assertEquals("wangwenjun", service.find("Tony"));


    }

    @Test
    public void testFindWithAnswer() throws Exception {

        UserDao userDao = PowerMockito.mock(UserDao.class);
        PowerMockito.whenNew(UserDao.class).withAnyArguments().thenReturn(userDao);

        PowerMockito.when(userDao.queryByName(Mockito.anyString())).then(invocation -> {
            String arg = (String) invocation.getArguments()[0];
            switch (arg) {
                case "Jacky":
                    return "I am Jacky.";
                case "Alex":
                    return "I am Alex.";
                default:
                    throw new RuntimeException("Not support " + arg);

            }
        });

        UserService service = new UserService();

        assertEquals("I am Jacky.", service.find("Jacky"));
        assertEquals("I am Alex.", service.find("Alex"));

        try {
            String anyValue = service.find("AnyValue");
            fail("never process to here is right.");
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }


    static class MyArgumentMatcher extends ArgumentMatcher<String> {

        @Override
        public boolean matches(Object o) {
            String arg = (String) o;
            switch (arg) {
                case "Alex":
                case "Jacky":
                case "Van":
                case "Tony":
                    return true;
                default:
                    return false;
            }
        }
    }
}