package com.example.proxytest.proxytest.lmax;

import com.example.proxytest.proxytest.utils.SpringContextUtil;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CoreSaveMonitorDataHandler implements EventHandler<PeopleEvent> {

    public final static int DB_BATCH_SIZE = 200;
    public final static int RING_BATCH_SIZE = 1024;
    private List<PeopleEvent> cache = new ArrayList<>();
    
    private IDbBatchService dbBatchService = SpringContextUtil.getBean(IDbBatchService.class);


    public long max_count =0;

    @Override
    public void onEvent(PeopleEvent value, long sequence, boolean endOfBatch)
            throws Exception {
        try {
            saveMetricData(value, sequence, endOfBatch);
        } catch (Exception e) {
            log.error("Exception ", e);
        }
    }
 
 
    private void saveMetricData(PeopleEvent value, long sequence, boolean endOfBatch) {
        cache.add(value);
        max_count++;
        if ((sequence + 1) % DB_BATCH_SIZE == 0) {
            dbBatchService.batchSave(cache);
            cache.clear();
        }
        if (endOfBatch) {
            if ((sequence + 1) % RING_BATCH_SIZE != 0) {
                dbBatchService.batchSave(cache);
                cache.clear();
            }
        }
    }
}
