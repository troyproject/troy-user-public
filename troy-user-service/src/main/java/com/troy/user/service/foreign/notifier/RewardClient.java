package com.troy.user.service.foreign.notifier;

import com.troy.user.dto.out.user.reward.RewardRes;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Han
 */
@FeignClient(qualifier = "rewardClient", name = "${troy.reward.serviceName}", url = "${troy.reward.serviceAddress}")
public interface RewardClient {

    /**
     * 推送消息
     *
     * @param token
     * @param groupId
     * @param achievement
     * @param email
     * @return
     */
    @RequestMapping(value = "/achievements/custom")
    RewardRes send(@RequestParam("token") String token, @RequestParam("group_id") Long groupId, @RequestParam("achievement") Integer achievement, @RequestParam("email") String email);
}
