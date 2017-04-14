package com.wangwenjun.app;

/**
 * Created by wangwenjun on 2016/10/9.
 */
public class Result
{

    private String userName;

    private int age;

    public Result(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}