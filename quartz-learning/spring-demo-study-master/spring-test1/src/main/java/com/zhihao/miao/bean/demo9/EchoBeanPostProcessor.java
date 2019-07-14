package com.zhihao.miao.bean.demo9;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
@Component
public class EchoBeanPostProcessor implements BeanPostProcessor{

    /**
     * 是在bean依赖装配（属性设置完）完成之后触发
     * 这里可以对指定的bean做一些处理，比如说，返该对象的代理对象
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("=========postProcessBeforeInitialization=========" + bean.getClass());
        //返回具体实例的代理对象
        return bean;
    }

    /**
     * 是在bean init方法执行之后触发的
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("=========postProcessAfterInitialization=========" + bean.getClass());
        return bean;
    }
}
