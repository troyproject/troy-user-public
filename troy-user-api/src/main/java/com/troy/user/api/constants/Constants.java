package com.troy.user.api.constants;

/**
 * description 定义 api 模块常量
 *
 * @author Han
 * @date 2018/9/27 10:04
 */
public class Constants {

    public static final String URL_ROOT = "/user";

    /**
     * ============================   消息通知 api begin  ===========================
     */
    public static final String URL_NOTIFY_ROOT = URL_ROOT + "/notify";
    public static final String URL_VERIFICATION_CODE_ROOT = URL_NOTIFY_ROOT + "/verification-code";
    public static final String URL_RISK_CONTROL_CODE = URL_NOTIFY_ROOT + "/riskControl-code";
    public static final String URL_RISK_JUDGE_CODE = URL_NOTIFY_ROOT + "/riskControl/judge-code";
    public static final String URL_RISK_CONTROL_POINT = URL_NOTIFY_ROOT + "/riskControl-point";


    /**
     * ============================   消息通知 api end  ===========================
     */

    /**
     * ============================   鉴权客户端 api begin  ===========================
     */
    public static final String URL_AUTH_CLIENT_ROOT = URL_ROOT + "/auth-client";
    public static final String URL_AUTH_CLIENT_ADD = URL_AUTH_CLIENT_ROOT + "/register";
    public static final String URL_AUTH_CLIENT_MODIFY = URL_AUTH_CLIENT_ROOT + "/modify";
    public static final String URL_AUTH_CLIENT_VIEW = URL_AUTH_CLIENT_ROOT + "/view";
    public static final String URL_AUTH_CLIENT_ENABLE = URL_AUTH_CLIENT_ROOT + "/enable";
    public static final String URL_AUTH_CLIENT_DISABLE = URL_AUTH_CLIENT_ROOT + "/disable";
    public static final String URL_AUTH_CLIENT_REMOVE = URL_AUTH_CLIENT_ROOT + "/remove";
    public static final String URL_AUTH_CLIENT_LIST_PAGE = URL_AUTH_CLIENT_ROOT + "/list/page";
    /**
     * ============================   鉴权客户端 api end  ===========================
     */

    /**
     * ============================   鉴权 api begin  ===========================
     */
    public static final String URL_AUTH_ROOT = URL_ROOT + "/auth";
    /**
     * 标准 oauth2 服务
     */
    public static final String URL_OAUTH_NORMAL_ROOT = URL_AUTH_ROOT + "/normal";
    public static final String URL_OAUTH_NORMAL_TOKEN = URL_OAUTH_NORMAL_ROOT + "/token";
    public static final String URL_OAUTH_NORMAL_TOKEN_CHECK = URL_OAUTH_NORMAL_ROOT + "/token-check";
    public static final String URL_OAUTH_NORMAL_TOKEN_KEY = URL_OAUTH_NORMAL_ROOT + "/token-key";
    /**
     * 代理 oauth2 服务
     */
    public static final String URL_OAUTH_PROXY_ROOT = URL_AUTH_ROOT + "/proxy";
    public static final String URL_OAUTH_PROXY_TOKEN = URL_OAUTH_PROXY_ROOT + "/token";
    public static final String URL_OAUTH_PROXY_TOKEN_REFRESH = URL_OAUTH_PROXY_ROOT + "/token-refresh";
    public static final String URL_OAUTH_PROXY_TOKEN_CHECK = URL_OAUTH_PROXY_ROOT + "/token-check";
    public static final String URL_OAUTH_PROXY_TOKEN_KEY = URL_OAUTH_PROXY_ROOT + "/token-key";
    /**
     * ============================   鉴权 api end  =============================
     */

    /**
     * ============================   用户 api begin  ===========================
     */
    public static final String URL_USER_REGISTER = URL_ROOT + "/register";
    public static final String URL_USER_DETAILS = URL_ROOT + "/details";
    public static final String URL_USER_BEFORE_LOGIN = URL_ROOT + "/before-login";
    public static final String URL_USER_BEFORE_RESET_PASSWORD = URL_ROOT + "/before-reset-password";
    public static final String URL_USER_BEFORE_MODIFY_PASSWORD = URL_ROOT + "/before-modify-password";
    public static final String URL_USER_BEFORE_COMMON = URL_ROOT + "/before-common";
    public static final String URL_USER_RESET_PASSWORD = URL_ROOT + "/reset-password";
    public static final String URL_USER_MODIFY_PASSWORD = URL_ROOT + "/modify-password";
    public static final String URL_USER_INFO = URL_ROOT + "/info";
    public static final String URL_USER_INFO_BY_ID = URL_ROOT + "/get-user-by-id";
    public static final String URL_USER_BIND_TYPE_VERIFY = URL_ROOT + "/bind-type-verify";
    public static final String URL_USER_BIND_SEND_CODE = URL_ROOT + "/send-bind-code";
    public static final String URL_USER_BIND = URL_ROOT + "/bind";
    public static final String URL_USER_DOCUMENT_AUTH = URL_ROOT + "/document-auth";
    public static final String URL_USER_GOOGLE_AUTHENTICATION_INFO = URL_ROOT + "/google-auth-info";
    public static final String URL_USER_VERIFY_BIND_GOOGLE_CODE = URL_ROOT + "/verify-bind-google-code";
    public static final String URL_USER_VERIFY_ALL = URL_ROOT + "/verify-all-code";
    public static final String URL_USER_DOCUMENT_INFO = URL_ROOT + "/document-info";
    public static final String URL_USER_LOGIN_LOG = URL_ROOT + "/login-log";
    public static final String URL_USER_BIND_BEFORE = URL_ROOT + "/bind/before";
    /**
     * ============================   用户 api end  ===========================
     */

    /**
     * ============================   文件上传 api begin  ===========================
     */
    public static final String URL_FILE_ROOT = URL_ROOT + "/file";
    public static final String URL_FILE_UPLOAD_PIC = URL_FILE_ROOT + "/upload-pic";
    /**
     * ============================   文件上传 api end  ===========================
     */
}
