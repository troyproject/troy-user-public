package com.troy.user.dto.out.user;

import com.troy.commons.dto.out.ResData;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public
class InviteCodeResData extends ResData {

    private Long id;

    private String inviteCode;

    private Integer status;

}
