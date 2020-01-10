package com.troy.user.dto.constants;

/**
 * @author Han
 */

public enum TextMessageTemplate {


    /**
     *
     *
     *
     *
     *
     *
     *
     *
     *  ################################################################################################################################################################################################
     *  #                                                                                                                                                                                              #
     *  #                                                                                                                                                                                              #
     *  #    注意：HTML模板内容不得含有单引号，在经过java.text.MessageFormat格式化以后会失去单引号，以至于发出去的邮件内容显示异常，故，HTML模板中所有的单引号应使用两个单引号来表示，如：<body tabindex=''0''></body>     #
     *  #                                                                                                                                                                                              #
     *  #                                                                                                                                                                                              #
     *  ################################################################################################################################################################################################
     *
     *
     *
     *
     *
     *
     *
     *
     */

    /**
     * 用户注册（简体中文）
     */
    REGISTER_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.REGISTER,
            "您正在尝试【注册】，验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】欢迎注册Troy Trade",
            Constants.CN_EMAIL_1 + "【TROY】注册验证码：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【注册】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),
    /**
     * 用户注册（英文）
     */
    REGISTER_EN(
            LanguageType.EN,
            TextMessageType.REGISTER,
            "Your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Welcome to Troy Trade",
            Constants.EN_EMAIL_1 + "Registration Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),
    /**
     * 用户登录（简体中文）
     */
    LOGIN_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.LOGIN,
            "您正在尝试【登录】，登录验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】欢迎登录Troy Trade",
            Constants.CN_EMAIL_1 + "【TROY】登录验证码：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【登录】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),
    /**
     * 用户登录（英文）
     */
    LOGIN_EN(
            LanguageType.EN,
            TextMessageType.LOGIN,
            "You are trying to LOGIN your TroyTrade, your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Welcome to Troy Trade",
            Constants.EN_EMAIL_1 + "Login Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),
    /**
     * 用户修改密码（简体中文）
     */
    MODIFY_PASSWORD_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.MODIFY_PASSWORD,
            "您正在尝试【修改密码】，修改密码验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】修改密码",
            Constants.CN_EMAIL_1 + "【TROY】修改密码验证码：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【修改密码】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),
    /**
     * 用户修改密码（英文）
     */
    MODIFY_PASSWORD_EN(
            LanguageType.EN,
            TextMessageType.MODIFY_PASSWORD,
            "You are trying to MODIFY your TroyTrade login password, your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Modify Password",
            Constants.EN_EMAIL_1 + "Modify Password Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),
    /**
     * 用户找回密码（简体中文）
     */
    RESET_PASSWORD_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.RESET_PASSWORD,
            "您正在尝试【找回密码】，验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】找回密码",
            Constants.CN_EMAIL_1 + "【TROY】找回密码验证码：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【找回密码】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),
    /**
     * 用户找回密码（英文）
     */
    RESET_PASSWORD_EN(
            LanguageType.EN,
            TextMessageType.RESET_PASSWORD,
            "Your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Find Password",
            Constants.EN_EMAIL_1 + "Find Password Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),
    /**
     * 用户安全项绑定--手机（简体中文）
     */
    USER_EMAIL_SAFE_BIND_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.USER_EMAIL_SAFE_BIND,
            "您正在尝试【绑定手机】，验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】绑定手机",
            Constants.CN_EMAIL_1 + "【TROY】绑定手机验证码：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【绑定手机】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),
    /**
     * 用户安全项绑定--手机（英文）
     */
    USER_EMAIL_SAFE_BIND_EN(
            LanguageType.EN,
            TextMessageType.USER_EMAIL_SAFE_BIND,
            "Your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Bind Phone",
            Constants.EN_EMAIL_1 + "Bind Phone Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),
    /**
     * 用户安全项绑定--谷歌验证码（简体中文）
     */
    USER_GOOGLE_SAFE_BIND_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.USER_GOOGLE_SAFE_BIND,
            "您正在尝试【绑定谷歌验证】，验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】绑定谷歌验证",
            Constants.CN_EMAIL_1 + "【TROY】绑定谷歌验证的验证码：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【绑定谷歌验证】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),
    /**
     * 用户安全项绑定--谷歌验证码（英文）
     */
    USER_GOOGLE_SAFE_BIND_EN(
            LanguageType.EN,
            TextMessageType.USER_GOOGLE_SAFE_BIND,
            "Your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Bind Google Verification",
            Constants.EN_EMAIL_1 + "Bind Google Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),
    /**
     * 个人中心账号安全绑定
     */
    USER_SAFE_BIND_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.USER_EMAIL_SAFE_BIND,
            "您正在尝试【账号安全绑定】，验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】账号安全绑定",
            Constants.CN_EMAIL_1 + "【TROY】账号安全绑定验证码为：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【账户安全绑定】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),
    /**
     * 个人中心账号安全绑定（英文）
     */
    USER_SAFE_BIND_EN(
            LanguageType.EN,
            TextMessageType.USER_EMAIL_SAFE_BIND,
            "Your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Safe Bind",
            Constants.EN_EMAIL_1 + "Safe Bind Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),

    // ------
    /**
     * 用户手机安全解绑
     */
    USER_PHONE_SAFE_UNBIND_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.USER_PHONE_SAFE_UNBIND,
            "您正在尝试【关闭手机验证】，验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】关闭手机验证",
            Constants.CN_EMAIL_1 + "【TROY】关闭手机验证的验证码为：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【关闭手机验证】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),
    /**
     * 用户手机安全解绑（英文）
     */
    USER_PHONE_SAFE_UNBIND_EN(
            LanguageType.EN,
            TextMessageType.USER_PHONE_SAFE_UNBIND,
            "Your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Unbind the phone verification",
            Constants.EN_EMAIL_1 + "Unbind the phone Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),
    /**
     * 用户谷歌验证码安全解绑
     */
    USER_GOOGLE_SAFE_UNBIND_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.USER_GOOGLE_SAFE_UNBIND,
            "您正在尝试【关闭谷歌验证】，验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】关闭谷歌验证",
            Constants.CN_EMAIL_1 + "【TROY】关闭谷歌验证的验证码为：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【关闭谷歌验证】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),
    /**
     * 用户谷歌验证码安全解绑（英文）
     */
    USER_GOOGLE_SAFE_UNBIND_EN(
            LanguageType.EN,
            TextMessageType.USER_GOOGLE_SAFE_UNBIND,
            "Your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Unbind Google Verification",
            Constants.EN_EMAIL_1 + "Unbind Google Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),

    // ------
    /**
     * 提现
     */
    WITHDRAW_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.WITHDRAW,
            "您正在尝试【提现】，验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】提现",
            Constants.CN_EMAIL_1 + "【TROY】提现验证码为：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【提现】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),

    /**
     * 提现（英文）
     */
    WITHDRAW_EN(
            LanguageType.EN,
            TextMessageType.WITHDRAW,
            "Your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】Withdraw",
            Constants.EN_EMAIL_1 + "Withdraw Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),

    /**
     * 风控
     */
    RISK_CONTROL_ZH_CN(
            LanguageType.ZH_CN,
            TextMessageType.RISK_CONTROL,
            "您正在尝试【风控设置】，验证码：{0}" + Constants.CN_SMS_1,
            "【TROY】风控设置",
            Constants.CN_EMAIL_1 + "【TROY】风控设置验证码为：" + Constants.CN_EN_EMAIL_2 + "您正在尝试【风控设置】，该验证码将于{1}分钟后失效。" + Constants.CN_EMAIL_3
    ),

    /**
     * （英文）
     */
    RISK_CONTROL_EN(
            LanguageType.EN,
            TextMessageType.RISK_CONTROL,
            "Your SMS verification code is: {0}, the code will expired in {1}mins and DO NOT tell anyone your verification code.",
            "【TROY】RISK_CONTROL",
            Constants.EN_EMAIL_1 + "RISK_CONTROL Verification Code is:" + Constants.CN_EN_EMAIL_2 + Constants.EN_EMAIL_3
    ),
    ;

    private LanguageType languageType;
    private TextMessageType textMessageType;
    private String smsTemplate;
    private String emailSubject;
    /**
     * 注意：HTML模板内容不得含有单引号，在经过java.text.MessageFormat格式化以后会失去单引号，以至于发出去的邮件内容显示异常，故，HTML模板中所有的单引号应使用两个单引号来表示，如：<body tabindex=''0''></body>
     */
    private String emailTemplate;

    TextMessageTemplate(LanguageType languageType, TextMessageType textMessageType, String smsTemplate, String emailSubject, String emailTemplate) {
        this.languageType = languageType;
        this.textMessageType = textMessageType;
        this.smsTemplate = smsTemplate;
        this.emailSubject = emailSubject;
        this.emailTemplate = emailTemplate;
    }

    public String getSmsTemplate() {
        return smsTemplate;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }

    public static TextMessageTemplate find(LanguageType languageType, TextMessageType textMessageType) {
        for (TextMessageTemplate e : TextMessageTemplate.values()) {
            if (e.languageType.getCode().equals(languageType.getCode()) && e.textMessageType.getCode().equals(textMessageType.getCode())) {
                return e;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //System.out.println(TextMessageTemplate.USER_SAFE_BIND_ZH_CN.getEmailTemplate().replace("''", "'"));
        System.out.println(find(LanguageType.ZH_CN, TextMessageType.RISK_CONTROL));
    }
}