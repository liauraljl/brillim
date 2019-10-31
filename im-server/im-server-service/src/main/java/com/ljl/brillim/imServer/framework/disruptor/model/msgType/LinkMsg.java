package com.ljl.brillim.imServer.framework.disruptor.model.msgType;

import lombok.Data;

import java.io.Serializable;

@Data
public class LinkMsg implements Serializable {

    private static final long serialVersionUID = -5784402811683906059L;

    private String title;

    private String url;

    private String picurl;

    private String description;
}
