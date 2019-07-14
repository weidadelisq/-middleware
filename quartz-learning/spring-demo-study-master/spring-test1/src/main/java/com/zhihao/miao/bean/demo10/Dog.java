package com.zhihao.miao.bean.demo10;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
@Component
public class Dog implements SpringContextAware {

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void show(){
        System.out.println("dog:"+applicationContext.getClass());
    }

}
