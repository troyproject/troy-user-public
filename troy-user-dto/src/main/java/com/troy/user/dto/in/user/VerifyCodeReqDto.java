package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqData;
import com.troy.user.dto.constants.TextMessageType;
import lombok.Getter;
import lombok.Setter;

/**
 * 校验验证码请求
 *
 * @author dp
 */
@Setter
@Getter
public class VerifyCodeReqDto extends ReqData {

    /**
     * 谷歌验证码
     */
    private String googleVerificationCode;

    /**
     * 短信验证码
     */
    private String smsVerificationCode;

    /**
     * 验邮箱证码
     */
    private String emailVerificationCode;

    /**
     * 类型
     *
     * @see TextMessageType
     */
    private TextMessageType textMessageType;

    /**
     * 是否只校验最高绑定权限（false为校验所有）
     */
    private boolean onlyTopPriority;
}
