package com.ljl.brillim.imServer.framework.disruptor.model.msgType;

import lombok.Data;

import java.io.Serializable;

@Data
public class VoiceMsg implements Serializable {

    private static final long serialVersionUID = -150560044258156495L;

    private Integer time;

    private String url;

    private String mediaid;
}
