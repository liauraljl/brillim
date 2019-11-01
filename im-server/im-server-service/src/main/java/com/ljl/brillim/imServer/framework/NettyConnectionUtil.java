package com.ljl.brillim.imServer.framework;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyConnectionUtil {

    /**
     * userId和通道的绑定关系
     */
    public static Map<String, ChannelHandlerContext> userIdChannelMap=new ConcurrentHashMap<>();

    /**
     * 通道id和用户id的绑定关系
     */
    public static Map<String,String> channelIdUserIdMap=new ConcurrentHashMap<>();

    /**
     * 注册用户和通道的关系
     * @param userId
     * @param cx
     */
    public static void registerUserId(String userId, ChannelHandlerContext cx) {

        ChannelHandlerContext chc = userIdChannelMap.get(userId);
        if(null != chc){
            userIdChannelMap.remove(userId);
        }
        String channelId = getChannelId(cx);
        String uid = channelIdUserIdMap.get(channelId);
        if(null != uid){
            channelIdUserIdMap.remove(channelId);
        }
        userIdChannelMap.put(userId, cx);
        channelIdUserIdMap.put(channelId, userId);

    }

    /**
     * 获取通道id
     * @param cx
     * @return
     */
    public static String getChannelId(ChannelHandlerContext cx) {
        return cx.channel().id().asShortText();
    }


}
