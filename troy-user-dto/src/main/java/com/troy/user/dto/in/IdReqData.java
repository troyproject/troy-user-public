package com.troy.user.dto.in;


import com.troy.commons.dto.in.ReqData;

/**
 * description 业务标识请求入参
 *
 * @author Han
 * @date 2018-10-18 16:19
 */
public class IdReqData extends ReqData {

    private static final long serialVersionUID = 2996211332073256346L;

    /**
     * 业务主键
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
