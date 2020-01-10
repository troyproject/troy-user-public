package com.troy.user.dto.in.user;

import com.troy.commons.dto.in.ReqData;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public
class InviteCodeReqData extends ReqData {

    private Long id;

    private String inviteCode;

    private Integer status;

}
