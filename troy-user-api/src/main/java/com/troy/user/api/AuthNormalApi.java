package com.troy.user.api;

import com.troy.user.api.constants.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * description 标准的鉴权服务
 *
 * @author Han
 * @date 2018-09-30 14:42
 */
public interface AuthNormalApi {

    /**
     * description 获取 Token
     *
     * @param parameters 参数列表
     * @return 响应结果
     * @author Han
     * @date 2018/10/10 15:49
     */
    @RequestMapping(value = Constants.URL_OAUTH_NORMAL_TOKEN, method = {RequestMethod.POST}, headers = {"Authorization"})
    String getToken(@RequestParam Map<String, String> parameters);

    /**
     * description 校验 Token
     *
     * @param token 令牌
     * @return 响应结果
     * @author Han
     * @date 2018/10/10 15:49
     */
    @RequestMapping(value = Constants.URL_OAUTH_NORMAL_TOKEN_CHECK, method = {RequestMethod.POST, RequestMethod.GET}, headers = {"Authorization"}, params = {"token"})
    String checkToken(String token);

    /**
     * description 获取 Token 公钥
     *
     * @return 响应结果
     * @author Han
     * @date 2018/10/10 15:49
     */
    @RequestMapping(value = Constants.URL_OAUTH_NORMAL_TOKEN_KEY, method = {RequestMethod.GET})
    String getTokenKey();
}
