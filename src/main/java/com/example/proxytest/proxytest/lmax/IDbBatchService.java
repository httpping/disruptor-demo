package com.example.proxytest.proxytest.lmax;

import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public interface IDbBatchService {

    boolean batchSave(List<PeopleEvent> events);
}
