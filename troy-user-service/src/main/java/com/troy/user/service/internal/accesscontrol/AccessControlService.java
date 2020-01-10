package com.troy.user.service.internal.accesscontrol;

import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.troy.user.dto.in.notify.VerificationCodeReqData;

/**
 * 人机验证
 *
 * @author Han
 */
public interface AccessControlService {

    boolean verification(VerificationCodeReqData reqData, String remoteIp) throws Exception;

    boolean verification(AuthenticateSigRequest authenticateSigRequest) throws Exception;
}
