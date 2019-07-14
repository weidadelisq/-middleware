package com.zhihao.miao.bean.demo4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/8
 * @since 1.0
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        Bus bus = context.getBean(Bus.class);
        System.out.println(bus);
        context.close();
    }


}
