package com.example.proxytest.proxytest.lmax;

import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author dean <tanping@globalegrow.com>
 * @version 1.0.0
 * @date 2019/07/05 09:02
 * @since 1.0.0
 */
@Data
@Service
public class MaxService {

    Disruptor<PeopleEvent> disruptor;

    public void runMax(){
        //Executor将用来为消费者构建线程
        Executor executor = Executors.newCachedThreadPool();

        //事件工厂用来创建事件
        PeopleEventFactory peopleEventFactory = new PeopleEventFactory();

        //指定Ring Buffer大小，2的倍数
        int buffSize = 1024;

        /**
         * 构造Disruptor
         * 并发系统提高性能之一就是单一写者原则，如果代码中仅有一个事件生产者，可以设置单一生产者模式来提高系统的性能。
         * 通过ProduceType.SINGLE和ProduceType.MULTI进行控制。
         *
         * 等待策略
         * Disruptor默认的等待策略是BlockingWaitStrategy，使用一个锁和条件变量来控制执行和等待，这是最慢的策略，但也是CPU使用最低
         * 和最稳定的策略。
         * SleepingWaitStrategy：也是CPU使用率低的策略，它使用循环等待并且循环间调用LockSupport.parkNanos(1)来睡眠。它的优点在于
         * 生产线程只需记数，而不执行任何命令，并且没有条件变量的消耗。但是对象从生产者到消费者传递延迟变大了，适用于不需要低延迟的场景，
         * YieldingWaitStrategy：是可以被用作低延迟系统的两个策略之一，这种策略在低延迟同时会增加CPU运算量。YieldingWaitStrategy
         * 会循环等待sequence增加到合适值，循环调用Tread.yield()允许其它准备好的线程执行。如果高性能而且事件消费者线程比逻辑内核少的
         * 时候，推荐使用YieldingWaitStrategy策略。
         * BusySpinWaitStrategy是性能最高的策略，同时也是对部署环境要求最高的策略。这个策略最好用在时间处理线程比物理内核数目还要少的时候。
         */


        disruptor = new Disruptor<PeopleEvent>(peopleEventFactory,buffSize,new MaxThreadFactory(),
                ProducerType.SINGLE,new YieldingWaitStrategy());




    }

}
