package com.troy.user.service.internal.user.impl;

import com.alibaba.fastjson.JSON;
import com.troy.commons.dto.in.Req;
import com.troy.commons.dto.in.ReqFactory;
import com.troy.commons.dto.in.ReqHead;
import com.troy.commons.dto.out.Res;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.notifier.dto.constants.EmailType;
import com.troy.notifier.dto.in.email.EmailSendReqData;
import com.troy.notifier.dto.in.sms.SmsSendReqData;
import com.troy.user.dao.mapper.user.UserMapper;
import com.troy.user.dao.model.user.UserModel;
import com.troy.user.dto.constants.*;
import com.troy.user.dto.in.user.*;
import com.troy.user.dto.out.user.GoogleCodeVerifyInfoResData;
import com.troy.user.service.configurator.properties.AppInfoProperties;
import com.troy.user.service.constants.Constants;
import com.troy.user.service.foreign.notifier.EmailClient;
import com.troy.user.service.foreign.notifier.SmsClient;
import com.troy.user.service.internal.user.UserBindService;
import com.troy.user.util.GoogleAuthenticatorUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static com.troy.user.util.ObjectRequireUtil.requireNonEmpty;
import static com.troy.user.util.ObjectRequireUtil.requireNonNull;

/**
 * @author zhangchengjie
 * @date 2019/07/31
 */
@Service
@Slf4j
public class UserBindServiceImpl implements UserBindService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SmsClient smsClient;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private AppInfoProperties appInfoProperties;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String GOOGLE_CODE_AUTH_PRE_CACHE_KEY = "google_code_auth:";

    private static final String BIND_CODE_CACHE_KEY = "USER_BIND_CODE:";

    /**
     * 发送邮箱验证码
     *
     * @param reqData
     * @return
     */
    @Override
    public boolean sendMailCode(UserBindSendCodeReqData reqData) {
        String code = this.getSixCode();
        TextMessageTemplate textMessageTemplate = getTextMessageTemplate(reqData);
        // 调用邮箱发送
        this.sendEmail(reqData.getEmail(), code, textMessageTemplate);
        // 放入缓存
        this.setBindCodeToCache(reqData, code);
        return Boolean.TRUE;
    }


    @Override
    public boolean sendMobileCode(UserBindSendCodeReqData reqData) {
        String code = this.getSixCode();
        TextMessageTemplate textMessageTemplate = getTextMessageTemplate(reqData);
        // 调用短信发送
        this.sendMobile(reqData.getPhoneAreaCode(), reqData.getMobile(), code, textMessageTemplate);
        // 放入缓存
        this.setBindCodeToCache(reqData, code);
        return Boolean.TRUE;
    }

    private String getSixCode() {
        return RandomUtils.nextInt(100000, 999999) + "";
    }

    private void sendEmail(String email, String code, TextMessageTemplate textMessageTemplate) {
        log.info("个人中心安全绑定功能-邮件发送,收件邮箱{}, 验证码{}", email, code);
        ReqHead head = new ReqHead();
        head.setClientId(this.appInfoProperties.getAppId());
        EmailSendReqData data = new EmailSendReqData();
        data.setContent(MessageFormat.format(textMessageTemplate.getEmailTemplate(), code, Constants.DEFAULT_VERIFICATION_CODE_EXPIRATION_MINUTES));
        data.setSubject(textMessageTemplate.getEmailSubject());
        data.setEmail(email);
        data.setType(EmailType.HTML.getCode());
        Req<EmailSendReqData> req = ReqFactory.getInstance().createReq(head, data);
        Res<ResData> res = this.emailClient.send(req);
        if (res == null || res.getHead() == null
                || !StateTypeSuper.SUCCESS_DEFAULT.getCode().equals(res.getHead().getCode())) {
            throw new ServiceException(ServiceResState.FAIL_PARAMETER);
        }
    }

    public void sendMobile(String phoneAreaCode, String mobile, String code, TextMessageTemplate textMessageTemplate) {
        log.info("个人中心安全绑定功能-手机短信发送, 手机号{}, 验证码{}", mobile, code);
        ReqHead head = new ReqHead();
        head.setClientId(this.appInfoProperties.getAppId());
        SmsSendReqData data = new SmsSendReqData();
        data.setContent(MessageFormat.format(textMessageTemplate.getSmsTemplate(), code, Constants.DEFAULT_VERIFICATION_CODE_EXPIRATION_MINUTES));
        data.setPhoneAreaCode(phoneAreaCode);
        data.setPhoneNumber(mobile);
        log.info("===========>短信内容为：" + data.getContent());
        Req<SmsSendReqData> req = ReqFactory.getInstance().createReq(head, data);
        Res<ResData> res = this.smsClient.send(req);
        if (res == null || res.getHead() == null || !StateTypeSuper.SUCCESS_DEFAULT.getCode().equals(res.getHead().getCode())) {
            throw new ServiceException(ServiceResState.FAIL_PARAMETER);
        }
    }

    private TextMessageTemplate getTextMessageTemplate(UserBindSendCodeReqData reqData) {
        String type = reqData.getType();
        if (StringUtils.isBlank(type)) {
            // 默认为绑定功能
            type = TextMessageType.USER_SAFE_BIND.getCode();
        }
        TextMessageType textMessageType = TextMessageType.find(type);
        return TextMessageTemplate.find(LanguageType.find(reqData.getLanguage()), textMessageType);
    }



    /**
     * 验证码放入缓存
     *
     * @param reqData
     * @param code
     */
    private void setBindCodeToCache(UserBindSendCodeReqData reqData, String code) {
        BindCodeCacheDto cacheDto = BindCodeCacheDto.builder().build();
        BeanUtils.copyProperties(reqData, cacheDto);
        String bindCodeCacheKey = this.getBindCodeCacheKey(cacheDto);
        log.info("验证码code存入缓存, 缓存key为{}, value为{}", bindCodeCacheKey, code);
        stringRedisTemplate.opsForValue().set(bindCodeCacheKey, code,
                Constants.DEFAULT_VERIFICATION_CODE_EXPIRATION_MINUTES, TimeUnit.MINUTES);
    }

    private void compareCacheCode(String code, BindCodeCacheDto cacheDto) {
        String bindCodeCacheKey = this.getBindCodeCacheKey(cacheDto);
      /*
      String cacheCode = stringRedisTemplate.opsForValue().get(bindCodeCacheKey);
        log.info("从缓存中取出值, 缓存key为{}, 缓存value为{}", bindCodeCacheKey, cacheCode);
        log.info("判断验证码是否正确, 缓存验证码为{}, 用户输入code为{}", cacheCode, code);
        if (StringUtils.isEmpty(cacheCode) || !cacheCode.equals(code)) {
            if (UserBindTypeEnum.email.getCode() == cacheDto.getSendWay()) {
                throw new VerificationException(ServiceResState.EMAIL_VERIFY_CODE_NOT_PASS, "email");
            } else {
                throw new VerificationException(ServiceResState.MOBILE_VERIFY_CODE_NOT_PASS, "mobile");
            }
        }
        */
        compareCacheCodePublic(code, cacheDto);
        stringRedisTemplate.delete(bindCodeCacheKey);
    }

    /**
     * 不删除缓存
     *
     * @param code
     * @param cacheDto
     */
    public void compareCacheCodePublic(String code, BindCodeCacheDto cacheDto) {
        String bindCodeCacheKey = this.getBindCodeCacheKey(cacheDto);
        String cacheCode = stringRedisTemplate.opsForValue().get(bindCodeCacheKey);
        log.info("从缓存中取出值, 缓存key为{}, 缓存value为{}", bindCodeCacheKey, cacheCode);
        log.info("判断验证码是否正确, 缓存验证码为{}, 用户输入code为{}", cacheCode, code);
        if (StringUtils.isEmpty(cacheCode) || !cacheCode.equals(code)) {
            if (UserBindTypeEnum.email.getCode() == cacheDto.getSendWay()) {
                throw new VerificationException(ServiceResState.EMAIL_VERIFY_CODE_NOT_PASS, "email");
            } else {
                throw new VerificationException(ServiceResState.MOBILE_VERIFY_CODE_NOT_PASS, "mobile");
            }
        }
    }

    /**
     * 拼接绑定缓存key
     *
     * @param codeCacheDto
     * @return
     */
    private String getBindCodeCacheKey(BindCodeCacheDto codeCacheDto) {
        final String splitStr = ":";
        return BIND_CODE_CACHE_KEY.concat(codeCacheDto.getUserId().toString()).concat(splitStr)
                .concat(codeCacheDto.getOperateType().toString()).concat(splitStr)
                .concat(codeCacheDto.getBindType().toString()).concat(splitStr)
                .concat(StringUtils.join(codeCacheDto.getVerifyWays(), "_").concat(splitStr)
                        .concat(codeCacheDto.getSendWay().toString()));
    }

    /**
     * 判断当前是否已绑定
     *
     * @param userModel
     * @param reqData
     */
    @Override
    public void checkWhetherTheBindingHasBeenDone(UserModel userModel, UserBindTypeVerifyReqData reqData) {
        // email
        if (UserBindTypeEnum.email.getCode() == reqData.getBindType()) {
            this.conditionalJudgment(userModel.getBindEmail(), reqData,
                    ServiceResState.EMAIL_HAS_BEEN_BOUND, ServiceResState.EMAIL_NOT_BOUND);
            // 解绑操作，且未绑定手机
            if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_2
                    && userModel.getBindMobile().intValue() == YesOrNo.NO.getCode()) {
                throw new ServiceException(ServiceResState.FAIL_EMAIL_AND_MOBILE_MUST_BIND_ONE);
            }
        }
        // mobile
        else if (UserBindTypeEnum.mobile.getCode() == reqData.getBindType()) {
            this.conditionalJudgment(userModel.getBindMobile(), reqData,
                    ServiceResState.MOBILE_HAS_BEEN_BOUND, ServiceResState.MOBILE_NOT_BOUND);
            if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_2
                    && userModel.getBindEmail().intValue() == YesOrNo.NO.getCode()) {
                throw new ServiceException(ServiceResState.FAIL_EMAIL_AND_MOBILE_MUST_BIND_ONE);
            }
        }
        // google
        else if (UserBindTypeEnum.google.getCode() == reqData.getBindType()) {
            this.conditionalJudgment(userModel.getBindGoogleCode(), reqData,
                    ServiceResState.GOOGLE_HAS_BEEN_BOUND, ServiceResState.GOOGLE_NOT_BOUND);
        } else {
            throw new ServiceException(ServiceResState.FAIL_PARAMETER);
        }
    }

    @Override
    public void bindRequestDataVerify(UserBindReqData reqData) {
        requireNonNull(reqData.getOperateType(), ServiceResState.FAIL_PARAMETER);
        requireNonNull(reqData.getBindType(), ServiceResState.BIND_TYPE_NOT_NULL);
        requireNonEmpty(reqData.getVerifyWays(), ServiceResState.VERIFY_WAY_NOT_NULL);
        // 绑定操作，校验绑定项是否存在
        if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_1) {
            if (reqData.getBindType() == UserBindTypeEnum.email.getCode()) {
                requireNonEmpty(reqData.getEmail(), ServiceResState.FAIL_PARAMETER);
            } else if (UserBindTypeEnum.mobile.getCode() == reqData.getBindType()) {
                if (StringUtils.isEmpty(reqData.getPhoneAreaCode()) || StringUtils.isEmpty(reqData.getMobile())) {
                    throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "phoneAreaCode or mobile");
                }
                if (Pattern.matches(Constants.REGEX_PHONE_AREA_CODE_DOMESTIC, reqData.getPhoneAreaCode())
                        && !Pattern.matches(Constants.REGEX_PHONE_DOMESTIC, reqData.getMobile())) {
                    throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID_DETAILS, "phoneNumber", reqData.getMobile());
                }
            } else if (UserBindTypeEnum.google.getCode() == reqData.getBindType()) {
                if (StringUtils.isEmpty(reqData.getGoogleCodeAuthToken())) {
                    throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "googleCodeAuthToken");
                }
            } else {
                throw new ServiceException(ServiceResState.FAIL_PARAMETER);
            }
        }

    }

    /**
     * {@code checkWhetherTheBindingHasBeenDone()}条件判断校验
     *
     * @param conditional
     * @param reqData
     * @param serviceResStateHasBind
     * @param serviceResStateNotBind
     */
    private void conditionalJudgment(Integer conditional,
                                     UserBindTypeVerifyReqData reqData,
                                     ServiceResState serviceResStateHasBind,
                                     ServiceResState serviceResStateNotBind) {
        // 绑定
        if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_1) {
            // 已经绑定
            if (YesOrNo.YES.getCode() == conditional) {
                throw new ServiceException(serviceResStateHasBind);
            }
        }
        // 解绑
        else {
            // 还未绑定
            if (YesOrNo.NO.getCode() == conditional) {
                throw new ServiceException(serviceResStateNotBind);
            }
        }
    }

    /**
     * 获取用户验证方式
     *
     * @param userModel
     * @param reqData
     * @return
     */
    @Override
    public List<Integer> getVerifyWay(UserModel userModel, UserBindTypeVerifyReqData reqData) {
        // 获取用户已经绑定的类型
        List<Integer> userHasBindWays = this.getUserHasBindWay(userModel);
        if (userHasBindWays.isEmpty()) {
            throw new ServiceException(ServiceResState.NO_VERIFIABLE_WAY_FOR_USERS);
        }
        // 绑定操作
        if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_1) {
            userHasBindWays.remove(reqData.getBindType());
        }
        if (userHasBindWays.isEmpty()) {
            throw new ServiceException(ServiceResState.NO_VERIFIABLE_WAY_FOR_USERS);
        }
        return userHasBindWays;
    }

    /**
     * 获取用户已经绑定的类型
     *
     * @param userModel
     * @return
     */
    private List<Integer> getUserHasBindWay(UserModel userModel) {
        List<Integer> userHasBindWays = new ArrayList<>();
        if (YesOrNo.YES.getCode() == userModel.getBindEmail()) {
            userHasBindWays.add(UserBindTypeEnum.email.getCode());
        }
        if (YesOrNo.YES.getCode() == userModel.getBindMobile()) {
            userHasBindWays.add(UserBindTypeEnum.mobile.getCode());
        }
        if (YesOrNo.YES.getCode() == userModel.getBindGoogleCode()) {
            userHasBindWays.add(UserBindTypeEnum.google.getCode());
        }
        return userHasBindWays;
    }

    @Override
    public void checkVerifyCode(UserBindReqData reqData, UserModel userModel) {
        // 验证码校验集合
        List<Integer> sendWayList = new ArrayList<>(reqData.getVerifyWays());
        // 绑定操作 增加验证码校验集合
        if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_1) {
            sendWayList.add(reqData.getBindType());
        }
        // 依次校验
        for (Integer sendWay : sendWayList) {
            BindCodeCacheDto bindCodeCacheDto = BindCodeCacheDto.builder()
                    .bindType(reqData.getBindType()).operateType(reqData.getOperateType())
                    .verifyWays(reqData.getVerifyWays()).userId(reqData.getUserId()).sendWay(sendWay).build();
            if (UserBindTypeEnum.email.getCode() == sendWay) {
                this.compareCacheCode(reqData.getEmailCode(), bindCodeCacheDto);
            } else if (UserBindTypeEnum.mobile.getCode() == sendWay) {
                this.compareCacheCode(reqData.getMobileCode(), bindCodeCacheDto);
            } else {    // google
                if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_1
                        && reqData.getBindType() == UserBindTypeEnum.google.getCode()) {
                    // 绑定操作 && 绑定谷歌
                    if (StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(GOOGLE_CODE_AUTH_PRE_CACHE_KEY + reqData.getGoogleCodeAuthToken()))) {
                        throw new ServiceException(ServiceResState.GOOGLE_VERIFY_CODE_NOT_PASS);
                    }
                    continue;
                } else {
                    // (绑定操作 && 不是绑定谷歌) || 解绑操作
                    reqData.setGoogleSecretKey(userModel.getGoogleCode());
                }

                boolean checkResult = GoogleAuthenticatorUtil.check_code(reqData.getGoogleSecretKey(),
                        reqData.getGoogleCode(), System.currentTimeMillis());
                if (!checkResult) {
                    throw new VerificationException(ServiceResState.GOOGLE_VERIFY_CODE_NOT_PASS, "google");
                }
            }
        }
    }

    @Override
    public void checkVerifyCode(UserBindReqData reqData, UserModel userModel, UserBindTypeEnum exclude) {
        // 验证码校验集合
        List<Integer> sendWayList = new ArrayList<>(reqData.getVerifyWays());
        // 绑定操作 增加验证码校验集合
        if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_1) {
            sendWayList.add(reqData.getBindType());
        }
        // 依次校验
        for (Integer sendWay : sendWayList) {
            if (exclude.getCode() == sendWay) {
                continue;
            }
            BindCodeCacheDto bindCodeCacheDto = BindCodeCacheDto.builder()
                    .bindType(reqData.getBindType()).operateType(reqData.getOperateType())
                    .verifyWays(reqData.getVerifyWays()).userId(reqData.getUserId()).sendWay(sendWay).build();
            if (UserBindTypeEnum.email.getCode() == sendWay) {
                this.compareCacheCode(reqData.getEmailCode(), bindCodeCacheDto);
            } else if (UserBindTypeEnum.mobile.getCode() == sendWay) {
                this.compareCacheCode(reqData.getMobileCode(), bindCodeCacheDto);
            } else {    // google
                if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_1
                        && reqData.getBindType() == UserBindTypeEnum.google.getCode()) {
                    // 绑定操作 && 绑定谷歌
                    if (StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(GOOGLE_CODE_AUTH_PRE_CACHE_KEY + reqData.getGoogleCodeAuthToken()))) {
                        throw new ServiceException(ServiceResState.GOOGLE_VERIFY_CODE_NOT_PASS);
                    }
                    continue;
                } else {
                    // (绑定操作 && 不是绑定谷歌) || 解绑操作
                    reqData.setGoogleSecretKey(userModel.getGoogleCode());
                }

                boolean checkResult = GoogleAuthenticatorUtil.check_code(reqData.getGoogleSecretKey(),
                        reqData.getGoogleCode(), System.currentTimeMillis());
                if (!checkResult) {
                    throw new VerificationException(ServiceResState.GOOGLE_VERIFY_CODE_NOT_PASS, "google");
                }
            }
        }
    }

    /**
     * 单独验证手机号
     *
     * @param reqData
     * @param userModel
     */
    @Override
    public void checkVerifyCodeForMobile(UserBindReqData reqData, UserModel userModel) {
        // 验证码校验集合
        List<Integer> sendWayList = new ArrayList<>(reqData.getVerifyWays());
        // 绑定操作 增加验证码校验集合
        if (reqData.getOperateType().intValue() == UserBindTypeVerifyReqData.OPERATETYPE_1) {
            sendWayList.add(reqData.getBindType());
        }
        // 依次校验
        for (Integer sendWay : sendWayList) {
            BindCodeCacheDto bindCodeCacheDto = BindCodeCacheDto.builder()
                    .bindType(reqData.getBindType()).operateType(reqData.getOperateType())
                    .verifyWays(reqData.getVerifyWays()).userId(reqData.getUserId()).sendWay(sendWay).build();
            if (UserBindTypeEnum.mobile.getCode() == sendWay) {
                this.compareCacheCodePublic(reqData.getMobileCode(), bindCodeCacheDto);
            }
        }
    }

    @Override
    public void userSafeBind(UserBindReqData reqData, UserModel userModel) {
        UserModel userModelForUpdate = new UserModel();
        userModelForUpdate.setUserId(reqData.getUserId());
        userModelForUpdate.setSafeLevel(userModel.getSafeLevel() + 1);
        userModelForUpdate.setUpdateBy(reqData.getUserId().toString());
        userModelForUpdate.setUpdateTime(new Date());
        if (reqData.getBindType() == UserBindTypeEnum.email.getCode()) {
            userModelForUpdate.setBindEmail(YesOrNo.YES.getCode());
            userModelForUpdate.setEmail(reqData.getEmail());
        } else if (reqData.getBindType() == UserBindTypeEnum.mobile.getCode()) {
            userModelForUpdate.setBindMobile(YesOrNo.YES.getCode());
            userModelForUpdate.setMobileAreaCode(reqData.getPhoneAreaCode());
            userModelForUpdate.setMobile(reqData.getMobile());
        } else {
            String googleCodeJson = stringRedisTemplate.opsForValue().get(GOOGLE_CODE_AUTH_PRE_CACHE_KEY + reqData.getGoogleCodeAuthToken());
            if (StringUtils.isEmpty(googleCodeJson)) {
                throw new ServiceException(ServiceResState.GOOGLE_VERIFY_CODE_NOT_PASS);
            }
            GoogleCodeAuthReqData googleCodeAuthDto = JSON.parseObject(googleCodeJson, GoogleCodeAuthReqData.class);
            userModelForUpdate.setBindGoogleCode(YesOrNo.YES.getCode());
            userModelForUpdate.setGoogleCode(googleCodeAuthDto.getGoogleSecretKey());
        }
        if (userMapper.update(userModelForUpdate) <= 0) {
            throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
        }
    }

    @Override
    public void userSafeUnBind(UserBindReqData reqData, UserModel userModel) {
        UserModel userModelForUpdate = new UserModel();
        userModelForUpdate.setUserId(reqData.getUserId());
        userModelForUpdate.setSafeLevel(userModel.getSafeLevel() - 1);
        userModelForUpdate.setUpdateBy(reqData.getUserId().toString());
        userModelForUpdate.setUpdateTime(new Date());
        if (reqData.getBindType() == UserBindTypeEnum.email.getCode()) {
            userModelForUpdate.setBindEmail(YesOrNo.NO.getCode());
            userModelForUpdate.setEmail(null);
            if (userMapper.unbindEmail(userModelForUpdate) <= 0) {
                throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
            }
        } else if (reqData.getBindType() == UserBindTypeEnum.mobile.getCode()) {
            userModelForUpdate.setBindMobile(YesOrNo.NO.getCode());
            userModelForUpdate.setMobileAreaCode(null);
            userModelForUpdate.setMobile(null);
            if (userMapper.unbindMobile(userModelForUpdate) <= 0) {
                throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
            }
        } else {
            userModelForUpdate.setBindGoogleCode(YesOrNo.NO.getCode());
            userModelForUpdate.setGoogleCode("");
            if (userMapper.update(userModelForUpdate) <= 0) {
                throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
            }
        }
    }

    @Override
    public GoogleCodeVerifyInfoResData verifyBindGoogleCode(GoogleCodeAuthReqData reqData) {
        if (!GoogleAuthenticatorUtil.check_code(reqData.getGoogleSecretKey(),
                reqData.getGoogleCode(), System.currentTimeMillis())) {
            throw new ServiceException(ServiceResState.GOOGLE_VERIFY_CODE_NOT_PASS);
        }
        String googleCodeAuthToken = UUID.randomUUID().toString().replaceAll("-", "");
        stringRedisTemplate.opsForValue().set(GOOGLE_CODE_AUTH_PRE_CACHE_KEY + googleCodeAuthToken,
                JSON.toJSONString(reqData), Constants.DEFAULT_VERIFICATION_CODE_EXPIRATION_MINUTES, TimeUnit.MINUTES);
        return GoogleCodeVerifyInfoResData.builder().googleCodeAuthToken(googleCodeAuthToken).build();
    }
}
