package com.example.proxytest.proxytest.lmax;

import java.util.concurrent.ThreadFactory;

/**
 *  线程工程
 *
 * @author dean <tanping@globalegrow.com>
 * @version 1.0.0
 * @date 2019/07/05 09:08
 * @since 1.0.0
 */
public class MaxThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}
