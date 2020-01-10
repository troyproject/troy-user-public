package com.troy.user.web.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * description 使用内嵌 Tomcat 应用启动入口
 *
 * @author Han
 * @date 2018/9/27 11:44
 */
@SpringBootApplication(scanBasePackages = {"com.troy.user", "com.troy.commons"})
@EnableFeignClients(basePackages = {"com.troy.user.service.foreign"})
@MapperScan("com.troy.user.dao.mapper")
@EnableAuthorizationServer
@EnableEurekaClient
@EnableHystrix
@EnableAsync
public class TroyUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(TroyUserApplication.class, args);
    }

}
