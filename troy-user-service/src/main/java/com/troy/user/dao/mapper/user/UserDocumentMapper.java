package com.troy.user.dao.mapper.user;

import com.troy.user.dao.model.user.UserDocumentModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserDocumentMapper
 *
 * @author zhangchengjie
 * @date 2019/07/29
 */
@Repository
public interface UserDocumentMapper {

	UserDocumentModel get(Long userDocumentId);

	Integer insert(UserDocumentModel userDocument);

	Integer update(UserDocumentModel userDocument);

	Integer delete(Long userDocumentId);

	UserDocumentModel getByUserId(Long userId);

	List<UserDocumentModel> queryForList(UserDocumentModel userDocumentModel);

}