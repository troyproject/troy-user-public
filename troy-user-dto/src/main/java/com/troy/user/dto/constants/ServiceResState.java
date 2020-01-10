package com.troy.user.dto.constants;


import com.troy.commons.exception.enums.StateTypeSuper;

/**
 * description 服务响应状态
 *
 * @author Han
 * @date 2018/9/27 11:28
 */
public enum ServiceResState implements StateTypeSuper {

    // 异常命名：FAIL_异常类型
    // 错误编码：从 1000 起
    FAIL_CLIENT_NOT_EXIST("1000", "客户端不存在"),
    EMAIL_HAS_BEEN_BOUND("1001", "邮箱已经绑定"),
    EMAIL_NOT_BOUND("1002", "邮箱还未绑定"),
    MOBILE_HAS_BEEN_BOUND("1003", "手机号已经绑定"),
    MOBILE_NOT_BOUND("1004", "手机号还未绑定"),
    GOOGLE_HAS_BEEN_BOUND("1005", "谷歌验证码已经绑定"),
    GOOGLE_NOT_BOUND("1006", "谷歌验证码还未绑定"),
    NO_VERIFIABLE_WAY_FOR_USERS("1007", "用户当前没有可验证方式"),
    BIND_TYPE_NOT_NULL("1008", "绑定类型不能为空"),
    VERIFY_WAY_NOT_NULL("1009", "验证方式不能为空"),
    SEND_WAY_NOT_NULL("1010", "发送方式不能为空"),
    EMAIL_VERIFY_CODE_NOT_PASS("1011", "邮箱验证码不正确"),
    MOBILE_VERIFY_CODE_NOT_PASS("1012", "手机验证码不正确"),
    GOOGLE_VERIFY_CODE_NOT_PASS("1013", "谷歌验证码不正确"),
    FAIL_INVALID_VERIFICATION_CODE("1014", "无效的验证码"),
    IDENTITY_AUTH_FAILED("1015", "身份认证未通过"),
    FAIL_ACCESS_CONTROL_REJECTED("1016", "人机验证未通过"),
    FAIL_EMAIL_AND_MOBILE_MUST_BIND_ONE("1017", "不可解绑，邮箱和手机至少绑定一个"),
    FAIL_USER_EXIST("1018", "用户已存在"),
    FAIL_EMAIL_SAME("1019", "注册邮箱与推荐人邮箱不能是同一个"),
    IN_IDENTITY_AUTHENTICATION("1020", "当前用户处于身份认证中"),
    SUCCESSFUL_IDENTITY_AUTHENTICATION("1021", "当前用户已经身份认证成功"),
    BIND_MOBILE_EXIST("1022", "手机号已经被绑定过"),
    DOCUMENT_REPEAT("1023", "证件号重复"),
    INVITECODE_ERROR("1024", "邀请码错误"),
    INVITECODE_REPEAT("1027", "邀请码重复使用"),
    ERROR_USER_PASSWORD("1028","账号或密码错误"),
    RISK_CONTROL_CODE_NOT_PASS("1029","风控绑定,无效的验证码"),
    RISK_CONTROL_PHONE_INVALID("1030","风控设置,无效的手机号"),
    ;

    private String code;
    private String depict;

    ServiceResState(String code, String depict) {
        this.code = code;
        this.depict = depict;
    }

    /**
     * description: 获取状态描述
     *
     * @param
     * @return 状态描述
     */
    @Override
    public String getDepict() {
        return depict;
    }

    /**
     * description:  获取状态码
     *
     * @param
     * @return 状态码
     */
    @Override
    public String getCode() {
        return code;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }
}
