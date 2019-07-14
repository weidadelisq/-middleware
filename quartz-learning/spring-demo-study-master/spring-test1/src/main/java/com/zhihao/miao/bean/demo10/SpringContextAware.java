package com.zhihao.miao.bean.demo10;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
@Component
public interface SpringContextAware {
    void setApplicationContext(ApplicationContext applicationContext);
}
