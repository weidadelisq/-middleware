package com.lyh.demo.entiy;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author: lishuangqiang
 * @Date: 2019/6/15
 * @Description:
 */
@Data
@Accessors(chain = true)
public class Student {
    private    String  name;
    private    int  age;
}
