package com.zhihao.miao.bean.demo3;

import com.zhihao.miao.bean.demo2.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/8
 * @since 1.0
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        Car car = context.getBean(Car.class);
        System.out.println(car);

        Car car2 = (Car) context.getBean("createCar");
        System.out.println(car2);
        context.close();
    }
}
