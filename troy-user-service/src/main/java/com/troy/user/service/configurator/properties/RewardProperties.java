package com.troy.user.service.configurator.properties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Han
 */
@Component
@ConfigurationProperties(prefix = "troy.reward")
@Setter
@Getter
public class RewardProperties {

    private String token;
    private Long groupId;
    private Integer achievement;
    private List<String> sources;

    @Override
    public String toString() {
        return "RewardProperties{" +
                "token='" + token + '\'' +
                ", groupId=" + groupId +
                ", achievement=" + achievement +
                ", sources=" + sources +
                '}';
    }
}