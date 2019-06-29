package com.neo.config;

import java.util.List;

public class MyResult {
    private Integer status;
    private String msg;
    private List<Object> data;

    public MyResult(Integer status, String msg, List<Object> data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static MyResult OK(String msg, List<Object> data) {
        return new MyResult(200, msg, data);
    }

    public static MyResult Error(Integer status, String msg) {
        return new MyResult(status, msg, null);
    }
}
