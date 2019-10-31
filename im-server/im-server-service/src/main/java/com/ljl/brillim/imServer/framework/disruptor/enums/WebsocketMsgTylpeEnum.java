package com.ljl.brillim.imServer.framework.disruptor.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * websocket消息类型
 */
@AllArgsConstructor
public enum WebsocketMsgTylpeEnum {
    AUTH(0,"AUTH"),//权限校验
    INSYSTEM(1,"INSYSTEM"),//进入系统
    INCHATROOM(2,"INCHATROOM"),//进入聊天室
    OUTCHATROOM(3,"OUTCHATROOM"),//离开聊天室
    OUTSYSTEM(4,"OUTSYSTEM"),//离开系统
    GROUPMSG(5,"GROUPMSG"),//群消息
    FRIENDMSG(6,"FRIENDMSG");//好友消息

    @Getter
    private int type;

    @Getter
    private String desc;

    public static WebsocketMsgTylpeEnum of(String s) {
        for (WebsocketMsgTylpeEnum msgType : WebsocketMsgTylpeEnum.values() ) {
            if (msgType.desc.equals(s) ) {
                return msgType;
            }
        }
        return null;
    }

    public static WebsocketMsgTylpeEnum getByType(Integer s) {
        for (WebsocketMsgTylpeEnum msgType : WebsocketMsgTylpeEnum.values()) {
            if (msgType.type == s ) {
                return msgType;
            }
        }
        return null;
    }
}
