package com.example.proxytest.proxytest.controler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author dean <tanping@globalegrow.com>
 * @version 1.0.0
 * @date 2019/07/04 09:50
 * @since 1.0.0
 */

@RestController
public class ProxyControler implements IProxyControler {


    @Override
    @PostMapping(path = "hello")
    public String hello(String param0) {
        return "hello world!";
    }
}
