package com.troy.user.service.internal.user;

import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.user.dto.constants.TextMessageType;
import com.troy.user.dto.in.user.*;
import com.troy.user.dto.in.user.add.UserRegisterReqData;
import com.troy.user.dto.in.user.login.UserLoginBeforeReqData;
import com.troy.user.dto.in.user.login.UserLoginReqData;
import com.troy.user.dto.in.user.security.UserPasswordUpdateReqData;
import com.troy.user.dto.out.user.*;
import com.troy.user.dto.out.user.security.UserSafetyMeasuresResData;

import java.util.List;

/**
 * description 应用账户
 *
 * @author Han
 * @date 2018-09-30 10:50
 */
public interface UserService {

    /**
     * 查询用户数
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    int getUserCount(UserCountReqData reqData) throws Exception;

    UserDetails getByIdForUserDetails(Long userId) throws Exception;

    /**
     * description: 根据用户登陆所使用的用户名加载应用用户详情
     *
     * @param loginUsername 用户名/手机号/邮箱
     * @return 账户详情
     * @throws Exception
     */
    UserDetails loadUserByLoginUsername(String loginUsername) throws Exception;

    void checkUser(UserDetails userDetails, String loginPassword) throws Exception;

    /**
     * 获取用户安全项
     *
     * @param user
     * @param
     * @return
     * @throws Exception
     */
    UserSafetyMeasuresResData getUserSafetyMeasures(UserDetails user, boolean onlyTopPriority) throws Exception;

    /**
     * 还未成为本系统用户的处理
     * @param loginUsername
     * @return
     */
    UserSafetyMeasuresResData processUserNotExist(String loginUsername);

    void checkUserSafetyMeasures(UserDetails userDetails, String googleVerificationCode, String smsVerificationCode, String emailVerificationCode, TextMessageType textMessageType, boolean onlyTopPriority) throws Exception;

    /**
     * 添加账号
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    void register(UserRegisterReqData reqData) throws Exception;

    /**
     * 登录前
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    UserSafetyMeasuresResData beforeLogin(UserLoginBeforeReqData reqData) throws Exception;

    void updatePassword(UserPasswordUpdateReqData reqData, TextMessageType textMessageType) throws Exception;

    /**
     * 查询个人基本信息
     *
     * @param userId
     * @return
     */
    UserInfo getUserInfo(Long userId);

    /**
     * 个人中心 账户绑定类型校验
     *
     * @param reqData
     * @return
     */
    UserBindTypeVerifyResData bindTypeVerify(UserBindTypeVerifyReqData reqData);

    /**
     * 个人中心 账户绑定功能发送验证码
     *
     * @param reqData
     * @return
     */
    ResData sendBindCode(UserBindSendCodeReqData reqData);

    /**
     * 个人中心 账户绑定
     *
     * @param reqData
     * @return
     */
    ResData bind(UserBindReqData reqData);

    /**
     * 绑定之前检查
     * @param reqData
     * @return
     */
    ResData bindBefore(UserBindReqData reqData);

    /**
     * 认证身份证
     *
     * @param reqData
     * @return
     */
    ResData documentAuth(UserDocumentReqData reqData);

    /**
     * 获取谷歌验证码SecretKey和QrCodeUrl
     *
     * @param req
     * @return
     */
    GoogleAuthenticatorResData googleAuthInfo(GoogleCodeAuthReqData req);

    UserDocumentResData getDocumentInfo(Long userId);

    /**
     * 验证谷歌验证码
     *
     * @param reqData
     * @return
     */
    GoogleCodeVerifyInfoResData verifyBindGoogleCode(GoogleCodeAuthReqData reqData);
    /**
     * 根据用户id获取用户信息
     *
     * @param reqData
     * @return
     */
    UserInfo getUserById(UserByIdReqData reqData);
    /**
     *
     * 后台管理 用户认证列表
     * @param reqData
     * @return
     */
    UserInfoDetailPageResData queryUserList(UserListReqData reqData);

    /**
     *
     * 后台管理 用户认证审核
     * @param reqData
     * @return
     */
    UserInfoDetails auditUser(UserAuditReqData reqData);

    /**
     * 用户账户详情
     * @param reqData
     * @return
     */
    UserDetails userAccountDetail(UserAccountReqData reqData) throws Exception;

    /**
     *
     * 后台管理 停用和启用账号
     * @param reqData
     * @return
     */
    Object updateStatus(UserStatusReqData reqData);

    /**
     * 重置密码
     * @param reqData
     */
    void adminResetPassword(UserStatusReqData reqData);

    /**
     * 获取用户权限列表
     * @param reqData
     * @return
     */
    UserPerssionPageResData getUserRight(UserRightReqData reqData);

    /**
     * 设置用户权限
     * @param reqData
     * @return
     */
    void applyRight(UserRightReqData reqData);

    /**
     * 解除google验证码绑定
     * @param reqData
     */
    void unbind(UserRightReqData reqData);

    UserInfoDetails queryUserDetail(UserListReqData reqData);

    /**
     * 根据手机号是否已经绑定过用户
     * @param reqData
     * @return
     */
    UserDetails existUser (UserBindMobileExistReqData reqData);

    List<UserDetails> queryList();
}
