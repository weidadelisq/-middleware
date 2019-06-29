package com.neo;

import com.neo.controller.RequestCacheCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.util.Assert;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class HelloWorld {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
      /*  System.out.println(123);
        String result = new CommandHelloworld(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"), "aaabbbccc").execute();
        String result2 = new CommandHelloworld(HystrixCommandGroupKey.Factory.asKey("ExampleGroup2"), "123sss").execute();
        System.out.println(result);
        System.out.println(result2);
*/




/*
        HelloWorldCommand helloWorldCommand = new HelloWorldCommand("Asynchronous-hystrix");
        //异步调用,可自由控制获取结果时机,
        Future<String> future = helloWorldCommand.queue();
        //get操作不能超过command定义的超时时间,默认:1秒
        String  result = future.get(1, TimeUnit.SECONDS);
        System.out.println("result=" + result);
        System.out.println("mainThread=" + Thread.currentThread().getName());*/







    }
}
