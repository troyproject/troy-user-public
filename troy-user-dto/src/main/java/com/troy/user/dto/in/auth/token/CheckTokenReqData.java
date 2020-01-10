package com.troy.user.dto.in.auth.token;


import com.troy.commons.dto.in.ReqData;

/**
 * description 校验令牌请求入参
 *
 * @author Han
 * @date 2018/10/12 9:56
 */
public class CheckTokenReqData extends ReqData {

    private static final long serialVersionUID = 2996243191542377409L;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
