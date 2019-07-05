package com.example.proxytest.proxytest;

import com.example.proxytest.proxytest.lmax.*;
import com.lmax.disruptor.RingBuffer;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 *  1、批量重数据库拿数据。
 *  2、Filter 重复数据，开16个过滤器
 *  3、批量consumer 插入 数据库
 *  4、结果数量检查，总量不一致。
 *  是否批量过滤，是否批量插入
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class MaxTests {

    @Autowired
    MaxService maxService;

    @Test
    public void testMax() throws NoSuchMethodException {
        maxService.runMax();
        //从Disruptor获取RingBuffer，用来发布
        RingBuffer<PeopleEvent> ringBuffer = maxService.getDisruptor().getRingBuffer();

        //批量处理
        CoreSaveMonitorDataHandler saveMetricDataHandler = new CoreSaveMonitorDataHandler();

        //链接处理器
        maxService.getDisruptor().handleEventsWith(new PeopleEventHandler()).then(new PeopleEventHandler())
        .then(saveMetricDataHandler)
         ;




        //监控队列
//       maxService.getDisruptor().handleEventsWith(saveMetricDataHandler);


        //启动Disruptor，启动所有线程
        maxService.getDisruptor().start();


        PeopleEventProducer producer = new PeopleEventProducer(ringBuffer);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name","yjz");
        map.put("age",25);
        map.put("sex",1);


        long start = System.currentTimeMillis();

        int max = 1;
        int index = 0;
        while (index++< max) {
            map = new HashMap<String, Object>();
            map.put("name","yjz"+index);
            map.put("age",index);
            map.put("sex",1);
            producer.onData(map);
        }

        maxService.getDisruptor().shutdown();

        long end = System.currentTimeMillis();
        end = end - start;
        log.info("消耗：" + end +" 数量：" +saveMetricDataHandler.max_count);


    }




}
