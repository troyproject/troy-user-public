package com.troy.user.service.internal.user;

import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.out.ResData;
import com.troy.user.dto.constants.TextMessageType;
import com.troy.user.dto.in.user.*;
import com.troy.user.dto.in.user.add.UserRegisterReqData;
import com.troy.user.dto.in.user.login.UserLoginBeforeReqData;
import com.troy.user.dto.in.user.security.UserPasswordUpdateReqData;
import com.troy.user.dto.out.user.*;
import com.troy.user.dto.out.user.security.UserSafetyMeasuresResData;

import java.util.List;

/**
 * description
 *
 * @author sz
 * @date 2018-09-30 10:50
 */
public interface CountryService {


    List<ContryDetails> queryList();
}
