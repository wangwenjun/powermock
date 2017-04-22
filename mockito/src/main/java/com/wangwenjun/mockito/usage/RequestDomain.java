package com.wangwenjun.mockito.usage;

/***************************************
 * @author:Alex Wang
 * @Date:2017/4/16 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class RequestDomain {
    private String username;
    private String password;

    public RequestDomain(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
