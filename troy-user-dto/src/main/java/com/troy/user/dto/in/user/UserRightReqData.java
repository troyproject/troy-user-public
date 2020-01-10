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
public class UserRightReqData extends ReqPage {

    private static final long serialVersionUID = 183687330483313850L;


    /**
     * 用户id
     */
    private Long userId;


    /**
     *email
     */
    private String email;

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

    private String updateBy;

    public UserRightReqData(){}

    public UserRightReqData(Long userId,String updateBy){
        this.userId=userId;
        this.updateBy=updateBy;
    }

    public UserRightReqData(Long userId, String email, Integer canTrade, Integer canTrans, Integer canWithdraw, Integer canDeposit,String updateBy) {
        this.userId = userId;
        this.email = email;
        this.canTrade = canTrade;
        this.canTrans = canTrans;
        this.canWithdraw = canWithdraw;
        this.canDeposit = canDeposit;
        this.updateBy=updateBy;
    }
}
