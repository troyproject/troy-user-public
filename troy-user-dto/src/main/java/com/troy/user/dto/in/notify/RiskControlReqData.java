package com.troy.user.dto.in.notify;

import com.troy.commons.dto.in.ReqData;

/**
 * @author libohan
 */
public class RiskControlReqData extends ReqData {

    private static final long serialVersionUID = -3805651623366562983L;

    /**
     */
    private String language;
    /**
     */
    private String type;
    /**
     */
    private String channel;
    private String phoneAreaCode;
    private String phoneNumber;
    private String email;
    private Integer flag;
    private Boolean huaxin;

    private String content;

    /**
     * 人机验证-会话ID。
     */
    private String sessionId;
    /**
     * 人机验证-签名串。
     */
    private String sig;
    /**
     * 人机验证-请求唯一标识。
     */
    private String token;
    /**
     * 人机验证-场景标识。
     */
    private String scene;

    public Boolean getHuaxin() {
        return huaxin;
    }

    public void setHuaxin(Boolean huaxin) {
        this.huaxin = huaxin;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(String phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
