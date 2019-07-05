package com.example.proxytest.proxytest.lmax;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.util.Map;

public class PeopleEventProducer {
    private final RingBuffer<PeopleEvent> ringBuffer;

    public PeopleEventProducer(RingBuffer ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<PeopleEvent, Map<String, Object>> tranlator = new EventTranslatorOneArg<PeopleEvent, Map<String, Object>>() {
        @Override
        public void translateTo(PeopleEvent event, long sequence, Map<String, Object> data) {
            event.setName(data.get("name").toString());
            event.setAge((Integer) data.get("age"));
            event.setSex((Integer) data.get("sex"));
            event.setSequence(sequence);
        }
    };

    /**
     * onData用来发布事件，每调用一次就发布一次事件，它的参数会通过事件传递给消费者
     */
    public void onData(Map<String, Object> data) {
        ringBuffer.publishEvent(tranlator, data);
    }
}
