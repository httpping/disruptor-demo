package com.example.proxytest.proxytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.nio.Buffer;
import java.nio.ByteBuffer;

@EnableAspectJAutoProxy(proxyTargetClass = false,exposeProxy=false)
@SpringBootApplication
public class ProxytestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProxytestApplication.class, args);
    }

}
