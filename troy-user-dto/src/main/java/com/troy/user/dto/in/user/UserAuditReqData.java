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
public class UserAuditReqData extends ReqPage {

    private static final long serialVersionUID = 183687330483313850L;

    private java.lang.Long userDocumentId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 等级
     */
    private Integer grade;
    /**
     * 证件类型 （1身份证 2护照 3其他）
     */
    private Integer documentType;

    /**
     * 证件号码
     */
    private String documentNumber;

    /**
     * 身份认证状态 （0未认证 1初级认证中 2初级认证通过 3中级认证中 4中级认证通过 5高级认证中 6高级认证通过）
     */
    private Integer documentStatus;

    /**
     * 审核类别
     */
    private java.lang.String remark;

    /**
     * 身份证过期时间
     */
    private java.lang.String carddate;

    /**
     * 审核内容
     */
    private java.lang.String  content;

    private String updateBy;
}
