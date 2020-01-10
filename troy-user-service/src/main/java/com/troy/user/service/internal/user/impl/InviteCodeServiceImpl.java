package com.troy.user.service.internal.user.impl;

import com.troy.user.dao.mapper.user.InviteCodeMapper;
import com.troy.user.dao.model.user.InviteCodeModel;
import com.troy.user.dto.in.user.InviteCodeReqData;
import com.troy.user.service.internal.user.InviteCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InviteCodeServiceImpl implements InviteCodeService {

    @Autowired
    private InviteCodeMapper inviteCodeMapper;

    @Override
    public List<InviteCodeModel> queryByCondition(InviteCodeReqData reqDate) {
        return inviteCodeMapper.selectByCondition(reqDate);
    }

    @Override
    public Integer insert(InviteCodeModel model) {
        return inviteCodeMapper.insert(model);
    }

    @Override
    public Integer update(InviteCodeModel model) {
        return inviteCodeMapper.update(model);
    }

    @Override
    public Integer insertBatch(List<InviteCodeModel> models){
        return inviteCodeMapper.insertBatch(models);
    }


}
