package com.ljl.brillim.imServer.framework.disruptor.model.msgType;

import lombok.Data;

import java.io.Serializable;

@Data
public class TextMsg implements Serializable {

    private static final long serialVersionUID = -5261243205113131511L;

    private String text;
}
