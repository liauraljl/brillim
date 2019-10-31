package com.ljl.brillim.imServer.framework.disruptor.model.msgType;

import lombok.Data;

import java.io.Serializable;

@Data
public class PicMsg implements Serializable {

    private static final long serialVersionUID = 5994490469530156125L;

    private String picUrl;

    private String mediaid;
}
