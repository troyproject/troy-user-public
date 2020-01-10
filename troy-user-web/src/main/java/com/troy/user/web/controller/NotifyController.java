package com.troy.user.web.controller;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResFactory;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.proxy.GreedyRequestProxy;
import com.troy.commons.proxy.SimpleRequestProxy;
import com.troy.user.api.NotifyApi;
import com.troy.user.dto.constants.ServiceResState;
import com.troy.user.dto.constants.TextMessageType;
import com.troy.user.dto.in.notify.RiskControlReqData;
import com.troy.user.dto.in.notify.RiskJudgeCodeReqData;
import com.troy.user.dto.in.notify.VerificationCodeReqData;
import com.troy.user.dto.in.user.UserBindReqData;
import com.troy.user.dto.out.user.UserDetails;
import com.troy.user.service.internal.accesscontrol.AccessControlService;
import com.troy.user.service.internal.notifier.RiskControlPointService;
import com.troy.user.service.internal.notifier.VerificationCodeService;
import com.troy.user.service.internal.user.UserService;
import com.troy.user.web.constants.FlagEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * description 通知 HTTP 服务
 *
 * @author Han
 * @date 2018-09-30 15:01
 */
@Slf4j
@RestController
@Api(tags = "通知服务")
public class NotifyController extends AbstractController implements NotifyApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotifyController.class);

    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private AccessControlService accessControlService;
    @Autowired
    private UserService userService;
    @Autowired
    private RiskControlPointService riskControlPointService;

    @Override
    @ApiOperation(value = "发送验证码", notes = "支持短信、邮件类型的验证码发送，如：用户注册、用户登录、重置密码等")
    public Res<ResData> sendVerificationCode(@RequestBody Req<VerificationCodeReqData> req) {
        return super.process(req, (SimpleRequestProxy<VerificationCodeReqData>) (reqHead, reqData) -> {
            StopWatch stopWatch = new StopWatch("send code");
            if(exclude(reqData)){
                stopWatch.start("verification");
                boolean verified = this.accessControlService.verification(reqData, super.currentRequest().getRemoteAddr());
                stopWatch.stop();
                if (!verified) {
                    throw new ServiceException(ServiceResState.FAIL_ACCESS_CONTROL_REJECTED);
                }
            }
            if(TextMessageType.REGISTER.getCode().equals(reqData.getType())){
                UserDetails userDetails = userService.loadUserByLoginUsername(StringUtils.isNotBlank(reqData.getEmail()) ? reqData.getEmail() : reqData.getPhoneNumber());
                if (null != userDetails){
                    // throw new ServiceException(ServiceResState.FAIL_USER_EXIST);
                    log.info("[用户注册]-{}不能重复注册", StringUtils.isNotBlank(reqData.getEmail()) ? reqData.getEmail() : reqData.getPhoneNumber());
                    // 默认返回发送成功
                    return true;
                }
            }
            stopWatch.start("send message");
            boolean result = this.verificationCodeService.sendVerificationCode(reqData);
            stopWatch.stop();
            LOGGER.info("Sending verification code takes time：{}", stopWatch.prettyPrint());
            return result;
        });
    }

    @Override
    public Res<ResData> sendRiskControlCode(@RequestBody Req<RiskControlReqData> req) {

        return super.process(req, (SimpleRequestProxy<RiskControlReqData>) (reqHead, reqData) -> {
            StopWatch stopWatch = new StopWatch("send riskcontrol code");
            VerificationCodeReqData verificationCodeReqData=new VerificationCodeReqData();
            BeanUtils.copyProperties(req.getData(), verificationCodeReqData);
            stopWatch.start("send riskcontrol-code");
            boolean result = this.verificationCodeService.sendRiskControlCode(verificationCodeReqData);
            stopWatch.stop();
            LOGGER.info("Sending risk control code takes time：{}", stopWatch.prettyPrint());
            return result;
        });
    }

    @Override
    public Res<ResData> judgePhoneCode(@RequestBody Req<RiskJudgeCodeReqData> req) {
            boolean flag = verificationCodeService.judgePhoneCode(req.getData());
            return true == flag ?  ResFactory.getInstance().success(null) : ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT);
    }

    @Override
    public Res<ResData> sendRiskControlPoint(Req<RiskControlReqData> req) {
            StopWatch stopWatch = new StopWatch("send riskcontrol-point");
            stopWatch.start("send riskcontrol-point");
            boolean result = this.riskControlPointService.sendForSms(req.getData());
            stopWatch.stop();
            LOGGER.info("Sending risk riskcontrol-point takes time：{}", stopWatch.prettyPrint());
            return true == result ?  ResFactory.getInstance().success(null) : ResFactory.getInstance().createRes(StateTypeSuper.FAIL_DEFAULT);
    }

    /**
     * 排除人机校验部分（提现，修改密码,注册时再次发送验证码）
     * @param reqData
     * @return
     */
    public boolean exclude(VerificationCodeReqData reqData){
        if(TextMessageType.MODIFY_PASSWORD.getCode().equals(reqData.getType()) || TextMessageType.WITHDRAW.getCode().equals(reqData.getType())){
            return false;
        }
        if(null != reqData.getFlag() && FlagEnum.RETRY.getCode() == reqData.getFlag()){
            return false;
        }
        return true;
    }
}
