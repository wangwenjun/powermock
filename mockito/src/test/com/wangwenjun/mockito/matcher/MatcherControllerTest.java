package com.wangwenjun.mockito.matcher;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.when;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/30
 * QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class MatcherControllerTest {

    @Mock
    private PermissionService permissionService;

    @Mock
    private HttpServletRequest request;

    private final static List<String> userNameList = Arrays.asList("alex", "wen", "jun", "wang", "jack");
    private final static List<String> passwordList = Arrays.asList("12345", "11111", "22222", "3333", "4444");

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(permissionService.getRole("guest", "guest")).thenReturn("guest");
        when(permissionService.getRole("admin", "admin")).thenReturn("admin");
        when(permissionService.getRole("alex", "alex")).thenReturn("user");
    }

    @Test
    public void testGetAdminRole() {
        MatcherController controller = new MatcherController();
        controller.setPermissionService(permissionService);
        when(request.getParameter(anyString())).thenReturn("admin").thenReturn("admin");

        String role = controller.getRole(request);
        assertThat(role, equalTo("admin"));
    }

    @Test
    public void testGetGuestRole() {
        MatcherController controller = new MatcherController();
        controller.setPermissionService(permissionService);

        when(request.getParameter(anyString())).thenReturn("guest").thenReturn("guest");

        String role = controller.getRole(request);
        assertThat(role, equalTo("guest"));
    }

    @Test
    public void testGetLoginUser() {
        MatcherController controller = new MatcherController();
        controller.setPermissionService(permissionService);
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("alex");
        String role = controller.getRole(request);
        assertThat(role, equalTo("user"));
    }

    @Test
    public void testGetLoginUserWithMatcher() {
        MatcherController controller = new MatcherController();
        controller.setPermissionService(permissionService);
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("11111");

        when(permissionService.getRole(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object o) {
                return userNameList.contains(o.toString());
            }
        }), argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object o) {
                return passwordList.contains(o.toString());
            }
        }))).thenReturn("user");
        String role = controller.getRole(request);

        assertThat(role, equalTo("user"));
    }

    @Test
    public void testGetLoginUserByAssert() {
        MatcherController controller = new MatcherController();
        controller.setPermissionService(permissionService);
        when(request.getParameter("username")).thenReturn("alex");
        when(request.getParameter("password")).thenReturn("11111");

        when(permissionService.getRole(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object o) {
                return userNameList.contains(o.toString());
            }
        }), argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(Object o) {
                return passwordList.contains(o.toString());
            }
        }))).thenReturn("admin");

        String role = controller.getRole(request);
        assertThat(role, either(is("user")).or(is("admin")));
        assertThat(role, both(not("user")).and(not("guest")));

        assertThat(role, anyOf(is("user"), is("admin"), is("guest"), is("other")));

        assertThat(role, not(anyOf(is("user"), is("guest"), is("other"))));
        assertThat(role, not(allOf(is("user"), is("guest"), is("admin"), is("other"))));
    }
}