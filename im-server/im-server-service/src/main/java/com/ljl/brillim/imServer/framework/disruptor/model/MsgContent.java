package com.ljl.brillim.imServer.framework.disruptor.model;

import lombok.Data;

@Data
public class MsgContent {
    private Integer msgSource;

    private Long friendId;

    private Long groupId;

    private Integer msgType;

    private Object content;
}
