package com.troy.user.dao.mapper.auth;

import com.troy.user.dao.Dao;
import com.troy.user.dao.model.auth.AuthClientModel;
import com.troy.user.dto.in.auth.client.AuthClientQueryReqData;
import com.troy.user.dto.out.auth.client.AuthClientDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * description 鉴权客户端数据 CURD
 *
 * @author Han
 * @date 2018-09-29 10:40
 */
public interface AuthClientMapper extends Dao<AuthClientModel> {

    /**
     * description: 根据客户端业务主键查询客户端
     *
     * @param id 客户端业务主键
     * @return 客户端详情
     * @throws Exception
     */
    AuthClientDetails selectForAuthClientDetailsById(Long id) throws Exception;

    /**
     * description: 根据客户端标识加载客心端
     *
     * @param clientId 客户端标识
     * @return 客户端详情
     * @throws Exception
     */
    AuthClientDetails selectByClientId(String clientId) throws Exception;

    /**
     * description: 根据请求数据加载客户端列表
     *
     * @param reqData
     * @return 客户端列表
     * @throws Exception
     */
    List<AuthClientDetails> select(AuthClientQueryReqData reqData) throws Exception;

    /**
     * 根据业务主键集合查询客户端
     *
     * @param ids
     * @return
     * @throws Exception
     */
    List<AuthClientModel> selectByIds(Set<Long> ids) throws Exception;

    /**
     * 启用或禁用客户端
     *
     * @param ids
     * @param enable
     * @throws Exception
     */
    void enable(@Param("ids") Set<Long> ids, @Param("enable") Integer enable) throws Exception;
}
