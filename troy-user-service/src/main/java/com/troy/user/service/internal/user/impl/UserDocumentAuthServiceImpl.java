package com.troy.user.service.internal.user.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.user.dao.mapper.user.UserDocumentIdcardMapper;
import com.troy.user.dao.mapper.user.UserDocumentMapper;
import com.troy.user.dao.mapper.user.UserMapper;
import com.troy.user.dao.model.user.UserDocumentIdcardModel;
import com.troy.user.dao.model.user.UserDocumentModel;
import com.troy.user.dao.model.user.UserModel;
import com.troy.user.dto.in.user.UserDocumentAuthReqData;
import com.troy.user.dto.in.user.UserDocumentReqData;
import com.troy.user.service.constants.Constants;
import com.troy.user.service.internal.user.UserDocumentAuthService;
import com.troy.user.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author zhangchengjie
 * @date 2019/08/02
 */
@Slf4j
@Service
public class UserDocumentAuthServiceImpl implements UserDocumentAuthService {

    @Autowired
    private UserDocumentMapper userDocumentMapper;
    @Autowired
    private UserDocumentIdcardMapper userDocumentIdcardMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 阿里云市场 身份证2要素
     *  https://xxxxx.aliyun.com/products/57000002/cmapi029522.html?spm=5176.10695662.1996646101.searchclickresult.482f4e9bMnCHyM#sku=yuncode2352200001
     * 现有10次免费调用
     * AppKey：203731277     
     * AppSecret：1d3q7ikhbjafv3gc9jljvwjetlfuptz6    
     * AppCode：1dae1832909144a0ae5ccbc50021a1a6
     *
     * @param reqData
     * @return
     */
    @Override
    public UserDocumentAuthReqData callAliYunIdCardCert(UserDocumentReqData reqData) {
        // 完整请求url
        String fullRequestUrl = String.format(Constants.ALIYUN_ID_CARD_AUTH_URL, reqData.getDocumentNumber(), reqData.getDocumentName());
        // 发送请求
        HttpResponse response = HttpRequest.get(fullRequestUrl)
                .header("Authorization", "APPCODE " + Constants.ALIYUN_ID_CARD_APP_CODE)
                .execute();
        String responseBody = response.body();
        UserDocumentAuthReqData userDocumentAuthReqData = JSON.parseObject(responseBody, UserDocumentAuthReqData.class);
        log.info("userDocumentAuthReqData = {}", userDocumentAuthReqData);
        return userDocumentAuthReqData;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertUserDocumentRecord(UserDocumentReqData reqData,
                                         UserDocumentAuthReqData userDocumentAuthReqData) {
        UserDocumentModel userDocument = UserDocumentModel.builder()
                .documentStatus(UserDocumentModel.DOCUMENT_STATUS_1)
                .createBy(reqData.getUserId().toString())
                .createTime(new Date())
                .updateBy(reqData.getUserId().toString())
                .updateTime(new Date())
                .build();
        BeanUtils.copyProperties(reqData, userDocument);
        userDocument.setUserDocumentId(IdWorker.getId());
        userDocument.setApplyTime(new Date());
        if (userDocumentMapper.insert(userDocument) <= 0) {
            throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
        }

        if (reqData.getDocumentType().intValue() == UserDocumentModel.DOCUMENT_TYPE_1) {
            UserDocumentIdcardModel userDocumentIdcard = new UserDocumentIdcardModel();
            BeanUtils.copyProperties(userDocumentAuthReqData, userDocumentIdcard);
            userDocumentIdcard.setUserDocumentIdcardId(IdWorker.getId());
            userDocumentIdcard.setUserDocumentId(userDocument.getUserDocumentId());
            userDocumentIdcard.setCreateBy(reqData.getUserId().toString());
            userDocumentIdcard.setCreateTime(new Date());
            userDocumentIdcard.setUpdateBy(reqData.getUserId().toString());
            userDocumentIdcard.setUpdateTime(new Date());
            if (userDocumentIdcardMapper.insert(userDocumentIdcard) <= 0) {
                throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
            }
        }

        UserModel userModel = new UserModel();
        userModel.setUserId(reqData.getUserId());
        userModel.setDocumentStatus(userDocument.getDocumentStatus());
        userModel.setUpdateBy(reqData.getUserId().toString());
        userModel.setUpdateTime(new Date());
        if (userMapper.update(userModel) <= 0) {
            throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reUserDocumentRecord(UserDocumentReqData reqData, UserDocumentAuthReqData userDocumentAuthReqData) {
        UserDocumentModel userDocument = userDocumentMapper.getByUserId(reqData.getUserId());
        if (userDocument == null) {
            throw new ServiceException(StateTypeSuper.FAIL_DEFAULT);
        }

        UserDocumentModel userDocumentForUpdate = UserDocumentModel.builder()
                .documentStatus(UserDocumentModel.DOCUMENT_STATUS_1)
                .updateBy(reqData.getUserId().toString())
                .updateTime(new Date())
                .build();
        BeanUtils.copyProperties(reqData, userDocumentForUpdate);
        userDocumentForUpdate.setUserDocumentId(userDocument.getUserDocumentId());
        userDocumentForUpdate.setApplyTime(new Date());
        if (userDocumentMapper.update(userDocumentForUpdate) <= 0) {
            throw new ServiceException(StateTypeSuper.FAIL_DEFAULT, "数据库更新了0行");
        }

        if (reqData.getDocumentType().intValue() == UserDocumentModel.DOCUMENT_TYPE_1) {
            UserDocumentIdcardModel userDocumentIdcardModel =
                    userDocumentIdcardMapper.getByUserDocumentId(userDocument.getUserDocumentId());
            if (userDocumentIdcardModel == null) {
                UserDocumentIdcardModel userDocumentIdcard = new UserDocumentIdcardModel();
                BeanUtils.copyProperties(userDocumentAuthReqData, userDocumentIdcard);
                userDocumentIdcard.setUserDocumentIdcardId(IdWorker.getId());
                userDocumentIdcard.setUserDocumentId(userDocument.getUserDocumentId());
                userDocumentIdcard.setCreateBy(reqData.getUserId().toString());
                userDocumentIdcard.setCreateTime(new Date());
                userDocumentIdcard.setUpdateBy(reqData.getUserId().toString());
                userDocumentIdcard.setUpdateTime(new Date());
                if (userDocumentIdcardMapper.insert(userDocumentIdcard) <= 0) {
                    throw new ServiceException(StateTypeSuper.FAIL_DEFAULT, "数据库插入0行");
                }
            } else {
                UserDocumentIdcardModel userDocumentIdcardForUpdate = new UserDocumentIdcardModel();
                BeanUtils.copyProperties(userDocumentAuthReqData, userDocumentIdcardForUpdate);
                userDocumentIdcardForUpdate.setUserDocumentIdcardId(userDocumentIdcardModel.getUserDocumentIdcardId());
                userDocumentIdcardForUpdate.setUpdateBy(reqData.getUserId().toString());
                userDocumentIdcardForUpdate.setUpdateTime(new Date());
                if (userDocumentIdcardMapper.update(userDocumentIdcardForUpdate) <= 0) {
                    throw new ServiceException(StateTypeSuper.FAIL_DEFAULT, "数据库修改了0行");
                }
            }
        }

        UserModel userModel = new UserModel();
        userModel.setUserId(reqData.getUserId());
        userModel.setDocumentStatus(userDocumentForUpdate.getDocumentStatus());
        userModel.setUpdateBy(reqData.getUserId().toString());
        userModel.setUpdateTime(new Date());
        if (userMapper.update(userModel) <= 0) {
            throw new ServiceException(StateTypeSuper.FAIL_DEFAULT, "数据库修改了0行");
        }
    }
}
