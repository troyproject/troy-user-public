package com.troy.user.service.internal.notifier;

import com.troy.user.dto.constants.TextMessageType;
import com.troy.user.dto.in.notify.RiskControlReqData;
import com.troy.user.dto.in.notify.RiskJudgeCodeReqData;
import com.troy.user.dto.in.notify.VerificationCodeReqData;

public interface RiskControlPointService {

    boolean sendForSms(RiskControlReqData riskControlReqData);
}