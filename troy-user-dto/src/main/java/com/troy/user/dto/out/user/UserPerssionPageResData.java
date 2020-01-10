package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 应用系统账户查询响应数据
 *
 * @author sz
 * @date 2018-10-15 11:03
 * @copyright
 */
@Setter
@Getter
public class UserPerssionPageResData extends ResData {
    /**
     * 数据
     */
    private List<UserPerssionResData> list;
    /**
     * 总数量
     */
    private long total;

}
