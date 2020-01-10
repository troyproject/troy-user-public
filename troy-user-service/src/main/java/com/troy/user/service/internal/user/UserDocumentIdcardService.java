package com.troy.user.service.internal.user;

import com.troy.user.dao.model.user.UserDocumentIdcardModel;

public interface UserDocumentIdcardService {

    UserDocumentIdcardModel get(Long userDocumentIdcardId);

    UserDocumentIdcardModel getByUserDocumentId(Long userDocumentId);
}
