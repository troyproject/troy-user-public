package com.troy.user.dto.in.auth.client;

import com.troy.commons.dto.in.ReqPage;
import com.troy.user.dto.constants.YesOrNo;

/**
 * description 查询鉴权客户端请求入参
 *
 * @author Han
 * @date 2018-10-18 15:25
 */
public class AuthClientQueryReqData extends ReqPage {

    private static final long serialVersionUID = 2943690991543256346L;

    /**
     * 业务主键
     */
    private Long id;
    /**
     * 是否启用
     *
     * @see YesOrNo
     */
    private Integer enable;
    /**
     * 客户端标识
     */
    private String clientId;
    /**
     * 说明
     */
    private String explain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }
}
