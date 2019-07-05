package com.example.proxytest.proxytest;

import com.example.proxytest.proxytest.controler.IProxyControler;
import com.example.proxytest.proxytest.controler.ProxyControler;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class ProxytestApplicationTests {

    @Autowired
    IProxyControler proxyControler;

//    3558 3992
//    273 com.example.proxytest.proxytest.controler.ProxyControler@fe09383
    @Test
    public void contextLoads() throws NoSuchMethodException {

        int count = 10000;

        long start = System.currentTimeMillis();

        int index = 0;
        while(index++<count){
            proxyControler.hello(index+"");
        }
        long end = System.currentTimeMillis();

        end = end - start;
        log.info("消耗：" + end);

    }

    @Test
    public void testInvoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        int count = 10000000;

        Method method  =proxyControler.getClass().getMethod("hello",String.class);
        long start = System.currentTimeMillis();

        int index = 0;
        while(index++<count){
            method.invoke(proxyControler,index+"");
//            proxyControler.hello(index+"");
        }
        long end = System.currentTimeMillis();

        end = end - start;
        log.info("消耗：" + end);

    }



    @Test
    public void testHandleInvoke() throws Throwable {

        int count = 10000000;

//        Method method  =proxyControler.getClass().getMethod("hello",String.class);
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();

        MethodType mt = MethodType.methodType(String.class,String.class);
        MethodHandle replaceMH = publicLookup.findVirtual(IProxyControler.class, "hello", mt);
        MethodHandle methodHandle2 =  replaceMH.bindTo(proxyControler);
        long start = System.currentTimeMillis();

        int index = 0;
        while(index++<count){
//            String a = (String) methodHandle2.invokeWithArguments(index + "");
            String a = (String) replaceMH.invokeExact(proxyControler,index + "");
        }
        long end = System.currentTimeMillis();

        end = end - start;
        log.info("消耗：" + end);

    }



}
