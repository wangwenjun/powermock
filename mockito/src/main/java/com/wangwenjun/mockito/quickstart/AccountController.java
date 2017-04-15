package com.wangwenjun.mockito.quickstart;

import javax.servlet.http.HttpServletRequest;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/15 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class AccountController {

    private final AccountDao accountDao;

    public AccountController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public String login(HttpServletRequest request) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(username);
        System.out.println(password);

        if (null == username || null == password)
            throw new RuntimeException();

        boolean valid = accountDao.login(username, password);
        return valid ? "/index" : "/login";
    }
}