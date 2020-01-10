package com.troy.user.api;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.user.api.constants.Constants;
import com.troy.user.dto.in.notify.RiskControlReqData;
import com.troy.user.dto.in.notify.RiskJudgeCodeReqData;
import com.troy.user.dto.in.notify.VerificationCodeReqData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface NotifyApi {

    @RequestMapping(value = Constants.URL_VERIFICATION_CODE_ROOT, method = {RequestMethod.POST})
    Res<ResData> sendVerificationCode(Req<VerificationCodeReqData> req);

    @RequestMapping(value = Constants.URL_RISK_CONTROL_CODE, method = {RequestMethod.POST})
    Res<ResData> sendRiskControlCode(Req<RiskControlReqData> req);

    @RequestMapping(value = Constants.URL_RISK_JUDGE_CODE, method = {RequestMethod.POST})
    Res<ResData> judgePhoneCode(Req<RiskJudgeCodeReqData> req);

    @RequestMapping(value = Constants.URL_RISK_CONTROL_POINT, method = {RequestMethod.POST})
    Res<ResData> sendRiskControlPoint(Req<RiskControlReqData> req);

}
