package com.ljl.brillim.imServer.framework.netty.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * websocket消息类型
 */
@AllArgsConstructor
public enum  NettyMsgTylpeEnum {
    AUTH(0,"AUTH"),//权限校验
    INSYSTEM(1,"INSYSTEM"),//进入系统
    INCHATROOM(2,"INCHATROOM"),//进入聊天室
    OUTCHATROOM(3,"OUTCHATROOM"),//离开聊天室
    OUTSYSTEM(4,"OUTSYSTEM"),//离开系统
    GROUPMSG(5,"GROUPMSG"),//群消息
    FRIENDMSG(6,"FRIENDMSG");//好友消息

    @Getter
    private int code;

    @Getter
    private String desc;

    public static NettyMsgTylpeEnum of(String s) {
        for (NettyMsgTylpeEnum msgType : NettyMsgTylpeEnum.values()) {
            if (msgType.desc.equals(s) ) {
                return msgType;
            }
        }
        return null;
    }

    public static NettyMsgTylpeEnum getByCode(Integer s) {
        for (NettyMsgTylpeEnum msgType : NettyMsgTylpeEnum.values()) {
            if (msgType.code == s ) {
                return msgType;
            }
        }
        return null;
    }
}
