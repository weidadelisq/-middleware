package com.neo.entiy;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class Test {
    /**
     * username 不为空 长度在5-16个字符之间
     * password 不为空 长度在8-16个字符之间
     * number 不为空 大小在0-100之间
     */
    @NotNull
    @Size(min = 5, max = 16)
    private String username;

    @NotNull
    @Size(min = 8, max = 16)
    private String password;

    @NotNull
    @Max(100)
    @Min(0)
    private int number;
}
