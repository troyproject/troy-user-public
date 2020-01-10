package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
import com.troy.user.dto.out.user.login.UserLoginLogDetails;
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
public class UserAccountDetails extends ResData {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;

    /**
     * userId
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;


    /**
     * 注册时间
     */
    private java.util.Date registerTime;
    /**
     * 状态 （0禁用 1可用）
     */
    private Integer status;
    /**
     * updateTime
     */
    private java.util.Date updateTime;
    /**
     * lastUpdateFromIp
     */
    private String lastUpdateFromIp;


    private List<UserLoginLogDetails> loginLogDetailsList;


}
