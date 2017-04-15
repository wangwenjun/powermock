package com.wangwenjun.mockito.quickstart;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/15 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class AccountControllerTest {


    private AccountDao accountDao;

    private HttpServletRequest request;

    @Before
    public void setUp() {
        accountDao = mock(AccountDao.class);
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void testLogin() {
        AccountController controller = new AccountController(accountDao);
        Mockito.when(request.getParameter("username")).thenReturn("alex");
        Mockito.when(request.getParameter("password")).thenReturn("123456");

        Mockito.when(accountDao.login("alex", "123456")).thenReturn(Boolean.TRUE);
        String page = controller.login(request);
        assertEquals("/index", page);
    }

    @Test
    public void testLoginFailed() {
        AccountController controller = new AccountController(accountDao);
        Mockito.when(request.getParameter("username")).thenReturn("alex");
        Mockito.when(request.getParameter("password")).thenReturn("123456");

        Mockito.when(accountDao.login("alex", "123456")).thenReturn(Boolean.FALSE);
        String page = controller.login(request);
        assertEquals("/login", page);
    }

    @Test(expected = RuntimeException.class)
    public void testLoginError() {
        AccountController controller = new AccountController(accountDao);
        Mockito.when(request.getParameter("username")).thenReturn(null);
        Mockito.when(request.getParameter("password")).thenReturn(null);

        Mockito.when(accountDao.login("alex", "123456")).thenReturn(Boolean.FALSE);
        controller.login(request);
        fail("should not process to here.");
    }
}