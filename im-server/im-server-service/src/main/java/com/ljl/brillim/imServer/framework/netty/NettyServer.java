package com.ljl.brillim.imServer.framework.netty;

import com.ljl.brillim.imServer.framework.netty.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * Created by liaura_ljl on 2019/10/25.
 */
@Slf4j
@Component
public class NettyServer {

    @Autowired
    private ServerHandler serverHandler;

    @PostConstruct
    public void nettyMain(){
        new Thread(()->{
            int port = 8888;
            NioEventLoopGroup boss = new NioEventLoopGroup();
            NioEventLoopGroup work = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss,work);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new IdleStateHandler(30,0,0, TimeUnit.SECONDS));
                    pipeline.addLast(new HttpServerCodec());
                    pipeline.addLast(new ChunkedWriteHandler());
                    pipeline.addLast(new HttpObjectAggregator(65536));
                    pipeline.addLast(new WebSocketServerProtocolHandler("/brillim/im/"));
                    pipeline.addLast(serverHandler);
                }
            });
            try {
                ChannelFuture sync = serverBootstrap.bind(port).sync();
                log.info("netty启动websocket服务端，占用端口：{}",port);
                sync.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                log.error("netty启动websocket服务端报错！");
            }finally {
                boss.shutdownGracefully();
                work.shutdownGracefully();
            }
        }).start();
    }
}
