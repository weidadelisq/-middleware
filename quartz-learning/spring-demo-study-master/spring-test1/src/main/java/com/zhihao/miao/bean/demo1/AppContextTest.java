package com.zhihao.miao.bean.demo1;

import com.zhihao.miao.bean.demo1.AppContext;
import com.zhihao.miao.bean.demo1.Course;
import com.zhihao.miao.bean.demo1.Module;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/8
 * @since 1.0
 */
public class AppContextTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
        System.out.println(context.getBean("course"));
        System.out.println(context.getBean(Course.class));
        System.out.println(context.getBean(Module.class));
        context.close();
    }

}