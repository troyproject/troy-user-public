package com.troy.user.client.auth;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.in.ReqFactory;
import com.troy.commons.dto.in.ReqHead;

/**
 * 鉴权客户端抽象模板
 *
 * @author Han
 */
public abstract class AbstractAuthClient {

    protected String appId;

    protected AbstractAuthClient(String appId) {
        this.appId = appId;
    }

    protected <D extends ReqData> Req<D> createReq(D data) {
        ReqHead reqHead = new ReqHead();
        reqHead.setClientId(this.appId);
        Req<D> req = ReqFactory.getInstance().createReq(reqHead, data);
        return req;
    }
}
