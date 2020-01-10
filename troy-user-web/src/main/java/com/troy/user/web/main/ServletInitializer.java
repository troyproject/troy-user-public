package com.troy.user.web.main;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * description 使用外部 Tomcat 应用启动入口
 *
 * @author Han
 * @date 2018/9/27 11:48
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TroyUserApplication.class);
    }
}