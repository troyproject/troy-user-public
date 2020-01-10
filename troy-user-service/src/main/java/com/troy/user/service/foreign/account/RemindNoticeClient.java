package com.troy.user.service.foreign.account;

import com.troy.trade.api.service.RemindNoticeApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(qualifier = "remindNoticeClient", name = "${troy.account.serviceName}")
public interface RemindNoticeClient extends RemindNoticeApi {
}
