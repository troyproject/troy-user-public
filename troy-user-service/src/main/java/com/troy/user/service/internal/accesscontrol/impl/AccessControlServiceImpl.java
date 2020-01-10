package com.troy.user.service.internal.accesscontrol.impl;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.user.dto.in.notify.VerificationCodeReqData;
import com.troy.user.service.configurator.properties.AcsClientProperties;
import com.troy.user.service.constants.Constants;
import com.troy.user.service.internal.accesscontrol.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Han
 */
@Service
public class AccessControlServiceImpl implements AccessControlService {

    @Autowired
    private AcsClientProperties properties;
    @Autowired
    private IAcsClient acsClient;

    @Override
    public boolean verification(VerificationCodeReqData reqData, String remoteIp) throws Exception {
        if (reqData == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        AuthenticateSigRequest request = new AuthenticateSigRequest();
        request.setSessionId(reqData.getSessionId());
        request.setSig(reqData.getSig());
        request.setToken(reqData.getToken());
        request.setScene(reqData.getScene());
        request.setAppKey(this.properties.getAppKey());
        request.setRemoteIp(remoteIp);
        return this.verification(request);
    }

    @Override
    public boolean verification(AuthenticateSigRequest authenticateSigRequest) throws Exception {
        try {
            AuthenticateSigResponse response = this.acsClient.getAcsResponse(authenticateSigRequest);
            if (response.getCode() != null && response.getCode().intValue() == Constants.ACS_RESPONSE_CODE_SUCCESS) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new ServiceException(StateTypeSuper.FAIL_UNEXPECTED_RESULTS, e.getMessage());
        }
    }
}