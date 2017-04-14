package com.wangwenjun.app;

import org.concordion.api.MultiValueResult;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwenjun on 2016/10/9.
 */
@RunWith(ConcordionRunner.class)
public class SystemResultFixture {

    private SystemResult system;

    @Before
    public void before() {
        system = new SystemResult();
    }

    public Result query(String userName) {
        return system.query(userName);
    }

    public Map split(String name) {
        return system.split(name);
    }

    public MultiValueResult list(String name) {
        List<Result> list = system.list(name);
        MultiValueResult result = new MultiValueResult();
        int index = 0;
        for (Result r : list) {
            result.with("result"+(++index),r);
        }
        return result;
    }
}