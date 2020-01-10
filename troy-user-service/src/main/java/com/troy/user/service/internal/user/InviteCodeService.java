package com.troy.user.service.internal.user;

import com.troy.user.dao.model.user.InviteCodeModel;
import com.troy.user.dto.in.user.InviteCodeReqData;

import java.util.List;

public interface InviteCodeService {

    List<InviteCodeModel> queryByCondition(InviteCodeReqData reqDate) ;

    Integer insert(InviteCodeModel model);

    Integer update(InviteCodeModel model);

    Integer insertBatch(List<InviteCodeModel> models);
}
