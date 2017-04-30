package com.wangwenjun.mockito.matcher;

import javax.servlet.http.HttpServletRequest;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/30
 * QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class MatcherController {

    private PermissionService permissionService;


    /**
     * admin/admin      ->admin
     * alex/alex        ->user
     * guest/guest      ->guest
     * other ->         throw not found the role exception
     *
     * @param request
     * @return
     */
    public String getRole(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return permissionService.getRole(username, password);
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }
}