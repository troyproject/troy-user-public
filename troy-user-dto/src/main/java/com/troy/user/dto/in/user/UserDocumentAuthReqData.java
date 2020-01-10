package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangchengjie
 * @date 2019/08/02
 */
@Setter
@Getter
public class UserDocumentAuthReqData extends ReqData {

    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别
     */
    private String sex;
    /**
     * 身份证所在地
     */
    private String area;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区县
     */
    private String prefecture;
    /**
     * 出生年月
     */
    private String birthday;
    /**
     * 地区代码
     */
    private String addrCode;
    /**
     * 身份证校验码
     */
    private String lastCode;
    /**
     * 状态码(01通过、02不通过、202无法验证、203异常情况、204姓名格式不正确、205身份证格式不正确)
     */
    private String status;

    /**
     * 提示信息
     */
    private String msg;

}
