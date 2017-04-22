package com.wangwenjun.mockito.usage;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/15 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class AccountDao {
    public boolean login(String username, String password) {
        this.check(username, password);
        throw new UnsupportedOperationException();
    }

    public boolean login(RequestDomain domain) {
        throw new UnsupportedOperationException();
    }

    public void check(String username, String password) {
        System.out.println("====================");
    }
}