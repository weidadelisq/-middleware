package com.zhihao.miao.bean.demo5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/8
 * @since 1.0
 */
@Configuration
public class AppContext {

    @Bean(initMethod="init",destroyMethod="destroy")
    public Bike createBike(){
        return new Bike();
    }
}
