package com.troy.user.api;

import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResList;
import com.troy.user.api.constants.Constants;
import com.troy.user.dto.in.user.*;
import com.troy.user.dto.in.user.add.UserRegisterReqData;
import com.troy.user.dto.in.user.login.UserLoginBeforeReqData;
import com.troy.user.dto.in.user.login.UserLoginLogReqData;
import com.troy.user.dto.in.user.security.UserPasswordUpdateReqData;
import com.troy.user.dto.in.user.security.UserSafetyMeasuresReqData;
import com.troy.user.dto.out.user.*;
import com.troy.user.dto.out.user.add.UserRegisterResData;
import com.troy.user.dto.out.user.login.UserLoginLogResData;
import com.troy.user.dto.out.user.security.UserSafetyMeasuresResData;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * description 应用账户服务
 *
 * @author Han
 * @author zhangchengjie
 * @date 2018-09-30 14:42
 */
public interface UserApi {

    /**
     * 获取用户登录日志
     *
     * @param req
     * @return
     * @date : 2018-10-10 11:19
     */
    @RequestMapping(value = Constants.URL_USER_LOGIN_LOG, method = {RequestMethod.POST})
    Res<UserLoginLogResData> getLoginLog(Req<UserLoginLogReqData> req);

    /**
     * 获取用户详情
     *
     * @param req
     * @return
     * @date : 2018-10-10 11:19
     */
    @RequestMapping(value = Constants.URL_USER_DETAILS, method = {RequestMethod.POST})
    Res<UserDetailsResData> getUserDetails(Req<UserDetailsReqData> req);

    /**
     * 新增应用账户
     *
     * @param req
     * @return
     * @date : 2018-10-10 11:19
     */
    @RequestMapping(value = Constants.URL_USER_REGISTER, method = {RequestMethod.POST})
    Res<UserRegisterResData> register(Req<UserRegisterReqData> req);

    /**
     * 用户登录前查询已绑定安全项
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_BEFORE_LOGIN, method = {RequestMethod.POST})
    Res<UserSafetyMeasuresResData> beforeLogin(Req<UserLoginBeforeReqData> req);

    /**
     * 用户重置密码前查询已绑定安全项
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_BEFORE_RESET_PASSWORD, method = {RequestMethod.POST})
    Res<UserSafetyMeasuresResData> beforeResetPassword(Req<UserSafetyMeasuresReqData> req);

    /**
     * 用户修改密码前查询已绑定安全项
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_BEFORE_MODIFY_PASSWORD, method = {RequestMethod.POST})
    Res<UserSafetyMeasuresResData> beforeModifyPassword(Req<ReqData> req);

    /**
     * 通用查询所有已绑定安全项
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_BEFORE_COMMON, method = {RequestMethod.POST})
    Res<UserSafetyMeasuresResData> beforeCommon(Req<ReqData> req);

    /**
     * 校验所有绑定项目
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_VERIFY_ALL, method = {RequestMethod.POST})
    Res<ResData> verifyAllCode(Req<VerifyCodeReqDto> req);

    /**
     * 修改密码
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_MODIFY_PASSWORD, method = {RequestMethod.POST})
    Res<ResData> modifyPassword(Req<UserPasswordUpdateReqData> req);

    /**
     * 重置密码
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_RESET_PASSWORD, method = {RequestMethod.POST})
    Res<ResData> resetPassword(Req<UserPasswordUpdateReqData> req);

    /**
     * 根据用户id获取用户信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_INFO_BY_ID, method = {RequestMethod.POST})
    Res<UserInfo> getUserById(Req<UserByIdReqData> req);

    /**
     * 个人信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_INFO, method = {RequestMethod.POST})
    Res<UserInfo> getUserInfo(Req<ReqData> req);

    /**
     * 个人中心 账户绑定类型校验
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_BIND_TYPE_VERIFY, method = {RequestMethod.POST})
    Res<UserBindTypeVerifyResData> bindTypeVerify(Req<UserBindTypeVerifyReqData> req);

    /**
     * 个人中心 账户绑定功能 发送验证码
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_BIND_SEND_CODE, method = {RequestMethod.POST})
    Res<ResData> sendBindCode(Req<UserBindSendCodeReqData> req);

    /**
     * 个人中心 账户绑定
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_BIND, method = {RequestMethod.POST})
    Res<ResData> bind(Req<UserBindReqData> req);

    /**
     * 绑定（手机号）之前校验
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_BIND_BEFORE, method = {RequestMethod.POST})
    Res<ResData> bindBefore(Req<UserBindReqData> req);

    /**
     * 认证身份证
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_DOCUMENT_AUTH, method = {RequestMethod.POST})
    Res<ResData> documentAuth(Req<UserDocumentReqData> req);

    /**
     * 获取谷歌验证码SecretKey和QrCodeUrl
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_GOOGLE_AUTHENTICATION_INFO, method = {RequestMethod.POST})
    Res<GoogleAuthenticatorResData> googleAuthInfo(Req<GoogleCodeAuthReqData> req);

    /**
     * 校验谷歌验证码是否正确
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_VERIFY_BIND_GOOGLE_CODE, method = {RequestMethod.POST})
    Res<GoogleCodeVerifyInfoResData> verifyBindGoogleCode(Req<GoogleCodeAuthReqData> req);

    /**
     * 获取用户认证信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = Constants.URL_USER_DOCUMENT_INFO, method = {RequestMethod.POST})
    Res<UserDocumentResData> getDocumentInfo(Req<ReqData> req);


    /**
     *
     * 国家区号
     * @return
     */
    @RequestMapping(value = "/user/dddddd/queryCountryList", method = {RequestMethod.POST})
    Res<ResList<ContryDetails>>  queryCountryList();
    /**
     *
     * 后台管理 用户认证列表
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/queryUserList", method = {RequestMethod.POST})
    Res<UserInfoDetailPageResData> queryUserList(@RequestBody Req<UserListReqData> req) ;

    /**
     *
     * 后台管理 用户认证详情
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/queryUserDetail", method = {RequestMethod.POST})
    Res<UserInfoDetails>  queryUserDetail(@RequestBody Req<UserListReqData> req) ;

    /**
     *
     * 后台管理 用户认证审核
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/auditUser", method = {RequestMethod.POST})
    Res<UserInfoDetails>  auditUser(@RequestBody Req<UserAuditReqData> req) ;

    /**
     * 后台管理 用户详情
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/userAccountDetail", method = {RequestMethod.POST})
    Res<UserDetails>  userAccountDetail(@RequestBody Req<UserAccountReqData> req) throws Exception;


    /**
     *
     * 后台管理 停用和启用账号
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/updateStatus", method = {RequestMethod.POST})
    Res<ResData>  updateStatus(@RequestBody Req<UserStatusReqData> req) ;

    /**
     *
     * 后台管理重置密码为 123456
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/adminResetPassword", method = {RequestMethod.POST})
    Res<ResData> adminResetPassword(@RequestBody Req<UserStatusReqData> req);

    /**
     * 登录日志
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/getAdminLoginLog", method = {RequestMethod.POST})
    Res<UserLoginLogResData> getAdminLoginLog(@RequestBody Req<UserLoginLogReqData> req);

    /**
     * 用户权限列表
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/getUserRight", method = {RequestMethod.POST})
    Res<UserPerssionPageResData>  getUserRight(@RequestBody Req<UserRightReqData> req);

    /**
     * 设置用户权限
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/applyRight", method = {RequestMethod.POST})
    Res<ResData>  applyRight(@RequestBody Req<UserRightReqData> req);

    /**
     * 解除google验证码绑定
     * @param req
     * @return
     */
    @RequestMapping(value = "/user/dddddd/unbind", method = {RequestMethod.POST})
    Res<ResData>  unbind(@RequestBody Req<UserRightReqData> req) ;

    /**
     * 验证手机号是否已经绑定过
     * @param userBindMobileExistReqData
     * @return
     */
    @RequestMapping(value = "/user/dddddd/exist/mobile", method = {RequestMethod.POST})
    Res<UserDetails> checkMobileExist(@RequestBody Req<UserBindMobileExistReqData> userBindMobileExistReqData);

    @RequestMapping(value = "/user/getUserList", method = {RequestMethod.POST})
    Res<ResList<UserDetails>>  getUserList() ;
}
