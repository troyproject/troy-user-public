package com.troy.user.service.configurator.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "troy.user.security.makeToken")
public class MakeTokenProperties {

    /**
     * 返回给用户的令牌中默认是否包含 grant_type
     */
    private Boolean respIncludeGrantType;
    private Boolean supportRefresh;
    private Boolean reuseRefresh;

    public Boolean getRespIncludeGrantType() {
        return respIncludeGrantType;
    }

    public void setRespIncludeGrantType(Boolean respIncludeGrantType) {
        this.respIncludeGrantType = respIncludeGrantType;
    }

    public Boolean getSupportRefresh() {
        return supportRefresh;
    }

    public void setSupportRefresh(Boolean supportRefresh) {
        this.supportRefresh = supportRefresh;
    }

    public Boolean getReuseRefresh() {
        return reuseRefresh;
    }

    public void setReuseRefresh(Boolean reuseRefresh) {
        this.reuseRefresh = reuseRefresh;
    }
}
