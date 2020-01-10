package com.troy.user.dto.in.user.add;

import com.troy.commons.dto.in.ReqData;
import lombok.Getter;
import lombok.Setter;

/**
 * 添加账号请求入参
 *
 * @author:
 * @date: 2018-10-10 11:21
 * @copyright
 */
@Setter
@Getter
public class UserRegisterReqData extends ReqData {

    private static final long serialVersionUID = -3805651623366562983L;

    /**
     * @see com.troy.user.dto.constants.RegisterType
     */
    private String registerType;
    private String phoneAreaCode;
    private String phoneNumber;
    private String email;
    private String password;
    private String verificationCode;
    private String invitationCode;
    private String source;

    private Long userId;
    private String emailReferrer;
    private String countryCode;
    // 邀请码
    private String inviteCode;
    // 参考ID(此ID为引流取到的ID)
    private String refId;
}
