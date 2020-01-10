package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 应用系统账户查询响应数据
 *
 * @author
 * @date 2018-10-15 11:03
 * @copyright
 */
@Setter
@Getter
public class UserDetails extends ResData implements Serializable {

    private static final long serialVersionUID = 1543234536236436174L;

    /**
     * userId
     */
    private Long userId;
    /**
     * createBy
     */
    private String createBy;
    /**
     * createTime
     */
    private java.util.Date createTime;
    /**
     * updateBy
     */
    private String updateBy;
    /**
     * updateTime
     */
    private java.util.Date updateTime;
    /**
     * lastUpdateFromIp
     */
    private String lastUpdateFromIp;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    private String mobileAreaCode;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 谷歌验证码
     */
    private String googleCode;
    /**
     * 等级
     */
    private Integer grade;
    /**
     * 经验值
     */
    private Long gradeValue;
    /**
     * 性别
     * @see com.troy.user.dto.constants.Sex
     */
    private Integer gender;
    /**
     * 头像
     */
    private String userAvatar;
    /**
     * 身份认证状态 （0未认证 1认证中 2已认证）
     */
    private Integer documentStatus;
    /**
     * 是否绑定email（0未绑定 1绑定）
     */
    private Integer bindEmail;
    /**
     * 是否绑定手机 （0未绑定 1绑定）
     */
    private Integer bindMobile;
    /**
     * 是否绑定谷歌验证码 （0未绑定 1绑定）
     */
    private Integer bindGoogleCode;
    /**
     * 安全等级 （1低 2中 3高）
     */
    private Integer safeLevel;
    /**
     * kyc进度 （1绑定手机 2身份认证 3绑定手机和身份认证）
     */
    private Integer kycLevel;
    /**
     * 我的邀请码
     */
    private String myInviteCode;
    /**
     * 我的邀请人
     */
    private Long inviteUserId;
    /**
     * 注册时间
     */
    private java.util.Date registerTime;
    /**
     * 状态 （0禁用 1可用）
     */
    private Integer status;




}
