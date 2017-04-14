package com.wangwenjun.power.helloworld.service;

import com.wangwenjun.power.common.User;
import com.wangwenjun.power.helloworld.dao.UserDao;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import javax.management.OperationsException;

import static org.junit.Assert.*;

/**
 * Created by wangwenjun on 2016/10/8.
 */
public class UserServiceTest {

    private UserService userService;

    @Before
    public void setup() {
        userService = new UserService(new UserDao());
    }

    @Mock
    private UserDao userDao;

    /**
     * do...when...then
     * when...()...then
     */
    @Test
    public void testQueryUserCountWithPowerMock(){
        UserDao uDao = PowerMockito.mock(UserDao.class);
        //PowerMockito.doReturn(10).when(uDao).getCount();
        PowerMockito.when(uDao.getCount()).thenReturn(10);
        UserService service = new UserService(uDao);
        int result = service.queryUserCount();
        assertEquals(10,result);
    }

    @Test
    public void testSaveUserWithPowerMock(){
        UserDao uDao = PowerMockito.mock(UserDao.class);
        User user = new User();
        PowerMockito.doNothing().when(uDao).insertUser(user);
        UserService userService  = new UserService(uDao);
        userService.saveUser(user);

        Mockito.verify(uDao).insertUser(user);
    }



    @Ignore
    @Test
    public void testQueryUserCountWithMockito(){
        MockitoAnnotations.initMocks(this);

        UserService service = new UserService(userDao);
        Mockito.when(userDao.getCount()).thenReturn(10);

        int result = service.queryUserCount();
        assertEquals(10,result);
    }

    @Ignore
    @Test
    public void testQueryUserCountWithJunit() {

        try {
            userService.queryUserCount();
            fail("should not process to here.");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    @Ignore
    @Test
    public void testSaveUserWithJunit() {
        try {
            userService.saveUser(new User());
            fail("should not process to here.");
        } catch (Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }
}