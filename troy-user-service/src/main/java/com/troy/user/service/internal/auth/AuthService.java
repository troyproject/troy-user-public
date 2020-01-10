package com.troy.user.service.internal.auth;

import com.troy.commons.dto.out.ResPage;
import com.troy.user.dto.in.IdReqData;
import com.troy.user.dto.in.IdsReqData;
import com.troy.user.dto.in.auth.client.AuthClientAddReqData;
import com.troy.user.dto.in.auth.client.AuthClientModifyReqData;
import com.troy.user.dto.in.auth.client.AuthClientQueryReqData;
import com.troy.user.dto.out.auth.client.AuthClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

import java.util.Set;

/**
 * description 鉴权
 *
 * @author Han
 * @date 2018-09-30 10:58
 */
public interface AuthService extends ClientDetailsService {

    /**
     * description: 根据客户端标识加载客户端
     *
     * @param clientId 客户端标识
     * @return 客户端详情
     * @throws Exception
     */
    AuthClientDetails queryByClientId(String clientId) throws Exception;

    /**
     * description: 添加客户端
     *
     * @param reqData 客户端
     * @return 客户端详情
     * @throws Exception
     */
    void add(AuthClientAddReqData reqData) throws Exception;

    /**
     * description: 修改客户端
     *
     * @param reqData 客户端
     * @return 是否成功
     * @throws Exception
     */
    boolean modify(AuthClientModifyReqData reqData) throws Exception;

    /**
     * description: 查看客户端
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    AuthClientDetails view(IdReqData reqData) throws Exception;

    Set<String> findClientIdByIds(Set<Long> ids) throws Exception;

    /**
     * 启用客户端
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    boolean enable(IdsReqData reqData) throws Exception;

    /**
     * 禁用客户端
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    boolean disable(IdsReqData reqData) throws Exception;

    /**
     * description: 移除客户端
     *
     * @param reqData
     * @return 是否成功
     * @throws Exception
     */
    boolean remove(IdReqData reqData) throws Exception;

    /**
     * description: 查询客户端
     *
     * @param reqData 查询参数
     * @return 分页结果集
     * @throws Exception
     */
    ResPage<AuthClientDetails> query(AuthClientQueryReqData reqData) throws Exception;
}
