package com.ljl.brillim.imServer.framework.disruptor.model;

import com.ljl.brillim.imServer.framework.netty.model.TranslatorData;
import io.netty.channel.ChannelHandlerContext;
import lombok.Data;

/**
 * disruptor 消息载体
 */
@Data
public class TranslatorDataWapper {

    private TranslatorData data;

    private ChannelHandlerContext ctx;
}
