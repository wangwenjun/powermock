package com.wangwenjun.app;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Created by wangwenjun on 2016/10/9.
 */

@RunWith(ConcordionRunner.class)
public class SystemSetFixture {

    private SystemSet systemSet;

    @Before
    public void before() {
        systemSet = new SystemSet();
    }


    public int sum(int firstNumber, int secondNumber) {
        return systemSet.sum(firstNumber, secondNumber);
    }
}