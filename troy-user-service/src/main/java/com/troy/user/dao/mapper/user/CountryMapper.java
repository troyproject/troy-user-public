package com.troy.user.dao.mapper.user;

import com.troy.user.dao.model.user.CountryModel;
import com.troy.user.dao.model.user.UserModel;
import com.troy.user.dto.in.user.UserCountReqData;
import com.troy.user.dto.in.user.UserListReqData;
import com.troy.user.dto.in.user.login.UserLoginReqData;
import com.troy.user.dto.out.user.ContryDetails;
import com.troy.user.dto.out.user.UserAccountDetails;
import com.troy.user.dto.out.user.UserDetails;
import com.troy.user.dto.out.user.UserInfoDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author sz
 * @date 2019/07/29
 */
@Repository
public interface CountryMapper {



    List<ContryDetails> selectAll();


    void insert(CountryModel c);
}
