package com.example;

import java.util.Date;

@org.apache.dubbo.config.annotation.Service
public class HelloServiceImpl implements HelloService {

	@Override
    public String sayHello(String name) {
        return "Hello, " + name + ", " + new Date();
    }

}