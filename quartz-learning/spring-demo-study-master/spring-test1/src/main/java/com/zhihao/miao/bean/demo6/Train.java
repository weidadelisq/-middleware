package com.zhihao.miao.bean.demo6;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
public class Train {

    @PostConstruct
    public void initial(){
        System.out.println("......initial......");
    }

    @PreDestroy
    public void close(){
        System.out.println("......close.........");
    }

}
