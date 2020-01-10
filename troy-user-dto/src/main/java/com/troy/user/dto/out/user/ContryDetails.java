package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 应用系统账户查询响应数据
 *
 * @author sz
 * @date 2018-10-15 11:03
 * @copyright
 */
@Setter
@Getter
public class ContryDetails extends ResData {

    /**
     * userId
     */
    private Long id;
    /**
     * 用户名
     */
    private String code;
    /**
     * 密码
     */
    private String ch;
    private String en;
    /**
     * 手机号
     */
    private String dialCode;
}
