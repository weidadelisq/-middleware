package com.zhihao.miao.bean.demo9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
@Configuration
public class MyConfig {

    @Bean(initMethod="init")
    public Car createCar(){
        return new Car();
    }
}
