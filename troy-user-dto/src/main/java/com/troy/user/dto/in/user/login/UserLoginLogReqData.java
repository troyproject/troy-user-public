package com.troy.user.dto.in.user.login;

import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.in.ReqPage;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Han
 */
@Setter
@Getter
public class UserLoginLogReqData extends ReqPage {

    private static final long serialVersionUID = -2593632132132572863L;

    private Long userId;
}
