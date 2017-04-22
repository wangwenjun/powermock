package com.wangwenjun.mockito.usage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/16 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class AccountControllerTest {

    private
    @Mock
    AccountDao accountDao;

    private
    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The when() method represents the trigger—when to stub it. The following
     * methods are used to represent a trigger action or what to do when the trigger is triggered.
     * <p>
     * thenReturn
     * 1. thenReturn(value)
     * 2. thenReturn(value...)
     * 3. thenReturn(value1).thenReturn(value2);
     * <p>
     * thenThrow
     *
     * @throws Exception
     */
    @Test(expected = RuntimeException.class)
    public void testLogin() throws Exception {
        AccountController controller = new AccountController(accountDao);
        when(request.getParameter(anyString())).thenReturn(null).thenReturn(null);
        when(accountDao.login(null, null)).thenThrow(RuntimeException.class);
        controller.login(request);
        fail("should not process to login.");
    }

    /**
     * doCallRealMethod usage.
     * @throws Exception
     */
    @Test
    public void testLogin2() throws Exception {
        when(accountDao.login(null, null)).thenReturn(false);
        boolean result = accountDao.login(null, null);
        assertFalse(result);

//        Mockito.doReturn(accountDao.login("alex", "123456")).doNothing();
        Mockito.doCallRealMethod().when(accountDao).check("alex", "123456");
        accountDao.check("alex", "123456");
//        accountDao.check();
    }

    /**
     * isA
     *
     * @throws Exception
     */
    @Test
    public void testLogin3() throws Exception {
        AccountController controller = new AccountController(accountDao);
        when(request.getParameter(anyString())).thenReturn("alex").thenReturn("123456");
        when(accountDao.login(isA(RequestDomain.class))).thenReturn(true);
        String page = controller.login(request);
        assertEquals("/index", page);
    }
}