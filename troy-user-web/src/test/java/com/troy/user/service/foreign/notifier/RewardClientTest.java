package com.troy.user.service.foreign.notifier;

import com.troy.user.dto.in.user.UserDocumentReqData;
import com.troy.user.dto.out.user.reward.RewardRes;
import com.troy.user.service.configurator.properties.RewardProperties;
import com.troy.user.service.internal.user.UserService;
import com.troy.user.web.main.TroyUserApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TroyUserApplication.class)
@ContextConfiguration
public class RewardClientTest {

    @Autowired
    private RewardProperties rewardProperties;
    @Autowired
    private RewardClient rewardClient;

    @Autowired
    private UserService userService;

    @Test
    public void testSend() {
        RewardRes rewardRes = this.rewardClient.send(this.rewardProperties.getToken(), this.rewardProperties.getGroupId(), this.rewardProperties.getAchievement(), "test@163.com");
        System.out.println(rewardRes);
    }

    public void testDocumentAuth(){
        UserDocumentReqData reqData = new UserDocumentReqData();
        reqData.setFirstName("东坡");
        reqData.setLastName("余");

        userService.documentAuth(reqData);
    }
}
