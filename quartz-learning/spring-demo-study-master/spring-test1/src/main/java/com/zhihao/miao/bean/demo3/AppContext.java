package com.zhihao.miao.bean.demo3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/8
 * @since 1.0
 */
@Configuration
public class AppContext {

    @Bean
    public CarFactory createCarFactory(){
        return new CarFactory();
    }

    @Bean
    public Car createCar(CarFactory factory){
        return factory.create();
    }

}
