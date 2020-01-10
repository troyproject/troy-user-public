package com.troy.user.service.internal.user.impl;

import com.github.pagehelper.PageHelper;
import com.troy.commons.dto.in.ReqData;
import com.troy.commons.dto.out.ResData;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.user.dao.mapper.user.CountryMapper;
import com.troy.user.dao.mapper.user.UserDocumentMapper;
import com.troy.user.dao.mapper.user.UserLoginLogsMapper;
import com.troy.user.dao.mapper.user.UserMapper;
import com.troy.user.dao.model.user.CountryModel;
import com.troy.user.dao.model.user.UserDocumentModel;
import com.troy.user.dao.model.user.UserModel;
import com.troy.user.dto.constants.*;
import com.troy.user.dto.in.user.*;
import com.troy.user.dto.in.user.add.UserRegisterReqData;
import com.troy.user.dto.in.user.login.UserLoginBeforeReqData;
import com.troy.user.dto.in.user.login.UserLoginReqData;
import com.troy.user.dto.in.user.security.UserPasswordUpdateReqData;
import com.troy.user.dto.out.user.*;
import com.troy.user.dto.out.user.login.UserLoginLogDetails;
import com.troy.user.dto.out.user.security.UserSafetyMeasuresResData;
import com.troy.user.service.constants.Constants;
import com.troy.user.service.internal.notifier.UserRegisterNotifyService;
import com.troy.user.service.internal.notifier.VerificationCodeService;
import com.troy.user.service.internal.user.CountryService;
import com.troy.user.service.internal.user.UserBindService;
import com.troy.user.service.internal.user.UserDocumentAuthService;
import com.troy.user.service.internal.user.UserService;
import com.troy.user.service.validator.Validator;
import com.troy.user.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

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
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public List<ContryDetails> queryList() {
        return countryMapper.selectAll();
    }
}
