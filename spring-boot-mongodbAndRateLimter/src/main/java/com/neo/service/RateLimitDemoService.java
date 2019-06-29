package com.neo.service;

import com.neo.config.MyResult;
import com.neo.config.RateLimit;
import org.springframework.stereotype.Service;

/**
 * @Auther: HP
 * @Date: 2019/6/29 13:31
 * @Description:
 */
@Service
public interface RateLimitDemoService {

     MyResult getResults() ;

     MyResult getResultTwo();


}
