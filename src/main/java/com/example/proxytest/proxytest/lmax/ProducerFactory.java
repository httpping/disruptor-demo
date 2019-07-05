package com.example.proxytest.proxytest.lmax;

import com.lmax.disruptor.EventFactory;

/**
 * TODO
 *
 * @author dean <tanping@globalegrow.com>
 * @version 1.0.0
 * @date 2019/07/05 11:45
 * @since 1.0.0
 */
public class ProducerFactory implements EventFactory<PeopleEvent> {
    @Override
    public PeopleEvent newInstance() {
        return null;
    }
}
