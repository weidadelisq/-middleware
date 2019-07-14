package com.zhihao.miao.bean.demo4;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/8
 * @since 1.0
 */
public class Bus implements InitializingBean,DisposableBean {

    private Info info;

    public void setInfo(Info info) {
        System.out.println("setInfo");
        this.info = info;
    }

    public Info getInfo() {
        return info;
    }

    public Bus(){
        System.out.println("bus constr");
    }

    //InitializingBean的方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("==============afterPropertiesSet==========");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("=================destroy===================");
    }
}
