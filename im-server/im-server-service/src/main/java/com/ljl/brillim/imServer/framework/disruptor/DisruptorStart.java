package com.ljl.brillim.imServer.framework.disruptor;

import com.ljl.brillim.imServer.framework.disruptor.consumer.MessageConsumer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DisruptorStart {

    /**
     * disruptor初始化
     */
    @PostConstruct
    public void disruptorStart(){
        //为保证消息有序性，采用单生产者、消费者模式
        //不注重有序性时，可以采用多生产者、消费者模式，提高吞吐量
        MessageConsumer[] conusmers = new MessageConsumer[1];
        for(int i =0; i < conusmers.length; i++) {
            MessageConsumer messageConsumer = new MessageConsumer();
            conusmers[i] = messageConsumer;
        }
        //初始化Disruptor
        RingBufferWorkerPoolFactory.getInstance().initAndStart(ProducerType.SINGLE,
                1024*1024,
                //new YieldingWaitStrategy(),
                new BlockingWaitStrategy(),
                conusmers);
    }
}
