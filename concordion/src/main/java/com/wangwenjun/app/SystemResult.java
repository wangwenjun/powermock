package com.wangwenjun.app;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangwenjun on 2016/10/9.
 */
public class SystemResult {

    public Result query(String userName) {
        Result result = new Result("Alex", 31);
        return result;
    }

    public Map split(String name) {
        Map<String, String> result = new HashMap<>();
        String[] names = name.split(",");
        result.put("firstName", names[0]);
        result.put("secondName", names[1]);
        return result;
    }

    public List<Result> list(String userName) {
        return Arrays.<Result>asList(new Result("Alex", 31),
                new Result("Tony", 32), new Result("Van", 28));
    }
}
