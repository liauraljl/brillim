package com.ljl.brillim.imServer.framework.disruptor.consumer;

import com.ljl.brillim.imServer.framework.disruptor.model.TranslatorDataWapper;
import com.ljl.brillim.imServer.framework.netty.model.TranslatorData;
import com.lmax.disruptor.RingBuffer;
import io.netty.channel.ChannelHandlerContext;
import lombok.AllArgsConstructor;

/**
 * 生产者
 */
@AllArgsConstructor
public class MessageProducer {

    private RingBuffer<TranslatorDataWapper> ringBuffer;

    /**
     * 投递消息
     * @param data
     * @param ctx
     */
    public void pushData(TranslatorData data, ChannelHandlerContext ctx){
        long sequence=ringBuffer.next();
        try{
            TranslatorDataWapper wapper=ringBuffer.get(sequence);
            wapper.setData(data);
            wapper.setCtx(ctx);
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
