package com.troy.user.service.internal.auth.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.troy.commons.dto.out.ResPage;
import com.troy.commons.exception.enums.StateTypeSuper;
import com.troy.commons.exception.service.ServiceException;
import com.troy.commons.exception.verification.VerificationException;
import com.troy.user.dao.mapper.auth.AuthClientMapper;
import com.troy.user.dao.model.auth.AuthClientModel;
import com.troy.user.dto.constants.ServiceResState;
import com.troy.user.dto.constants.YesOrNo;
import com.troy.user.dto.in.IdReqData;
import com.troy.user.dto.in.IdsReqData;
import com.troy.user.dto.in.auth.client.AuthClientAddReqData;
import com.troy.user.dto.in.auth.client.AuthClientModifyReqData;
import com.troy.user.dto.in.auth.client.AuthClientQueryReqData;
import com.troy.user.dto.out.auth.client.AuthClientDetails;
import com.troy.user.service.converter.TypeConverter;
import com.troy.user.service.internal.auth.AuthService;
import com.troy.user.service.validator.Validator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * description 鉴权
 *
 * @author Han
 * @date 2018-09-30 11:01
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    @Qualifier("authClientAddReqDataConverter")
    private TypeConverter<AuthClientAddReqData, AuthClientModel> authClientAddReqDataConverter;
    @Autowired
    @Qualifier("authClientModifyReqDataConverter")
    private TypeConverter<AuthClientModifyReqData, AuthClientModel> authClientModifyReqDataConverter;
    @Autowired
    @Qualifier("authClientAddReqDataValidator")
    private Validator<AuthClientAddReqData> authClientAddReqDataValidator;
    @Autowired
    @Qualifier("authClientModifyReqDataValidator")
    private Validator<AuthClientModifyReqData> authClientModifyReqDataValidator;
    @Autowired
    private AuthClientMapper authClientMapper;

    @Override
    public AuthClientDetails queryByClientId(String clientId) throws Exception {
        if (StringUtils.isEmpty(clientId)) {
            return null;
        }
        return this.authClientMapper.selectByClientId(clientId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(AuthClientAddReqData reqData) throws Exception {
        this.authClientAddReqDataValidator.verify(reqData);
        AuthClientModel model = authClientAddReqDataConverter.convert(reqData);
        this.authClientMapper.insert(model);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean modify(AuthClientModifyReqData reqData) throws Exception {
        //参数校验
        this.authClientModifyReqDataValidator.verify(reqData);
        //校验客户端是否存在
        if (this.authClientMapper.selectById(reqData.getId()) == null) {
            throw new ServiceException(ServiceResState.FAIL_CLIENT_NOT_EXIST);
        }
        AuthClientModel authClient = authClientModifyReqDataConverter.convert(reqData);
        this.authClientMapper.updateForSelectiveById(authClient);
        return true;
    }

    /**
     * 根据业务主键集合查询客户端标识
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @Override
    public Set<String> findClientIdByIds(Set<Long> ids) throws Exception {
        Set<String> usernameList = new HashSet<>();
        List<AuthClientModel> list = this.authClientMapper.selectByIds(ids);
        if (org.apache.commons.collections4.CollectionUtils.isNotEmpty(list)) {
            for (AuthClientModel item : list) {
                usernameList.add(item.getClientId());
            }
        }
        return usernameList;
    }

    /**
     * 启用
     *
     * @param reqData
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean enable(IdsReqData reqData) throws Exception {
        if (reqData == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "value");
        }
        if (reqData.getIds() == null || reqData.getIds().isEmpty()) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "ids");
        }
        this.authClientMapper.enable(reqData.getIds(), YesOrNo.YES.getCode());
        return true;
    }

    /**
     * 启用
     *
     * @param reqData
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean disable(IdsReqData reqData) throws Exception {
        if (reqData == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "value");
        }
        if (reqData.getIds() == null || reqData.getIds().isEmpty()) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "ids");
        }
        this.authClientMapper.enable(reqData.getIds(), YesOrNo.NO.getCode());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(IdReqData reqData) throws Exception {
        //参数校验
        if (reqData == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "value");
        }
        if (reqData.getId() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "id");
        }
        int count = this.authClientMapper.deleteById(reqData.getId());
        return count > 0;
    }

    @Override
    public ResPage<AuthClientDetails> query(AuthClientQueryReqData reqData) throws Exception {
        PageHelper.startPage(reqData.getPageNum(), reqData.getPageSize());
        List<AuthClientDetails> authClientDetailsList = this.authClientMapper.select(reqData);
        PageInfo pageInfo = new PageInfo(authClientDetailsList);
        return new ResPage<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (StringUtils.isEmpty(clientId)) {
            return null;
        }
        try {
            AuthClientDetails authClientDetails = this.authClientMapper.selectByClientId(clientId);
            if (authClientDetails == null || !YesOrNo.isTrue(authClientDetails.getEnable())) {
                return null;
            }
            BaseClientDetails clientDetails = new BaseClientDetails();
            clientDetails.setAccessTokenValiditySeconds(authClientDetails.getAccessTokenValidity());
            clientDetails.setAuthorizedGrantTypes(authClientDetails.getGrantTypeSet());
            clientDetails.setClientId(authClientDetails.getClientId());
            clientDetails.setClientSecret(authClientDetails.getSecret());
            clientDetails.setRefreshTokenValiditySeconds(authClientDetails.getRefreshTokenValidity());
            return clientDetails;
        } catch (Exception e) {
            String errorMsg = MessageFormat.format("Load client error,clientId={0}", clientId);
            LOGGER.error(errorMsg, e);
            throw new ClientRegistrationException(errorMsg, e);
        }
    }

    @Override
    public AuthClientDetails view(IdReqData reqData) throws Exception {
        //参数校验
        if (reqData == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "value");
        }
        if (reqData.getId() == null) {
            throw new VerificationException(StateTypeSuper.FAIL_VALUE_INVALID, "id");
        }
        AuthClientDetails authClientDetails = this.authClientMapper.selectForAuthClientDetailsById(reqData.getId());
        return authClientDetails;
    }
}
