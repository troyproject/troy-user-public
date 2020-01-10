package com.troy.user.service.foreign.account;

/**
 * TODO 账户服务客户端
 *
 * @author
 * @date 2018-11-19 11:07
 * @copyright
 */

import com.troy.trade.api.service.AccountApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(qualifier = "accountClient", name = "${troy.account.serviceName}")
public interface AccountClient extends AccountApi {

}
