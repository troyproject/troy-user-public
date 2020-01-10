package com.troy.user.service.internal.user.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.troy.commons.dto.in.ReqFactory;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.dto.out.ResList;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.trade.api.model.constant.RemindNoticeTypeEnum;
import com.troy.trade.api.model.dto.in.account.AccountRegisterReqData;
import com.troy.trade.api.model.dto.in.account.UserIdReqData;
import com.troy.trade.api.model.dto.out.account.RemindNoticeResDto;
import com.troy.trade.api.model.dto.out.order.OrderDetails;
import com.troy.user.dao.mapper.user.UserDocumentMapper;
import com.troy.user.dao.mapper.user.UserMapper;
import com.troy.user.dao.model.user.InviteCodeModel;
import com.troy.user.dao.model.user.UserDocumentIdcardModel;
import com.troy.user.dao.model.user.UserDocumentModel;
import com.troy.user.dao.model.user.UserModel;
import com.troy.user.dto.constants.*;
import com.troy.user.dto.in.user.*;
import com.troy.user.dto.in.user.add.UserRegisterReqData;
import com.troy.user.dto.in.user.login.UserLoginBeforeReqData;
import com.troy.user.dto.in.user.login.UserLoginReqData;
import com.troy.user.dto.in.user.security.UserPasswordUpdateReqData;
import com.troy.user.dto.out.user.*;
import com.troy.user.dto.out.user.security.UserSafetyMeasuresResData;
import com.troy.user.service.constants.Constants;
import com.troy.user.service.foreign.account.AccountClient;
import com.troy.user.service.foreign.account.RemindNoticeClient;
import com.troy.user.service.internal.notifier.UserRegisterNotifyService;
import com.troy.user.service.internal.notifier.VerificationCodeService;
import com.troy.user.service.internal.user.*;
import com.troy.user.service.validator.Validator;
import com.troy.user.util.GoogleAuthenticatorUtil;
import com.troy.user.util.IdWorker;
import com.troy.user.util.PasswordGenerator;
import com.troy.user.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.troy.user.util.ObjectRequireUtil.requireNonNull;

/**
 * description 应用账户
 *
 * @author Han
 * @date 2018-09-30 10:53
 */
@Service
@Slf4j
@RefreshScope
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRegisterReqDataValidator")
    private Validator<UserRegisterReqData> userRegisterReqDataValidator;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDocumentMapper userDocumentMapper;
    @Autowired
    private UserBindService userBindService;
    @Autowired
    private UserDocumentAuthService userDocumentAuthService;
    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private UserRegisterNotifyService userRegisterNotifyService;
    @Value("${troy.user.security.key}")
    private String key;
    @Value("${troy.user.inviteCode.common}")
    private List<String> commonInviteCode;
    @Autowired
    private SecurityUtil securityUtil;
    @Autowired
    private AccountClient accountClient;

    @Autowired
    private UserDocumentIdcardService userDocumentIdcardService;

    @Autowired
    private RemindNoticeClient remindNoticeClient;

    @Autowired
    private InviteCodeService inviteCodeService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public int getUserCount(UserCountReqData reqData) throws Exception {
        int count = this.userMapper.selectUserCount(reqData);
        return count;
    }

    @Override
    public UserDetails getByIdForUserDetails(Long userId) throws Exception {
        if (userId == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "userId");
        }
        return this.userMapper.selectByIdForUserDetails(userId);
    }

    @Override
    public UserDetails loadUserByLoginUsername(String loginUsername) throws Exception {
        if (StringUtils.isEmpty(loginUsername)) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "loginUsername", loginUsername);
        }
        UserLoginReqData reqData = new UserLoginReqData();
        if (Pattern.matches(Constants.REGEX_EMAIL, loginUsername)) {
            reqData.setEmail(loginUsername);
        } else if (Pattern.matches(Constants.REGEX_PHONE_DOMESTIC, loginUsername)) {
            reqData.setMobile(loginUsername);
        } else {
            return null;
        }
        UserDetails userDetails = this.userMapper.selectByUserLoginReqData(reqData);
        return userDetails;
    }

    @Override
    public void checkUser(UserDetails userDetails, String loginPassword) throws Exception {
        if (userDetails == null) {
            //throw new UsernameNotFoundException("账号或密码错误");
            throw new ServiceException(ServiceResState.ERROR_USER_PASSWORD);
        }
        if (userDetails.getStatus() != null && userDetails.getStatus().intValue() == YesOrNo.NO.getCode()) {
            throw new ServiceException(StateTypeSuper.FAIL_AUTH_USER_DISABLED);
        }
        String credentials = PasswordGenerator.generate(loginPassword, userDetails.getUserId());
        if (!userDetails.getPassword().equals(credentials)) {
            throw new ServiceException(ServiceResState.ERROR_USER_PASSWORD);
        }
    }

    @Override
    public UserSafetyMeasuresResData getUserSafetyMeasures(UserDetails userDetails, boolean onlyTopPriority) throws Exception {
        if (userDetails == null) {
            throw new VerificationException(ServiceResState.ERROR_USER_PASSWORD);
        }
        UserSafetyMeasuresResData resData = new UserSafetyMeasuresResData();
        List<Integer> validationTypeList = new ArrayList<>();
        resData.setValidationTypeList(validationTypeList);
        if (userDetails.getBindGoogleCode() != null && YesOrNo.YES.getCode() == userDetails.getBindGoogleCode().intValue()) {
            validationTypeList.add(UserBindTypeEnum.google.getCode());
            if (onlyTopPriority) {
                return resData;
            }
        }
        if (userDetails.getBindMobile() != null && YesOrNo.YES.getCode() == userDetails.getBindMobile().intValue()) {
            validationTypeList.add(UserBindTypeEnum.mobile.getCode());
            resData.setPhoneAreaCode(userDetails.getMobileAreaCode());
            resData.setPhoneNumber(userDetails.getMobile());
            if (onlyTopPriority) {
                return resData;
            }
        }
        if (userDetails.getBindEmail() != null && YesOrNo.YES.getCode() == userDetails.getBindEmail().intValue()) {
            validationTypeList.add(UserBindTypeEnum.email.getCode());
            resData.setEmail(userDetails.getEmail());
            if (onlyTopPriority) {
                return resData;
            }
        }
        return resData;
    }

    /**
     * 找回密码前校验用户不存在时的处理
     *
     * @param loginUsername
     * @return
     */
    @Override
    public UserSafetyMeasuresResData processUserNotExist(String loginUsername) {
        UserSafetyMeasuresResData resData = new UserSafetyMeasuresResData();
        List<Integer> validationTypeList = new ArrayList<>();
        resData.setValidationTypeList(validationTypeList);
        if (Pattern.matches(Constants.REGEX_EMAIL, loginUsername)) {
            validationTypeList.add(UserBindTypeEnum.email.getCode());
            resData.setEmail(loginUsername);
            return resData;
        } else if (Pattern.matches(Constants.REGEX_PHONE_DOMESTIC, loginUsername)) {
            validationTypeList.add(UserBindTypeEnum.mobile.getCode());
            resData.setPhoneAreaCode("+86");
            resData.setPhoneNumber(loginUsername);
            return resData;
        } else {
            throw new ServiceException(StateTypeSuper.FAIL_PARAMETER);
        }

    }

    @Override
    public void checkUserSafetyMeasures(UserDetails user, String googleVerificationCode, String smsVerificationCode, String emailVerificationCode, TextMessageType textMessageType, boolean onlyTopPriority) throws Exception {
        if (user == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        if (user.getBindGoogleCode() != null && YesOrNo.YES.getCode() == user.getBindGoogleCode().intValue()) {
            boolean result = GoogleAuthenticatorUtil.check_code(user.getGoogleCode(), googleVerificationCode, System.currentTimeMillis());
            if (!result) {
                throw new ServiceException(ServiceResState.GOOGLE_VERIFY_CODE_NOT_PASS);
            }
            if (onlyTopPriority) {
                return;
            }
        }
        if (user.getBindMobile() != null && YesOrNo.YES.getCode() == user.getBindMobile().intValue()) {
            boolean result = this.verificationCodeService.checkVerificationCode(textMessageType, user.getMobile(), smsVerificationCode);
            if (!result) {
                throw new ServiceException(ServiceResState.MOBILE_VERIFY_CODE_NOT_PASS);
            }
            if (onlyTopPriority) {
                return;
            }
        }
        if (user.getBindEmail() != null && YesOrNo.YES.getCode() == user.getBindEmail().intValue()) {
            boolean result = this.verificationCodeService.checkVerificationCode(textMessageType, user.getEmail(), emailVerificationCode);
            if (!result) {
                throw new ServiceException(ServiceResState.EMAIL_VERIFY_CODE_NOT_PASS);
            }
            if (onlyTopPriority) {
                return;
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterReqData reqData) throws Exception {
        if (reqData != null && StringUtils.isNotEmpty(reqData.getPassword())) {
            String password = this.securityUtil.decrypt(reqData.getPassword());
            reqData.setPassword(password);
        }
        this.userRegisterReqDataValidator.verify(reqData);
        RegisterType registerType = RegisterType.find(reqData.getRegisterType());
        String target = RegisterType.PHONE == registerType ? reqData.getPhoneNumber() : reqData.getEmail();
        boolean result = this.verificationCodeService.checkVerificationCode(TextMessageType.REGISTER, target, reqData.getVerificationCode());
        if (!result) {
            throw new ServiceException(ServiceResState.FAIL_INVALID_VERIFICATION_CODE);
        }
        String inviteCode = reqData.getInviteCode();

        if (StringUtils.isBlank(inviteCode)) {
            throw new ServiceException(ServiceResState.FAIL_PARAMETER);
        }

        // 邀请码不为通用邀请码，则验证邀请码的正确性
        boolean needValidateInviteCode = CollectionUtils.isEmpty(commonInviteCode) || !commonInviteCode.contains(inviteCode);
        if (!needValidateInviteCode) {
            if (StringUtils.isBlank(reqData.getRefId())) {
                throw new ServiceException(ServiceResState.FAIL_PARAMETER);
            }
        } else {
            verifyInviteCode(inviteCode, true, null);
        }
        UserModel model = new UserModel();
        long id = IdWorker.getId();
        model.setUserId(id);
        model.setEmailReferrer(reqData.getEmailReferrer());
        model.setCountryCode(reqData.getCountryCode());
        if (RegisterType.PHONE == registerType) {
            model.setMobileAreaCode(reqData.getPhoneAreaCode());
            model.setMobile(reqData.getPhoneNumber());
            model.setBindMobile(YesOrNo.YES.getCode());
        } else if (RegisterType.EMAIL == registerType) {
            model.setEmail(reqData.getEmail());
            model.setBindEmail(YesOrNo.YES.getCode());
        }
        UserIdReqData userIdReqData = new UserIdReqData();
        userIdReqData.setUserId(id);
        accountClient.saveDefaultFavorite(ReqFactory.getInstance().createReq(userIdReqData));
        model.setCreateTime(new Date());
        String password = PasswordGenerator.generate(reqData.getPassword(), id);
        model.setPassword(password);
        model.setSource(reqData.getSource());
        model.setCanWithdraw(YesOrNo.YES.getCode());
        model.setCanTrans(YesOrNo.YES.getCode());
        model.setCanTrade(YesOrNo.YES.getCode());
        model.setCanDeposit(YesOrNo.YES.getCode());
        this.userMapper.insert(model);
        reqData.setUserId(id);
        AccountRegisterReqData accountRegisterReqData = new AccountRegisterReqData();
        accountRegisterReqData.setUserId(id);
        accountRegisterReqData.setExchangeCode("troy");
        accountRegisterReqData.setName("troy" + model.getUserId());
        accountClient.register(accountRegisterReqData);

        if (!needValidateInviteCode) {
            userRegisterNotifyService.afterRegister(reqData);
        } else {
            verifyInviteCode(inviteCode, false, id);
        }
    }

    /**
     * 用户注册-校验邀请码是否正确
     *
     * @param inviteCode
     * @param operationType true:校验，false:更新状态
     * @author caq
     */
    public void verifyInviteCode(String inviteCode, boolean operationType, Long userId) {
        if (operationType) {
            if (StringUtils.isBlank(inviteCode)) {
                log.info("[用户注册]-邀请码为空");
                throw new ServiceException(ServiceResState.INVITECODE_ERROR);
            }
            String pattern = "^[a-zA-Z0-9]{8}";
            boolean isMatch = Pattern.matches(pattern, inviteCode);
            // 8位
            if (!isMatch) {
                log.info("[用户注册]-邀请码错误");
                throw new ServiceException(ServiceResState.INVITECODE_ERROR);
            }
            InviteCodeReqData data = new InviteCodeReqData();
            data.setInviteCode(inviteCode);
            data.setStatus(InviteCodeStatusEnum.USE.getCode());
            List<InviteCodeModel> list = inviteCodeService.queryByCondition(data);
            if (CollectionUtils.isEmpty(list)) {
                log.info("[用户注册]-{}邀请码不存在", inviteCode);
                throw new ServiceException(ServiceResState.INVITECODE_ERROR);
            }
            if (InviteCodeStatusEnum.USED.getCode() == list.get(0).getStatus()) {
                log.info("[用户注册]-{}邀请码重复使用", inviteCode);
                throw new ServiceException(ServiceResState.INVITECODE_REPEAT);
            }
        } else {
            InviteCodeModel model = new InviteCodeModel();
            model.setInviteCode(inviteCode);
            model.setRemark(null != userId ? userId.toString() : "");
            inviteCodeService.update(model);
        }
    }

    @Override
    public UserSafetyMeasuresResData beforeLogin(UserLoginBeforeReqData reqData) throws Exception {
        if (reqData == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        if (StringUtils.isNotEmpty(reqData.getPassword())) {
            String password = this.securityUtil.decrypt(reqData.getPassword());
            reqData.setPassword(password);
        }
        UserDetails userDetails = this.loadUserByLoginUsername(reqData.getLoginUsername());
        this.checkUser(userDetails, reqData.getPassword());
        UserSafetyMeasuresResData resData = this.getUserSafetyMeasures(userDetails, true);
        return resData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePassword(UserPasswordUpdateReqData reqData, TextMessageType textMessageType) throws Exception {
        if (reqData == null) {
            throw new VerificationException(StateTypeSuper.FAIL_PARAMETER);
        }
        if (StringUtils.isNotEmpty(reqData.getPassword())) {
            String password = this.securityUtil.decrypt(reqData.getPassword());
            reqData.setPassword(password);
        }
        if (!Pattern.matches(Constants.USER_PASSWORD_REGEX, reqData.getPassword())) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "password", reqData.getPassword());
        }
        UserDetails user = null;
        if (reqData.getUserId() != null) {
            user = this.userMapper.selectByIdForUserDetails(reqData.getUserId());
        } else if (StringUtils.isNotEmpty(reqData.getLoginUsername())) {
            user = this.loadUserByLoginUsername(reqData.getLoginUsername());
        }
        if (user == null) {
            String msg = MessageFormat.format("userId={0},loginUsername={1}", reqData.getUserId(), reqData.getLoginUsername());
            throw new UsernameNotFoundException(msg);
        }
        this.checkUserSafetyMeasures(user, reqData.getGoogleVerificationCode(), reqData.getSmsVerificationCode(), reqData.getEmailVerificationCode(), textMessageType, false);
        UserModel updateUserModel = new UserModel();
        updateUserModel.setUserId(user.getUserId());
        String password = PasswordGenerator.generate(reqData.getPassword(), user.getUserId());
        updateUserModel.setPassword(password);
        updateUserModel.setUpdateTime(new Date());
        int count = this.userMapper.update(updateUserModel);
        if (count != 1) {
            throw new ServiceException(StateTypeSuper.FAIL_UNEXPECTED_RESULTS);
        }
    }

    @Override
    public UserInfo getUserInfo(Long userId) {
        UserModel userModel = this.getUserById(userId);
        // bean covert
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userModel, userInfo);

        UserDocumentModel userDocument = userDocumentMapper.getByUserId(userId);
        // 查询证件姓名
        if (userInfo.getDocumentStatus().intValue() >= UserDocumentModel.DOCUMENT_STATUS_2.intValue()
                && userDocument != null) {
            userInfo.setDocumentType(userDocument.getDocumentType());
            userInfo.setDocumentName(userDocument.getDocumentName());
        }
        // hide mobile
        if (userModel.getBindMobile().intValue() == YesOrNo.YES.getCode()) {
            try {
                userInfo.setMobile(userModel.getMobileAreaCode()
                        .concat(new String("-*****"))
                        .concat(userModel.getMobile().substring(5)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userInfo;
    }

    /**
     * 根据userId获取用户
     *
     * @param userId
     * @return
     */
    private UserModel getUserById(Long userId) {
        requireNonNull(userId, StateTypeSuper.FAIL_PARAMETER);
        UserModel userModel = userMapper.getUserInfoById(userId);
        requireNonNull(userModel, StateTypeSuper.FAIL_AUTH_USERNAME_NOT_FOUND);
        return userModel;
    }

    @Override
    public UserBindTypeVerifyResData bindTypeVerify(UserBindTypeVerifyReqData reqData) {
        requireNonNull(reqData, StateTypeSuper.FAIL_PARAMETER);
        requireNonNull(reqData.getBindType(), StateTypeSuper.FAIL_PARAMETER);
        requireNonNull(reqData.getOperateType(), StateTypeSuper.FAIL_PARAMETER);

        UserModel userModel = this.getUserById(reqData.getUserId());
        // 判断当前是否已绑定
        userBindService.checkWhetherTheBindingHasBeenDone(userModel, reqData);
        // 获取验证方式
        List<Integer> verifyWay = userBindService.getVerifyWay(userModel, reqData);

        // 封装返回结果
        UserBindTypeVerifyResData resData = new UserBindTypeVerifyResData();
        // 如果绑定google验证码 返回google秘钥等
        if (reqData.getBindType().intValue() == UserBindTypeEnum.google.getCode()) {
            resData.setGoogleSecretKey(GoogleAuthenticatorUtil.generateSecretKey());
            resData.setGoogleUsername(this.getUserGoogleCodeUsername(userModel));
            resData.setGoogleQrCode(GoogleAuthenticatorUtil.getQRBarcode(resData.getGoogleUsername(),
                    resData.getGoogleSecretKey()));
        }
        resData.setVerifyWay(verifyWay);
        return resData;
    }

    private String getUserGoogleCodeUsername(UserModel userModel) {
        String googleCodeUsername = "troy:";
        if (StringUtils.isNotEmpty(userModel.getEmail())) {
            googleCodeUsername += userModel.getEmail();
        } else if (StringUtils.isNotEmpty(userModel.getMobile())) {
            googleCodeUsername += userModel.getMobile();
        } else {
            googleCodeUsername += System.currentTimeMillis();
        }
        return googleCodeUsername;
    }

    @Override
    public ResData sendBindCode(UserBindSendCodeReqData reqData) {
        requireNonNull(reqData.getBindType(), ServiceResState.BIND_TYPE_NOT_NULL);
        requireNonNull(reqData.getVerifyWays(), ServiceResState.VERIFY_WAY_NOT_NULL);
        requireNonNull(reqData.getSendWay(), ServiceResState.SEND_WAY_NOT_NULL);

        UserModel userModel = this.getUserById(reqData.getUserId());
        checkExistBindMobil(reqData);
        // 选择发送方式
        // 邮箱
        if (reqData.getSendWay().intValue() == UserBindTypeEnum.email.getCode()) {
            if (reqData.getVerifyWays().contains(reqData.getSendWay())) {
                reqData.setEmail(userModel.getEmail());
            }
            // 发送邮箱验证码
            userBindService.sendMailCode(reqData);
        }
        // 手机号
        else if (reqData.getSendWay().intValue() == UserBindTypeEnum.mobile.getCode()) {
            if (reqData.getVerifyWays().contains(reqData.getSendWay())) {
                reqData.setMobile(userModel.getMobile());
                reqData.setPhoneAreaCode(userModel.getMobileAreaCode());
            }
            // 发送手机验证码
            userBindService.sendMobileCode(reqData);
        } else {
            throw new ServiceException(ServiceResState.FAIL_PARAMETER);
        }

        return new ResData();
    }

    @Override
    public ResData bind(UserBindReqData reqData) {
        // 数据校验
        userBindService.bindRequestDataVerify(reqData);
        UserModel userModel = this.getUserById(reqData.getUserId());
        // 校验验证码
        // userBindService.checkVerifyCode(reqData, userModel);
        userBindService.checkVerifyCode(reqData, userModel, UserBindTypeEnum.mobile);
        // 绑定操作
        if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_1) {
            userBindService.userSafeBind(reqData, userModel);
        }
        // 解绑操作
        else {
            userBindService.userSafeUnBind(reqData, userModel);
        }
        return new ResData();
    }

    @Override
    public ResData bindBefore(UserBindReqData reqData) {
        // 数据校验
        userBindService.bindRequestDataVerify(reqData);
        UserModel userModel = this.getUserById(reqData.getUserId());
        // 校验验证码
        userBindService.checkVerifyCodeForMobile(reqData, userModel);
        return new ResData();
    }

    @Override
    public ResData documentAuth(UserDocumentReqData reqData) {
        UserModel userModel = this.getUserById(reqData.getUserId());
        if (userModel.getDocumentStatus().equals(UserDocumentModel.DOCUMENT_STATUS_1)) {
            throw new ServiceException(ServiceResState.IN_IDENTITY_AUTHENTICATION);
        }
        if (userModel.getDocumentStatus().equals(UserDocumentModel.DOCUMENT_STATUS_2)) {
            throw new ServiceException(ServiceResState.SUCCESSFUL_IDENTITY_AUTHENTICATION);
        }

        if (reqData.getDocumentType() == null) {
            throw new ServiceException(StateTypeSuper.FAIL_VALUE_INVALID, "证件类型");
        }
        if (StringUtils.isEmpty(reqData.getDocumentNumber())) {
            throw new ServiceException(StateTypeSuper.FAIL_VALUE_INVALID, "证件号码");
        }
        if (StringUtils.isEmpty(reqData.getFirstName())) {
            throw new ServiceException(StateTypeSuper.FAIL_VALUE_INVALID, "名字");
        }
        if (StringUtils.isEmpty(reqData.getLastName())) {
            throw new ServiceException(StateTypeSuper.FAIL_VALUE_INVALID, "姓");
        }
//        if (reqData.getCountryId() == null) {
//            throw new ServiceException(StateTypeSuper.FAIL_VALUE_INVALID, "国家码或者中文国家名称");
//        }

        // 校验已认证或认证中的身份证只能有一个
        UserDocumentModel queryUserDocumentModel = new UserDocumentModel();
        queryUserDocumentModel.setDocumentNumber(reqData.getDocumentNumber());
        List<UserDocumentModel> userDocumentModels = userDocumentMapper.queryForList(queryUserDocumentModel);
        if (!CollectionUtils.isEmpty(userDocumentModels)) {
            boolean isDocumentNumberRepeat = userDocumentModels.stream().anyMatch(userDocumentModel -> Lists.newArrayList(UserDocumentModel.DOCUMENT_STATUS_1, UserDocumentModel.DOCUMENT_STATUS_2).contains(userDocumentModel.getDocumentStatus()));
            if (isDocumentNumberRepeat) {
                throw new ServiceException(ServiceResState.DOCUMENT_REPEAT);
            }
        }

        reqData.setDocumentName(reqData.getLastName().trim() + reqData.getFirstName().trim());
        // 身份证认证
        UserDocumentAuthReqData userDocumentAuthReqData = null;
        if (reqData.getDocumentType().intValue() == UserDocumentModel.DOCUMENT_TYPE_1) {
            userDocumentAuthReqData = userDocumentAuthService.callAliYunIdCardCert(reqData);
            if (userDocumentAuthReqData == null
                    || !Constants.USER_DOCUMENT_AUTH_SUCCESS_STATUS.equals(userDocumentAuthReqData.getStatus())) {
                ServiceResState identityAuthFailed = ServiceResState.IDENTITY_AUTH_FAILED;
                identityAuthFailed.setDepict(userDocumentAuthReqData.getMsg());
                throw new ServiceException(identityAuthFailed);
            }
        }

        if (userModel.getDocumentStatus().equals(UserDocumentModel.DOCUMENT_STATUS_0)) {
            // 未认证
            userDocumentAuthService.insertUserDocumentRecord(reqData, userDocumentAuthReqData);
        } else if (userModel.getDocumentStatus().equals(UserDocumentModel.DOCUMENT_STATUS_3)) {
            // 审核未通过，重新提交
            userDocumentAuthService.reUserDocumentRecord(reqData, userDocumentAuthReqData);
        }
        sendAuthNotice();
        return new ResData();
    }


    /**
     * 新用户身份认证提醒通知
     */
    public void sendAuthNotice() {
        log.info("[身份认证通知提醒]----");
        try {
            Res<ResList<RemindNoticeResDto>> res = remindNoticeClient.getRemindNotice();
            if (res.isSuccess()) {
                List<RemindNoticeResDto> list = res.getData().getList().stream().filter(dto -> dto.getRemindType() == RemindNoticeTypeEnum.AUTH.getCode()).collect(Collectors.toList());
                if (list.size() > 0) {
                    RemindNoticeResDto resDto = list.get(0);
                    String[] phones = resDto.getPhone().split(",");
                    for (String phone : phones) {
                        log.info("[身份认证通知提醒]----{}", phone);
                        String phoneAreaCode = "+86";
                        verificationCodeService.sendForSms(phoneAreaCode, phone, Constants.REMIND_NOTICE_MSG);
                    }
                }
            }
        } catch (Exception e) {
            log.error("[身份认证通知]-发送新用户身份认证错误", e);
        }

    }

    @Override
    public GoogleAuthenticatorResData googleAuthInfo(GoogleCodeAuthReqData reqData) {
        String secretKey = GoogleAuthenticatorUtil.generateSecretKey();
        String googleCodeUsername = this.getUserGoogleCodeUsername(this.getUserById(reqData.getUserId()));
        return GoogleAuthenticatorResData.builder()
                .googleSecretKey(secretKey)
                .googleQrCode(GoogleAuthenticatorUtil.getQRBarcode(googleCodeUsername, secretKey))
                .googleUsername(googleCodeUsername)
                .build();
    }

    @Override
    public UserDocumentResData getDocumentInfo(Long userId) {
        UserDocumentResData resData = new UserDocumentResData();
        UserModel userModel = this.getUserById(userId);
        resData.setCountryCode(userModel.getCountryCode());
        if (userModel.getDocumentStatus().intValue() == UserDocumentModel.DOCUMENT_STATUS_0) {
            resData.setDocumentStatus(UserDocumentModel.DOCUMENT_STATUS_0);
            return resData;
        }
        UserDocumentModel userDocumentModel = userDocumentMapper.getByUserId(userId);
        if (userDocumentModel == null
                || userDocumentModel.getDocumentStatus().intValue() == UserDocumentModel.DOCUMENT_STATUS_0) {
            resData.setDocumentStatus(UserDocumentModel.DOCUMENT_STATUS_0);
            return resData;
        }
        BeanUtils.copyProperties(userDocumentModel, resData);
        resData.setCountryCode(userModel.getCountryCode());
        processFLName(resData, userDocumentModel);
        if (StringUtils.isNotEmpty(resData.getDocumentNumber()) && resData.getDocumentNumber().length() > 5) {
            resData.setDocumentNumber(StrUtil.replace(resData.getDocumentNumber(), 3,
                    resData.getDocumentNumber().length() - 2, '*'));
        }
        return resData;
    }

    /**
     * 查出姓和名并赋值
     *
     * @param resData
     * @param userDocumentModel
     */
    public void processFLName(UserDocumentResData resData, UserDocumentModel userDocumentModel) {
        UserDocumentIdcardModel userDocumentIdcardModel = userDocumentIdcardService.getByUserDocumentId(userDocumentModel.getUserDocumentId());
        if (null != userDocumentIdcardModel) {
            resData.setFirstName(userDocumentIdcardModel.getFirstName());
            resData.setLastName(userDocumentIdcardModel.getLastName());
        }
    }

    @Override
    public GoogleCodeVerifyInfoResData verifyBindGoogleCode(GoogleCodeAuthReqData reqData) {
        if (StringUtils.isEmpty(reqData.getGoogleCode())) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "googleCode");
        }
        if (StringUtils.isEmpty(reqData.getGoogleSecretKey())) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "googleSecretKey");
        }

        return userBindService.verifyBindGoogleCode(reqData);
    }

    @Override
    public UserInfo getUserById(UserByIdReqData reqData) {
        if (reqData == null || reqData.getUserId() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "reqData or userId");
        }

        UserModel userModel = userMapper.get(reqData.getUserId());
        if (userModel == null) {
            return null;
        }

        UserInfo userInfo = new UserInfo();
        BeanUtil.copyProperties(userModel, userInfo);
        return userInfo;
    }

    @Override
    public UserInfoDetailPageResData queryUserList(UserListReqData reqData) {
        Integer pageNum = reqData.getPageNum();
        Integer pageSize = reqData.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        List<UserInfoDetails> list = userMapper.queryUserList(reqData);
        PageInfo<OrderDetails> detailsPageInfo = new PageInfo(list);

        UserInfoDetailPageResData pageInfoResDto = new UserInfoDetailPageResData();
        if (detailsPageInfo.getSize() > 0) {
            pageInfoResDto.setList(list);
            pageInfoResDto.setTotal(detailsPageInfo.getTotal());
        }
        return pageInfoResDto;

    }

    @Override
    public List<UserDetails> queryList() {
        return userMapper.queryList();
    }

    @Override
    public UserInfoDetails queryUserDetail(UserListReqData reqData) {
        PageHelper.startPage(0, 1);
        List<UserInfoDetails> list = userMapper.queryUserList(reqData);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Transactional
    @Override
    public UserInfoDetails auditUser(UserAuditReqData reqData) {
        // 更新用户状态
        UserModel userModel = new UserModel();
        userModel.setDocumentStatus(reqData.getDocumentStatus());
        userModel.setUserId(reqData.getUserId());
        userModel.setUpdateTime(new Date());
        userModel.setUpdateBy(reqData.getUpdateBy());
        userMapper.update(userModel);

        // 更新用户审核状态
        UserDocumentModel documentModel = new UserDocumentModel();
        documentModel.setUserDocumentId(reqData.getUserDocumentId());
        documentModel.setDocumentStatus(reqData.getDocumentStatus());
        documentModel.setRemark(reqData.getRemark());
        documentModel.setCarddate(reqData.getCarddate());
        documentModel.setContent(reqData.getContent());
        documentModel.setUpdateTime(new Date());
        documentModel.setUpdateBy(reqData.getUpdateBy());
        userDocumentMapper.update(documentModel);
        return new UserInfoDetails();
    }

    @Override
    public UserDetails userAccountDetail(UserAccountReqData reqData) throws Exception {
        if (Pattern.matches(Constants.REGEX_EMAIL, reqData.getKeyword())) {
            reqData.setEmail(reqData.getKeyword());
        } else if (Pattern.matches(Constants.REGEX_PHONE_DOMESTIC, reqData.getKeyword())) {
            reqData.setMobile(reqData.getKeyword());
        } else {
            reqData.setEmail("-1");
        }
        UserDetails userModel = userMapper.getUserInfoByMobileOrPhone(reqData);
        // List<UserLoginLogDetails> loginLogDetailsList = userLoginLogsMapper.selectForUserLoginLogDetails(userModel.getUserId(),100);
        // userModel.setLoginLogDetailsList(loginLogDetailsList);
        return userModel;
    }

    @Override
    public Integer updateStatus(UserStatusReqData reqData) {

        UserModel userModel = new UserModel();
        userModel.setStatus(reqData.getStatus());
        userModel.setUserId(reqData.getUserId());
        userModel.setUpdateTime(new Date());
        return userMapper.update(userModel);
    }

    @Override
    public void adminResetPassword(UserStatusReqData reqData) {
        UserModel updateUserModel = new UserModel();
        updateUserModel.setUserId(reqData.getUserId());
        String password = PasswordGenerator.generate("123456", reqData.getUserId());
        updateUserModel.setPassword(password);
        updateUserModel.setUpdateTime(new Date());
        this.userMapper.update(updateUserModel);
    }

    @Override
    public UserPerssionPageResData getUserRight(UserRightReqData reqData) {
        Integer pageNum = reqData.getPageNum();
        Integer pageSize = reqData.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        List<UserPerssionResData> list = userMapper.getUserRight(reqData);
        PageInfo<OrderDetails> detailsPageInfo = new PageInfo(list);

        UserPerssionPageResData pageInfoResDto = new UserPerssionPageResData();
        if (detailsPageInfo.getSize() > 0) {
            pageInfoResDto.setList(list);
            pageInfoResDto.setTotal(detailsPageInfo.getTotal());
        }
        return pageInfoResDto;
    }

    @Override
    public void applyRight(UserRightReqData reqData) {
        Long userId = reqData.getUserId();
        Integer canTrade = reqData.getCanTrade();
        Integer canTrans = reqData.getCanTrans();
        Integer canWithdraw = reqData.getCanWithdraw();
        Integer canDeposit = reqData.getCanDeposit();
        UserModel userModel = new UserModel();
        userModel.setCanDeposit(reqData.getCanDeposit());
        userModel.setCanTrade(reqData.getCanTrade());
        userModel.setCanTrans(reqData.getCanTrans());
        userModel.setCanWithdraw(reqData.getCanWithdraw());
        userModel.setUserId(reqData.getUserId());
        userModel.setUpdateTime(new Date());
        userModel.setUpdateBy(reqData.getUpdateBy());
        userMapper.update(userModel);
        String key = CacheRedisKeyConstants.USERORDERRIGHT;
        Object json = redisUtil.hGet(key, reqData.getUserId() + "");
        UserRightReqData userRightReqData = new UserRightReqData();
        if (json == null) {
            userRightReqData = new UserRightReqData(reqData.getUserId(), null, canTrade, canTrans, canWithdraw, canDeposit, reqData.getUpdateBy());

        } else {
            userRightReqData = (UserRightReqData) JSON.parseObject(json.toString(), UserRightReqData.class);
            if (canTrade != null) {
                userRightReqData.setCanTrade(canTrade);
            }
            if (canTrans != null) {
                userRightReqData.setCanTrans(canTrans);
            }
            if (canWithdraw != null) {
                userRightReqData.setCanWithdraw(canWithdraw);
            }
            if (canDeposit != null) {
                userRightReqData.setCanDeposit(canDeposit);
            }
        }
        redisUtil.hPut(key, userId + "", JSON.toJSONString(userRightReqData));

    }

    @Override
    public void unbind(UserRightReqData reqData) {
        UserModel newUserModel = userMapper.getUserInfoById(reqData.getUserId());
        UserModel userModel = userMapper.getUserInfoById(reqData.getUserId());
        userModel.setBindGoogleCode(YesOrNo.NO.getCode());
        userModel.setGoogleCode(null);
        userModel.setUserId(reqData.getUserId());
        userModel.setSafeLevel(newUserModel.getSafeLevel() - 1);
        userModel.setUpdateTime(new Date());
        userModel.setUpdateBy(reqData.getUpdateBy());
        userMapper.unbind(userModel);
    }

    @Override
    public UserDetails existUser(UserBindMobileExistReqData reqData) {
        return this.userMapper.queryByCondition(reqData);
    }

    /**
     * 检查手机号是否已经被绑定过
     *
     * @param reqData
     */
    public void checkExistBindMobil(UserBindSendCodeReqData reqData) {
        // 绑定手机时校验是否已经被绑定过
        if (UserBindTypeEnum.mobile.getCode() == reqData.getBindType()
                && BindOperateTypeEnum.BIND.getCode() == reqData.getOperateType()
                && SendWayEnum.MOBILE.getCode() == reqData.getSendWay()) {
            UserBindMobileExistReqData userBindMobileExistReqData = new UserBindMobileExistReqData();
            userBindMobileExistReqData.setMobile(reqData.getMobile());
            userBindMobileExistReqData.setBindMobile(UserBindMobileTypeEnum.BIND.getCode());
            UserDetails userDetails = this.userMapper.queryByCondition(userBindMobileExistReqData);
            if (null != userDetails) {
                throw new ServiceException(ServiceResState.BIND_MOBILE_EXIST);
            }
        }
    }
}
