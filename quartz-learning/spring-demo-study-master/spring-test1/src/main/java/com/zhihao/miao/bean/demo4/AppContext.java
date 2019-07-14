package com.zhihao.miao.bean.demo4;

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
    public Info info(){
        return new Info();
    }

    @Bean
    public Bus createBus(){
        Bus bus = new Bus();
        bus.setInfo(info());
        return bus;
    }
}
