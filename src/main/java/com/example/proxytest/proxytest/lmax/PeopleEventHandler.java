package com.example.proxytest.proxytest.lmax;

import com.lmax.disruptor.EventHandler;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class PeopleEventHandler implements EventHandler<PeopleEvent> {
    @Override
    public void onEvent(PeopleEvent event, long sequence, boolean endOfBatch) throws Exception {
        log.info( sequence + "-" + event.getSequence()+" end:"+ endOfBatch +"  == " +"name:" + event.getName() + ",sex:" + event.getSex() + ",age:" + event.getAge());
        event.setName(this.hashCode() +" name");
    }

}
