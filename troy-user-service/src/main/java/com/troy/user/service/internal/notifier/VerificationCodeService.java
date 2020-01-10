package com.troy.user.service.internal.notifier;

import com.troy.user.dto.constants.TextMessageType;
import com.troy.user.dto.in.notify.RiskControlReqData;
import com.troy.user.dto.in.notify.RiskJudgeCodeReqData;
import com.troy.user.dto.in.notify.VerificationCodeReqData;

public interface VerificationCodeService {

    boolean sendVerificationCode(VerificationCodeReqData reqData);

    boolean sendRiskControlCode(VerificationCodeReqData reqData);

    boolean judgePhoneCode(RiskJudgeCodeReqData reqData);


    /**
     * 校验验证码
     *
     * @param type        验证码类型
     * @param target      手机号或邮箱
     * @param currentCode 验证码
     * @return
     */
    boolean checkVerificationCode(TextMessageType type, String target, String currentCode);


    boolean sendForSms(String phoneAreaCode, String phone, String content);
}