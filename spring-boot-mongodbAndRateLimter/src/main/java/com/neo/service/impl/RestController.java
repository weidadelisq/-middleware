package com.neo.service.impl;

import com.neo.config.MyResult;
import com.neo.config.RateLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @Author: lishuangqiang
 * @Date: 2019/6/29
 * @Description:
 */
@org.springframework.web.bind.annotation.RestController
public class RestController   {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    // 2个接口设定没秒限流5个和美妙限流10个
    @RateLimit(limitNum = 1.0)
    @GetMapping("/getResults")
    public String getResults() {
        log.info("调用了方法getResults");
       // return MyResult.OK("调用了方法", null);
        return "调用了方法";
    }

    @RateLimit(limitNum = 10.0)
    public MyResult getResultTwo() {
        log.info("调用了方法getResultTwo");
        return MyResult.OK("调用了方法getResultTwo", null);
    }



}
