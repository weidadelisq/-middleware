package com.neo.controller;

import com.neo.entiy.Test;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
// 声明一个控制器
@RequestMapping("/hello")
public class HomeController {
    @PostMapping("/submit")
    public String submit(@RequestBody @Valid Test test, Errors errors){
        if(errors.hasErrors()){
            System.out.println(errors.hasErrors());
            System.out.println(errors);
            return "failed"; //如果提交的表单数据不符合校验规则就返回失败
        }
        return "success";//符合规则 就成功
    }
}


