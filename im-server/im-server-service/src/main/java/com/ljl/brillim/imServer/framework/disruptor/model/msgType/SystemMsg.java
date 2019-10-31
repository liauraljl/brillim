package com.ljl.brillim.imServer.framework.disruptor.model.msgType;

import lombok.Data;

import java.io.Serializable;

@Data
public class SystemMsg implements Serializable {

    private static final long serialVersionUID = 6278922562664931335L;

    private String type;

    private String text;
}
