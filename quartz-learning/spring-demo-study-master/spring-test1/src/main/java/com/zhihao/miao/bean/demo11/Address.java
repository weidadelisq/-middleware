package com.zhihao.miao.bean.demo11;

import javax.annotation.PostConstruct;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/10
 * @since 1.0
 */
public class Address {
    private String pro;
    private String city;

    public String getPro() {
        return pro;
    }

    public void setPro(String pro) {
        this.pro = pro;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @PostConstruct
    public void init(){
        System.out.println("=== Address ===init ====");
    }
}
