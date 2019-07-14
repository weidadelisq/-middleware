package com.zhihao.miao.bean.demo1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @Auther : 苗志浩 (zhihao.miao@ele.me)
 * @Date :2017/8/8
 * @since 1.0
 */
@Configuration
public class AppContext {

    @Bean
    @Scope("prototype")
    public Course course(){
        Course course = new Course();
        course.setModule(module());
        return course;
    }

    @Bean
    public Module module(){
        Module module = new Module();
        module.setAssignment(assignment());
        return module;
    }

    @Bean
    public Assignment assignment(){
        Assignment assignment = new Assignment();
        return assignment;
    }

}
