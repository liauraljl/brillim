package com.ljl.brillim.imServer.framework.disruptor.consumer;

import com.alibaba.fastjson.JSONObject;
import com.ljl.brillim.imServer.common.RedisService;
import com.ljl.brillim.imServer.framework.disruptor.enums.WebsocketMsgTylpeEnum;
import com.ljl.brillim.imServer.framework.disruptor.model.MsgContent;
import com.ljl.brillim.imServer.framework.disruptor.model.TranslatorDataWapper;
import com.ljl.brillim.imServer.framework.netty.model.TranslatorData;
import com.lmax.disruptor.WorkHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务端消费者
 */
@Slf4j
@Data
public class MessageConsumer implements WorkHandler<TranslatorDataWapper> {

    private String consumerId;

    public MessageConsumer(String consumerId){
        this.consumerId=consumerId;
    }
    @Override
    public void onEvent(TranslatorDataWapper translatorDataWapper) throws Exception {
        TranslatorData translatorData=translatorDataWapper.getData();
        ChannelHandlerContext ctx=translatorDataWapper.getCtx();
        //ctx.writeAndFlush(response);
        MsgContent msgContent=JSONObject.parseObject(translatorData.getMessage(), MsgContent.class);
        switch (WebsocketMsgTylpeEnum.getByType(msgContent.getMsgType())){
            case AUTH:

                break;
            case INSYSTEM:

                break;
            case INCHATROOM:

                break;
            case OUTCHATROOM:

                break;
            case OUTSYSTEM:

                break;
            case GROUPMSG:

                break;
            case FRIENDMSG:

                break;
        }

    }
}
