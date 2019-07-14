package com.zhihao.miao.bean.demo6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
@Configuration
public class AppContext {

    @Bean
    public Train createTrain(){
        return new Train();
    }

}
