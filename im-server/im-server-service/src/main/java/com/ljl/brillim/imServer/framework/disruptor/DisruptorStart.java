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
        MessageConsumer[] conusmers = new MessageConsumer[1];
        for(int i =0; i < conusmers.length; i++) {
            MessageConsumer messageConsumer = new MessageConsumer();
            conusmers[i] = messageConsumer;
        }
        RingBufferWorkerPoolFactory.getInstance().initAndStart(ProducerType.MULTI,
                1024*1024,
                //new YieldingWaitStrategy(),
                new BlockingWaitStrategy(),
                conusmers);
    }
}
