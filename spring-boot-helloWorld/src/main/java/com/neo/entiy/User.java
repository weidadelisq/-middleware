package com.neo.entiy;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: lishuangqiang
 * @Date: 2019/1/23
 * @Description:
 */
@Builder(toBuilder = true)
@Data
public class User {
    private    String  userName;
    private    String  passWord;

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}
