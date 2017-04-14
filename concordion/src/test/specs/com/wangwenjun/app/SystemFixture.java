package com.wangwenjun.app;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by wangwenjun on 2016/10/8.
 */
@RunWith(ConcordionRunner.class)
public class SystemFixture {

    private System system;

    @Before
    public void before() {
        system = new System();
    }

    public String invokeLogin(String user) {
        return system.login(user);
    }
}