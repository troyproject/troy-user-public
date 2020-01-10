package com.troy.user.dto.in;

/**
 * description 修改请求入参
 *
 * @author Han
 * @date 2018-10-18 15:25
 */
public abstract class AbstractModifyReqData extends IdReqData {

    private static final long serialVersionUID = 2996211331543256346L;

    /**
     * 版本号
     */
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
