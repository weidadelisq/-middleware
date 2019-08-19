package com.neo.util;

/**
 * @Author: lishuangqiang
 * @Date: 2019/8/19
 * @Description:
 */

public class Utills {
    /**
     *
     * 功能描述:获取字符hashcode取余100后的值
     * @auther: lishuangqiang
     * @date: 2019/8/19
     * @param: [key]
     * @return: int
     */
    public static  int hash(String key) {
        return (key.hashCode() & Integer.MAX_VALUE) % 100;
    }
}
