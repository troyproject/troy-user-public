package com.troy.user.service.foreign.notifier;

/**
 * TODO 短信服务客户端
 *
 * @author
 * @date 2018-11-19 11:07
 * @copyright
 */

import com.troy.notifier.api.SmsApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(qualifier = "smsClient", name = "${troy.notifier.serviceName}")
public interface SmsClient extends SmsApi {

}