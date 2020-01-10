package com.troy.user.service.configurator;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.troy.user.service.configurator.properties.AcsClientProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Han
 */
@Configuration
public class AcsClientConfigurator {

    @Autowired
    private AcsClientProperties properties;

    @Bean
    IAcsClient createAcsClient() throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile(this.properties.getRegionId(), this.properties.getAccessKey(), this.properties.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);
        DefaultProfile.addEndpoint(this.properties.getEndpointName(), this.properties.getRegionId(), this.properties.getProduct(), this.properties.getDomain());
        return client;
    }
}