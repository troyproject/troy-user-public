package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqPage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统应用账户查询入参
 *
 * @author: sz
 * @date: 2018-10-10 11:21
 * @copyright
 */
@Setter
@Getter
public class UserAccountReqData extends ReqPage {

    private static final long serialVersionUID = 183687330483313850L;

    /**
     * 手机号 or 邮箱
     */
    private String keyword;

    private String email ;

    private String mobile ;

}
