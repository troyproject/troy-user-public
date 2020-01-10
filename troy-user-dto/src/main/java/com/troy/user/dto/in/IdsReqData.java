package com.troy.user.dto.in;

import com.troy.commons.dto.in.ReqData;

import java.util.Set;

/**
 * description 业务标识列表请求入参
 *
 * @author
 * @date 2018/10/22 14:15
 * @copyright
 */
public class IdsReqData extends ReqData {

    private static final long serialVersionUID = 2996211332073256346L;

    /**
     * 业务主键集合
     */
    private Set<Long> ids;

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }
}
