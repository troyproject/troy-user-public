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
public class UserPerssionResData extends ResData {


    /**
     * 谷歌验证码
     */
    private String googleCode;

    /**
     * 是否绑定谷歌验证码 （0未绑定 1绑定）
     */
    private Integer bindGoogleCode;

    private String email;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 交易状态（0不可以 1可以）
     */
    private Integer canTrade;

    /**
     * 资产划转（0不可以 1可以）
     */
    private Integer canTrans;

    /**
     * 提币权限0不可以 1可以）
     */
    private Integer canWithdraw;

    /**
     * 充币权限（0不可以 1可以）
     */
    private Integer canDeposit;

    /**
     * createTime
     */
    private java.util.Date createTime;
}
