package com.troy.user.service.converter.auth;

import com.troy.user.dao.model.auth.AuthClientModel;
import com.troy.user.dto.in.auth.client.AuthClientAddReqData;
import com.troy.user.service.converter.AbstractTypeConverter;
import com.troy.user.service.converter.TypeConverter;
import org.springframework.stereotype.Component;

/**
 * 添加鉴权客户端请求数据转换
 *
 * @author
 * @date 2018-10-19 10:19
 * @copyright
 */
@Component
public class AuthClientAddReqDataConverter extends AbstractTypeConverter<AuthClientAddReqData, AuthClientModel> implements TypeConverter<AuthClientAddReqData, AuthClientModel> {

}
