package com.ljl.brillim.imServer.framework.disruptor.model.msgType;

import lombok.Data;

import java.io.Serializable;

@Data
public class VideoMsg implements Serializable {

    private static final long serialVersionUID = 215144517685396918L;

    private String thumbnailUrl; //视频缩略图

    private String url;

    private String mediaid;
}
