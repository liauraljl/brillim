package com.ljl.brillim.imServer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableDubboConfiguration
@EnableAsync
@ImportResource(locations = {
        "classpath*:applicationContext-dubbo.xml"
})
@SpringBootApplication
public class ImServerServiceApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ImServerServiceApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ImServerServiceApplication.class, args);
    }

}
