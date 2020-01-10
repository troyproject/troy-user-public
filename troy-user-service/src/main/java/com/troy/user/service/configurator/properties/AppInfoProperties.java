package com.troy.user.service.configurator.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppInfoProperties {

    @Value("${troy.user.appId}")
    private String appId;
    @Value("${spring.application.name}")
    private String appName;

    public String getAppId() {
        return appId;
    }

    public String getAppName() {
        return appName;
    }
}
