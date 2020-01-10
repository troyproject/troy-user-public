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
public class UserListReqData extends ReqPage {

    private static final long serialVersionUID = 183687330483313850L;


    /**
     * 用户id
     */
    private java.lang.Long userId;
    /**
     * 等级
     */
    private Integer grade;
    /**
     * 证件类型 （1身份证 2护照 3其他）
     */
    private java.lang.Integer documentType;

    /**
     * 证件号码
     */
    private java.lang.String documentNumber;

    /**
     * 身份认证状态 （0未认证 1初级认证中 2初级认证通过 3中级认证中 4中级认证通过 5高级认证中 6高级认证通过）
     */
    private java.lang.Integer documentStatus;

    /**
     * createTime
     */
    protected java.util.Date startTime;

    /**
     * updateTime
     */
    protected java.util.Date endTime;

    /**
     * createTime
     */
    protected java.util.Date startCheckTime;

    /**
     * updateTime
     */
    protected java.util.Date endCheckTime;
}
