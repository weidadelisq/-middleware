package com.zhihao.miao.bean.demo2;

import org.springframework.beans.factory.FactoryBean;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/8
 * @since 1.0
 */
public class RunnableFactoryBean  implements FactoryBean<Jeep> {

    //实现FactoryBean实际要返回的对象
    @Override
    public Jeep getObject() throws Exception {
        return new Jeep();
    }

    @Override
    public Class<?> getObjectType() {
        return Jeep.class;
    }

    //作用域是否是单列
    @Override
    public boolean isSingleton() {
        return false;
    }

}
