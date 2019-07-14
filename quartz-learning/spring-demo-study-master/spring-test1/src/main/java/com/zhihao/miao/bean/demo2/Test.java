package com.zhihao.miao.bean.demo2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/8
 * @since 1.0
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        System.out.println(context.getBean("createRunnableFactoryBean"));//com.zhihao.miao.bean.demo2.Jeep@24b1d79b
        System.out.println(context.getBean("&createRunnableFactoryBean"));  //com.zhihao.miao.bean.demo2.RunnableFactoryBean@68ceda24
        context.close();

    }

}
