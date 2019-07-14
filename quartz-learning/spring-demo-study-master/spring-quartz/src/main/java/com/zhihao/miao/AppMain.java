package com.zhihao.miao;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/15
 * @since 1.0
 */
public class AppMain {
    public static void main(String args[]){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("spring-common.xml");
        SimpleTriggerFactoryBean simpleTriggerFactoryBean =  context.getBean(SimpleTriggerFactoryBean.class);
        System.out.println("======"+context.getBean("simpleJobDetail").getClass());
    }

}
