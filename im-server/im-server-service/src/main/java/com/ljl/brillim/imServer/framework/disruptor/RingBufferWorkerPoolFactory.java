package com.ljl.brillim.imServer.framework.disruptor;

import com.ljl.brillim.imServer.framework.disruptor.consumer.MessageConsumer;
import com.ljl.brillim.imServer.framework.disruptor.consumer.MessageProducer;
import com.ljl.brillim.imServer.framework.disruptor.model.TranslatorDataWapper;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * disruptor工厂类
 */
public class RingBufferWorkerPoolFactory {

    private static class SingetonHolder{
        static final RingBufferWorkerPoolFactory instance=new RingBufferWorkerPoolFactory();
    }

    private RingBufferWorkerPoolFactory(){}

    public static RingBufferWorkerPoolFactory getInstance(){
        return SingetonHolder.instance;
    }

    private static Map<String, MessageConsumer> consumers=new ConcurrentHashMap<>();

    private static Map<String, MessageProducer> producers=new ConcurrentHashMap<>();

    private RingBuffer<TranslatorDataWapper> ringBuffer;

    private SequenceBarrier sequenceBarrier;

    private WorkerPool<TranslatorDataWapper> workerPool;

    /**
     * 初始化disruptor
     * @param producerType
     * @param bufferSize
     * @param waitStrategy
     * @param messageConsumers
     */
    public void initAndStart(ProducerType producerType, int bufferSize, WaitStrategy waitStrategy,MessageConsumer[] messageConsumers){
        //1、构建ringBuffer对象
        this.ringBuffer=RingBuffer.create(producerType, new EventFactory<TranslatorDataWapper>() {
            @Override
            public TranslatorDataWapper newInstance() {
                return new TranslatorDataWapper();
            }
        },bufferSize,waitStrategy);
        //2、设置序号栅栏
        this.sequenceBarrier=ringBuffer.newBarrier();
        //3、设置工作池
        this.workerPool=new WorkerPool<>(this.ringBuffer,this.sequenceBarrier,new EventExceptionHandler(),messageConsumers);
        //4、把所构建的消费者置入池中
        for(MessageConsumer mc:messageConsumers){
            this.consumers.put(mc.getConsumerId(),mc);
        }
        //5、添加sequences
        this.ringBuffer.addGatingSequences(this.workerPool.getWorkerSequences());
        //6、启动工作池
        this.workerPool.start(getWorkPoolStartExecutor());
        //初始化生产者
        initProducer();
    }

    /**
     * 初始化生产者
     */
    private void initProducer(){
        for(int i=0;i<10;i++){
            String producerId="server:disruptor:producerId:"+i;
            MessageProducer messageProducer=new MessageProducer(producerId,this.ringBuffer);
            this.producers.put(producerId,messageProducer);
        }
    }

    /**
     * 获取消息生产者
     * @param producerId
     * @return
     */
    public MessageProducer getMessageProducer(String producerId){
        MessageProducer messageProducer=this.producers.get(producerId);
        if(null==messageProducer){
            messageProducer=new MessageProducer(producerId,this.ringBuffer);
            this.producers.put(producerId,messageProducer);
        }
        return messageProducer;
    }

    /**
     * 异常处理静态类
     */
    private class EventExceptionHandler implements ExceptionHandler<TranslatorDataWapper>{

        @Override
        public void handleEventException(Throwable throwable, long l, TranslatorDataWapper translatorDataWapper) {

        }

        @Override
        public void handleOnStartException(Throwable throwable) {

        }

        @Override
        public void handleOnShutdownException(Throwable throwable) {

        }
    }

    /**
     * disruptor启动线程池(单列模式，无法由spring注入)
     * @return
     */
    private ThreadPoolTaskExecutor getWorkPoolStartExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(10000);
        executor.setKeepAliveSeconds(20);
        executor.setAllowCoreThreadTimeOut(true);
        executor.setThreadNamePrefix("[workPoolStartExecutor] -");
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
