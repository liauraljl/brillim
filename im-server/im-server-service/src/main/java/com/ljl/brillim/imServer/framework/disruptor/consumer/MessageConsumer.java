package com.ljl.brillim.imServer.framework.disruptor.consumer;

import com.ljl.brillim.imServer.framework.disruptor.model.TranslatorDataWapper;
import com.ljl.brillim.imServer.framework.netty.model.TranslatorData;
import com.lmax.disruptor.WorkHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * 服务端消费者
 */
@Data
public class MessageConsumer implements WorkHandler<TranslatorDataWapper> {

    private String consumerId;

    public MessageConsumer(String consumerId){
        this.consumerId=consumerId;
    }
    @Override
    public void onEvent(TranslatorDataWapper translatorDataWapper) throws Exception {
        TranslatorData data=translatorDataWapper.getData();
        ChannelHandlerContext ctx=translatorDataWapper.getCtx();
        //1.业务处理逻辑:
        System.err.println("Sever端: id= " + data.getId()
                + ", name= " + data.getName()
                + ", message= " + data.getMessage());

        //2.回送响应信息:
        TranslatorData response = new TranslatorData();
        response.setId("resp: " + data.getId());
        response.setName("resp: " + data.getName());
        response.setMessage("resp: " + data.getMessage());
        //写出response响应信息:
        ctx.writeAndFlush(response);
    }
}
