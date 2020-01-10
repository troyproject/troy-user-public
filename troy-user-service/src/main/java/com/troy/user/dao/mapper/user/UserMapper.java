package com.troy.user.dao.mapper.user;

import com.troy.user.dao.model.user.UserModel;
import com.troy.user.dto.in.user.*;
import com.troy.user.dto.in.user.login.UserLoginReqData;
import com.troy.user.dto.out.user.UserDetails;
import com.troy.user.dto.out.user.UserInfoDetails;
import com.troy.user.dto.out.user.UserPerssionResData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * UserMapper
 *
 * @author zhangchengjie
 * @date 2019/07/29
 */
@Repository
public interface UserMapper {

    /**
     * 查询用户数
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    int selectUserCount(UserCountReqData reqData) throws Exception;

    UserModel get(java.lang.Long userId);

    Integer insert(UserModel user);

    Integer update(UserModel user);

    Integer delete(java.lang.Long userId);

    UserModel getUserInfoById(Long userId);

    UserDetails selectByUserLoginReqData(UserLoginReqData reqData);

    UserDetails selectByIdForUserDetails(java.lang.Long userId);

    List<UserInfoDetails> queryUserList(UserListReqData reqData);

    UserDetails getUserInfoByMobileOrPhone(UserAccountReqData reqData);

    List<UserPerssionResData> getUserRight(UserRightReqData reqData);

    void unbind(UserModel userModel);

    Integer unbindMobile(UserModel userModel);

    Integer unbindEmail(UserModel userModel);

    UserDetails queryByCondition(UserBindMobileExistReqData reqData);

    List<UserDetails> queryList();
}
