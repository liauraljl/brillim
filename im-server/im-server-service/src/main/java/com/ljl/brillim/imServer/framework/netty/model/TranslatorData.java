package com.ljl.brillim.imServer.framework.netty.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * netty 消息体
 */
@Data
@AllArgsConstructor
public class TranslatorData implements Serializable {

    private static final long serialVersionUID = 1773460827606922639L;

    private Long msgId;

    private String message;
}
