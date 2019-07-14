package com.zhihao.miao.bean.demo7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
@Component
public class User {

    //使用Spring提供的方式
    @Autowired
    private UserService userService;

    //JSR-250 的注解
    @Resource
    private Car car;

    //JSR-330 的注解,要加入inject依赖
    @Inject
    private Bus bus;

    @Override
    public String toString() {
        return "User [userService=" + userService + ", car=" + car + ", bus=" + bus + "]";
    }

}
