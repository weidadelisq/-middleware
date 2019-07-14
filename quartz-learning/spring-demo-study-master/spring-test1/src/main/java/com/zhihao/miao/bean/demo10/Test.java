package com.zhihao.miao.bean.demo10;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.zhihao.miao.bean.demo10");
        Dog dog = context.getBean(Dog.class);
        dog.show();
    }
}
