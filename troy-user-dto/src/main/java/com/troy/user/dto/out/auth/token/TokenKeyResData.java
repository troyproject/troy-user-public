package com.troy.user.dto.out.auth.token;


import com.troy.commons.dto.out.ResData;

/**
 * description 令牌公钥请求出参
 *
 * @author Han
 * @date 2018/10/12 9:56
 */
public class TokenKeyResData extends ResData {

    private static final long serialVersionUID = 2996290991543614391L;

    private String alg;
    private String value;

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
