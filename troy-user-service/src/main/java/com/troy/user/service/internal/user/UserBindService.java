package com.troy.user.service.internal.user;

import com.troy.user.dao.model.user.UserModel;
import com.troy.user.dto.constants.UserBindTypeEnum;
import com.troy.user.dto.in.user.GoogleCodeAuthReqData;
import com.troy.user.dto.in.user.UserBindReqData;
import com.troy.user.dto.in.user.UserBindSendCodeReqData;
import com.troy.user.dto.in.user.UserBindTypeVerifyReqData;
import com.troy.user.dto.out.user.GoogleCodeVerifyInfoResData;

import java.util.List;

/**
 * @author zhangchengjie
 * @date 2019/07/31
 */
public interface UserBindService {

    /**
     * 发送邮箱验证码
     *
     * @param reqData
     * @return
     */
    boolean sendMailCode(UserBindSendCodeReqData reqData);

    /**
     * 发送手机验证码
     *
     * @param reqData
     * @return
     */
    boolean sendMobileCode(UserBindSendCodeReqData reqData);

    /**
     * 获取用户验证方式
     *
     * @param userModel
     * @param reqData
     * @return
     */
    List<Integer> getVerifyWay(UserModel userModel, UserBindTypeVerifyReqData reqData);

    /**
     * 判断当前是否已绑定
     *
     * @param userModel
     * @param reqData
     */
    void checkWhetherTheBindingHasBeenDone(UserModel userModel, UserBindTypeVerifyReqData reqData);

    /**
     * 数据校验
     *
     * @param reqData
     */
    void bindRequestDataVerify(UserBindReqData reqData);

    /**
     * 校验验证码是否正确
     *
     * @param reqData
     */
    void checkVerifyCode(UserBindReqData reqData, UserModel userModel);

    /**
     * 校验验证码是否正确-排除某个类型
     * @param reqData
     * @param userModel
     * @param exclude
     */
    void checkVerifyCode(UserBindReqData reqData, UserModel userModel, UserBindTypeEnum exclude);

    /**
     * 校验验证码是否正确-手机号
     * @param reqData
     * @param userModel
     */
    void checkVerifyCodeForMobile(UserBindReqData reqData, UserModel userModel);

    /**
     * 用户安全认证项绑定
     *
     * @param reqData
     * @param userModel
     */
    void userSafeBind(UserBindReqData reqData, UserModel userModel);

    /**
     * 用户安全认证项解绑
     *
     * @param reqData
     * @param userModel
     */
    void userSafeUnBind(UserBindReqData reqData, UserModel userModel);

    GoogleCodeVerifyInfoResData verifyBindGoogleCode(GoogleCodeAuthReqData reqData);
}
