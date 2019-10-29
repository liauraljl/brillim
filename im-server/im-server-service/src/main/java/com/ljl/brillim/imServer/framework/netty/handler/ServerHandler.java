package com.ljl.brillim.imServer.framework.netty.handler;

import com.ljl.brillim.imServer.framework.disruptor.RingBufferWorkerPoolFactory;
import com.ljl.brillim.imServer.framework.disruptor.consumer.MessageProducer;
import com.ljl.brillim.imServer.framework.netty.model.TranslatorData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class ServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        /*TranslatorData data=(TranslatorData)msg;
        String producerId="code:producerId:server";
        MessageProducer messageProducer= RingBufferWorkerPoolFactory.getInstance().getMessageProducer(producerId);
        messageProducer.pushData(data,ctx);*/
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String channelId = ctx.channel().id().asLongText();
        /*log.info("通道激活：{}",ctx);
        //加入延时队列 5秒内未作权限校验，删除通道
        if(!AuthQueue.authMap.containsKey(channelId)){
            AuthTask authTask = new AuthTask(5L, ctx);
            AuthQueue.authMap.put(channelId,authTask);
            AuthQueue.delayQueue.offer(authTask);
            log.info("delayQueue content:{}", Arrays.toString(AuthQueue.delayQueue.toArray()));
        }*/
        super.channelActive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //NettyConnectionUtil.userOutRoom(ctx);
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            if(idleStateEvent.state() == IdleState.READER_IDLE){
                log.info("通道：{}，30秒未检测到读事件发生删除通道！",ctx);
                ctx.channel().close();
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
