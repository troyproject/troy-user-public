package com.troy.user.service.internal.user.impl;

import com.troy.user.dao.mapper.user.UserDocumentIdcardMapper;
import com.troy.user.dao.model.user.UserDocumentIdcardModel;
import com.troy.user.service.internal.user.UserDocumentIdcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDocumentIdcardServiceImpl implements UserDocumentIdcardService {

    @Autowired
    UserDocumentIdcardMapper userDocumentIdcardMapper;

    @Override
    public UserDocumentIdcardModel get(Long userDocumentIdcardId) {
        return userDocumentIdcardMapper.get(userDocumentIdcardId);
    }

    @Override
    public UserDocumentIdcardModel getByUserDocumentId(Long userDocumentId) {
        return userDocumentIdcardMapper.getByUserDocumentId(userDocumentId);
    }
}
