package com.troy.user.service.foreign.notifier;

/**
 * TODO 邮件服务客户端
 *
 * @author
 * @date 2018-11-19 11:07
 * @copyright
 */

import com.troy.notifier.api.EmailApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(qualifier = "emailClient", name = "${troy.notifier.serviceName}")
public interface EmailClient extends EmailApi {

}