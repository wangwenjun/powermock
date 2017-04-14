package com.wangwenjun.app;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by wangwenjun on 2016/10/9.
 */
@RunWith(ConcordionRunner.class)
public class SystemEmptyFixture {

    public int length(String name) {
        return name.length();
    }
}