package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqData;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangchengjie
 * @date 2019/07/30
 */
@Setter
@Getter
public class UserByIdReqData extends ReqData {
    /**
     * 用户id
     */
    private transient Long userId;
}
