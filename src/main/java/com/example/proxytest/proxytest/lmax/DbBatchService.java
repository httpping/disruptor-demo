package com.example.proxytest.proxytest.lmax;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author dean <tanping@globalegrow.com>
 * @version 1.0.0
 * @date 2019/07/05 09:32
 * @since 1.0.0
 */
@Log4j2
@Service
public class DbBatchService implements IDbBatchService{

    @Override
    public boolean batchSave(List<PeopleEvent> events){
        log.info("events:" + events.size());
        return true;
    }
}
