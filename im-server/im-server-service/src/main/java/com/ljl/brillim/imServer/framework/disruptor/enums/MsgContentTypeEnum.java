package com.ljl.brillim.imServer.framework.disruptor.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MsgContentTypeEnum {
    TEXT(0,"TEXT"),//文字
    LINK(1,"LINK"),//链接
    PICTURE(2,"PICTURE"),//图片
    VIDEO(3,"VIDEO"),//视频
    VOICE(4,"VOICE"),//声音
    SYSTEM(5,"SYSTEM");//系统消息

    @Getter
    private int code;

    @Getter
    private String desc;

    public static MsgContentTypeEnum of(String s) {
        for (MsgContentTypeEnum msgType : MsgContentTypeEnum.values() ) {
            if (msgType.desc.equals(s) ) {
                return msgType;
            }
        }
        return null;
    }

    public static MsgContentTypeEnum getByCode(Integer s) {
        for (MsgContentTypeEnum msgType : MsgContentTypeEnum.values()) {
            if (msgType.code == s ) {
                return msgType;
            }
        }
        return null;
    }
}
