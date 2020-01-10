package com.troy.user.web.controller;

import com.troy.commons.constraints.Log;
import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResFactory;
import com.troy.commons.dto.out.ResList;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.commons.proxy.GreedyRequestProxy;
import com.troy.commons.proxy.SimpleRequestProxy;
import com.troy.trade.api.model.dto.out.order.OrderDetails;
import com.troy.user.api.UserApi;
import com.troy.user.dto.constants.BindOperateTypeEnum;
import com.troy.user.dto.constants.ServiceResState;
import com.troy.user.dto.constants.TextMessageType;
import com.troy.user.dto.constants.UserBindTypeEnum;
import com.troy.user.dto.in.user.*;
import com.troy.user.dto.in.user.add.UserRegisterReqData;
import com.troy.user.dto.in.user.login.UserLoginBeforeReqData;
import com.troy.user.dto.in.user.login.UserLoginLogReqData;
import com.troy.user.dto.in.user.security.UserPasswordUpdateReqData;
import com.troy.user.dto.in.user.security.UserSafetyMeasuresReqData;
import com.troy.user.dto.out.user.*;
import com.troy.user.dto.out.user.add.UserRegisterResData;
import com.troy.user.dto.out.user.login.UserLoginLogDetails;
import com.troy.user.dto.out.user.login.UserLoginLogResData;
import com.troy.user.dto.out.user.security.UserSafetyMeasuresResData;
import com.troy.user.service.internal.accesscontrol.AccessControlService;
import com.troy.user.service.internal.user.CountryService;
import com.troy.user.service.internal.user.UserLoginLogService;
import com.troy.user.service.internal.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * description 应用账户 HTTP 服务
 *
 * @author Han
 * @date 2018-09-30 15:01
 */
@Slf4j
@RestController
@Api(tags = "用户服务")
public class UserController extends AbstractController implements UserApi {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UserLoginLogService userLoginLogService;
    @Autowired
    private AccessControlService accessControlService;

    @Autowired
    private CountryService countryService;
    @ApiOperation(value = "用户登录日志", notes = "用户登录日志")
    public Res<UserLoginLogResData> getLoginLog1(@RequestBody Req<UserLoginLogReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserLoginLogReqData, UserLoginLogResData>) (reqHead, reqData) -> {
            String token = super.tokenConverter.readAccessToken(super.currentRequest());
            Long userId = StringUtils.isEmpty(token) ? reqData.getUserId() : super.tokenConverter.convertAccessToken(token).getUserId();
            List<UserLoginLogDetails> details = this.userLoginLogService.listRecent(userId);
            return new UserLoginLogResData(details);
        });
    }

    @Override
    @ApiOperation(value = "用户登录日志", notes = "用户登录日志")
    public Res<UserLoginLogResData> getLoginLog(@RequestBody Req<UserLoginLogReqData> req) {
        try {
            // return ResFactory.getInstance().success(this.userLoginLogService.list(super.currentUser().getUserId()));
            req.getData().setUserId(super.currentUser().getUserId());
            return ResFactory.getInstance().success(this.userLoginLogService.list(req.getData()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @ApiOperation(value = "用户详情", notes = "用户详情")
    public Res<UserDetailsResData> getUserDetails(@RequestBody Req<UserDetailsReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserDetailsReqData, UserDetailsResData>) (reqHead, reqData) -> {
            UserDetails userDetails = this.userService.loadUserByLoginUsername(reqData.getLoginUsername());
            return new UserDetailsResData(userDetails);
        });
    }

    /**
     * 添加应用账户
     *
     * @param req
     * @return
     */
    @Override
    @ApiOperation(value = "用户注册", notes = "用户注册")
    public Res<UserRegisterResData> register(@RequestBody Req<UserRegisterReqData> req) {
        Res<UserRegisterResData> res = super.process(req, (GreedyRequestProxy<UserRegisterReqData, UserRegisterResData>) (reqHead, reqData) -> {
            StopWatch stopWatch = new StopWatch("user register");
            stopWatch.start("add user");
            userService.register(reqData);
            stopWatch.stop();
            UserRegisterResData resData = new UserRegisterResData();
            try {
                stopWatch.start("get user count");
                int count = this.userService.getUserCount(null);
                stopWatch.stop();
                resData.setCountRegistered(count);
            } catch (Exception e) {
                LOGGER.error("Failed to get user count", e);
            }
            LOGGER.info("Register user takes time：{}", stopWatch.prettyPrint());
            return resData;
        });
        return res;
    }

    @Override
    @ApiOperation(value = "查询用户安全项（登录前）", notes = "用户登录前查询用户安全项")
    public Res<UserSafetyMeasuresResData> beforeLogin(@RequestBody Req<UserLoginBeforeReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserLoginBeforeReqData, UserSafetyMeasuresResData>) (reqHead, reqData) -> {
            //极验

            return this.userService.beforeLogin(reqData);
        });
    }

    @Override
    @ApiOperation(value = "查询用户安全项（重置密码前）", notes = "重置密码前查询用户安全项")
    public Res<UserSafetyMeasuresResData> beforeResetPassword(@RequestBody Req<UserSafetyMeasuresReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserSafetyMeasuresReqData, UserSafetyMeasuresResData>) (reqHead, reqData) -> {
            //极验
            UserDetails userDetails = this.userService.loadUserByLoginUsername(reqData.getLoginUsername());
            if(null == userDetails){
                return userService.processUserNotExist(reqData.getLoginUsername());
            }else{
                return this.userService.getUserSafetyMeasures(userDetails, false);
            }
        });
    }

    @Override
    @ApiOperation(value = "查询用户安全项（修改密码前）", notes = "修改密码前查询用户安全项")
    public Res<UserSafetyMeasuresResData> beforeModifyPassword(@RequestBody Req<ReqData> req) {
        return super.process(req, (GreedyRequestProxy<ReqData, UserSafetyMeasuresResData>) (reqHead, reqData) -> {
            UserDetails userDetails = this.userService.getByIdForUserDetails(super.currentUser().getUserId());
            UserSafetyMeasuresResData resData = this.userService.getUserSafetyMeasures(userDetails, false);
            return resData;
        });
    }

    @Override
    @ApiOperation(value = "通用查询所有已绑定安全项", notes = "通用查询所有已绑定安全项")
    public Res<UserSafetyMeasuresResData> beforeCommon(@RequestBody Req<ReqData> req) {
        return super.process(req, (GreedyRequestProxy<ReqData, UserSafetyMeasuresResData>) (reqHead, reqData) -> {
            UserDetails userDetails = this.userService.getByIdForUserDetails(super.currentUser().getUserId());
            UserSafetyMeasuresResData resData = this.userService.getUserSafetyMeasures(userDetails, false);
            return resData;
        });
    }

    @Override
    @ApiOperation(value = "校验所有绑定项目", notes = "校验所有绑定项目")
    public Res<ResData> verifyAllCode(@RequestBody Req<VerifyCodeReqDto> req) {
        return super.process(req, (GreedyRequestProxy<VerifyCodeReqDto, ResData>) (reqHead, reqData) -> {
            VerifyCodeReqDto verifyCodeReqDto = req.getData();
            UserDetails userDetails = this.userService.getByIdForUserDetails(super.currentUser().getUserId());
            userService.checkUserSafetyMeasures(userDetails, verifyCodeReqDto.getGoogleVerificationCode(), verifyCodeReqDto.getSmsVerificationCode(), verifyCodeReqDto.getEmailVerificationCode(), verifyCodeReqDto.getTextMessageType(), verifyCodeReqDto.isOnlyTopPriority());
            return new ResData();
        });
    }

    @Override
    @ApiOperation(value = "修改密码", notes = "修改密码")
    public Res<ResData> modifyPassword(@RequestBody Req<UserPasswordUpdateReqData> req) {
        return super.process(req, (SimpleRequestProxy<UserPasswordUpdateReqData>) (reqHead, reqData) -> {
            reqData.setUserId(super.currentUser().getUserId());
            this.userService.updatePassword(reqData, TextMessageType.MODIFY_PASSWORD);
            return true;
        });
    }

    @Override
    @ApiOperation(value = "重置密码", notes = "重置密码")
    public Res<ResData> resetPassword(@RequestBody Req<UserPasswordUpdateReqData> req) {
        return super.process(req, (SimpleRequestProxy<UserPasswordUpdateReqData>) (reqHead, reqData) -> {
            this.userService.updatePassword(reqData, TextMessageType.RESET_PASSWORD);
            return true;
        });
    }

    @Override
    public Res<UserInfo> getUserById(@RequestBody Req<UserByIdReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserByIdReqData, UserInfo>) (reqHead, reqData) ->
                userService.getUserById(req.getData()));
    }

    @Override
    @ApiOperation(value = "个人信息", notes = "个人中心-个人信息")
    public Res<UserInfo> getUserInfo(@RequestBody Req<ReqData> req) {
        return super.process(req, (GreedyRequestProxy<ReqData, UserInfo>) (reqHead, reqData) ->
                userService.getUserInfo(super.currentUser().getUserId()));
    }

    @Override
    @ApiOperation(value = "安全绑定前校验", notes = "个人中心-绑定邮箱、手机号、谷歌验证码前的校验")
    public Res<UserBindTypeVerifyResData> bindTypeVerify(@RequestBody Req<UserBindTypeVerifyReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserBindTypeVerifyReqData, UserBindTypeVerifyResData>) (reqHead, reqData) -> {
            reqData.setUserId(super.currentUser().getUserId());
            return userService.bindTypeVerify(reqData);
        });
    }

    @Override
    @ApiOperation(value = "发送绑定验证码", notes = "个人中心-绑定邮箱、手机号、谷歌验证码时发送验证码")
    public Res<ResData> sendBindCode(@RequestBody Req<UserBindSendCodeReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserBindSendCodeReqData, ResData>) (reqHead, reqData) -> {
            reqData.setUserId(super.currentUser().getUserId());
            if(excludeVerify(reqData)){
                boolean verified = this.accessControlService.verification(reqData, super.currentRequest().getRemoteAddr());
                if (!verified) {
                    throw new ServiceException(ServiceResState.FAIL_ACCESS_CONTROL_REJECTED);
                }
            }
            return userService.sendBindCode(reqData);
        });
    }

    /**
     * 排除非校验类别(绑定/解除绑定手机，绑定Google)
     * @param reqData
     */
    public boolean excludeVerify(UserBindSendCodeReqData reqData){
        // true 校验，false 不校验
        boolean verify = true;
        // 绑定
        if (BindOperateTypeEnum.BIND.getCode() == reqData.getOperateType()) {
            if(UserBindTypeEnum.mobile.getCode() == reqData.getBindType() || UserBindTypeEnum.google.getCode() == reqData.getBindType()){
                verify = false;
            }
        }
        // 解除绑定
        if (BindOperateTypeEnum.UNBIND.getCode() == reqData.getOperateType()) {
            if(UserBindTypeEnum.mobile.getCode() == reqData.getBindType() || UserBindTypeEnum.google.getCode() == reqData.getBindType()){
                verify = false;
            }
        }
        return verify;

    }

    /**
     * test
     * @param userBindMobileExistReqData
     * @return
     */
    @Override
    public Res<UserDetails> checkMobileExist(@RequestBody Req<UserBindMobileExistReqData> userBindMobileExistReqData){
        UserDetails userDetails = userService.existUser(userBindMobileExistReqData.getData());
        return ResFactory.getInstance().success(userDetails);
    }

    @Override
    @ApiOperation(value = "绑定操作", notes = "个人中心-绑定邮箱、手机号、谷歌验证码")
    public Res<ResData> bind(@RequestBody Req<UserBindReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserBindReqData, ResData>) (reqHead, reqData) -> {
            reqData.setUserId(super.currentUser().getUserId());
            return userService.bind(reqData);
        });
    }

    @ApiOperation(value = "绑定之前操作", notes = "检查个人中心-手机号验证码")
    @Override
    public Res<ResData> bindBefore(@RequestBody Req<UserBindReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserBindReqData, ResData>) (reqHead, reqData) -> {
            reqData.setUserId(super.currentUser().getUserId());
            return userService.bindBefore(reqData);
        });
    }

    @Log("身份认证")
    @Override
    @ApiOperation(value = "身份认证", notes = "个人中心-身份认证")
    public Res<ResData> documentAuth(@RequestBody Req<UserDocumentReqData> req) {
        return super.process(req, (GreedyRequestProxy<UserDocumentReqData, ResData>) (reqHead, reqData) -> {
            reqData.setUserId(super.currentUser().getUserId());
            return userService.documentAuth(reqData);
        });
    }

    @Override
    @ApiOperation(value = "谷歌验证码信息", notes = "获取谷歌验证码SecretKey和QrCodeUrl")
    public Res<GoogleAuthenticatorResData> googleAuthInfo(@RequestBody Req<GoogleCodeAuthReqData> req) {
        return super.process(req, (GreedyRequestProxy<GoogleCodeAuthReqData, GoogleAuthenticatorResData>) (reqHead, reqData) -> {
            reqData.setUserId(super.currentUser().getUserId());
            return userService.googleAuthInfo(reqData);
        });
    }

    @Override
    @ApiOperation(value = "验证谷歌验证码", notes = "验证谷歌验证码")
    public Res<GoogleCodeVerifyInfoResData> verifyBindGoogleCode(@RequestBody Req<GoogleCodeAuthReqData> req) {
        return super.process(req, (GreedyRequestProxy<GoogleCodeAuthReqData, GoogleCodeVerifyInfoResData>) (reqHead, reqData) -> {
            reqData.setUserId(super.currentUser().getUserId());
            return userService.verifyBindGoogleCode(reqData);
        });
    }

    @Override
    @ApiOperation(value = "获取国家区号信息", notes = "获取国家区号信息")
    public Res<ResList<ContryDetails>>  queryCountryList() {
        return ResFactory.getInstance().successList(countryService.queryList());
    }


    @Override
    @ApiOperation(value = "获取用户认证信息", notes = "获取用户认证信息、认证状态")
    public Res<UserDocumentResData> getDocumentInfo(@RequestBody Req<ReqData> req) {
        return super.process(req, (GreedyRequestProxy<ReqData, UserDocumentResData>) (reqHead, reqData) -> userService.getDocumentInfo(super.currentUser().getUserId()));
    }



    @Override
    @ApiOperation(value = "用户认证列表", notes = "用户认证列表")
    public Res<UserInfoDetailPageResData>  queryUserList(@RequestBody Req<UserListReqData> req) {
        UserListReqData reqData =  req.getData();
        log.debug("用户认证列表：", reqData.toString());
        return ResFactory.getInstance().success(userService.queryUserList(reqData));
    }

    @Override
    @ApiOperation(value = "用户详情", notes = "用户详情")
    public Res<UserInfoDetails>  queryUserDetail(@RequestBody Req<UserListReqData> req){
        UserListReqData reqData =  req.getData();
        log.debug("用户认证列表：", reqData.toString());
        return ResFactory.getInstance().success(userService.queryUserDetail(reqData));
    }
    @Override
    @ApiOperation(value = "用户认证审核", notes = "用户认证审核")
    public Res<UserInfoDetails>  auditUser(@RequestBody Req<UserAuditReqData> req) {
        UserAuditReqData reqData =  req.getData();
        log.debug("用户认证审核：", reqData.toString());
        return ResFactory.getInstance().success(userService.auditUser(reqData));
    }

    @Override
    @ApiOperation(value = "后台管理 用户信息详情", notes = "用户信息详情")
    public Res<UserDetails>  userAccountDetail(@RequestBody Req<UserAccountReqData> req) throws Exception {
        UserAccountReqData reqData =  req.getData();
        if (reqData == null || reqData.getKeyword() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "keyword");
        }
        log.debug("用户信息详情：", reqData.toString());
        return ResFactory.getInstance().success(userService.userAccountDetail(reqData));
    }

    @Override
    @ApiOperation(value = "停用和启用账号", notes = "停用和启用账号")
    public Res<ResData>  updateStatus(@RequestBody Req<UserStatusReqData> req) {
        UserStatusReqData reqData =  req.getData();
        if (reqData == null || reqData.getUserId() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "userId");
        }
        if (reqData == null || reqData.getStatus() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "status");
        }
        log.debug("停用和启用账号：", reqData.toString());
        userService.updateStatus(reqData);
        return ResFactory.getInstance().success(null);
    }

    @Override
    @ApiOperation(value = "后台管理重置密码为 123456", notes = "后台管理重置密码")
    public Res<ResData> adminResetPassword(@RequestBody Req<UserStatusReqData> req) {
        UserStatusReqData reqData =  req.getData();
        if (reqData == null || reqData.getUserId() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "userId");
        }
        log.debug("后台管理重置密码为 123456：", reqData.toString());
        userService.adminResetPassword(reqData);
        return ResFactory.getInstance().success(null);
    }

    @Override
    @ApiOperation(value = "用户登录日志", notes = "用户登录日志")
    public Res<UserLoginLogResData> getAdminLoginLog(@RequestBody Req<UserLoginLogReqData> req) {
        UserLoginLogReqData reqData =  req.getData();
        try {
            // return ResFactory.getInstance().success(this.userLoginLogService.list(reqData.getUserId()));
            return ResFactory.getInstance().success(this.userLoginLogService.list(reqData));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @ApiOperation(value = "用户权限列表", notes = "用户权限列表")
    public Res<UserPerssionPageResData>  getUserRight(@RequestBody Req<UserRightReqData> req) {
        UserRightReqData reqData =  req.getData();
        log.debug("用户权限列表：", reqData.toString());
        return ResFactory.getInstance().success(userService.getUserRight(reqData));
    }

    @Override
    @ApiOperation(value = "设置用户权限", notes = "设置用户权限")
    public Res<ResData>  applyRight(@RequestBody Req<UserRightReqData> req) {
        UserRightReqData reqData =  req.getData();
        if (reqData == null || reqData.getUserId() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "userId");
        }

        log.debug("设置用户权限：", reqData.toString());
        userService.applyRight(reqData);
        return ResFactory.getInstance().success(null);
    }
    @Override
    @ApiOperation(value = "解除google验证码绑定", notes = "解除google验证码绑定")
    public Res<ResData>  unbind(@RequestBody Req<UserRightReqData> req) {
        UserRightReqData reqData =  req.getData();
        if (reqData == null || reqData.getUserId() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "userId");
        }

        log.debug("解除google验证码绑定：", reqData.toString());
        userService.unbind(reqData);
        return ResFactory.getInstance().success(null);
    }

    @Override
    @ApiOperation(value = "用户列表", notes = "用户权限列表")
    public Res<ResList<UserDetails>>  getUserList() {
        return ResFactory.getInstance().successList(userService.queryList());
    }
}
