package com.troy.user.dao.mapper.user;

import com.troy.user.dao.model.user.InviteCodeModel;
import com.troy.user.dto.in.user.InviteCodeReqData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InviteCodeMapper {

    List<InviteCodeModel> selectByCondition(InviteCodeReqData reqDate) ;

    Integer insert(InviteCodeModel model);

    Integer update(InviteCodeModel model);

    Integer insertBatch(List<InviteCodeModel> models);
}
