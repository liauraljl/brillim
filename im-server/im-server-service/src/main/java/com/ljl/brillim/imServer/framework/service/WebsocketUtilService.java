package com.ljl.brillim.imServer.framework.service;

import com.ljl.brillim.common.utils.IdWorker;
import com.ljl.brillim.imServer.common.RedisConstant;
import com.ljl.brillim.imServer.common.RedisService;
import jodd.typeconverter.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class WebsocketUtilService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private IdWorker idWorker;

    /**
     * 发放token
     * @return
     */
    public String getToken() {
        String token = Convert.toString(idWorker.nextId());
        String key = String.format(RedisConstant.AUTHKEY, token);
        log.info("发放token:{}", token);
        redisService.set(key, token, 10, TimeUnit.SECONDS);
        return token;
    }
}
