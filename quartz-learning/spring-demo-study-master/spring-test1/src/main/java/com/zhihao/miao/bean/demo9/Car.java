package com.zhihao.miao.bean.demo9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
public class Car {


    private ApplicationContext applicationContext;

    @Autowired
    public void setApplication(ApplicationContext applicationContext) {
        System.out.println("=========set=========");
        this.applicationContext = applicationContext;
    }

    public void show(){
        System.out.println("================show=========="+applicationContext.getClass());
    }

    public void init(){
        System.out.println("===== Car init ==========");
    }
}
