package com.troy.user.dao.mapper.user;

import com.troy.user.dao.model.user.UserDocumentIdcardModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDocumentIdcardMapper {

	UserDocumentIdcardModel get(Long userDocumentIdcardId);

	Integer insert(UserDocumentIdcardModel userDocumentIdcard);

	Integer update(UserDocumentIdcardModel userDocumentIdcard);

	Integer delete(Long userDocumentIdcardId);

    UserDocumentIdcardModel getByUserDocumentId(Long userDocumentId);
}