package com.neo.easyRules;

import lombok.Data;

@Data
public class Person {

    private boolean adult;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;
    //getter, setter 省略

}