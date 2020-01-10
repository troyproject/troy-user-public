package com.troy.user.service.constants;

/**
 * description 定义 service 模块常量
 *
 * @author Han
 * @date 2018/9/27 10:04
 */
public class Constants {

    public static final String REGEX_PHONE_AREA_CODE_DOMESTIC = "^\\+?861?$";
    public static final String REGEX_PHONE_DOMESTIC = "^1[3|5|6|7|8|9]\\d{9}$";
    public static final String REGEX_EMAIL = "^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-z]{2,})$";
    /**
     * 正式上线使用 ^[a-zA-Z][0-9a-zA-Z,.!@#$%^&*?]{7,19}$
     */
    public static final String USER_PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z])[0-9a-zA-Z]{8,32}$";

    /**
     * 阿里云身份证实名认证服务url
     */
    public static final String ALIYUN_ID_CARD_AUTH_URL = "https://idcardcert.market.alicloudapi.com/idCardCert?idCard=%s&name=%s";
    /**
     * 阿里云身份认证通过状态码
     */
    public static final String USER_DOCUMENT_AUTH_SUCCESS_STATUS = "01";

    public static final String ALIYUN_ID_CARD_APP_CODE = "1dae1832909144a0ae5ccbc50021a1a6";

    /**
     * 验证码相关
     */
    public static final int DEFAULT_VERIFICATION_CODE_SIZE = 6;
    public static final int DEFAULT_VERIFICATION_CODE_EXPIRATION_MINUTES = 3;
    public static final String REGEX_VERIFICATION_CODE = "^\\d{" + DEFAULT_VERIFICATION_CODE_SIZE + "}$";
    public static final int DEFAULT_RISK_CONTROL_CODE_EXPIRATION_MINUTES = 6;


    public static final int ACS_RESPONSE_CODE_SUCCESS = 100;

    public static final int LOGIN_LOG_VIEW_MAX_SIZE = 5;

    public static final byte[] DEFAULT_SECURITY_SECRET_IV = {14, 57, 7, 86, 34, 91, 93, 65, 0, 40, 10, 95, 95, 12, 90, 81};

    /**
     * 认证提醒文本
     */
    public static final String REMIND_NOTICE_MSG = "有新用户认证，请尽快审核!";
}
