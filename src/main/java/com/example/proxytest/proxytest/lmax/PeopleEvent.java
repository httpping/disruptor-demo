package com.example.proxytest.proxytest.lmax;

import lombok.Data;

@Data
public  class PeopleEvent {
    private String name;
    private Integer age;
    private Integer sex;
    long sequence;


}