package com.troy.user.service.internal.user;

import com.troy.user.dto.in.user.UserDocumentAuthReqData;
import com.troy.user.dto.in.user.UserDocumentReqData;

/**
 * @author zhangchengjie
 * @date 2019/08/02
 */
public interface UserDocumentAuthService {

    /**
     * 调用阿里云身份证实名认证服务
     *
     * @param reqData
     * @return
     */
    UserDocumentAuthReqData callAliYunIdCardCert(UserDocumentReqData reqData);

    /**
     * 身份认证信息记录数据库
     *
     * @param reqData
     * @param userDocumentAuthReqData
     */
    void insertUserDocumentRecord(UserDocumentReqData reqData,
                                  UserDocumentAuthReqData userDocumentAuthReqData);

    /**
     * 重新身份认证信息记录数据库
     *
     * @param reqData
     * @param userDocumentAuthReqData
     */
    void reUserDocumentRecord(UserDocumentReqData reqData, UserDocumentAuthReqData userDocumentAuthReqData);
}
